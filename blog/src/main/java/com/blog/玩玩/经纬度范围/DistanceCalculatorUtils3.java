package com.blog.玩玩.经纬度范围;

/**
 * @description:求经纬度范围
 * @create: 2024-06-26 16:06
 * @author:tanzhenxiang
 * @version: 1.0
 */
//public class DistanceCalculatorUtils {
//
//    private static final double EARTH_RADIUS = 6371.0; // 地球半径，单位公里
//
//    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
//
//        double radLat1 = Math.toRadians(lat1);
//        double radLon1 = Math.toRadians(lon1);
//        double radLat2 = Math.toRadians(lat2);
//        double radLon2 = Math.toRadians(lon2);
//
//        double deltaLat = radLat2 - radLat1;
//        double deltaLon = radLon2 - radLon1;
//
//
//        double a = Math.pow(Math.sin(deltaLat / 2), 2)
//                + Math.cos(radLat1) * Math.cos(radLat2)
//                * Math.pow(Math.sin(deltaLon / 2), 2);
//        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
//
//        return EARTH_RADIUS * c;
//    }
//
//    public static boolean isWithin5Km(double lat1, double lon1, double lat2, double lon2,int radius) {
//        double distance = calculateDistance(lat1, lon1, lat2, lon2);
//        return distance <= radius;
//    }
//    public static boolean isWithin5Km( double[][] points,int radius){
//        boolean allWithin5Km = true;
//        for (int i = 0; i < points.length; i++) {
//            for (int j = i + 1; j < points.length; j++) {
//                if (!isWithin5Km(points[i][0], points[i][1], points[j][0], points[j][1],radius)) {
//                    allWithin5Km = false;
//                    break;
//                }
//            }
//        }
//        return allWithin5Km;
//    }
//
//    public static void main(String[] args) {
//
//        double lat1 = 23.126486;
//        double lon1 = 113.269765;
//        double lat2 = 23.126486;
//        double lon2 = 113.269765;
//        double lat3 = 23.126486;
//        double lon3 = 113.269765;
//
//
//        double[][] points = {
//                {lat1, lon1},
//                {lat2, lon2},
//                {lat3, lon3}
//        };
//        boolean within5Km = isWithin5Km(points,5);
//        if (within5Km) {
//            System.out.println("所有点都在5公里内");
//        } else {
//            System.out.println("不是所有点都在5公里内");
//        }
//    }
//}


//public class DistanceCalculatorUtils {
//
//    private static final double EARTH_RADIUS = 6371.0; // 地球半径，单位公里
//
//    // 计算两点之间的距离
//    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
//
//        double radLat1 = Math.toRadians(lat1);
//        double radLon1 = Math.toRadians(lon1);
//        double radLat2 = Math.toRadians(lat2);
//        double radLon2 = Math.toRadians(lon2);
//
//        double deltaLat = radLat2 - radLat1;
//        double deltaLon = radLon2 - radLon1;
//
//
//        double a = Math.pow(Math.sin(deltaLat / 2), 2)
//                + Math.cos(radLat1) * Math.cos(radLat2)
//                * Math.pow(Math.sin(deltaLon / 2), 2);
//        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
//
//        return EARTH_RADIUS * c;
//    }
//
//    // 检查两点是否在5公里范围内
//    public static boolean isWithin5Km(double lat1, double lon1, double lat2, double lon2) {
//        double distance = calculateDistance(lat1, lon1, lat2, lon2);
//        return distance <= 5;
//    }
//
//    // 找到并返回所有在5公里范围内的点的组合
//    public static String findClosePoints(double[][] points, int radius) {
//        StringBuilder result = new StringBuilder();
//        for (int i = 0; i < points.length; i++) {
//            for (int j = i + 1; j < points.length; j++) {
//                if (isWithin5Km(points[i][0], points[i][1], points[j][0], points[j][1])) {
//                    result.append("Point ").append(i + 1)
//                            .append(" (Lat: ").append(points[i][0])
//                            .append(", Lon: ").append(points[i][1])
//                            .append(") and Point ").append(j + 1)
//                            .append(" (Lat: ").append(points[j][0])
//                            .append(", Lon: ").append(points[j][1])
//                            .append(") are within ").append(radius)
//                            .append(" km range.\n");
//                }
//            }
//        }
//        return result.toString();
//    }
//
//    public static void main(String[] args) {
//        // 你的经纬度示例
//        double lat1 = 23.126486;
//        double lon1 = 113.269765;
//        double lat2 = 23.126595; // 稍微改变纬度
//        double lon2 = 113.270865; // 稍微改变经度
//        double lat3 = 23.127789; // 更改第三个点的经纬度以确保在5公里内
//        double lon3 = 113.268954;
//
//        double[][] points = {
//                {lat1, lon1},
//                {lat2, lon2},
//                {lat3, lon3}
//        };
//
//        // 查找并打印所有在5公里范围内的点的组合
//        String closePoints = findClosePoints(points, 5);
//        if (!closePoints.isEmpty()) {
//            System.out.println("以下点的组合在5公里范围内：\n" + closePoints);
//        } else {
//            System.out.println("没有找到任何点的组合在5公里范围内。");
//        }
//    }
//}


//public class DistanceCalculatorUtils {
//
//    // 地球半径，单位公里
//    private static final double EARTH_RADIUS = 6371.0;
//
//    // 计算两点之间的距离
//    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
//        double radLat1 = Math.toRadians(lat1);
//        double radLon1 = Math.toRadians(lon1);
//        double radLat2 = Math.toRadians(lat2);
//        double radLon2 = Math.toRadians(lon2);
//
//        double deltaLat = radLat2 - radLat1;
//        double deltaLon = radLon2 - radLon1;
//
//
//        double a = Math.pow(Math.sin(deltaLat / 2), 2)
//                + Math.cos(radLat1) * Math.cos(radLat2)
//                * Math.pow(Math.sin(deltaLon / 2), 2);
//        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
//
//        return EARTH_RADIUS * c;
//    }
//
//    // 检查两点是否在5公里范围内
//    public static boolean isWithin5Km(double lat1, double lon1, double lat2, double lon2) {
//                double distance = calculateDistance(lat1, lon1, lat2, lon2);
//        return distance <= 5;
//    }
//
//    // 查找并返回所有在5公里范围内的点的索引组合
//    public static int[][] findClosePoints(double[][] points, int radius) {
//        List<int[]> closePairs = new ArrayList<>();
//        for (int i = 0; i < points.length; i++) {
//            for (int j = i + 1; j < points.length; j++) {
//                if (isWithin5Km(points[i][0], points[i][1], points[j][0], points[j][1])) {
//                    closePairs.add(new int[]{i, j});
//                }
//            }
//        }
//        return closePairs.toArray(new int[closePairs.size()][]);
//    }
//
//    public static void main(String[] args) {
//        // 示例经纬度
//        double[][] points = {
//                {23.126486, 113.269765},
//                {23.126595, 113.270865}, // 稍微改变纬度和经度
//                {23.127789, 113.268954}  // 更改第三个点的经纬度
//        };
//
//        // 查找所有在5公里范围内的点的组合
//        int[][] closePairs = findClosePoints(points, 5);
//        if (closePairs.length > 0) {
//            System.out.println("找到以下点的组合在5公里范围内：");
//            for (int[] pair : closePairs) {
//                System.out.println("Point " + pair[0] + " and Point " + pair[1]);
//            }
//        } else {
//            System.out.println("没有找到任何点的组合在5公里范围内。");
//        }
//    }
//}


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DistanceCalculatorUtils3 {

    // 地球半径，单位公里
    private static final double EARTH_RADIUS = 6371.0;

    // 计算两点之间的距离
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

    // 查找并返回所有在5公里范围内的点的经纬度组合
    public static Map<double[],List<double[]>> findClosePoints(double[][] points, int radius) {
        List<double[]> closePairs = new ArrayList<>();
        Map<double[],List<double[]>> map = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (isWithin5Km(points[i][0], points[i][1], points[j][0], points[j][1],radius)) {
                    closePairs.add(new double[]{points[i][0], points[i][1]});
                    closePairs.add(new double[]{points[j][0], points[j][1]});
                    map.put(new double[]{points[i][0], points[i][1]},closePairs);
                }
            }
        }
        return map;
    }

    public static void main(String[] args) {
        // 示例经纬度
        double[][] points = {
                {23.126486, 113.269765},
                {23.126595, 113.270865}, // 稍微改变纬度和经度
                {23.127789, 113.268954}  // 更改第三个点的经纬度
        };

        // 查找所有在5公里范围内的点的组合
//        List<double[]> closePairs = findClosePoints(points, 5);
        Map<double[], List<double[]>> closePoints = findClosePoints(points, 5);
        if (!closePoints.isEmpty()) {
            System.out.println("找到以下点的组合在5公里范围内：");
//            for (double[] pair : closePoints) {
//                System.out.println("Latitude: " + pair[0] + ", Longitude: " + pair[1]);
//            }
            System.out.println(closePoints);
        } else {
            System.out.println("没有找到任何点的组合在5公里范围内。");
        }
    }
}