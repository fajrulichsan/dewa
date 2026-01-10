package com.astra.dewa.cms.command.resource.user.management;

import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.astra.dewa.model.Cabang;
import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.UserRoleType;
import com.astra.dewa.model.UsersDealers;
import com.astra.dewa.service.CabangLocalServiceUtil;
import com.astra.dewa.service.UsersDealersLocalServiceUtil;
import com.astra.dewa.utils.DateUtil;
import com.astra.dewa.utils.DealerUtils;
import com.astra.dewa.utils.RoleDealerUtils;
import com.astra.dewa.utils.RoleUtils;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;

/**
 * @author psmmutia0113
 * @author psmahmad1402
 */
@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + CmsPortletKeys.ROLEDEALERWEB,
                "mvc.command.name=/user-dealer-list"
        },
        service = MVCResourceCommand.class
)
public class UserManagementListResourceCommand extends BaseMVCResourceCommand {
    private final Log _log = LogFactoryUtil.getLog(UserManagementListResourceCommand.class);

    @Override
    protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
        HttpServletRequest httpServletRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        long userId = themeDisplay.getUserId();

        // datatable params
        int start = ParamUtil.getInteger(httpServletRequest, "start");
        int length = ParamUtil.getInteger(httpServletRequest, "length");
        int draw = ParamUtil.getInteger(httpServletRequest, "draw");
        String searchValue = ParamUtil.getString(httpServletRequest, "search[value]");
        String orderColumn = ParamUtil.getString(httpServletRequest, "order[0][column]");
        String orderDir = ParamUtil.getString(httpServletRequest, "order[0][dir]");

        long count = start;
        long filteredUser = 0;

        _log.info("==========================");
        _log.info("Fetching data user ...");

        long startTime = System.currentTimeMillis();

        String roleId = httpServletRequest.getParameter("roleId");
        boolean isAdminDivision = false;

        JSONArray jsonData = JSONFactoryUtil.createJSONArray();

        try {
            AuthTokenUtil.checkCSRFToken(httpServletRequest, this.getClass().getName());

            boolean isLiferayAdmin = RoleUtils.isLiferayAdmin(userId);

            if (!isLiferayAdmin) {
                isAdminDivision = RoleDealerUtils.isAdminDepartment(userId);
            }

            StringBuilder roleQuery = new StringBuilder();
            if (isAdminDivision) {
                roleQuery.append("AND UserId IN (\n" +
                        "SELECT UserId FROM UserRoleType WHERE RowStatus = 1\n" +
                        "AND RoleId IN (");
                List<Integer> roleIdMember = RoleDealerUtils.getAdminRoleIdMember(userId);
                for (int i = 0; i < roleIdMember.size(); i++) {
                    roleQuery.append(roleIdMember.get(i));
                    if (i < roleIdMember.size() - 1) {
                        roleQuery.append(", ");
                    }
                }
                roleQuery.append(")\n)\n");
            } else if (!roleId.equalsIgnoreCase("ALL") && !roleId.isEmpty()) {
                roleQuery.append("AND UserId IN (\n" +
                        "SELECT UserId FROM UserRoleType WHERE RowStatus = 1\n" +
                        "AND RoleId = " + Integer.parseInt(roleId) + ")\n");
            }

            String orderQuery = getOrderParam(orderColumn, orderDir);

            List<UsersDealers> result = UsersDealersLocalServiceUtil.getDealerUsers(orderQuery, start, length, searchValue, roleQuery.toString());

            for (UsersDealers roleDealer : result) {
                try {
                    JSONObject dto = JSONFactoryUtil.createJSONObject()
                            .put("id", roleDealer.getId())
                            .put("userId", roleDealer.getUserId());

                    // approved date
                    if (roleDealer.getApprovedDate() != null) {
                        dto.put("dateApprovSorting", roleDealer.getApprovedDate().getTime());
                        dto.put("approvedDate", DateUtil.convertDateToStringIndo(roleDealer.getApprovedDate(), true));
                    } else {
                        dto.put("dateApprovSorting", "-");
                        dto.put("approvedDate", "-");
                    }
                    dto.put("fullName", HtmlUtil.escape(roleDealer.getFullName()));
                    dto.put("userEmail", roleDealer.getUserEmail());

                    // user role group
                    if (RoleDealerUtils.isSuperUser(roleDealer.getUserId())) {
                        dto.put("dealerId", "-")
                                .put("dealerName", "-")
                                .put("cabangId", "-")
                                .put("cabangName", "-")
                                .put("kodeHo", "-");
                    } else {
                        Dealer dealer = DealerUtils.getDealer(roleDealer.getDealerId(), true);

                        dto.put("dealerId", Validator.isNull(dealer) ? "" : dealer.getId());
                        dto.put("dealerName", Validator.isNull(dealer) ? "" : HtmlUtil.escape(dealer.getName()));
                        dto.put("kodeHo", Validator.isNull(dealer) ? "" : dealer.getKodeHo());

                        if (Validator.isNotNull(dealer) && dealer.getCabangId() > 0) {
                            Cabang cabang = CabangLocalServiceUtil.getCabang(dealer.getCabangId());
                            dto.put("cabangId", cabang.getId());
                            dto.put("cabangName", HtmlUtil.escape(cabang.getName()));
                        } else {
                            dto.put("cabangId", "-");
                            dto.put("cabangName", "-");
                        }

                        dto.put("isDefault", "-");
                        dto.put("ordinal", "-");
                    }

                    // user roles
                    JSONArray roles = JSONFactoryUtil.createJSONArray();
                    List<UserRoleType> userRoles = RoleDealerUtils.getUserRoles(roleDealer.getUserId());
                    for (UserRoleType userRole : userRoles) {
                        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
                        String roleName = "";
                        if (userRole.getRoleId() != 0) {
                            roleName = RoleUtils.getRoleName(userRole.getRoleId());
                        }
                        jsonObject.put("name", roleName);
                        roles.put(jsonObject);
                    }
                    dto.put("no", ++count);
                    dto.put("roles", roles);

                    jsonData.put(dto);
                } catch (Exception e) {
                    _log.warn("Failed to get UsersDealers: " + roleDealer.getUserId());
                    _log.error(e.getMessage(), e);
                }
            }

            filteredUser = UsersDealersLocalServiceUtil.getDealerUsersCount(searchValue, roleQuery.toString());
        } catch (Exception e) {
            if (e instanceof PrincipalException) {
                _log.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(request));
                _log.error("Invalid CSRF token! Token: " + ParamUtil.get(request, "p_auth", "none"), e);
            } else {
                _log.error(e.getMessage(), e);
            }
        }

        long endTime = System.currentTimeMillis();
        _log.info("Fetch data user complete in " + (endTime - startTime) + "ms");
        _log.info("==========================");

        JSONObject jsonObject = FORMAT(count, filteredUser, draw, jsonData);
        jsonObject.put("isAdminDivision", isAdminDivision);
        response.setContentType("application/json");
        ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
    }

    private static String getOrderParam(String orderColumn, String orderDir) {
        StringBuilder orderQuery = new StringBuilder("ORDER BY ");

        switch (orderColumn) {
            case "2":
                if (orderDir.equals("asc")) {
                    orderQuery.append("KodeHo ASC\n");
                } else {
                    orderQuery.append("KodeHo DESC\n");
                }
                break;
            case "3":
                if (orderDir.equals("asc")) {
                    orderQuery.append("FullName ASC\n");
                } else {
                    orderQuery.append("FullName DESC\n");
                }
                break;
            case "4":
                if (orderDir.equals("asc")) {
                    orderQuery.append("UserEmail ASC\n");
                } else {
                    orderQuery.append("UserEmail DESC\n");
                }
                break;
            case "5":
                if (orderDir.equals("asc")) {
                    orderQuery.append("DealerName ASC\n");
                } else {
                    orderQuery.append("DealerName DESC\n");
                }
                break;
            case "6":
                if (orderDir.equals("asc")) {
                    orderQuery.append("CabangName ASC\n");
                } else {
                    orderQuery.append("CabangName DESC\n");
                }
                break;
            case "7":
                if (orderDir.equals("asc")) {
                    orderQuery.append("ApprovedDate ASC\n");
                } else {
                    orderQuery.append("ApprovedDate DESC\n");
                }
                break;
            default:
                orderQuery.append("ModifiedDate DESC\n");
                break;
        }
        return orderQuery.toString();
    }
}