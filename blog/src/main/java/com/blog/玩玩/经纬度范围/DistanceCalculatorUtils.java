package com.blog.玩玩.经纬度范围;


import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

/**
 * @author: tanzhenxiang
 * @date: 2024/6/27
 * @description:获取所有能在自定义范围内的所有经纬度组合
 */
public class DistanceCalculatorUtils {

        private static final double EARTH_RADIUS = 6371.0; // 地球半径，单位公里
        private static final ObjectMapper objectMapper = new ObjectMapper();

        static class Location {
            public String ORDER_ID;
            public double LONGITUDE;
            public double LATITUDE;

            public Location() {}

            public String getORDER_ID() {
                return ORDER_ID;
            }

            public void setORDER_ID(String ORDER_ID) {
                this.ORDER_ID = ORDER_ID;
            }

            public double getLONGITUDE() {
                return LONGITUDE;
            }

            public void setLONGITUDE(double LONGITUDE) {
                this.LONGITUDE = LONGITUDE;
            }

            public double getLATITUDE() {
                return LATITUDE;
            }

            public void setLATITUDE(double LATITUDE) {
                this.LATITUDE = LATITUDE;
            }
        }

        // 计算两点之间的距离并检查是否在5公里范围内
        public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
            double radLat1 = Math.toRadians(lat1);
            double radLon1 = Math.toRadians(lon1);
            double radLat2 = Math.toRadians(lat2);
            double radLon2 = Math.toRadians(lon2);

            double deltaLat = radLat2 - radLat1;
            double deltaLon = radLon2 - radLon1;


            double a = Math.pow(Math.sin(deltaLat / 2), 2)
                    + Math.cos(radLat1) * Math.cos(radLat2)
                    * Math.pow(Math.sin(deltaLon / 2), 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

            return EARTH_RADIUS * c;
        }

    // 检查两点是否在5公里范围内
    public static boolean isWithin5Km(double lat1, double lon1, double lat2, double lon2, int radius) {
        double distance = calculateDistance(lat1, lon1, lat2, lon2);
        return distance <= radius;
    }

    public static List<JSONArray> findCloseLocations(String jsonInput,int radius) throws Exception {
        List<Location> locations = objectMapper.readValue(jsonInput, new TypeReference<List<Location>>(){});
        List<JSONArray> closeLocationGroups = new ArrayList<>();
        // 遍历每个位置
        for (Location loc : locations) {
            JSONArray group = new JSONArray();
            group.put(new JSONObject()
                    .put("ORDER_ID", loc.getORDER_ID())
                    .put("LONGITUDE", loc.getLONGITUDE())
                    .put("LATITUDE", loc.getLATITUDE()));
            // 遍历其他位置
            for (Location otherLoc : locations) {
                if (!loc.getORDER_ID().equals(otherLoc.getORDER_ID()) &&
                        isWithin5Km(loc.getLATITUDE(), loc.getLONGITUDE(),
                                otherLoc.getLATITUDE(), otherLoc.getLONGITUDE(),radius)) {
                    group.put(new JSONObject()
                            .put("ORDER_ID", otherLoc.getORDER_ID())
                            .put("LONGITUDE", otherLoc.getLONGITUDE())
                            .put("LATITUDE", otherLoc.getLATITUDE()));
                }
            }
            //有两个经纬度的组合才添加
            if (group.size() > 1) {
                closeLocationGroups.add(group);
            }
        }
        return closeLocationGroups;
    }
    public static  List<JSONArray> getAllLocationsCombination(String jsonInput,int radius) throws Exception {
        List<JSONArray> jsonArrays;
        jsonArrays = findCloseLocations(jsonInput.toString(),radius);
        //过滤重复组合
        if (jsonArrays.size() > 1){
            jsonArrays = processJSONArrayList(jsonArrays);
        }
        return jsonArrays;
    }

        public static void main(String[] args) {
            String jsonInput = "[\n" +
                    "  {\n" +
                    "    \"ORDER_ID\": \"1\",\n" +
                    "    \"LONGITUDE\": \"-118.243683\",\n" +
                    "    \"LATITUDE\": \"34.052235\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"ORDER_ID\": \"2\",\n" +
                    "    \"LONGITUDE\": \"-118.243000\",  \n" +
                    "    \"LATITUDE\": \"34.052000\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"ORDER_ID\": \"3\",\n" +
                    "    \"LONGITUDE\": \"-119.232976\",\n" +
                    "    \"LATITUDE\": \"34.056220\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"ORDER_ID\": \"5\",\n" +
                    "    \"LONGITUDE\": \"-119.232977\",\n" +
                    "    \"LATITUDE\": \"34.056220\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"ORDER_ID\": \"4\",\n" +
                    "    \"LONGITUDE\": \"-118.231000\",  \n" +
                    "    \"LATITUDE\": \"34.057000\"\n" +
                    "  }\n" +
                    "]";

            try {
                com.alibaba.fastjson.JSONArray jsonArray = com.alibaba.fastjson.JSONArray.parseArray(jsonInput);
                List<JSONArray> allLocationsCombination = getAllLocationsCombination(jsonArray.toString(),5);
                System.out.println(allLocationsCombination);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    public static List<JSONArray> processJSONArrayList(List<JSONArray> inputArrays) {
        Map<String, JSONArray> uniqueCombinations = new LinkedHashMap<>();

        for (JSONArray jsonArray : inputArrays) {
            try {
                List<JSONObject> jsonObjects = new ArrayList<>();
                for (int i = 0; i < jsonArray.size(); i++) {
                    jsonObjects.add(jsonArray.getJSONObject(i));
                }
                //排序
                jsonObjects.sort(Comparator.comparing(obj -> obj.getStr("ORDER_ID")));

                StringBuilder keyBuilder = new StringBuilder();
                for (JSONObject obj : jsonObjects) {
                    keyBuilder.append(obj.getStr("ORDER_ID")).append("-");
                }
                String key = keyBuilder.toString();

              //过滤重复值
                if (!uniqueCombinations.containsKey(key)) {
                    JSONArray uniqueJSONArray = new JSONArray();
                    for (JSONObject obj : jsonObjects) {
                        uniqueJSONArray.add(obj);
                    }
                    uniqueCombinations.put(key, uniqueJSONArray);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return new ArrayList<>(uniqueCombinations.values());
    }
    }
