package com.blog.controller.test;

import com.blog.util.FileCopyUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.aspectj.util.FileUtil;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequestMapping("/es")
public class esController {

    @RequestMapping("/getEs")
    @ResponseBody
    public String esTest(){
       try {

           RestHighLevelClient client= new RestHighLevelClient(RestClient.builder(new HttpHost("172.16.76.102",19200,"http")));
           System.out.println(client);
           client.close();
       }catch (Exception e){

       }
        return null;
    }

    @RequestMapping("/getcp")
    @ResponseBody
    public String cp(){
        try {

            FileCopyUtils.copyFile(true,"/sftp/sftp1/test","/sftp/sftp","txt");
        }catch (Exception e){

        }
        return null;
    }
}
