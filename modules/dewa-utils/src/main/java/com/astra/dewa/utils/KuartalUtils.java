package com.astra.dewa.utils;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class KuartalUtils {

    private static Map<String, String> kuartalList() {
        HashMap<String, String> kuartalMap = new HashMap<>();
        for (int i = 1; i <= 4; i++) {
            kuartalMap.put("kuartal" + i, "Kuartal " + i);
        }
        TreeMap<String, String> sortedKuartalMap = new TreeMap<>();
        sortedKuartalMap.putAll(kuartalMap);
        return sortedKuartalMap;
    }

    public static JSONArray getKuartalList() {
        JSONArray kuartalArray = JSONFactoryUtil.createJSONArray();
        Map<String, String> kuartalMap = kuartalList();
        for (Map.Entry<String, String> kuartal : kuartalMap.entrySet()) {
            JSONObject kuartalList = JSONFactoryUtil.createJSONObject();
            kuartalList.put("id", kuartal.getKey());
            kuartalList.put("text", kuartal.getValue());
            kuartalArray.put(kuartalList);
        }
        return kuartalArray;
    }

    public static String getKuartalName(String kuartalId) {
        Map<String, String> kuartalMap = kuartalList();
        String kuartalName = kuartalMap.get(kuartalId);
        return kuartalName;
    }

    public static Map<Integer, String> getQuarters() {
        Map<Integer, String> quarterMap = new HashMap<>();
        for (int i = 1; i <= 4; i++) {
            quarterMap.put(i, "Kuartal " + i);
        }
        return quarterMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    public static String getQuarterName(int i) {
        Map<Integer, String> quarterMap = getQuarters();
        return quarterMap.getOrDefault(i, null);
    }

    public static int getQuarterId(String s) {
        Map<Integer, String> qm = getQuarters();
        for (Map.Entry<Integer, String> entry : qm.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(s)) {
                return entry.getKey();
            }
        }
        return -1;
    }
}