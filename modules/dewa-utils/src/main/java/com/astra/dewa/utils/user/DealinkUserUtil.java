package com.astra.dewa.utils.user;

import com.astra.dewa.model.UserRoleType;
import com.astra.dewa.service.MenuAuthorizationLocalServiceUtil;
import com.astra.dewa.service.RolesLocalServiceUtil;
import com.astra.dewa.service.UserRoleTypeLocalServiceUtil;
import com.astra.dewa.utils.RolesEnum;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author psmahmad1402
 */
public class DealinkUserUtil {
    /**
     * The list of the super roles.
     */
    public static final RolesEnum[] SUPER_ROLES = {
            RolesEnum.ADMIN_DSO,
            RolesEnum.ADMIN_DIVISION,
            RolesEnum.DSO
    };

    /**
     * Retrieves the list of the User roles map.
     *
     * @param userId The unique identifier of the User.
     * @return The list of the User roles map.
     */
    public static List<UserRoleType> getUserRoleTypes(long userId) {
        DynamicQuery q = UserRoleTypeLocalServiceUtil.dynamicQuery()
                .add(RestrictionsFactoryUtil.eq("UserId", userId))
                .add(RestrictionsFactoryUtil.eq("RowStatus", true));
        List<UserRoleType> r = UserRoleTypeLocalServiceUtil.dynamicQuery(q);
        return r.isEmpty() ? new ArrayList<>() : r;
    }

    /**
     * Retrieves the RolesEnum with the User.
     *
     * @param userId the unique identifier of the User.
     * @return The RolesEnum object if found, otherwise null.
     */
    public static RolesEnum getUserRoleGroupEnum(long userId) {
        // get user roles
        List<UserRoleType> urtl = getUserRoleTypes(userId);
        if (urtl.isEmpty()) {
            return null;
        }
        List<Integer> urtil = urtl.stream().map(UserRoleType::getRoleId).collect(Collectors.toList());
        // get roles
        DynamicQuery rq = RolesLocalServiceUtil
                .dynamicQuery()
                .add(PropertyFactoryUtil.forName("Id").in(urtil))
                .setProjection(PropertyFactoryUtil.forName("Name"));
        List<String> ur = RolesLocalServiceUtil.dynamicQuery(rq);
        if (ur.isEmpty()) {
            return null;
        } else {
            if (ur.contains(RolesEnum.ADMIN_DSO.getName())) {
                return RolesEnum.ADMIN_DSO;
            } else {
                for (String r : ur) {
                    if (r.trim().toLowerCase().startsWith(RolesEnum.ADMIN_DIVISION.getName().trim().toLowerCase())) {
                        return RolesEnum.ADMIN_DIVISION;
                    } else if (r.trim().toLowerCase().startsWith(RolesEnum.DSO.getName().trim().toLowerCase())) {
                        return RolesEnum.DSO;
                    } else if (r.trim().toLowerCase().startsWith(RolesEnum.HO_DEALER.getName().trim().toLowerCase())) {
                        return RolesEnum.HO_DEALER;
                    } else if (r.trim().toLowerCase().startsWith(RolesEnum.DEALER.getName().trim().toLowerCase())) {
                        return RolesEnum.DEALER;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Checks whether the active themeDisplay has the Liferay administrator permission.
     *
     * @param themeDisplay the active theme display
     * @return True if the active themeDisplay has the Liferay administrator permission. <br>
     * False otherwise.
     */
    public static boolean isLiferayAdmin(ThemeDisplay themeDisplay) {
        return themeDisplay.getPermissionChecker().isOmniadmin() || themeDisplay.getPermissionChecker().isGroupAdmin(themeDisplay.getSiteGroupId());
    }

    /**
     * Checks whether the specified User has the permission role.
     *
     * @param userId    the unique identifier of the User.
     * @param rolesEnum the specified role.
     * @return True if the specified User has the permission role. <br>
     * False otherwise.
     */
    public static boolean hasRoleGroup(long userId, RolesEnum rolesEnum) {
        RolesEnum re = getUserRoleGroupEnum(userId);
        if (re == null) return false;
        return re == rolesEnum;
    }

    /**
     * Checks whether the specified User has at least one of the permission roles.
     *
     * @param userId        the unique identifier of the User.
     * @param rolesEnumList the list of specified roles.
     * @return True if the specified User has at least one of the permission roles. <br>
     * False otherwise.
     */
    public static boolean hasRoleGroup(long userId, RolesEnum[] rolesEnumList) {
        RolesEnum re = getUserRoleGroupEnum(userId);
        if (re == null) return false;
        for (RolesEnum role : rolesEnumList) {
            if (re == role) return true;
        }
        return false;
    }

    /**
     * Check whether the specified User has role authorized as RSSP integration role.
     *
     * @param userId the unique identifier of the User.
     * @return True if the specified User has authorized RSSP role.
     */
    public static boolean hasRsspRole(long userId) {
        // get user roles
        List<UserRoleType> urtl = getUserRoleTypes(userId);
        if (urtl.isEmpty()) {
            return false;
        }
        List<Integer> urtil = urtl.stream().map(UserRoleType::getRoleId).collect(Collectors.toList());
        // menu authorization query
        DynamicQuery menuQuery = MenuAuthorizationLocalServiceUtil.dynamicQuery()
                .add(RestrictionsFactoryUtil.in("RoleId", urtil))
                .add(RestrictionsFactoryUtil.eq("IsRssp", true))
                .add(RestrictionsFactoryUtil.eq("RowStatus", true));
        long count = MenuAuthorizationLocalServiceUtil.dynamicQueryCount(menuQuery);
        return count > 0;
    }

    /**
     * Check whether the specified User has role authorized as CMS DSO integration role.
     *
     * @param userId the unique identifier of the User.
     * @return True if the specified User has authorized CMS DSO role.
     */
    public static boolean hasCmsDsoRole(long userId) {
        // get user roles
        List<UserRoleType> urtl = getUserRoleTypes(userId);
        if (urtl.isEmpty()) {
            return false;
        }
        List<Integer> urtil = urtl.stream().map(UserRoleType::getRoleId).collect(Collectors.toList());
        // menu authorization query
        DynamicQuery menuQuery = MenuAuthorizationLocalServiceUtil.dynamicQuery()
                .add(RestrictionsFactoryUtil.in("RoleId", urtil))
                .add(RestrictionsFactoryUtil.eq("IsCmsDso", true))
                .add(RestrictionsFactoryUtil.eq("RowStatus", true));
        long count = MenuAuthorizationLocalServiceUtil.dynamicQueryCount(menuQuery);
        return count > 0;
    }
}
