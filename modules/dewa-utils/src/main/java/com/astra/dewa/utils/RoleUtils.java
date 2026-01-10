package com.astra.dewa.utils;

import com.astra.dewa.exception.NoSuchRolesException;
import com.astra.dewa.model.MenuAuthorization;
import com.astra.dewa.model.Roles;
import com.astra.dewa.model.UsersDealers;
import com.astra.dewa.service.MenuAuthorizationLocalServiceUtil;
import com.astra.dewa.service.RolesLocalServiceUtil;
import com.astra.dewa.service.UserRoleTypeLocalServiceUtil;
import com.astra.dewa.service.UsersDealersLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

public class RoleUtils {

   private static final Log log = LogFactory.getLog(RoleUtils.class);

   public static boolean isLiferayAdmin(long userId) throws PortalException {
      User user = UserLocalServiceUtil.getUser(userId);
      List<Role> userRoles = user.getRoles();
      List<String> userRolesString = new ArrayList<>();
      if (!userRoles.isEmpty()) {
         for (Role role : userRoles) {
            userRolesString.add(role.getName());
         }
      }
      return userRolesString.contains(RolesEnum.ADMIN_DSO.getName());
   }

   /**
    * This method is used to get all active roles.
    * @return The list of active roles.
    * @throws SystemException If an error occurs during the process.
    */
   public static JSONArray selects() throws SystemException {
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      DynamicQuery query = RolesLocalServiceUtil.dynamicQuery()
            .add(RestrictionsFactoryUtil.eq("RowStatus", true));
      List<Roles> roles = RolesLocalServiceUtil.dynamicQuery(query);
      roles.forEach(data -> {
         JSONObject dto = JSONFactoryUtil.createJSONObject();
         dto.put("id", data.getId());
         dto.put("text", data.getName());
         dto.put("isSuperRole", isAdminDSO(data.getId()) || isDSO(data.getId()));
         jsonData.put(dto);
      });
      return jsonData;
   }

   /**
    * This method is used to get all active super roles, i.e. Administrator and DSO.
    * @return The list of active super roles.
    * @throws SystemException If an error occurs during the process.
    */
   public static JSONArray selectSuperRole() throws SystemException {
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      DynamicQuery query = RolesLocalServiceUtil.dynamicQuery()
            .add(RestrictionsFactoryUtil.or(
                  RestrictionsFactoryUtil.like("Name", RolesEnum.ADMIN_DIVISION.getName() + "%"),
                  RestrictionsFactoryUtil.like("Name", RolesEnum.DSO.getName() + "%")
            ))
            .add(RestrictionsFactoryUtil.eq("RowStatus", true))
            .addOrder(OrderFactoryUtil.asc("Name"));
      List<Roles> result = RolesLocalServiceUtil.dynamicQuery(query);
      result.forEach(data -> {
         JSONObject dto = JSONFactoryUtil.createJSONObject()
               .put("id", data.getId())
               .put("text", data.getName());
         jsonData.put(dto);
      });
      return jsonData;
   }

   public static Integer getUserRole(Long userId) {
      int roleId = 0;
      try {
         DynamicQuery query = UserRoleTypeLocalServiceUtil.dynamicQuery()
               .add(RestrictionsFactoryUtil.eq("UserId", userId))
               .add(RestrictionsFactoryUtil.eq("RowStatus", true))
               .setProjection(ProjectionFactoryUtil.property("RoleId"));
         List<Integer> result = UserRoleTypeLocalServiceUtil.dynamicQuery(query);
         roleId = result.get(0);
      } catch (Exception e) {
         e.printStackTrace();
      }
      return roleId;
   }

   public static boolean isAdminDSO(int roleId) {
      try {
         Roles role = RolesLocalServiceUtil.getRoles(roleId);
         return role.getId() == RolesEnum.ADMIN_DSO.getId();
      } catch (PortalException e) {
         return false;
      }
   }

   public static boolean isAdminDivision(int roleId) {
      try {
         Roles role = RolesLocalServiceUtil.getRoles(roleId);
         return role.getId() != RolesEnum.ADMIN_DSO.getId() &&
               role.getName().toLowerCase().startsWith(RolesEnum.ADMIN_DIVISION.getName().toLowerCase());
      } catch (PortalException e) {
         return false;
      }
   }

   public static boolean isDSO(int roleId) {
      try {
         Roles role = RolesLocalServiceUtil.getRoles(roleId);
         return role.getName().toLowerCase().startsWith(RolesEnum.DSO.getName().toLowerCase());
      } catch (PortalException e) {
         return false;
      }
   }

   public static boolean isHODealer(int roleId) {
      try {
         Roles role = RolesLocalServiceUtil.getRoles(roleId);
         return role.getName().toLowerCase().startsWith(RolesEnum.HO_DEALER.getName().toLowerCase());
      } catch (PortalException e) {
         return false;
      }
   }

   public static boolean isDealer(int roleId) {
      try {
         Roles role = RolesLocalServiceUtil.getRoles(roleId);
         return role.getName().toLowerCase().startsWith(RolesEnum.DEALER.getName().toLowerCase());
      } catch (PortalException e) {
         return false;
      }
   }

   public static boolean isRsspRole(int roleId) throws SystemException {
      DynamicQuery query = MenuAuthorizationLocalServiceUtil.dynamicQuery()
            .add(RestrictionsFactoryUtil.eq("RoleId", roleId))
            .add(RestrictionsFactoryUtil.eq("RowStatus", true));
      List<MenuAuthorization> roles = MenuAuthorizationLocalServiceUtil.dynamicQuery(query);
      if (roles.isEmpty())
         return false;
      return roles.get(0).getIsRssp();
   }

   public static boolean isCmsDsoRole(int roleId) {
      DynamicQuery query = MenuAuthorizationLocalServiceUtil.dynamicQuery()
              .add(RestrictionsFactoryUtil.eq("RoleId", roleId))
              .add(RestrictionsFactoryUtil.eq("RowStatus", true));
      List<MenuAuthorization> roles = MenuAuthorizationLocalServiceUtil.dynamicQuery(query);
      return !roles.isEmpty() && roles.get(0).getIsCmsDso();
   }

   /**
    * This method is used to get the department name of the DSO roles.
    * @param roleId The ID of selected role.
    * @return The department name of the role as a string.
    */
   public static String getDepartmetName(int roleId) {
      try {
         Roles role = RolesLocalServiceUtil.getRoles(roleId);
         if (isDSO(roleId)) {
            String[] roleProps = role.getName().split("\\s*-\\s*");
            return roleProps[1];
         } else {
            return role.getName();
         }
      } catch (PortalException e) {
         log.error(e);
         return null;
      }
   }

   public static boolean isSameDepartment(long userId, int roleId) {
      DynamicQuery query = UsersDealersLocalServiceUtil.dynamicQuery();
      query.add(RestrictionsFactoryUtil.eq("UserId", userId));
      query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
      List<UsersDealers> roleDealers = UsersDealersLocalServiceUtil.dynamicQuery(query);
      if (roleDealers.isEmpty()) return true;

      boolean isInDepartment = false;
      for (UsersDealers data : roleDealers) {
         if ((isAdminDSO(data.getRoleId()) && isDSO(roleId)) ||
               (isAdminDSO(roleId) && isDSO(data.getRoleId())) ||
               getDepartmetName(data.getRoleId()).equalsIgnoreCase(getDepartmetName(roleId))
         ) {
            isInDepartment = true;
         } else if (!getDepartmetName(data.getRoleId()).equalsIgnoreCase(getDepartmetName(roleId))) {
            isInDepartment = false;
            break;
         }
      }
      return isInDepartment;
   }

   /**
    * @param userId
    * @return total role those user has
    * @throws SystemException
    */
   public static long countUserRole(long userId) throws SystemException {
      DynamicQuery query = UsersDealersLocalServiceUtil.dynamicQuery()
            .add(RestrictionsFactoryUtil.eq("UserId", userId))
            .add(RestrictionsFactoryUtil.eq("RowStatus", true));
      return UsersDealersLocalServiceUtil.dynamicQueryCount(query);
   }

   public static String getRoleGroupName(int roleId) {
      try {
         if (isAdminDSO(roleId) || isAdminDivision(roleId)) return RolesEnum.ADMIN_DSO.getName();
         else if (isDSO(roleId)) return RolesEnum.DSO.getName();
         else if (isHODealer(roleId)) return RolesEnum.HO_DEALER.getName();
         else if (isDealer(roleId)) return RolesEnum.DEALER.getName();
         else throw new NoSuchRolesException();
      } catch (NoSuchRolesException e) {
         log.error(e);
         return null;
      }
   }

   public static int getRoleGroupId(int roleId) {
      try {
         if (isAdminDSO(roleId) || isAdminDivision(roleId)) return RolesEnum.ADMIN_DSO.getId();
         else if (isDSO(roleId)) return RolesEnum.DSO.getId();
         else if (isHODealer(roleId)) return RolesEnum.HO_DEALER.getId();
         else if (isDealer(roleId)) return RolesEnum.DEALER.getId();
         else throw new NoSuchRolesException();
      } catch (NoSuchRolesException e) {
         log.error(e);
         return -1;
      }
   }

   public static boolean isRoleGroupValid(JSONArray roles) {
      int i = -1;
      String initRole = "";
      while (++i < roles.length()) {
         int roleId = roles.getJSONObject(i).getInt("id");
         if (i == 0) {
            initRole = getRoleGroupName(roleId);
         } else {
            assert initRole != null;
            if (!initRole.equalsIgnoreCase(getRoleGroupName(roleId))) {
               return false;
            }
         }
      }
      return true;
   }

   public static Roles getRole(int id) {
      DynamicQuery query = RolesLocalServiceUtil.dynamicQuery()
            .add(RestrictionsFactoryUtil.eq("Id", id))
            .add(RestrictionsFactoryUtil.eq("RowStatus", true));
      List<Roles> result = RolesLocalServiceUtil.dynamicQuery(query);
      return result.isEmpty() ? null : result.get(0);
   }

   public static String getRoleName(int roleId) throws SystemException {
      DynamicQuery query = RolesLocalServiceUtil.dynamicQuery();
      query.add(RestrictionsFactoryUtil.eq("Id", roleId));
      List<Roles> roleName = RolesLocalServiceUtil.dynamicQuery(query);
      if (!roleName.isEmpty()) {
         return roleName.get(0).getName();
      } else {
         return "";
      }
   }

   public static JSONArray selectRoleByGroup(int groupId) throws SystemException {
      String groupName = "";
      JSONArray result = JSONFactoryUtil.createJSONArray();
      if (GroupDealerEnum.HO_DEALER.ordinal() == groupId) groupName = RolesEnum.HO_DEALER.getName();
      else if (GroupDealerEnum.DEALER_CABANG.ordinal() == groupId) groupName = RolesEnum.DEALER.getName();
      DynamicQuery query = RolesLocalServiceUtil.dynamicQuery()
            .add(RestrictionsFactoryUtil.like("Name", groupName + "%"))
            .add(RestrictionsFactoryUtil.eq("RowStatus", true));
      List<Roles> roles = RolesLocalServiceUtil.dynamicQuery(query);
      if (roles.isEmpty()) throw new SystemException();
      else {
         roles.forEach(role -> {
            JSONObject dto = JSONFactoryUtil.createJSONObject()
                  .put("id", role.getId())
                  .put("text", role.getName());
            result.put(dto);
         });
         return result;
      }
   }

   /**
    * This method is used to get the List of the role member IDs of the selected role.
    * @param roleId The ID of the specific role.
    * @return The list of role member ID in List<Integer> format.
    * @throws PortalException If an error occurs during the process.
    */
   public static List<Integer> getRoleIdMember(int roleId) throws PortalException {
      List<Integer> result = new ArrayList<>();
      Roles role = RolesLocalServiceUtil.getRoles(roleId);
      if (!role.getRoleIdMember().isEmpty()) {
         JSONArray memberRoleIds = JSONFactoryUtil.createJSONArray(role.getRoleIdMember());
         for (int i = 0; i < memberRoleIds.length(); i++) {
            Roles memberRole = RolesLocalServiceUtil.getRoles(memberRoleIds.getJSONObject(i).getInt("id"));
            if (!result.contains(memberRole.getId())) {
               result.add(memberRole.getId());
            }
         }
      }
      return result.isEmpty() ? null : result;
   }

   public static List<Integer> getRolesAsList(JSONArray roles) {
      List<Integer> result = new ArrayList<>();
      int i = -1;
      while (++i < roles.length()) {
         int roleId = roles.getJSONObject(i).getInt("id");
         result.add(roleId);
      }
      return result.isEmpty() ? null : result;
   }

   public static boolean isRoleValid(int roleId) throws NoSuchRolesException {
      DynamicQuery query = RolesLocalServiceUtil.dynamicQuery()
            .add(RestrictionsFactoryUtil.eq("Id", roleId))
            .add(RestrictionsFactoryUtil.eq("RowStatus", true));
      long result = RolesLocalServiceUtil.dynamicQueryCount(query);
      return result > 0;
   }
}
