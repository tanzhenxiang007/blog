package com.blog.test1;


import org.junit.platform.commons.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonTransformation {
//    public static void main(String[] args) throws JSONException {
//        String jsonArray = "[{\"labelname\":\"labelA\",\"labelvalue\":\"b\"},{\"labelname\":\"labelA\",\"labelvalue\":\"s\"},{\"labelname\":\"labelB\",\"labelvalue\":\"C\"},{\"labelname\":\"labelB\",\"labelvalue\":\"V\"}]";
//
//        JSONArray jsonArrayInput = new JSONArray(jsonArray);
//        Map<String, JSONArray> resultMap = new HashMap<>();
//
//        for (int i = 0; i < jsonArrayInput.length(); i++) {
//            JSONObject jsonObject = jsonArrayInput.getJSONObject(i);
//            String labelname = jsonObject.getString("labelname");
//            String labelvalue = jsonObject.getString("labelvalue");
//            if (resultMap.containsKey(labelname)) {
//                JSONArray values = resultMap.get(labelname);
//                values.put(labelvalue);
//            } else {
//                JSONArray values = new JSONArray();
//                values.put(labelvalue);
//                resultMap.put(labelname, values);
//            }
//        }
//        System.out.println(resultMap);
//        JSONObject resultJson = new JSONObject();
//        for (Map.Entry<String, JSONArray> entry : resultMap.entrySet()) {
//            resultJson.put(entry.getKey(), entry.getValue());
//        }
//
//        System.out.println(resultJson);  // {"labelA":["b","s"],"labelB":["C","V"]}
//    }
//public static void main(String[] args) {
//    List<JSONObject> list=new ArrayList<>();
//    List<JSONObject> list2=new ArrayList<>();
//    JSONObject jsonObject=new JSONObject();
//    JSONObject jsonObject2=new JSONObject();
//    jsonObject.put("name","张三");
//    jsonObject.put("age",18);
//    jsonObject2.put("name","张三1");
//    jsonObject2.put("age",181);
//    list.add(jsonObject);
//    list2.add(jsonObject2);
//    JSONObject resultJson=new JSONObject();
//    JSONObject resultJson3=new JSONObject();
//    JSONObject resultJson4=new JSONObject();
//    resultJson3.put("ii",list);
//    resultJson3.put("iv",list2);
//    resultJson4.put("levelList",resultJson3);
//    resultJson4.put("enableLevel","i");
//    resultJson.put("data",resultJson4);
//    System.out.println(resultJson);
//}


//    public static void main(String[] args) {
//        Map<String, JSONObject> resultMap = new HashMap<>();
////        EasySQL sql = new EasySQL("select t1.LABEL_TYPE_NAME,t2.LABEL_NAME from tt_city_label_type t1 LEFT JOIN tt_city_label t2 on t1.LABEL_TYPE_ID=t2.LABEL_TYPE_ID");
//        List<JSONObject> list = new ArrayList<>();
//        JSONObject jsonObject0 = new JSONObject();
//        JSONObject jsonObject1 = new JSONObject();
//        JSONObject jsonObject2 = new JSONObject();
//        jsonObject0.put("LABEL_TYPE_NAME","labelA");
//        jsonObject0.put("LABEL_NAME","name1");
//        jsonObject0.put("id","1");
//        jsonObject1.put("LABEL_TYPE_NAME","labelA");
//        jsonObject1.put("LABEL_NAME","name2");
//        jsonObject1.put("id","2");
//        jsonObject2.put("LABEL_TYPE_NAME","labelB");
//        jsonObject2.put("LABEL_NAME","nameb");
//        jsonObject2.put("id","3");
//        list.add(jsonObject0);list.add(jsonObject1);list.add(jsonObject2);
//        System.out.println(list);
//        JSONObject result = new JSONObject();
//        for (JSONObject obj : list) {
//            String labelTypeName = obj.getString("LABEL_TYPE_NAME");
//            if (!result.containsKey(labelTypeName)) {
//                result.put(labelTypeName, new JSONArray());
//            }
////             result.getJSONArray(labelTypeName).remove("LABEL_TYPE_NAME");
//            JSONArray list2 = result.getJSONArray(labelTypeName);
//            list2.add(obj); // 添加到对应的列表中
//        }
//
//
//        System.out.println(result);
//    }
public static void main(String[] args) throws Exception {
//    String tenantIdList = "2002,2003,2004";
//    String[] List = tenantIdList.split(",");
//    String entid="2001";
//    boolean isMatch = Arrays.stream(List).anyMatch(s -> s.equals(entid));
//    System.out.println(isMatch);
    int timeLimited = getTimeLimited("2024-01-29 00:00:00", "2024-01-29 23:59:59");
    System.out.println(timeLimited);
}
    public static  int getTimeLimited(String startDate,String endDate) throws Exception{
        if(StringUtils.isBlank(startDate) || StringUtils.isBlank(endDate)){
            return 999;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = dateFormat.parse(startDate.substring(0, 10));
        Date date2 = dateFormat.parse(endDate.substring(0, 10));
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));


        if (days>183) {
            return 183;
        }
        return -1;
    }
}