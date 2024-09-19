package com.blog.es;

import org.apache.http.HttpHost;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class Springboot18EsApplicationTests {
    private RestHighLevelClient client;

    //使用编程的形式设置连接的ES服务器，并获取客户端对象
    @Test
    void testCreateClient() throws IOException {
        HttpHost host = HttpHost.create("http://172.16.76.102:19200/");
        RestClientBuilder builder = RestClient.builder(host);
        client = new RestHighLevelClient(builder);

        client.close();
    }

//    //使用客户端对象操作ES，例如创建索引
//    @Test
//    void testCreateIndex() throws IOException {
//        HttpHost host = HttpHost.create("http://localhost:9200");
//        RestClientBuilder builder = RestClient.builder(host);
//        client = new RestHighLevelClient(builder);
//
//        CreateIndexRequest request = new CreateIndexRequest("books");
//        client.indices().create(request, RequestOptions.DEFAULT);
//
//        client.close();
//    }



    @BeforeEach
        //在测试类中每个操作运行前运行的方法
    void setUp() {
        HttpHost host = HttpHost.create("http://172.16.76.102:19200/");
        RestClientBuilder builder = RestClient.builder(host);
        client = new RestHighLevelClient(builder);
    }

    @AfterEach
        //在测试类中每个操作运行后运行的方法
    void tearDown() throws IOException {
        client.close();
    }

    @Test
    void testCreateIndex() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest("books");
        client.indices().create(request, RequestOptions.DEFAULT);
    }





    //创建索引（IK分词器）
    @Test
    void testCreateIndexByIK() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest("books");
        String json = "{\n" +
                "    \"mappings\":{\n" +
                "        \"properties\":{\n" +
                "            \"id\":{\n" +
                "                \"type\":\"keyword\"\n" +
                "            },\n" +
                "            \"name\":{\n" +
                "                \"type\":\"text\",\n" +
                "                \"analyzer\":\"ik_max_word\",\n" +
                "                \"copy_to\":\"all\"\n" +
                "            },\n" +
                "            \"type\":{\n" +
                "                \"type\":\"keyword\"\n" +
                "            },\n" +
                "            \"description\":{\n" +
                "                \"type\":\"text\",\n" +
                "                \"analyzer\":\"ik_max_word\",\n" +
                "                \"copy_to\":\"all\"\n" +
                "            },\n" +
                "            \"all\":{\n" +
                "                \"type\":\"text\",\n" +
                "                \"analyzer\":\"ik_max_word\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";
        //设置请求中的参数
        request.source(json, XContentType.JSON);
        client.indices().create(request, RequestOptions.DEFAULT);
    }


    @Test
//按id查询
    void testGet() throws IOException {
        GetRequest request = new GetRequest().index("book").id("1");

        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        String json = response.getSourceAsString();
        System.out.println(json);
    }


}


