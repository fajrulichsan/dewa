package com.astra.dewa.utils;

import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.UsersDealers;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.utils.user.DealinkUserUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * @author psmahmad1402
 */
public class CustomDealerUtil {
    /**
     * Retrieves the list of dealers by User dealer group.
     *
     * @param user The user object contains foreign keys.
     * @return The list of authorized dealer for specified user.
     * @throws NullPointerException if specified User has no active roles.
     * @throws PortalException      if an unexpected error occurs during transaction.
     */
    public static List<Dealer> getDealersByUserGroup(UsersDealers user) throws PortalException {
        // general query
        DynamicQuery q = DealerLocalServiceUtil
                .dynamicQuery()
                .add(RestrictionsFactoryUtil.eq("RowStatus", true));
        // user role permission
        RolesEnum userRoleGroup = DealinkUserUtil.getUserRoleGroupEnum(user.getUserId());
        if (null == userRoleGroup) {
            throw new NullPointerException("The selected user has no active roles.");
        }
        // restrict dealer by user role permission
        switch (userRoleGroup) {
            case HO_DEALER:
                Dealer dealer = DealerLocalServiceUtil.getDealer(user.getDealerId());
                DynamicQuery dealersQuery = DealerLocalServiceUtil
                        .dynamicQuery()
                        .add(RestrictionsFactoryUtil.eq("KodeHo", dealer.getKodeHo()))
                        .add(RestrictionsFactoryUtil.eq("RowStatus", true))
                        .setProjection(PropertyFactoryUtil.forName("Id"));
                q.add(PropertyFactoryUtil.forName("Id").in(dealersQuery));
                break;
            case DEALER:
                q.add(RestrictionsFactoryUtil.eq("Id", user.getDealerId()));
                break;
        }
        // result
        List<Dealer> r = DealerLocalServiceUtil.dynamicQuery(q);
        return r.isEmpty() ? null : r;
    }
}
