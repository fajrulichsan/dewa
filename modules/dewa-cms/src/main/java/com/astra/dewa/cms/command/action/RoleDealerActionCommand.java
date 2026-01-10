package com.astra.dewa.cms.command.action;

import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.astra.dewa.exception.NoSuchRolesException;
import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.UserRoleType;
import com.astra.dewa.model.UsersDealers;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.service.RolesLocalServiceUtil;
import com.astra.dewa.service.UserRoleTypeLocalServiceUtil;
import com.astra.dewa.service.UsersDealersLocalServiceUtil;
import com.astra.dewa.utils.ADB2C.ADB2CService;
import com.astra.dewa.utils.ADB2C.constants.APIConstants;
import com.astra.dewa.utils.DealerUtils;
import com.astra.dewa.utils.FilterXSS;
import com.astra.dewa.utils.GroupDealerEnum;
import com.astra.dewa.utils.LiferayRoleEnum;
import com.astra.dewa.utils.MasterApprovalUtils;
import com.astra.dewa.utils.RoleDealerUtils;
import com.astra.dewa.utils.RoleUtils;
import com.astra.dewa.utils.RolesEnum;
import com.astra.dewa.utils.api.service.CmsDsoService;
import com.astra.dewa.utils.api.service.RSSPService;
import com.astra.dewa.utils.exception.DealinkAPIException;
import com.astra.dewa.utils.user.DealinkUserUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import static com.astra.dewa.utils.CRUDActionKeys.CREATE;
import static com.astra.dewa.utils.CRUDActionKeys.DELETE;
import static com.astra.dewa.utils.CRUDActionKeys.UPDATE;
import static com.astra.dewa.utils.JSONResponseFormatUtil.DELETED_NOT_SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.DELETED_SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.NOT_SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.WARNING;

/**
 * @author psmmutia0113
 */
@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + CmsPortletKeys.ROLEDEALERWEB,
                "mvc.command.name=/role-dealer-action"
        },
        service = MVCResourceCommand.class
)
public class RoleDealerActionCommand extends BaseMVCResourceCommand {
    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
    private User user;

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
        ServiceContext serviceContext = ServiceContextFactory.getInstance(resourceRequest);
        this.user = UserLocalServiceUtil.getUser(serviceContext.getUserId());

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

        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
        try {
            // CSRF AUTHENTICATION
            AuthTokenUtil.checkCSRFToken(uploadPortletRequest, this.getClass().getName());

            if (isRequestContainsXSS) {
                jsonObject = ERROR("Oops! Something went wrong.");
            } else {
                String crudType = ParamUtil.getString(uploadPortletRequest, "crudType");
                String entryList = ParamUtil.getString(uploadPortletRequest, "entryList");
                String roles = ParamUtil.getString(resourceRequest, "roles");
                JSONArray jsonRoles = JSONFactoryUtil.createJSONArray(roles);

                int entryId = ParamUtil.getInteger(uploadPortletRequest, "entryId");
                long userId = ParamUtil.getLong(uploadPortletRequest, "userId");
                String dealerId = ParamUtil.getString(uploadPortletRequest, "dealerId");

                UsersDealers userDealer;
                switch (crudType) {
                    case CREATE:
                        userDealer = UsersDealersLocalServiceUtil.createUsersDealers(0);
                        jsonObject = create(userDealer, userId, jsonRoles);
                        break;
                    case UPDATE:
                        userDealer = UsersDealersLocalServiceUtil.getUsersDealers(entryId);
                        jsonObject = update(userDealer, jsonRoles, dealerId);
                        break;
                    case DELETE:
                        jsonObject = delete(entryId);
                        break;
                    case "DELETE_LIST":
                        jsonObject = deleteList(entryList);
                        break;
                    case "VALIDATE_ROLE":
                        if (!validateRoleGroup(jsonRoles).getString("status").equals("success")) {
                            jsonObject = validateRoleGroup(jsonRoles);
                            break;
                        }

                        // REQUESTED ROLES VALIDATION
                        List<Integer> roleIds = new ArrayList<>();
                        for (Object role : jsonRoles) {
                            int roleId = ((JSONObject) role).getInt("id");
                            roleIds.add(roleId);
                        }

                        // VALIDATE DEPARTMENT
                        if (!validateDepartment(roleIds).getString("status").equals("success")) {
                            jsonObject = validateDepartment(roleIds);
                        } else {
                            jsonObject = SUCCESS("", String.valueOf(RoleUtils.getRoleGroupId(roleIds.get(0))));
                        }
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid action.");
                }
            }
        } catch (Exception e) {
            if (e instanceof PrincipalException) {
                _log.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(resourceRequest));
                _log.error("Invalid CSRF token! Token: " + ParamUtil.get(resourceRequest, "p_auth", "none"), e);
                jsonObject = ERROR(401, "Unauthorized request!");
            } else {
                _log.error(e.getMessage(), e);
                jsonObject = ERROR(400, "Bad request!");
            }
        }
        ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
    }

    private JSONObject create(UsersDealers userDealer, long userId, JSONArray roles) {
        try {
            if (isExist(userId)) {
                return WARNING("User sudah terdaftar! Silahkan update dari User Management");
            }

            // ROLE GROUP VALIDATION
            if (!validateRoleGroup(roles).getString("status").equals("success")) {
                return validateRoleGroup(roles);
            }

            // REQUESTED ROLES VALIDATION
            List<Integer> roleIds = new ArrayList<>();
            for (Object role : roles) {
                int roleId = ((JSONObject) role).getInt("id");
                validateRole(roleId);

                // REQUESTED ROLE : HO DEALER OR DEALER
                if (RoleUtils.isHODealer(roleId) || RoleUtils.isDealer(roleId)) {
                    return WARNING("Role ini hanya bisa ditambahkan pada menu Registrasi.");
                }

                roleIds.add(roleId);
            }

            if (!validateDepartment(roleIds).getString("status").equals("success")) {
                return validateDepartment(roleIds);
            }

            // ADD NEW USER DEALER
            Date now = new Date();
            User user = UserLocalServiceUtil.getUserById(userId);
            userDealer.setFullName(user.getFullName());
            userDealer.setUserEmail(user.getEmailAddress());
            userDealer.setUserId(userId);
            userDealer.setCreatedDate(now);
            userDealer.setCreatedBy(this.user.getScreenName());
            userDealer.setModifiedDate(now);
            userDealer.setModifiedBy(this.user.getScreenName());
            userDealer.setRowStatus(true);
            UsersDealersLocalServiceUtil.createDealerUserWithRoles(userDealer, roleIds);

            // ADD LIFERAY SITE ACCESS
            Group group = GroupLocalServiceUtil.getGroup(PortalUtil.getDefaultCompanyId(), APIConstants.NAME_SITE);
            GroupLocalServiceUtil.addUserGroup(userId, group);

            // CREATE LIFERAY ROLES
            for (Integer roleId : roleIds) {
                Role role = ADB2CService.getRoleLiferay(roleId);
                UserLocalServiceUtil.addRoleUser(role.getRoleId(), userDealer.getUserId());
            }

            /*
             * Trigger RSSP synchronization if the selected role(s) are authorized to access RSSP.
             * Group dealer is always 2 because only super roles can be accessed through add new user.
             */
            _log.info("==== ADDING RSSP ROLE IF EXIST ====");
            RSSPService.rsspRoleCheck(
                    UserLocalServiceUtil.getUser(userDealer.getUserId()).getEmailAddress(),
                    "",
                    2,
                    roleIds,
                    userDealer.getCreatedBy(),
                    userDealer.getCreatedDate(),
                    userDealer.getModifiedBy(),
                    userDealer.getModifiedDate(),
                    1
            );

            return SUCCESS("Data tersimpan", String.valueOf(userDealer.getId()));
        } catch (Exception e) {
            _log.error(e.getMessage(), e);
            if (e instanceof NoSuchRolesException) {
                return WARNING("Role tidak terdaftar");
            } else if (e instanceof DealinkAPIException) {
                return WARNING(e.getMessage());
            } else {
                return NOT_SUCCESS(e.getMessage());
            }
        }
    }

    private JSONObject update(UsersDealers dealerUser, JSONArray roles, String dealerId) {
        try {
            // GET USER ID AND LIFERAY ROLE GROUP
            long userId = dealerUser.getUserId();
            int liferayRoleGroup = RoleDealerUtils.getUserRoleGroup(userId);

            // DEALER
            Dealer requestDealer = null;

            // REMOVE ADMINISTRATOR FLAG
            boolean isAdminRoleRemoved = true;

            // ROLE GROUP VALIDATION
            if (!validateRoleGroup(roles).getString("status").equals("success")) {
                return validateRoleGroup(roles);
            }

            // REQUESTED ROLES VALIDATION
            List<Integer> roleIds = new ArrayList<>();
            for (Object role : roles) {
                int roleId = ((JSONObject) role).getInt("id");
                validateRole(roleId);

                // CHECK USER ORIGINAL ROLES
                if (RoleDealerUtils.isSuperUser(userId)) {

                    // REQUESTED ROLE : HO DEALER OR DEALER
                    if (RoleUtils.isHODealer(roleId)) {
                        return WARNING("Anda tidak bisa mengubah role ini menjadi HO Dealer");
                    } else if (RoleUtils.isDealer(roleId)) {
                        return WARNING("Anda tidak bisa mengubah role ini menjadi Dealer");
                    }
                } else if (RoleUtils.isHODealer(liferayRoleGroup) || RoleUtils.isDealer(liferayRoleGroup)) {

                    // REQUESTED ROLE : ADMINISTRATOR OR DSO
                    if (RoleUtils.isAdminDSO(roleId)) {
                        return WARNING("Anda tidak bisa mengubah role ini menjadi Administrator");
                    } else if (RoleUtils.isDSO(roleId)) {
                        return WARNING("Anda tidak bisa mengubah role ini menjadi DSO");
                    }
                }

                if (isAdminRoleRemoved) {
                    if (RoleUtils.isAdminDSO(roleId)) isAdminRoleRemoved = false;
                }

                roleIds.add(roleId);
            }

            if (liferayRoleGroup == RolesEnum.ADMIN_DSO.getId() || liferayRoleGroup == RolesEnum.DSO.getId()) {
                if (!validateDepartment(roleIds).getString("status").equals("success")) {
                    return validateDepartment(roleIds);
                }
            }

            // GET ACTIVE ROLES
            List<UserRoleType> activeRoles = RoleDealerUtils.getUserRoles(userId);

            // GET ACTIVE LIFERAY ROLES
            // List<Integer> activeLiferayRoles = ADB2CService.getLiferayRoles(userId);

            // USER ROLES IN INTEGER LIST
            List<Integer> activeUserRoleIds = new ArrayList<>();
            for (UserRoleType activeRole : activeRoles) {
                activeUserRoleIds.add(activeRole.getRoleId());
            }

            // UPDATE USER ROLES
            if (roleIds.size() == activeRoles.size()) {
                int i = 0;
                for (UserRoleType activeRole : activeRoles) {
                    activeRole.setRoleId(roleIds.get(i++));
                    activeRole.setModifiedDate(new Date());
                    activeRole.setModifiedBy(this.user.getScreenName());
                    UserRoleTypeLocalServiceUtil.updateUserRoleType(activeRole);
                }
            } else if (roleIds.size() > activeRoles.size()) {
                int i = 0;
                for (UserRoleType activeRole : activeRoles) {
                    activeRole.setRoleId(roleIds.get(i++));
                    activeRole.setModifiedDate(new Date());
                    activeRole.setModifiedBy(this.user.getScreenName());
                    UserRoleTypeLocalServiceUtil.updateUserRoleType(activeRole);
                }
                for (int j = i; j < roleIds.size(); j++) {
                    UserRoleType newRole = UserRoleTypeLocalServiceUtil.createUserRoleType(0);
                    Date now = new Date();
                    newRole.setUserId(userId);
                    newRole.setRoleId(roleIds.get(j));
                    newRole.setRowStatus(true);
                    newRole.setCreatedDate(now);
                    newRole.setCreatedBy(this.user.getScreenName());
                    newRole.setModifiedDate(now);
                    newRole.setModifiedBy(this.user.getScreenName());
                    UserRoleTypeLocalServiceUtil.addUserRoleType(newRole);
                }
            } else {
                int i = 0;
                for (UserRoleType activeRole : activeRoles) {
                    activeRole.setModifiedDate(new Date());
                    activeRole.setModifiedBy(this.user.getScreenName());
                    if (i < roleIds.size()) {
                        activeRole.setRoleId(roleIds.get(i++));
                        UserRoleTypeLocalServiceUtil.updateUserRoleType(activeRole);
                    } else {
                        activeRole.setRowStatus(false);
                        UserRoleTypeLocalServiceUtil.updateUserRoleType(activeRole);
                    }
                }
            }

            // LIST OF UPDATED ROLES
            List<UserRoleType> updatedRoles = RoleDealerUtils.getUserRoles(userId);
            List<Integer> updatedRoleIds = new ArrayList<>();

            for (UserRoleType updatedRole : updatedRoles) {
                updatedRoleIds.add(updatedRole.getRoleId());
            }

            Dealer currentDealer = null;
            // UPDATE USER DEALER
            if (!dealerId.isEmpty()) {
                currentDealer = DealerLocalServiceUtil.getDealer(dealerUser.getDealerId());
                requestDealer = DealerLocalServiceUtil.getDealer(Integer.parseInt(dealerId));
                dealerUser.setDealerId(requestDealer.getId());
            } else {
                dealerUser.setDealerId(0);
            }

            List<Integer> deletedRoles = new ArrayList<>(activeUserRoleIds);
            deletedRoles.removeAll(roleIds);

            _log.info("==== DELETING RSSP ROLE IF EXIST ====");
            // DEACTIVATE RSSP ROLES
            RSSPService.rsspRoleCheck(
                    UserLocalServiceUtil.getUser(dealerUser.getUserId()).getEmailAddress(),
                    currentDealer == null ? "" : currentDealer.getCode(),
                    currentDealer == null ? 2 : currentDealer.getGroupDealer(),
                    deletedRoles,
                    dealerUser.getCreatedBy(),
                    dealerUser.getCreatedDate(),
                    dealerUser.getModifiedBy(),
                    dealerUser.getModifiedDate(),
                    0
            );

            _log.info("==== ADDING RSSP ROLE IF EXIST ====");
            // ACTIVATE RSSP ROLES
            RSSPService.rsspRoleCheck(
                    UserLocalServiceUtil.getUser(dealerUser.getUserId()).getEmailAddress(),
                    requestDealer == null ? "" : requestDealer.getCode(),
                    requestDealer == null ? 2 : requestDealer.getGroupDealer(),
                    updatedRoleIds,
                    dealerUser.getCreatedBy(),
                    dealerUser.getCreatedDate(),
                    dealerUser.getModifiedBy(),
                    dealerUser.getModifiedDate(),
                    1
            );


            String fullName = dealerUser.getFullName();
            String[] nameParts = dealerUser.getFullName().split("\\s+", 2);
            String firstName;
            String lastName;
            if (nameParts.length == 2) {
                firstName = nameParts[0];
                lastName = nameParts[1];
            } else {
                firstName = fullName;
                lastName = fullName;
            }


            _log.info("==== DELETING CMS DSO ROLE IF EXIST ====");
            int currentGroupDealer = currentDealer == null ? -1 : currentDealer.getGroupDealer();

            // deleted user areas
            JSONArray currentAreas = JSONFactoryUtil.createJSONArray();
            if (currentGroupDealer == GroupDealerEnum.HO_DEALER.ordinal()) {
                List<Dealer> branchDealers = DealerUtils.getBranchDealers(currentDealer.getId());
                for (Dealer branchDealer : branchDealers) {
                    String dealerCode = branchDealer.getCode().substring(branchDealer.getCode().length() - 4);
                    JSONObject area = JSONFactoryUtil
                            .createJSONObject()
                            .put("regionCode", branchDealer.getWilayahId())
                            .put("dealerCode", dealerCode);
                    currentAreas.put(area);
                }
            } else if (currentGroupDealer == GroupDealerEnum.DEALER_CABANG.ordinal()) {
                String dealerCode = currentDealer.getCode().substring(currentDealer.getCode().length() - 4);
                JSONObject area = JSONFactoryUtil
                        .createJSONObject()
                        .put("regionCode", currentDealer.getWilayahId())
                        .put("dealerCode", dealerCode);
                currentAreas.put(area);
            }

            // DEACTIVATE CMS DSO ROLES
            CmsDsoService.checkRole(
                    UserLocalServiceUtil.getUser(dealerUser.getUserId()).getEmailAddress(),
                    0,
                    firstName,
                    lastName,
                    "",
                    currentGroupDealer,
                    currentAreas,
                    dealerUser.getCreatedBy(),
                    dealerUser.getCreatedDate(),
                    dealerUser.getModifiedBy(),
                    dealerUser.getModifiedDate(),
                    deletedRoles
            );

            _log.info("==== ADDING CMS DSO ROLE IF EXIST ====");
            int requestGroupDealer = null == requestDealer ? -1 : requestDealer.getGroupDealer();

            JSONArray requestAreas = JSONFactoryUtil.createJSONArray();
            if (requestGroupDealer == GroupDealerEnum.HO_DEALER.ordinal()) {
                List<Dealer> branchDealers = DealerUtils.getBranchDealers(requestDealer.getId());
                for (Dealer branchDealer : branchDealers) {
                    String dealerCode = branchDealer.getCode().substring(branchDealer.getCode().length() - 4);
                    JSONObject area = JSONFactoryUtil
                            .createJSONObject()
                            .put("regionCode", branchDealer.getWilayahId())
                            .put("dealerCode", dealerCode);
                    requestAreas.put(area);
                }
            } else if (currentGroupDealer == GroupDealerEnum.DEALER_CABANG.ordinal()) {
                String dealerCode = requestDealer.getCode().substring(requestDealer.getCode().length() - 4);
                JSONObject area = JSONFactoryUtil
                        .createJSONObject()
                        .put("regionCode", requestDealer.getWilayahId())
                        .put("dealerCode", dealerCode);
                requestAreas.put(area);
            }

            // ACTIVATE CMS DSO ROLES
            CmsDsoService.checkRole(
                    UserLocalServiceUtil.getUser(dealerUser.getUserId()).getEmailAddress(),
                    1,
                    firstName,
                    lastName,
                    "",
                    requestGroupDealer,
                    requestAreas,
                    dealerUser.getCreatedBy(),
                    dealerUser.getCreatedDate(),
                    dealerUser.getModifiedBy(),
                    dealerUser.getModifiedDate(),
                    updatedRoleIds
            );

            updateLiferayRoles(userId, updatedRoleIds);

            dealerUser.setModifiedDate(new Date());
            dealerUser.setModifiedBy(this.user.getScreenName());
            UsersDealersLocalServiceUtil.updateUsersDealers(dealerUser);

            // REDIRECT IF LOGGED USER ADMINISTRATOR ROLE IS REMOVED
            return isAdminRoleRemoved && this.user.getUserId() == userId ?
                    SUCCESS(302, "Data berhasil diubah.") :
                    SUCCESS("Data berhasil diubah.", String.valueOf(dealerUser.getId()));
        } catch (Exception e) {
            _log.error(e.getMessage(), e);
            if (e instanceof NoSuchRolesException) {
                return WARNING("Role tidak terdaftar!");
            } else if (e instanceof DealinkAPIException) {
                return WARNING(e.getMessage());
            } else {
                return NOT_SUCCESS(e.getMessage());
            }
        }
    }

    private JSONObject delete(int entryId) {
        try {
            UsersDealers selectedUser = UsersDealersLocalServiceUtil.getUsersDealers(entryId);
            DynamicQuery selectedUserQuery = UsersDealersLocalServiceUtil.dynamicQuery()
                    .add(RestrictionsFactoryUtil.eq("Id", selectedUser.getId()))
                    .add(RestrictionsFactoryUtil.eq("RowStatus", true))
                    .setProjection(ProjectionFactoryUtil.property("UserId"));
            DynamicQuery selectedUserDataQuery = UsersDealersLocalServiceUtil.dynamicQuery()
                    .add(PropertyFactoryUtil.forName("UserId").eq(selectedUserQuery))
                    .add(RestrictionsFactoryUtil.eq("RowStatus", true));
            List<UsersDealers> selectedUserData = UsersDealersLocalServiceUtil.dynamicQuery(selectedUserDataQuery);
            UsersDealers dealerUser = selectedUserData.get(0);

            List<Integer> roleGroups = ADB2CService.getLiferayRoles(dealerUser.getUserId());

            String dealerCode = "";
            int dealerGroup = 2;
            int dealerWilayah = 0;
            RolesEnum userRoleGroup = DealinkUserUtil.getUserRoleGroupEnum(dealerUser.getUserId());
            if (
                    userRoleGroup == RolesEnum.HO_DEALER
                            || userRoleGroup == RolesEnum.DEALER
            ) {
                Dealer dealer = DealerLocalServiceUtil.getDealer(dealerUser.getDealerId());
                dealerCode = dealer.getCode();
                dealerGroup = dealer.getGroupDealer();
                dealerWilayah = dealer.getWilayahId();
            }

            List<UserRoleType> userRoles = RoleDealerUtils.getUserRoles(dealerUser.getUserId());
            List<Integer> roleIds = new ArrayList<>();

            userRoles.forEach(data -> {
                roleIds.add(data.getRoleId());
            });

            RSSPService.rsspRoleCheck(
                    UserLocalServiceUtil.getUser(dealerUser.getUserId()).getEmailAddress(),
                    dealerCode,
                    dealerGroup,
                    roleIds,
                    dealerUser.getCreatedBy(),
                    dealerUser.getCreatedDate(),
                    dealerUser.getModifiedBy(),
                    dealerUser.getModifiedDate(),
                    0
            );


            String fullName = dealerUser.getFullName();
            String[] nameParts = dealerUser.getFullName().split("\\s+", 2);
            String firstName;
            String lastName;
            if (nameParts.length == 2) {
                firstName = nameParts[0];
                lastName = nameParts[1];
            } else {
                firstName = fullName;
                lastName = fullName;
            }


            _log.info("==== DELETING CMS DSO ROLE IF EXIST ====");
            // deleted user areas
            JSONArray currentAreas = JSONFactoryUtil.createJSONArray();

            if (dealerGroup == GroupDealerEnum.HO_DEALER.ordinal()) {
                // Case HO Dealer + Role HO Dealer → gunakan branch dealers
                if (userRoleGroup == RolesEnum.HO_DEALER) {
                    List<Dealer> branchDealers = DealerUtils.getBranchDealers(dealerUser.getDealerId());

                    for (Dealer branchDealer : branchDealers) {
                        currentAreas.put(userArea(branchDealer.getWilayahId(), branchDealer.getCode()));
                    }

                    // Case HO Dealer + role lainnya → pakai dealerCode utama
                } else {
                    currentAreas.put(userArea(dealerWilayah, dealerCode));
                }

            } else if (dealerGroup == GroupDealerEnum.DEALER_CABANG.ordinal()) {
                // Case Dealer Cabang -> selalu pakai dealerCode utama
                currentAreas.put(userArea(dealerWilayah, dealerCode));
            }

            // JSONArray currentAreas = JSONFactoryUtil.createJSONArray();
            // if (dealerGroup == GroupDealerEnum.HO_DEALER.ordinal()) {
            //     if (userRoleGroup == RolesEnum.HO_DEALER) {
            //         List<Dealer> branchDealers = DealerUtils.getBranchDealers(dealerUser.getDealerId());
            //         for (Dealer branchDealer : branchDealers) {
            //             String deletedDealerCode = sliceDealerCode(branchDealer.getCode(), branchDealer.getCode().length() - 4);
            //             JSONObject area = JSONFactoryUtil
            //                     .createJSONObject()
            //                     .put("regionCode", branchDealer.getWilayahId())
            //                     .put("dealerCode", deletedDealerCode);
            //             currentAreas.put(area);
            //         }
            //     } else {
            //         String deletedDealerCode = sliceDealerCode(dealerCode, dealerCode.length() - 4);
            //         JSONObject area = JSONFactoryUtil
            //                 .createJSONObject()
            //                 .put("regionCode", dealerWilayah)
            //                 .put("dealerCode", deletedDealerCode);
            //         currentAreas.put(area);
            //     }
            // } else if (dealerGroup == GroupDealerEnum.DEALER_CABANG.ordinal()) {
            //     String deletedDealerCode = sliceDealerCode(dealerCode, dealerCode.length() - 4);
            //     JSONObject area = JSONFactoryUtil
            //             .createJSONObject()
            //             .put("regionCode", dealerWilayah)
            //             .put("dealerCode", deletedDealerCode);
            //     currentAreas.put(area);
            // }

            // DEACTIVATE CMS DSO ROLES
            CmsDsoService.checkRole(
                    UserLocalServiceUtil.getUser(dealerUser.getUserId()).getEmailAddress(),
                    0,
                    firstName,
                    lastName,
                    "",
                    dealerGroup,
                    currentAreas,
                    dealerUser.getCreatedBy(),
                    dealerUser.getCreatedDate(),
                    dealerUser.getModifiedBy(),
                    dealerUser.getModifiedDate(),
                    roleIds
            );


            JSONObject result;
            if (MasterApprovalUtils.isActiveApprover(dealerUser.getUserId())) {
                return DELETED_NOT_SUCCESS("User terdaftar sebagai PIC Approver. Silahkan ganti terlebih dahulu pada menu Master Approval.", String.valueOf(entryId));
            }

            dealerUser.setModifiedDate(new Date());
            dealerUser.setModifiedBy(this.user.getScreenName());
            dealerUser.setRowStatus(false);
            UsersDealersLocalServiceUtil.deleteDealerUserWithRoles(dealerUser);

            for (UsersDealers userData : selectedUserData) {
                userData.setModifiedDate(new Date());
                userData.setModifiedBy(this.user.getScreenName());
                userData.setRowStatus(false);
                UsersDealersLocalServiceUtil.updateUsersDealers(userData);
            }

            // DELETE EACH LIFERAY ROLE
            for (Integer roleId : roleGroups) {
                Role role = ADB2CService.getRoleLiferay(roleId);
                UserLocalServiceUtil.deleteRoleUser(role.getRoleId(), dealerUser.getUserId());
            }

            // REMOVE DEWA SITE ACCESS
            Group group = GroupLocalServiceUtil.getGroup(PortalUtil.getDefaultCompanyId(), APIConstants.NAME_SITE);
            GroupLocalServiceUtil.deleteUserGroup(dealerUser.getUserId(), group);

            // DELETE FROM ADB2C AND LIFERAY SIDE
            if (!dealerUser.getADB2CId().isEmpty()) {
                JSONObject jsonObject = ADB2CService.deleteUserDEWA(dealerUser.getUserId(), dealerUser.getADB2CId());
                String messageValue = jsonObject.getString("status");
                result = messageValue.equalsIgnoreCase("success") ?
                        DELETED_SUCCESS("Data terhapus.", String.valueOf(entryId)) :
                        DELETED_NOT_SUCCESS("Data tidak terhapus di ADB2C dan liferay.", String.valueOf(entryId));
            } else {
                result = DELETED_SUCCESS("Data terhapus.", String.valueOf(entryId));
            }

            if (this.user.getUserId() == dealerUser.getUserId()) {
                return SUCCESS(302, "Data terhapus.");
            }

            return result;
        } catch (Exception e) {
            _log.error(e.getMessage(), e);
            if (e instanceof DealinkAPIException) {
                return WARNING(e.getMessage());
            } else {
                return DELETED_NOT_SUCCESS("Data tidak terhapus.", String.valueOf(entryId));
            }
        }
    }

    private JSONObject deleteList(String list) {
        try {
            List<String> listEmailUSerGagal = new ArrayList<>();
            boolean isAdminRemoved = false;

            String[] ids = list.split(",");
            for (String id : ids) {
                UsersDealers selectedUser = UsersDealersLocalServiceUtil.getUsersDealers(Integer.parseInt(id));
                DynamicQuery selectedUserQuery = UsersDealersLocalServiceUtil.dynamicQuery()
                        .add(RestrictionsFactoryUtil.eq("Id", selectedUser.getId()))
                        .add(RestrictionsFactoryUtil.eq("RowStatus", true))
                        .setProjection(ProjectionFactoryUtil.property("UserId"));
                DynamicQuery selectedUserDataQuery = UsersDealersLocalServiceUtil.dynamicQuery()
                        .add(PropertyFactoryUtil.forName("UserId").in(selectedUserQuery))
                        .add(RestrictionsFactoryUtil.eq("RowStatus", true));
                List<UsersDealers> selectedUserData = UsersDealersLocalServiceUtil.dynamicQuery(selectedUserDataQuery);
                UsersDealers dealerUser = selectedUserData.get(0);

                List<Integer> roleGroups = ADB2CService.getLiferayRoles(dealerUser.getUserId());

                // MASTER APPROVAL STATUS
                if (MasterApprovalUtils.isActiveApprover(dealerUser.getUserId())) {
                    return DELETED_NOT_SUCCESS("User terdaftar sebagai PIC Approver. Silahkan ganti terlebih dahulu pada menu Master Approval.", String.valueOf(id));
                }

                // DEALER INFORMATION
                String dealerCode = "";
                int dealerGroup = 2;

                if (RolesEnum.HO_DEALER.getId() == RoleDealerUtils.getUserRoleGroup(dealerUser.getUserId()) ||
                        RolesEnum.DEALER.getId() == RoleDealerUtils.getUserRoleGroup(dealerUser.getUserId())
                ) {
                    Dealer dealer = DealerLocalServiceUtil.getDealer(dealerUser.getDealerId());
                    dealerCode = dealer.getCode();
                    dealerGroup = dealer.getGroupDealer();
                }

                List<UserRoleType> userRoles = RoleDealerUtils.getUserRoles(dealerUser.getUserId());
                List<Integer> roleIds = new ArrayList<>();

                userRoles.forEach(data -> {
                    roleIds.add(data.getRoleId());
                });

                // RSSP ROLE
                RSSPService.rsspRoleCheck(
                        UserLocalServiceUtil.getUser(dealerUser.getUserId()).getEmailAddress(),
                        dealerCode,
                        dealerGroup,
                        roleIds,
                        dealerUser.getCreatedBy(),
                        dealerUser.getCreatedDate(),
                        dealerUser.getModifiedBy(),
                        dealerUser.getModifiedDate(),
                        0
                );

                dealerUser.setRowStatus(false);
                dealerUser.setModifiedBy(this.user.getScreenName());
                dealerUser.setModifiedDate(new Date());
                UsersDealersLocalServiceUtil.deleteDealerUserWithRoles(dealerUser);

                for (UsersDealers userData : selectedUserData) {
                    userData.setModifiedDate(new Date());
                    userData.setModifiedBy(this.user.getScreenName());
                    userData.setRowStatus(false);
                    UsersDealersLocalServiceUtil.updateUsersDealers(userData);
                }

                // DELETE EACH LIFERAY ROLE
                for (Integer roleId : roleGroups) {
                    Role role = ADB2CService.getRoleLiferay(roleId);
                    UserLocalServiceUtil.deleteRoleUser(role.getRoleId(), dealerUser.getUserId());
                }

                // REMOVE DEWA SITE ACCESS
                Group group = GroupLocalServiceUtil.getGroup(PortalUtil.getDefaultCompanyId(), APIConstants.NAME_SITE);
                GroupLocalServiceUtil.deleteUserGroup(dealerUser.getUserId(), group);

                // DELETE FROM ADB2C AND LIFERAY SIDE
                if (!dealerUser.getADB2CId().isEmpty()) {
                    JSONObject jsonObject = ADB2CService.deleteUserDEWA(dealerUser.getUserId(), dealerUser.getADB2CId());
                    String messageValue = jsonObject.getString("status");
                    if (!messageValue.equalsIgnoreCase("success")) {
                        User userLiferay = UserLocalServiceUtil.getUserById(dealerUser.getUserId());
                        listEmailUSerGagal.add(userLiferay.getEmailAddress());
                    }
                }
                if (this.user.getUserId() == dealerUser.getUserId()) isAdminRemoved = true;
            }

            if (listEmailUSerGagal.isEmpty()) {
                return isAdminRemoved ?
                        SUCCESS(302, "Berhasil menghapus " + ids.length + " user dari " + ids.length) :
                        DELETED_SUCCESS("Berhasil menghapus " + ids.length + " user dari " + ids.length, String.valueOf(""));
            } else {
                int dataKeHapus = ids.length - listEmailUSerGagal.size();
                return DELETED_NOT_SUCCESS("Hanya berhasil menghapus " + dataKeHapus + " user dari " + ids.length, "");
            }
        } catch (Exception e) {
            _log.error(e.getMessage(), e);
            if (e instanceof DealinkAPIException) {
                return WARNING(e.getMessage());
            } else {
                return DELETED_NOT_SUCCESS("Data tidak terhapus.", "");
            }
        }
    }

    private JSONObject validateRoleGroup(JSONArray roles) {
        List<Integer> roleGroups = new ArrayList<>();
        int i = -1;
        while (++i < roles.length()) {
            int roleId = roles.getJSONObject(i).getInt("id");
            if (!roleGroups.contains(RoleUtils.getRoleGroupId(roleId))) {
                roleGroups.add(RoleUtils.getRoleGroupId(roleId));
            }
        }
        boolean hasAdmin = roleGroups.contains(RolesEnum.ADMIN_DSO.getId()) || roleGroups.contains(RolesEnum.ADMIN_DIVISION.getId());
        boolean hasDso = roleGroups.contains(RolesEnum.DSO.getId());
        boolean hasHoDealer = roleGroups.contains(RolesEnum.HO_DEALER.getId());
        boolean hasDealer = roleGroups.contains(RolesEnum.DEALER.getId());

        if (hasAdmin && (hasHoDealer || hasDealer)) {
            return ERROR("Role Administrator hanya bisa disandingkan dengan role DSO!");
        } else if ((hasDso && hasHoDealer) || (hasDso && hasDealer) || (hasHoDealer && hasDealer)) {
            return ERROR("Role harus dalam group yang sama! ex: DSO");
        }

        return SUCCESS("", String.valueOf(roleGroups.get(0)));
    }

    private JSONObject validateDepartment(List<Integer> roles) throws SystemException {
        int i = -1;
        String initDepartment = null;
        boolean isInSameDepartment = true;

        while (++i < roles.size()) {
            if (RoleUtils.isDSO(roles.get(i))) {
                if (null == initDepartment) {
                    initDepartment = RoleUtils.getDepartmetName(roles.get(i));
                } else {
                    if (!initDepartment.equalsIgnoreCase(RoleUtils.getDepartmetName(roles.get(i)))) {
                        isInSameDepartment = false;
                        break;
                    }
                }
            }
        }

        if (!isInSameDepartment) {
            return WARNING("Role lintas departemen tidak diperbolehkan");
        }
        return SUCCESS("", "");
    }

    private boolean isExist(long userId) throws SystemException {
        DynamicQuery query = UsersDealersLocalServiceUtil.dynamicQuery()
                .add(RestrictionsFactoryUtil.eq("UserId", userId))
                .add(RestrictionsFactoryUtil.eq("RowStatus", true));
        long count = UsersDealersLocalServiceUtil.dynamicQueryCount(query);
        return count > 0;
    }

   /*
   private boolean isExist(long userId, List<Integer> roles) throws SystemException {
      DynamicQuery query = UserRoleTypeLocalServiceUtil.dynamicQuery()
            .add(RestrictionsFactoryUtil.in("RoleId", roles))
            .add(RestrictionsFactoryUtil.eq("UserId", userId))
            .add(RestrictionsFactoryUtil.eq("RowStatus", true));
      long count = UserRoleTypeLocalServiceUtil.dynamicQueryCount(query);
      return count > 0;
   }
   */

    private void validateRole(int roleId) throws NoSuchRolesException {
        DynamicQuery query = RolesLocalServiceUtil.dynamicQuery()
                .add(RestrictionsFactoryUtil.eq("Id", roleId))
                .add(RestrictionsFactoryUtil.eq("RowStatus", true));
        if (RolesLocalServiceUtil.dynamicQueryCount(query) == 0) throw new NoSuchRolesException();
    }

    // private void generateUserDataFromLiferay(UsersDealers dealerUser) throws PortalException {
    //    User user = UserLocalServiceUtil.getUser(dealerUser.getUserId());
      /*
      if (RoleDealerUtils.isSuperUser(dealerUser.getUserId())) {
         dealerUser.setDealerId(0);
      }
       */
    //    dealerUser.setUserEmail(user.getEmailAddress());
    //    UsersDealersLocalServiceUtil.updateUsersDealers(dealerUser);
    // }

    /**
     * This method is used to get the list of Dealink Liferay set roles from the Enum
     *
     * @return the List of Liferay roles
     * @throws PortalException when an error occurs
     */
    public static List<Role> getDealinkRoles() throws PortalException {
        List<Role> r = new ArrayList<>();
        for (LiferayRoleEnum lre : LiferayRoleEnum.values()) {
            r.add(RoleLocalServiceUtil.getRole(PortalUtil.getDefaultCompanyId(), lre.getName()));
        }
        return r;
    }

   /*
   public static List<Role> getDealinkRoles() throws PortalException {
      return new ArrayList<Role>() {{
         add(RoleLocalServiceUtil.getRole(PortalUtil.getDefaultCompanyId(), APIConstants.ROLE_ADMIN_DSO));
         add(RoleLocalServiceUtil.getRole(PortalUtil.getDefaultCompanyId(), APIConstants.ROLE_DSO));
         add(RoleLocalServiceUtil.getRole(PortalUtil.getDefaultCompanyId(), APIConstants.ROLE_HO_DEALER));
         add(RoleLocalServiceUtil.getRole(PortalUtil.getDefaultCompanyId(), APIConstants.ROLE_DEALER));
      }};
   }
    */

    /**
     * This method is used to update User's Liferay roles based on the Dealink roles
     *
     * @param userId  - is the primary key of the selected User
     * @param roleIds - is the Dealink roles id request
     * @throws PortalException when an error occurs
     */
    public static void updateLiferayRoles(long userId, List<Integer> roleIds) throws PortalException {
        List<Role> dr = getDealinkRoles();

        // delete User's active Dealink role(s)
        for (Role r : dr) {
            UserLocalServiceUtil.deleteRoleUser(r.getRoleId(), userId);
        }

        // add Dealink role(s) request
        if (!roleIds.isEmpty()) {
            for (Integer i : roleIds) {
                Role r = ADB2CService.getRoleLiferay(i);
                UserLocalServiceUtil.addRoleUser(r.getRoleId(), userId);
            }
        }
    }

    private static String sliceDealerCode(String dealerCode, int beginIndex) {
        return dealerCode.substring(beginIndex);
    }

    private JSONObject userArea(long regionCode, String dealerCode) {
        String deletedDealerCode = sliceDealerCode(dealerCode, dealerCode.length() - 4);

        return JSONFactoryUtil.createJSONObject()
                .put("regionCode", regionCode)
                .put("dealerCode", deletedDealerCode);
    }

}
