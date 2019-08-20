package com.fjut.oj.util;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class MapSort {
    /**
     * 使用 Map按key进行排序
     *
     * @param map
     * @return
     */
    public static Map<String, Integer> sortMapByKey(Map<String, Integer> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        Map<String, Integer> sortMap = new TreeMap<String, Integer>(
                new MapKeyComparator());

        sortMap.putAll(map);

        return sortMap;
    }
}


class MapKeyComparator implements Comparator<String> {

    @Override
    public int compare(String str1, String str2) {

        return str1.compareTo(str2);
    }
}
