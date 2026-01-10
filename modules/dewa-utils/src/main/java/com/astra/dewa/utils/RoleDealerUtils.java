package com.astra.dewa.utils;

import com.astra.dewa.exception.NoSuchUserRoleTypeException;
import com.astra.dewa.model.Roles;
import com.astra.dewa.model.UserRoleType;
import com.astra.dewa.model.UsersDealers;
import com.astra.dewa.service.RolesLocalServiceUtil;
import com.astra.dewa.service.UserRoleTypeLocalServiceUtil;
import com.astra.dewa.service.UsersDealersLocalServiceUtil;
import com.astra.dewa.utils.user.DealinkUserUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

public class RoleDealerUtils {

    public static UsersDealers userId(Long userId) {
        DynamicQuery query = UsersDealersLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq("UserId", userId));
        query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
        List<UsersDealers> roleDealers = UsersDealersLocalServiceUtil.dynamicQuery(query);
        if (!roleDealers.isEmpty()) {
            return roleDealers.get(0);
        } else {
            return null;
        }
    }

    public static String getRole(List<Role> roles) {
        String roleString = "";
        if (!roles.isEmpty()) {
            for (Role role : roles) {
                String[] roleNews = role.getName().split(":");
                if (roleNews.length > 1) {
                    switch (roleNews[1]) {
                        case "DSO":
                            roleString = "DSO";
                            break;
                        case "HO Dealer":
                            roleString = "HO Dealer";
                            break;
                        case "Dealer":
                            roleString = "Dealer";
                            break;
                    }
                }
            }
        }
        return roleString;
    }

    public static String getUserName(long userId) throws SystemException {
        DynamicQuery query = UsersDealersLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq("UserId", userId));
        query.addOrder(OrderFactoryUtil.desc("ModifiedDate"));
        List<UsersDealers> users = UsersDealersLocalServiceUtil.dynamicQuery(query);
        if (!users.isEmpty()) {
            return users.get(0).getFullName();
        } else {
            return null;
        }
    }

    public static JSONArray selects() {
        JSONArray jsonData = JSONFactoryUtil.createJSONArray();
        DynamicQuery query = UsersDealersLocalServiceUtil.dynamicQuery();
        List<UsersDealers> roleDealers = UsersDealersLocalServiceUtil.dynamicQuery(query);
        roleDealers.forEach(data -> {
            JSONObject dto = JSONFactoryUtil.createJSONObject();
            // belum disesuaikan
//         dto.put("id", data.getHoCode());
//         dto.put("text", data.getHoCode() + " - " + data.getFullName());
            jsonData.put(dto);
        });
        return jsonData;
    }

    public static String getRoleDealerName(String roleDealerName) {
        // 5200005724 - Full Name
        String[] roleDealerNames = roleDealerName.split("- ");
        return roleDealerNames[roleDealerNames.length - 1];
    }

    public static String[] getEmails(String dealerCode) {
        DynamicQuery query = UsersDealersLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.ne("DealerCode", dealerCode));
        List<UsersDealers> roleDealers = UsersDealersLocalServiceUtil.dynamicQuery(query);

        String[] emails = new String[roleDealers.size()];
        for (int i = 0; i < roleDealers.size(); i++) {
            // belum disesuaikan
//         emails[i] = roleDealers.get(i).getUserEmail();
        }
        return emails;
    }

    public static List<UsersDealers> getUsersByRole(int roleId) {
        DynamicQuery query = UsersDealersLocalServiceUtil.dynamicQuery()
                .add(RestrictionsFactoryUtil.eq("RoleId", roleId))
                .add(RestrictionsFactoryUtil.eq("RowStatus", true));
        return UsersDealersLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * This method is used to check if the user has the same role as the assigned role in Master Approval
     *
     * @param userId               The selected User ID.
     * @param masterApprovalRoleId The selected Master Approval ID.
     * @return
     */
    public static boolean isInternalDSO(long userId, int masterApprovalRoleId) {
        DynamicQuery query = UserRoleTypeLocalServiceUtil.dynamicQuery()
                .add(RestrictionsFactoryUtil.eq("UserId", userId))
                .add(RestrictionsFactoryUtil.eq("RoleId", masterApprovalRoleId))
                .add(RestrictionsFactoryUtil.eq("RowStatus", true));
        return !UsersDealersLocalServiceUtil.dynamicQuery(query).isEmpty();
    }

    public static JSONArray selectNoAdminAndDSO() throws SystemException {
        JSONArray jsonData = JSONFactoryUtil.createJSONArray();
        DynamicQuery query = RolesLocalServiceUtil.dynamicQuery()
                .add(RestrictionsFactoryUtil.or(
                        RestrictionsFactoryUtil.like("Name", "HO" + "%"),
                        RestrictionsFactoryUtil.like("Name", "Dealer" + "%")
                ))
                .add(RestrictionsFactoryUtil.eq("RowStatus", true))
                .addOrder(OrderFactoryUtil.asc("Name"));
        List<Roles> roleDealers = RolesLocalServiceUtil.dynamicQuery(query);
        roleDealers.forEach(data -> {
            JSONObject dto = JSONFactoryUtil.createJSONObject();
            dto.put("id", data.getId());
            dto.put("text", data.getName());
            jsonData.put(dto);
        });
        return jsonData;
    }

    public static String[] listEmailAdminDSO(List<Integer> roles) {
        String[] recipients = new String[]{};
        try {
            List<String> listAdmin = new ArrayList<>();

            // LIST ADMIN DIVISION ROLE ID CONTAIN REQUESTED ROLES
            List<Integer> activeAdminDivisionRoles = new ArrayList<>();

            // ADMIN DIVISION QUERY
            DynamicQuery adminDivisionRoleQuery = RolesLocalServiceUtil.dynamicQuery()
                    .add(RestrictionsFactoryUtil.eq("RowStatus", true));

            List<Roles> adminDivisionRoles = RolesLocalServiceUtil.dynamicQuery(adminDivisionRoleQuery);
            if (!adminDivisionRoles.isEmpty()) {
                // LOOP EACH ADMIN DIVISION ROLE
                for (Roles role : adminDivisionRoles) {
                    if (Validator.isNotNull(role.getRoleIdMember())) {
                        JSONArray jsonArray = JSONFactoryUtil.createJSONArray(role.getRoleIdMember());

                        // HAS MEMBER FLAG
                        boolean hasMember = false;

                        for (Integer requestedRoleId : roles) {
                            boolean isMember = false;
                            int i = -1;
                            while (++i < jsonArray.length()) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                if (jsonObject.getInt("id") == requestedRoleId) {
                                    isMember = true;
                                    break;
                                }
                            }

                            if (isMember) {
                                hasMember = true;
                                break;
                            }
                        }

                        if (hasMember) {
                            activeAdminDivisionRoles.add(role.getId());
                        }
                    }
                }
            }

            // ADMIN DIVISION USER QUERY
            DynamicQuery userRoleQuery = UserRoleTypeLocalServiceUtil.dynamicQuery();
            if (!activeAdminDivisionRoles.isEmpty()) {
                userRoleQuery.add(RestrictionsFactoryUtil.or(
                        RestrictionsFactoryUtil.in("RoleId", activeAdminDivisionRoles),
                        RestrictionsFactoryUtil.eq("RoleId", RolesEnum.ADMIN_DSO.getId()))
                );
            } else {
                userRoleQuery.add(RestrictionsFactoryUtil.eq("RoleId", RolesEnum.ADMIN_DSO.getId()));
            }
            userRoleQuery.add(RestrictionsFactoryUtil.eq("RowStatus", true))
                    .setProjection(ProjectionFactoryUtil.property("UserId"));
            List<Long> adminDivisionUsers = UserRoleTypeLocalServiceUtil.dynamicQuery(userRoleQuery);

            if (!adminDivisionUsers.isEmpty()) {
                for (Long userId : adminDivisionUsers) {
                    User user = UserLocalServiceUtil.getUser(userId);
                    if (!listAdmin.contains(user.getEmailAddress())) {
                        listAdmin.add(user.getEmailAddress());
                    }
                }
            }

            recipients = listAdmin.toArray(new String[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipients;
    }

    public static boolean isUserHasRole(long userId, String roleName) throws SystemException {
        try {
            DynamicQuery query = UsersDealersLocalServiceUtil.dynamicQuery()
                    .add(RestrictionsFactoryUtil.eq("UserId", userId))
                    .add(RestrictionsFactoryUtil.eq("RowStatus", true))
                    .setProjection(ProjectionFactoryUtil.property("UserId"));
            DynamicQuery userRoleQuery = UserRoleTypeLocalServiceUtil.dynamicQuery()
                    .add(PropertyFactoryUtil.forName("UserId").in(query))
                    .add(RestrictionsFactoryUtil.eq("RowStatus", true));
            if (roleName.equals(RolesEnum.ADMIN_DSO.getName())) {
                userRoleQuery.add(RestrictionsFactoryUtil.eq("RoleId", RolesEnum.ADMIN_DSO.getId()));
            }
            userRoleQuery.setProjection(ProjectionFactoryUtil.property("RoleId"));
            List<Integer> roleIds = UsersDealersLocalServiceUtil.dynamicQuery(query);
            if (roleIds.isEmpty()) {
                return false;
            } else {
                String groupName = roleName.split("\\s*-")[0];
                DynamicQuery rolesQuery = RolesLocalServiceUtil.dynamicQuery()
                        .add(PropertyFactoryUtil.forName("Id").in(query))
                        .add(RestrictionsFactoryUtil.like("Name", groupName + "%"))
                        .add(RestrictionsFactoryUtil.eq("RowStatus", true))
                        .setProjection(ProjectionFactoryUtil.property("Id"));
                DynamicQuery userDealerQuery = UsersDealersLocalServiceUtil.dynamicQuery()
                        .add(PropertyFactoryUtil.forName("RoleId").in(rolesQuery))
                        .add(RestrictionsFactoryUtil.eq("RowStatus", true));
                long roleCount = UsersDealersLocalServiceUtil.dynamicQueryCount(userDealerQuery);
                return roleCount > 0;
            }
        } catch (SystemException se) {
            se.printStackTrace();
            throw new SystemException();
        }
    }

    /**
     * This method is used to get all user roles.
     *
     * @param userId The ID of the specified user.
     * @return The list of roles.
     * @throws NoSuchUserRoleTypeException If an error occurs during the process.
     */
    public static List<UserRoleType> getUserRoles(long userId) throws NoSuchUserRoleTypeException {
        DynamicQuery query = UserRoleTypeLocalServiceUtil.dynamicQuery()
                .add(RestrictionsFactoryUtil.eq("UserId", userId))
                .add(RestrictionsFactoryUtil.eq("RowStatus", true));
        List<UserRoleType> result = UserRoleTypeLocalServiceUtil.dynamicQuery(query);
        if (result.isEmpty()) {
            throw new NoSuchUserRoleTypeException();
        } else {
            return result;
        }
    }

    public static boolean isAdminDepartment(long userId) throws PortalException {
        List<UserRoleType> userRoles = getUserRoles(userId);
        if (!userRoles.isEmpty()) {
            for (UserRoleType userRole : userRoles) {
                Roles role = RolesLocalServiceUtil.getRoles(userRole.getRoleId());
                if (role.getName().equalsIgnoreCase("Administrator")) return false;
                if (role.getName().startsWith("Admin")) return true;
            }
        }
        return false;
    }

    public static List<Integer> getAdminRoleIdMember(long userId) throws PortalException {
        List<Integer> result = new ArrayList<>();

        DynamicQuery userRoleQuery = UserRoleTypeLocalServiceUtil.dynamicQuery()
                .add(RestrictionsFactoryUtil.eq("UserId", userId))
                .add(RestrictionsFactoryUtil.eq("RowStatus", true))
                .setProjection(ProjectionFactoryUtil.property("RoleId"));
        List<Integer> userRoles = UserRoleTypeLocalServiceUtil.dynamicQuery(userRoleQuery);

        for (Integer userRole : userRoles) {
            Roles role = RolesLocalServiceUtil.getRoles(userRole);
            if (!role.getRoleIdMember().isEmpty()) {
                JSONArray memberRoleIds = JSONFactoryUtil.createJSONArray(role.getRoleIdMember());
                for (int i = 0; i < memberRoleIds.length(); i++) {
                    Roles memberRole = RolesLocalServiceUtil.getRoles(memberRoleIds.getJSONObject(i).getInt("id"));
                    if (!result.contains(memberRole.getId())) {
                        result.add(memberRole.getId());
                    }
                }
            }
        }

        return result.isEmpty() ? null : result;
    }

    public static int getUserRoleGroup(long userId) throws SystemException {
        DynamicQuery query = UserRoleTypeLocalServiceUtil.dynamicQuery()
                .add(RestrictionsFactoryUtil.eq("UserId", userId))
                .add(RestrictionsFactoryUtil.eq("RowStatus", true))
                .setProjection(ProjectionFactoryUtil.property("RoleId"));
        List<Integer> roleIds = UserRoleTypeLocalServiceUtil.dynamicQuery(query);
        List<Integer> roleGroups = new ArrayList<>();
        for (Integer role : roleIds) {
            if (RoleUtils.isAdminDSO(role) && !roleGroups.contains(RolesEnum.ADMIN_DSO.getId()))
                roleGroups.add(RolesEnum.ADMIN_DSO.getId());
            else if (RoleUtils.isAdminDivision(role) && !roleGroups.contains(RolesEnum.ADMIN_DIVISION.getId()))
                roleGroups.add(RolesEnum.ADMIN_DIVISION.getId());
            else if (RoleUtils.isDSO(role) && !roleGroups.contains(RolesEnum.DSO.getId()))
                roleGroups.add(RolesEnum.DSO.getId());
            else if (RoleUtils.isHODealer(role) && !roleGroups.contains(RolesEnum.HO_DEALER.getId()))
                roleGroups.add(RolesEnum.HO_DEALER.getId());
            else if (RoleUtils.isDealer(role) && !roleGroups.contains(RolesEnum.DEALER.getId()))
                roleGroups.add(RolesEnum.DEALER.getId());
        }
        if (roleGroups.contains(RolesEnum.ADMIN_DSO.getId())) return RolesEnum.ADMIN_DSO.getId();
        else if (roleGroups.contains(RolesEnum.ADMIN_DIVISION.getId())) return RolesEnum.ADMIN_DIVISION.getId();
        else if (roleGroups.contains(RolesEnum.DSO.getId())) return RolesEnum.DSO.getId();
        else if (roleGroups.contains(RolesEnum.HO_DEALER.getId())) return RolesEnum.HO_DEALER.getId();
        else if (roleGroups.contains(RolesEnum.DEALER.getId())) return RolesEnum.DEALER.getId();
        else throw new SystemException();
    }

    public static String getUserRoleGroupName(int roleGroupId) {
        for (RolesEnum role : RolesEnum.values()) {
            if (role.getId() == roleGroupId) {
                return role.getName();
            }
        }
        return null;
    }

    public static boolean isSuperUser(long userId) {
        return DealinkUserUtil.hasRoleGroup(userId, DealinkUserUtil.SUPER_ROLES);
    }

    public static boolean isRegularUser(long userId) {
        return !DealinkUserUtil.hasRoleGroup(userId, DealinkUserUtil.SUPER_ROLES);
    }
}