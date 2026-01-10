package com.astra.dewa.utils;

import com.astra.dewa.exception.NoSuchDealerException;
import com.astra.dewa.model.Cabang;
import com.astra.dewa.model.Dealer;
import com.astra.dewa.service.CabangLocalServiceUtil;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DealerUtils {

    public static JSONArray selects() {
        JSONArray jsonData = JSONFactoryUtil.createJSONArray();
        DynamicQuery query = DealerLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
        List<Dealer> dealers = DealerLocalServiceUtil.dynamicQuery(query);
        dealers.forEach(data -> {
            JSONObject dto = JSONFactoryUtil.createJSONObject();
            dto.put("id", data.getId());
            dto.put("code", data.getCode());
            dto.put("text", data.getCode() + " - " + data.getName());
            jsonData.put(dto);
        });
        return jsonData;
    }

    public static JSONArray selects(int roleId, String hoCode, int dealerId) {
        JSONArray jsonData = JSONFactoryUtil.createJSONArray();
        DynamicQuery query = DealerLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
        List<Dealer> dealers = Collections.emptyList();
        if (roleId == RolesEnum.DSO.ordinal()) {
            dealers = DealerLocalServiceUtil.dynamicQuery(query);
        } else if (roleId == RolesEnum.HO_DEALER.ordinal()) {
            query.add(RestrictionsFactoryUtil.eq("KodeHo", hoCode));
            dealers = DealerLocalServiceUtil.dynamicQuery(query);
        } else if (roleId == RolesEnum.DEALER.ordinal()) {
            query.add(RestrictionsFactoryUtil.eq("Id", dealerId));
            dealers = DealerLocalServiceUtil.dynamicQuery(query);
        }
        dealers.forEach(data -> {
            JSONObject dto = JSONFactoryUtil.createJSONObject();
            dto.put("id", data.getId());
            dto.put("code", data.getCode());
            dto.put("text", data.getCode() + " - " + data.getName());
            jsonData.put(dto);
        });
        return jsonData;
    }

    public static JSONArray selectAll(int roleId, String hoCode, int dealerId) {
        JSONArray jsonData = JSONFactoryUtil.createJSONArray();
        DynamicQuery query = DealerLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
        List<Dealer> dealers = Collections.emptyList();
        if (roleId == RolesEnum.DSO.ordinal()) {
            dealers = DealerLocalServiceUtil.dynamicQuery(query);
        } else if (roleId == RolesEnum.HO_DEALER.ordinal()) {
//         query.add(RestrictionsFactoryUtil.eq("HoCode", hoCode));
            query.add(RestrictionsFactoryUtil.eq("KodeHo", hoCode));
            dealers = DealerLocalServiceUtil.dynamicQuery(query);
        } else if (roleId == RolesEnum.DEALER.ordinal()) {
            query.add(RestrictionsFactoryUtil.eq("Id", dealerId));
            dealers = DealerLocalServiceUtil.dynamicQuery(query);
        }
        dealers.forEach(data -> {
            JSONObject dto = JSONFactoryUtil.createJSONObject();
            dto.put("id", data.getId());
            dto.put("text", data.getName());
            jsonData.put(dto);
        });
        return jsonData;
    }

    public static JSONArray selectAllDealers() {
        JSONArray jsonData = JSONFactoryUtil.createJSONArray();
        DynamicQuery query = DealerLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
        List<Dealer> dealers = DealerLocalServiceUtil.dynamicQuery(query);
        dealers.forEach(data -> {
            JSONObject dto = JSONFactoryUtil.createJSONObject();
            dto.put("id", data.getId());
            dto.put("text", data.getName());
            jsonData.put(dto);
        });
        return jsonData;
    }

    public static JSONArray selectGroupDealer(int groupDealer) throws SystemException {
        JSONArray jsonData = JSONFactoryUtil.createJSONArray();
        DynamicQuery query = DealerLocalServiceUtil.dynamicQuery()
                .add(RestrictionsFactoryUtil.eq("RowStatus", true))
                .add(RestrictionsFactoryUtil.eq("GroupDealer", groupDealer));
        List<Dealer> dealers = DealerLocalServiceUtil.dynamicQuery(query);
        dealers.forEach(data -> {
            JSONObject dto = JSONFactoryUtil.createJSONObject();
            dto.put("id", data.getId());
            dto.put("text", data.getName());
            jsonData.put(dto);
        });
        return jsonData;
    }

    public static JSONArray selectGroupDealerDetail(int groupDealer) {
        JSONArray jsonData = JSONFactoryUtil.createJSONArray();
        DynamicQuery query = DealerLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
        query.add(RestrictionsFactoryUtil.eq("GroupDealer", groupDealer));
        List<Dealer> dealers = DealerLocalServiceUtil.dynamicQuery(query);
        dealers.forEach(data -> {
            JSONObject dto = JSONFactoryUtil.createJSONObject();
            dto.put("id", data.getId());
            dto.put("text", data.getKodeHo() + " - " + data.getName());
            jsonData.put(dto);
        });
        return jsonData;
    }

    public static JSONArray selectDealerByGroupId(int groupId) {
        JSONArray jsonData = JSONFactoryUtil.createJSONArray();
        DynamicQuery query = DealerLocalServiceUtil.dynamicQuery()
                .add(RestrictionsFactoryUtil.eq("GroupDealer", groupId))
                .add(RestrictionsFactoryUtil.eq("RowStatus", true));
        List<Dealer> dealers = DealerLocalServiceUtil.dynamicQuery(query);
        dealers.forEach(data -> {
            try {
                JSONObject dto = JSONFactoryUtil.createJSONObject()
                        .put("id", data.getId())
                        .put("text", data.getKodeHo() + " - " + data.getCode() + " - " + data.getName());
                jsonData.put(dto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return jsonData;
    }

    public static Dealer singleId(int id) throws PortalException {
        return DealerLocalServiceUtil.getDealer(id);
    }

    public static String getDealerName(String dealerName) {
        // 5200005724 - PT. NUSANTARA INDAH
        String[] dealerNames = dealerName.split("- ");
        return dealerNames[dealerNames.length - 1];
    }

    public static String getDealerCode(int dealerId) {
        DynamicQuery query = DealerLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq("Id", dealerId));
        query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
        List<Dealer> dealers = new ArrayList<>();
        dealers = DealerLocalServiceUtil.dynamicQuery(query);
        return dealers.get(0).getCode();
    }

    public static int getDealerIdByCode(String dealerCode) {
        DynamicQuery query = DealerLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq("Code", dealerCode));
        query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
        List<Dealer> dealers = new ArrayList<>();
        dealers = DealerLocalServiceUtil.dynamicQuery(query);
        return dealers.get(0).getId();
    }

    public static String getDealerNameByCode(String dealerCode) {
        DynamicQuery query = DealerLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq("Code", dealerCode));
        query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
        List<Dealer> dealers = new ArrayList<>();
        dealers = DealerLocalServiceUtil.dynamicQuery(query);
        return dealers.get(0).getName();
    }

    public static JSONArray selectDealerAndCabang() {
        JSONArray jsonData = JSONFactoryUtil.createJSONArray();
        DynamicQuery query = DealerLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
        List<Dealer> dealers = DealerLocalServiceUtil.dynamicQuery(query);
        dealers.forEach(data -> {
            try {
                Cabang cabang = CabangLocalServiceUtil.getCabang(data.getCabangId());
                JSONObject dto = JSONFactoryUtil.createJSONObject();
                dto.put("id", data.getId());
                dto.put("text", data.getName() + " - " + cabang.getName());
                jsonData.put(dto);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        return jsonData;
    }

    public static JSONArray selectCodeNameAndCabang() {
        JSONArray jsonData = JSONFactoryUtil.createJSONArray();
        DynamicQuery query = DealerLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
        List<Dealer> dealers = DealerLocalServiceUtil.dynamicQuery(query);
        dealers.forEach(data -> {
            try {
                Cabang cabang = CabangLocalServiceUtil.getCabang(data.getCabangId());
                JSONObject dto = JSONFactoryUtil.createJSONObject();
                dto.put("id", data.getId());
                dto.put("text", data.getCode() + " - " + data.getName() + " - " + cabang.getName());
                jsonData.put(dto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return jsonData;
    }

    public static JSONArray selectCodeNameAndCabang(int roleGroupId, String hoCode, int dealerId) {
        JSONArray jsonData = JSONFactoryUtil.createJSONArray();
        DynamicQuery query = DealerLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
        List<Dealer> dealers = Collections.emptyList();
        if (roleGroupId == RolesEnum.ADMIN_DSO.getId() || roleGroupId == RolesEnum.DSO.getId()) {
            dealers = DealerLocalServiceUtil.dynamicQuery(query);
        } else if (roleGroupId == RolesEnum.HO_DEALER.getId()) {
            query.add(RestrictionsFactoryUtil.eq("KodeHo", hoCode));
            dealers = DealerLocalServiceUtil.dynamicQuery(query);
        } else if (roleGroupId == RolesEnum.DEALER.getId()) {
            query.add(RestrictionsFactoryUtil.eq("Id", dealerId));
            dealers = DealerLocalServiceUtil.dynamicQuery(query);
        }
        dealers.forEach(data -> {
            try {
                Cabang cabang = CabangLocalServiceUtil.getCabang(data.getCabangId());
                JSONObject dto = JSONFactoryUtil.createJSONObject();
                dto.put("id", data.getId());
                dto.put("text", data.getCode() + " - " + data.getName() + " - " + cabang.getName());
                jsonData.put(dto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return jsonData;
    }

    public static JSONArray selectDetailDealer() {
        JSONArray jsonData = JSONFactoryUtil.createJSONArray();
        DynamicQuery query = DealerLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
        List<Dealer> dealers = DealerLocalServiceUtil.dynamicQuery(query);
        dealers.forEach(data -> {
            try {
                JSONObject dto = JSONFactoryUtil.createJSONObject();
                dto.put("id", data.getId());
                dto.put("text", data.getKodeHo() + " - " + data.getCode() + " - " + data.getName());
                jsonData.put(dto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return jsonData;
    }

    public static Dealer getDealer(String kodeHo, int cabangId) throws NoSuchDealerException {
        DynamicQuery query = DealerLocalServiceUtil.dynamicQuery()
                .add(RestrictionsFactoryUtil.eq("KodeHo", kodeHo))
                .add(RestrictionsFactoryUtil.eq("CabangId", cabangId))
                .add(RestrictionsFactoryUtil.eq("RowStatus", true));
        List<Dealer> dealers = DealerLocalServiceUtil.dynamicQuery(query);
        return dealers.isEmpty() ? null : dealers.get(0);
    }

    public static Dealer getDealer(int dealerId, Boolean rowStatus) {
        DynamicQuery q = DealerLocalServiceUtil
                .dynamicQuery()
                .add(RestrictionsFactoryUtil.eq("Id", dealerId));
        if (Validator.isNotNull(rowStatus)) {
            q.add(RestrictionsFactoryUtil.eq("RowStatus", rowStatus));
        }
        List<Dealer> r = DealerLocalServiceUtil.dynamicQuery(q);
        return r.isEmpty() ? null : r.get(0);
    }

    /**
     * Get the list of branch dealers under current HO dealer.
     *
     * @param hoDealerId The primary key of the HO dealer.
     * @return The list of branch dealers.
     * @throws PortalException When no dealer exists with current key.
     */
    public static List<Dealer> getBranchDealers(int hoDealerId) throws PortalException {
        Dealer dealer = DealerLocalServiceUtil.getDealer(hoDealerId);
        DynamicQuery q = DealerLocalServiceUtil
                .dynamicQuery()
                .add(RestrictionsFactoryUtil.eq("KodeHo", dealer.getKodeHo()))
                .add(RestrictionsFactoryUtil.eq("RowStatus", true));
        List<Dealer> r = DealerLocalServiceUtil.dynamicQuery(q);
        return r.isEmpty() ? new ArrayList<>() : r;
    }
}
