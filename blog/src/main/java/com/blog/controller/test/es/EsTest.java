//package com.blog.controller.test.es;
//
//import org.elasticsearch.action.get.GetRequest;
//import org.elasticsearch.action.get.GetResponse;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.util.Base64Utils;
//
//@Service
//public class EsTest {
//    @Autowired
//    private RestHighLevelClient client;
//
//    private static String auth;
//
//    public EsTest(ElasticsearchConfiguration elasticsearchConfiguration) {
//        auth = Base64Utils.encodeToString((elasticsearchConfiguration.getUser() + ":" + elasticsearchConfiguration.getPwd()).getBytes());
//        auth = "Basic " + auth;
//    }
//
//    public void testGet() throws Exception {
//        // 文档查询
//        GetRequest getRequest = new GetRequest("first-index", "_doc", "gvarh3gBF9fSFsHNuO49");
//        RequestOptions.Builder requestOptions = RequestOptions.DEFAULT.toBuilder();
//        requestOptions.addHeader("Authorization", auth);
//        GetResponse getResponse = client.get(getRequest, requestOptions.build());
//        if (getResponse.isExists()) {
//            String sourceAsString = getResponse.getSourceAsString();
//            System.out.println(sourceAsString);
//        } else {
//            System.out.println("no string!");
//        }
//    }
//}
