package com.astra.dewa.login.command.action;

import com.astra.dewa.login.constants.DewaLoginKeys;
import com.astra.dewa.login.constants.DewaLoginPortletKeys;
import com.astra.dewa.model.ApprovalDetailUser;
import com.astra.dewa.model.ApprovalHeaderUser;
import com.astra.dewa.model.Cabang;
import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.Roles;
import com.astra.dewa.model.Setting;
import com.astra.dewa.model.UsersDealers;
import com.astra.dewa.service.ApprovalDetailUserLocalServiceUtil;
import com.astra.dewa.service.ApprovalHeaderUserLocalServiceUtil;
import com.astra.dewa.service.CabangLocalServiceUtil;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.service.RolesLocalServiceUtil;
import com.astra.dewa.service.SettingLocalServiceUtil;
import com.astra.dewa.service.UsersDealersLocalServiceUtil;
import com.astra.dewa.utils.ADB2C.ADB2CService;
import com.astra.dewa.utils.ApplicationAssignStatusEnum;
import com.astra.dewa.utils.DealerUtils;
import com.astra.dewa.utils.FilterXSS;
import com.astra.dewa.utils.GroupDealerEnum;
import com.astra.dewa.utils.MailUtil;
import com.astra.dewa.utils.api.service.RSSPService;
import com.astra.dewa.utils.RoleDealerUtils;
import com.astra.dewa.utils.RoleUtils;
import com.astra.dewa.utils.api.service.CmsDsoService;
import com.astra.dewa.utils.exception.DealinkAPIException;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.StringJoiner;

import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.WARNING;

/**
 * @author psmmutia0113
 */
@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + DewaLoginPortletKeys.APPROVAL_REGISTER,
                "mvc.command.name=/approve-regist-action"
        },
        service = MVCResourceCommand.class
)
public class ApprovalRegistActionCommand extends BaseMVCResourceCommand {
    private static final Log _log = LogFactoryUtil.getLog(ApprovalRegistActionCommand.class);
    private ThemeDisplay themeDisplay;

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
        this.themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
        ServiceContext serviceContext = ServiceContextFactory.getInstance(resourceRequest);
        String state = ParamUtil.getString(uploadPortletRequest, "state");
        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

        try {
            Date now = new Date();

            int dataId = ParamUtil.getInteger(uploadPortletRequest, "dataId");
            int roleId = ParamUtil.getInteger(uploadPortletRequest, "roleId");

            // GET REQUESTED APPROVAL HEADER
            ApprovalHeaderUser approvalHeaderUser = ApprovalHeaderUserLocalServiceUtil.getApprovalHeaderUser(dataId);
            int dealerId = approvalHeaderUser.getDealerId();
            int cabangId = approvalHeaderUser.getCabangId();
            String fullName = approvalHeaderUser.getRequesterName();
            String email = approvalHeaderUser.getRequesterEmail();

            // GET REQUESTED DEALER
            Dealer dealer = DealerLocalServiceUtil.getDealer(dealerId);

            // GET REQUESTED HO DEALER
            Dealer currentDealer = DealerUtils.getDealer(dealer.getKodeHo(), cabangId);

            // SET DEALER NAME
            assert currentDealer != null;
            String dealerName = currentDealer.getName();

            // SET CABANG NAME
            Cabang cabang = CabangLocalServiceUtil.getCabang(cabangId);
            String cabangName = cabang.getName();

            // FIND ACTIVE REQUEST DETAIL
            DynamicQuery registrationDetailQuery = ApprovalDetailUserLocalServiceUtil.dynamicQuery()
                    .add(RestrictionsFactoryUtil.eq("ApprovalHeaderUserId", approvalHeaderUser.getId()))
                    .add(RestrictionsFactoryUtil.eq("RoleId", roleId))
                    .add(RestrictionsFactoryUtil.eq("RowStatus", true));
            List<ApprovalDetailUser> registrationDetails = ApprovalDetailUserLocalServiceUtil.dynamicQuery(registrationDetailQuery);
            ApprovalDetailUser requestDetail = registrationDetails.get(0);

            // DEFINE REQUESTED ROLE
            List<Integer> requestedRoles = new ArrayList<>();
            if (RoleUtils.isRoleValid(roleId)) {
                requestedRoles.add(roleId);
            }

            String linkDewa = themeDisplay.getURLPortal() + "/group/dealink";

            UsersDealers usersDealers = null;

            switch (state) {
                case "APPROVE_USER":
                    int approveStatusId = ApplicationAssignStatusEnum.APPROVE.getId();

                    // FIND USER DATA IN USER MANAGEMENT
                    DynamicQuery liferayUserQuery = UserLocalServiceUtil.dynamicQuery()
                            .add(RestrictionsFactoryUtil.eq("emailAddress", email))
                            .setProjection(ProjectionFactoryUtil.property("userId"));
                    List<Long> liferayUsers = UserLocalServiceUtil.dynamicQuery(liferayUserQuery);

                    // FIND USER IN USER MANAGEMENT
                    List<UsersDealers> activeDealinkUsers = null;
                    if (!liferayUsers.isEmpty()) {
                        DynamicQuery query = UsersDealersLocalServiceUtil.dynamicQuery()
                                .add(RestrictionsFactoryUtil.in("UserId", liferayUsers))
                                .add(RestrictionsFactoryUtil.eq("RowStatus", true));
                        activeDealinkUsers = UsersDealersLocalServiceUtil.dynamicQuery(query);
                    }

                    // DEACTIVATE SELECTED ROLE REQUEST
                    requestDetail.setRowStatus(false);
                    requestDetail.setModifiedBy(themeDisplay.getUser().getScreenName());
                    requestDetail.setModifiedDate(now);

                    boolean success;
                    // UPDATE IF USER IS EXIST
                    if (null != activeDealinkUsers) {
                        usersDealers = activeDealinkUsers.get(0);
                        ApprovalDetailUserLocalServiceUtil.updateDetailUserAndUserRole(requestDetail, usersDealers.getUserId(), roleId);

                        // UPDATE APPROVAL HEADER REQUEST STATUS
                        updateApprovalHeaderUser(approvalHeaderUser, approveStatusId, "");

                        success = true;
                    } else {
                        ApprovalDetailUserLocalServiceUtil.updateApprovalDetailUser(requestDetail);

                        // UPDATE APPROVAL HEADER REQUEST STATUS
                        updateApprovalHeaderUser(approvalHeaderUser, approveStatusId, "");

                        _log.info("==== Create ADB2C and DEALINK Account ====");
                        // CREATE ADB2C AND DEALINK ACCOUNT
                        usersDealers = ADB2CService.createUserDEWA(currentDealer.getId(), requestedRoles, fullName, email, themeDisplay.getUser().getScreenName(), serviceContext);
                        success = null != usersDealers;
                    }

                    if (success) {
                        // sync rssp role
                        RSSPService.rsspRoleCheck(
                                email,
                                currentDealer.getCode(),
                                currentDealer.getGroupDealer(),
                                requestedRoles,
                                approvalHeaderUser.getCreatedBy(),
                                approvalHeaderUser.getCreatedDate(),
                                approvalHeaderUser.getModifiedBy(),
                                approvalHeaderUser.getModifiedDate(),
                                1
                        );


                        _log.info("==== ADDING CMS DSO ROLE IF EXIST ====");
                        JSONArray requestAreas = JSONFactoryUtil.createJSONArray();
                        if (currentDealer.getGroupDealer() == GroupDealerEnum.HO_DEALER.ordinal()) {
                            List<Dealer> branchDealers = DealerUtils.getBranchDealers(currentDealer.getId());
                            for (Dealer branchDealer : branchDealers) {
                                String dealerCode = branchDealer.getCode().substring(branchDealer.getCode().length() - 4);
                                JSONObject area = JSONFactoryUtil
                                        .createJSONObject()
                                        .put("regionCode", branchDealer.getWilayahId())
                                        .put("dealerCode", dealerCode);
                                requestAreas.put(area);
                            }
                        } else if (currentDealer.getGroupDealer() == GroupDealerEnum.DEALER_CABANG.ordinal()) {
                            String dealerCode = currentDealer.getCode().substring(currentDealer.getCode().length() - 4);
                            JSONObject area = JSONFactoryUtil
                                    .createJSONObject()
                                    .put("regionCode", currentDealer.getWilayahId())
                                    .put("dealerCode", dealerCode);
                            requestAreas.put(area);
                        }


                        String[] nameParts = fullName.split("\\s+", 2);
                        String firstName;
                        String lastName;
                        if (nameParts.length == 2) {
                            firstName = nameParts[0];
                            lastName = nameParts[1];
                        } else {
                            firstName = fullName;
                            lastName = fullName;
                        }


                        // sync cms dso role
                        CmsDsoService.checkRole(
                                UserLocalServiceUtil.getUser(usersDealers.getUserId()).getEmailAddress(),
                                1,
                                firstName,
                                lastName,
                                "",
                                currentDealer.getGroupDealer(),
                                requestAreas,
                                approvalHeaderUser.getCreatedBy(),
                                approvalHeaderUser.getCreatedDate(),
                                approvalHeaderUser.getModifiedBy(),
                                approvalHeaderUser.getModifiedDate(),
                                requestedRoles
                        );


                        _log.info("==== Send Approved Email to New User ====");
                        // SEND APPROVED EMAIL TO NEW
                        sendEmailApprove(fullName, email, dealerName, cabangName, linkDewa, "Dealer Information Link", requestedRoles);

                        jsonObject = SUCCESS("New User Approval Success!", "");
                        _log.info("==== DONE ====");
                    } else {
                        jsonObject = WARNING("New User Approval Failed!");
                        _log.info("==== DONE ====");
                    }
                    break;
                case "REJECT_USER":
                    _log.info("TRY REJECT");

                    String rejectReason = ParamUtil.getString(uploadPortletRequest, "rejectReason");
                    int rejectStatusId = ApplicationAssignStatusEnum.REJECT.getId();

                    boolean isRequestContainsXSS = false;
                    Enumeration<String> attributes = resourceRequest.getParameterNames();
                    while (attributes.hasMoreElements()) {
                        String param = attributes.nextElement();
                        String value = resourceRequest.getParameter(param);
                        if (FilterXSS.checkXSS(value)) {
                            _log.warn(value + " contains XSS payload");
                            isRequestContainsXSS = true;
                            break;
                        }
                    }

                    attributes = uploadPortletRequest.getParameterNames();
                    while (attributes.hasMoreElements()) {
                        String param = attributes.nextElement();
                        String value = uploadPortletRequest.getParameter(param);
                        if (FilterXSS.checkXSS(value)) {
                            _log.warn(value + " contains XSS payload");
                            isRequestContainsXSS = true;
                            break;
                        }
                    }

                    if (isRequestContainsXSS) {
                        jsonObject = ERROR("Oops! Something went wrong.");
                    } else {
                        // DEACTIVATE SELECTED ROLE REQUEST
                        requestDetail.setRowStatus(false);
                        requestDetail.setModifiedBy(themeDisplay.getUser().getScreenName());
                        requestDetail.setModifiedDate(now);
                        ApprovalDetailUserLocalServiceUtil.updateApprovalDetailUser(requestDetail);

                        // UPDATE APPROVAL HEADER REQUEST STATUS
                        updateApprovalHeaderUser(approvalHeaderUser, rejectStatusId, rejectReason);

                        _log.info("==== Send Rejected Email to New User ====");
                        // SEND REJECTION EMAIL TO NEW USER
                        sendEmailReject(fullName, email, dealerName, cabangName, rejectReason, requestedRoles);

                        jsonObject = SUCCESS("New User Rejection Success!", "");
                        _log.info("==== DONE ====");
                    }
                    break;
                default:
                    jsonObject = ERROR("Oops! Something went wrong.");
                    break;
            }
        } catch (Exception e) {
            _log.error(e.getMessage(), e);
            if (e instanceof DealinkAPIException) {
                jsonObject = WARNING(e.getMessage());
            }
        }
        ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
    }

    private void updateApprovalHeaderUser(ApprovalHeaderUser approvalHeaderUser, int statusId, String rejectReason) throws SystemException {
        _log.info("==== Update Approval Header User ====");
        approvalHeaderUser.setApproverUserId(themeDisplay.getUserId());
        if (!isRequestActive(approvalHeaderUser.getId())) {
            approvalHeaderUser.setApplicationAssignStatusId(statusId);
            if (statusId == ApplicationAssignStatusEnum.REJECT.getId()) {
                approvalHeaderUser.setRejectReason(rejectReason);
            }
        }
        approvalHeaderUser.setModifiedBy(themeDisplay.getUser().getScreenName());
        approvalHeaderUser.setModifiedDate(new Date());
        ApprovalHeaderUserLocalServiceUtil.updateApprovalHeaderUserWithHistory(approvalHeaderUser);
    }

    private void sendEmailApprove(String fullName, String emailNewUser, String dealerName, String cabangName, String linkSite, String siteName, List<Integer> roles) {
        try {
            StringJoiner requestedRoles = new StringJoiner(", ");
            for (Integer role : roles) {
                Roles requestedRole = RolesLocalServiceUtil.getRoles(role);
                requestedRoles.add(requestedRole.getName());
            }

            String[] recipient = new String[]{emailNewUser};
            String[] cc = RoleDealerUtils.listEmailAdminDSO(roles);
            Setting bodyMail = SettingLocalServiceUtil.findCredential("mail", DewaLoginKeys.BODY_EMAIL_APPROVE_USER);
            String body = bodyMail.getValue();
            body = MailUtil.replace(
                    body,
                    new String[]{
                            "[Nama New User]",
                            "[Nama Lengkap User]",
                            "[Alamat Email User Baru]",
                            "[Nama Dealer]",
                            "[Cabang Dealer]",
                            "[Link login page]",
                            "[Login page]",
                            "[Role User]"
                    },
                    new String[]{
                            fullName,
                            fullName,
                            emailNewUser,
                            dealerName,
                            cabangName,
                            linkSite,
                            siteName,
                            requestedRoles.toString()
                    });

            MailUtil.sendEmail(
                    recipient,
                    cc,
                    "Dealer Information Link - Permintaan Registrasi Disetujui",
                    body,
                    null);
        } catch (Exception e) {
            _log.error(e.getMessage(), e);
        }
    }

    private void sendEmailReject(String fullName, String emailNewUser, String dealerName, String cabangName, String rejectReason, List<Integer> roles) {
        try {
            StringJoiner requestedRoles = new StringJoiner(", ");
            for (Integer role : roles) {
                Roles requestedRole = RolesLocalServiceUtil.getRoles(role);
                requestedRoles.add(requestedRole.getName());
            }

            String[] recipient = new String[]{emailNewUser};
            String[] cc = RoleDealerUtils.listEmailAdminDSO(roles);
            Setting bodyMail = SettingLocalServiceUtil.findCredential("mail", DewaLoginKeys.BODY_EMAIL_REJECT_USER);
            String body = bodyMail.getValue();
            body = MailUtil.replace(
                    body,
                    new String[]{
                            "[Nama New User]",
                            "[Nama Lengkap User]",
                            "[Alamat Email User Baru]",
                            "[Nama Dealer]",
                            "[Cabang Dealer]",
                            "[Rejection Reason]",
                            "[Role User]"
                    },
                    new String[]{
                            fullName,
                            fullName,
                            emailNewUser,
                            dealerName,
                            cabangName,
                            rejectReason,
                            requestedRoles.toString()
                    });

            MailUtil.sendEmail(
                    recipient,
                    cc,
                    "Dealer Information Link - Permintaan Registrasi Ditolak",
                    body,
                    null);
        } catch (Exception e) {
            _log.error(e.getMessage(), e);
        }
    }

    private boolean isRequestActive(int approvalHeaderUserId) throws SystemException {
        DynamicQuery query = ApprovalDetailUserLocalServiceUtil.dynamicQuery()
                .add(RestrictionsFactoryUtil.eq("ApprovalHeaderUserId", approvalHeaderUserId))
                .add(RestrictionsFactoryUtil.eq("RowStatus", true));
        long count = ApprovalDetailUserLocalServiceUtil.dynamicQueryCount(query);
        return count > 0;
    }
}
