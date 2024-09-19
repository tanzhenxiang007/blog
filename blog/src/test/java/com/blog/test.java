package com.blog;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.test.context.SpringBootTest;
import org.w3c.dom.events.EventException;
import sun.misc.BASE64Encoder;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@SpringBootTest
public class test {


    public static Map<String, Object> obj2Map(Object obj) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            Object value = getter != null ? getter.invoke(obj) : null;
            map.put(key, value);
        }
        return map;
    }

    private static Pattern pattern = Pattern.compile("(\\w+)=(\\w+)");

    private static JSONObject matcherAuthorization(String authorization) {
        JSONObject map = new JSONObject();
        /*String[] authorizations = authorization.split(",");
        for (String s : authorizations) {
            String[] split = s.split("=");
            map.put(split[0], split[1]);
        }*/
        Matcher matcher = pattern.matcher(authorization);
        while (matcher.find()) {
            map.put(matcher.group(1), matcher.group(2));
        }
        return map;
    }


    public static void main(String[] args) {
        String authorization = "EOPAUTH appaccount=f23b437ee1c4468798bc59323a11fccd,timestamp=1718591719862,sign=07b679a8f068873d32fe4bc17c29800b";
        JSONObject map = matcherAuthorization(authorization);
        System.out.println(map);
        System.out.println(map.getString("appaccount"));
    }
}
 class IntegerPartition {

    public static void main(String[] args) {
//        int n = 3; // 可以更改此值以测试不同的N
//        System.out.println(partitionCount(n));
//        Map<String,String> map = new HashMap<>();
//        map.put("a","b");
//        System.out.println(map);.


        getImageStr("C:\\Users\\tzx\\Desktop\\1.jpg");
    }

    private static int partitionCount(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1; // 初始化，0只有一种加法方式，即不加任何数

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[i - j]; // 对于每个i，累加所有可能的加法分解的数量
            }
        }

        return dp[n];
    }

     public static String getImageStr(String imgFile) {
         InputStream inputStream = null;
         byte[] data = null;
         try {
             inputStream = new FileInputStream(imgFile);
             data = new byte[inputStream.available()];
             inputStream.read(data);
             inputStream.close();
         } catch (IOException e) {
             e.printStackTrace();
         }
         // 加密
         BASE64Encoder encoder = new BASE64Encoder();
         String encode = encoder.encode(data);

         System.out.println(getBase64ImageSize(encode));
         return "";
     }
     /**
      * 计算Base64编码的图片大小（以KB为单位）。
      * @param base64Image 待计算大小的Base64编码字符串。
      * @return 图片大小（以KB为单位），如果输入无效返回-1。
      */
     public static long getBase64ImageSize(String base64Image) {
         if (base64Image == null || base64Image.isEmpty()) {
             return -1; // 输入无效时返回-1
         }

         try {
             byte[] imageBytes = Base64.getDecoder().decode(base64Image);
             // 返回以KB为单位的图片大小
             return imageBytes.length / 1024L;
         } catch (IllegalArgumentException e) {
             // 捕获并处理解码异常
             System.err.println("无法解码Base64字符串: " + e.getMessage());
             return -1; // 解码失败时返回-1
         }
     }

 }

 class User8 {
//     public static void main(String[] args) {
//      long a=1719216244105l;
//         if (System.currentTimeMillis() >= a) {
//             System.out.println(1);
//         }
//     }
public static void main(String[] args) {
    boolean time1Later = isTime1Later("2023-07-01 12:00:00", "2023-07-01 13:59:59");
    System.out.println(time1Later);

}
     public static boolean isTime1Later(String time1, String time2) {
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

         try {
             LocalDateTime dateTime1 = LocalDateTime.parse(time1, formatter);
             LocalDateTime dateTime2 = LocalDateTime.parse(time2, formatter);
             return dateTime1.isAfter(dateTime2);
         } catch (DateTimeParseException e) {
             // 如果时间格式不正确，打印错误并返回 false
             System.err.println("One of the provided times is not in the expected format 'yyyy-MM-dd HH:mm:ss'");
             return false;
         }
     }

 }

 class DistanceCalculator {
     public static void main(String[] args) {
         List<Map<String,String>> list = new ArrayList<>();
         Map<String, String> map1 = new java.util.HashMap<>();
         Map<String, String> map2 = new java.util.HashMap<>();
         Map<String, String> map3 = new java.util.HashMap<>();
         Map<String, String> map4 = new java.util.HashMap<>();
         Map<String, String> map5 = new java.util.HashMap<>();
         Map<String, String> map6 = new java.util.HashMap<>();
         Map<String, String> map7 = new java.util.HashMap<>();
         map1.put("key", "value1");
         map2.put("key", "value2");
         map3.put("key", "valu3");
         map4.put("key", "valu4");
         map5.put("key", "map5");
         map6.put("key", "map6");
         map7.put("key", "map7");
         list.add(map1);
         list.add(map2);
         list.add(map3);
         list.add(map4);
         list.add(map5);
         list.add(map6);
         list.add(map7);
//         list.add("ASF");list.add("UU");list.add("PP");list.add("LL");
         List<Map<String,String>>list2 = new ArrayList<>();
         list2.addAll(list);
         int value = 3;
         int value1 = 3;
         Iterator<Map<String, String>> iterator = list2.iterator();
         for (int j = 0; j < value1; j++) {
         while (iterator.hasNext()) {
             if (list2.size()>value){
                 list.clear();
                 list.addAll(list2);
                 StringBuffer sb = new StringBuffer();
                 for (int i = 0; i < value; i++) {
                     sb.append(list.get(i).get("key"));
                     if (i != (value-1)){
                         sb.append(",");
                     }
                     list2.remove(0);
                 }
                 System.out.println("1=="+sb.toString());
                 return;
             }else {
                 StringBuffer sb = new StringBuffer();
                 for (int i = 0; i < list2.size(); i++) {
                     sb.append(list2.get(i).get("key"));
                     if (i != (list2.size()-1)){
                         sb.append(",");
                     }
                 }
                 System.out.println("2=="+sb.toString());
                 break;
             }
         }
         }
     }
}


 class ProximityCoordinates {

    private static final double EARTH_RADIUS = 6371.0; // 地球半径，单位公里
    private static final double KM_TO_DEGREE = 0.008983; // 1公里大约等于0.008983度

    public static double distanceInKm(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;
    }

    public static boolean isWithin5Km(double lat, double lon, double testLat, double testLon) {
        return distanceInKm(lat, lon, testLat, testLon) <= 5;
    }

    public static List<double[]> generateProximityCoordinates(double startLat, double startLon) {
        List<double[]> coordinates = new ArrayList<>();
        coordinates.add(new double[]{startLat, startLon});
        Random random = new Random();

        // 确保至少有一个点
        if (coordinates.isEmpty()) {
            coordinates.add(new double[]{startLat, startLon});
        }

        // 生成其他点
        while (coordinates.size() < 3) {
            double newLat = startLat + (random.nextDouble() - 0.5) * 5 * KM_TO_DEGREE;
            double newLon = startLon + (random.nextDouble() - 0.5) * 5 * KM_TO_DEGREE;

            double[] newCoord = new double[]{newLat, newLon};
            // 检查新点是否与所有现有点都在5公里以内
            if (coordinates.stream().allMatch(coord -> isWithin5Km(coord[0], coord[1], newLat, newLon))) {
                coordinates.add(newCoord);
            }
        }

        return coordinates;
    }

    public static void main(String[] args) {
//        double startLat = 34.0; // 起始纬度
//        double startLon = 117.0; // 起始经度
//
//        List<double[]> proximityCoordinates = generateProximityCoordinates(startLat, startLon);
//
//        for (double[] coord : proximityCoordinates) {
//            System.out.println("Latitude: " + coord[0] + ", Longitude: " + coord[1]);
//        }
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONArray jsonArray2 = new JSONArray();
        jsonObject.put("ORDER_ID",45646);
        jsonObject.put("LONGITUDE",44);
        jsonObject.put("LATITUDE",66);
        jsonObject2.put("ORDER_ID",888);
        jsonObject2.put("LONGITUDE",55);
        jsonObject2.put("LATITUDE",11);
        jsonArray.add(jsonObject);
        jsonArray.add(jsonObject2);
        jsonArray2.add(jsonObject);
        jsonArray2.add(jsonObject2);
        List<JSONArray> list = new ArrayList<>();
        list.add(jsonArray);
        list.add(jsonArray2);
        System.out.println(list);
    }
}




 class BatchCompressFiles {
    public static void main(String[] args) {
//        // 假设filesToCompress是一个包含所有文件绝对路径的数组
//        String[] filesToCompress = {
//              "D:\\ce\\123\\c-副本.txt",
//                "D:\\ce\\123\\c.txt"
//        };
//
//        // 指定输出ZIP文件的前缀
//        String outputZipPrefix = "D:\\ce\\123\\abc";
//
//        try {
//            // 调用compressFiles方法进行分批次压缩
//            compressFiles(filesToCompress, outputZipPrefix, 100);
//            System.out.println("所有批次的压缩完成！");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Path path = Paths.get("D:\\ce\\66\\1.txt");
        Path dirPath = path.getParent(); // 获取目录路径

        // 检查目录是否存在
        if (Files.notExists(dirPath)) {
            // 目录不存在，创建目录
            try {
                Files.createDirectories(dirPath); // 创建所有必需的父目录
                System.out.println("目录创建成功: " + dirPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void compressFiles(String[] filesToCompress, String outputZipPrefix, int batchSize) throws IOException {
        for (int i = 0; i < filesToCompress.length; i += batchSize) {
            // 计算批次的结束索引，避免数组越界
            int endIndex = Math.min(i + batchSize, filesToCompress.length);
            // 获取当前批次的文件路径子数组
            String[] batchFiles = Arrays.copyOfRange(filesToCompress, i, endIndex);
            // 构造当前批次的ZIP文件名
            String zipFileName = outputZipPrefix + "_" + (i / batchSize) + ".zip";
            // 压缩当前批次的文件
            compressZipFile(batchFiles, zipFileName);
        }
    }

    private static void compressZipFile(String[] files, String zipFileName) throws IOException {
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFileName))) {
            for (String filePath : files) {
                compressFile(filePath, zipOut);
            }
        }
    }

    private static void compressFile(String filePath, ZipOutputStream zipOut) throws IOException {
        File fileToZip = new File(filePath);
        if (!fileToZip.exists()) {
            System.err.println("文件不存在，跳过压缩: " + filePath);
            return;
        }
        ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
        zipOut.putNextEntry(zipEntry);

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileToZip))) {
            byte[] buffer = new byte[1024];
            int read;
            while ((read = bis.read(buffer)) != -1) {
                zipOut.write(buffer, 0, read);
            }
        }
        zipOut.closeEntry();
    }


}
abstract class nettyTest {
    private  HashMap<String,String> events;
    public nettyTest(){
       this.initEvent();
    }


    private void initEvent() {

        events = new HashMap<String, String>();
        events.put("1", "1");
        events.put("2", "2");
        events.put("3", "3");
    }
    protected String getEvent(String messageId){
        return  events.get(messageId);
    }
    public abstract JSONObject handleMessage(String requestDataModel) throws EventException;

}
class ntr extends nettyTest{

    public static void main(String[] args) {
//       this.handleMessage("1");
        String A="III,111,222,333,44";
        String[] split = A.split(",");
        int length = 6;
        String[] splitThree = new String[length];
        if (length <= split.length){
            // 定义一个新的数组来存储前三个元素
            // 复制前三个元素到新数组
            for (int i = 0; i < length && i < split.length; i++) {
                splitThree[i] = split[i];
            }
        }
        for (String s : splitThree) {
            System.out.println(s);
        }
    }
        public String getEvent(String messageId){
            return getEvent("1");
        }

    @Override
    public JSONObject handleMessage(String requestDataModel) throws EventException {
       this.getEvent(requestDataModel);
        return null;
    }
}