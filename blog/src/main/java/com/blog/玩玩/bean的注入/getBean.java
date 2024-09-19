package com.blog.玩玩.bean的注入;

import com.alibaba.fastjson.JSONObject;
import com.blog.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/Json")
public class getBean {
    @Autowired
    private User json1;//获取名字交json的bean

    @RequestMapping("/getJson")
    @ResponseBody  //如果有需要直接返回的数据，那么在方法上加@ResponseBody即可，而不会请求到页面。
    public JSONObject getJson(){
        User user1 = this.json1;

        System.out.println(user1);
        JSONObject json1 = new JSONObject();
        //user对象转json对象
        json1 = (JSONObject) JSONObject.toJSON(user1);

        //结果：{"password":null,"createTime":null,"blogs":[],"nickname":null,"updateTime":null,"avatar":"123","id":12,"type":null,"email":"123","username":null}
        return json1;
    }

    @RequestMapping ("/downloadExcel")
    public void downloadExcel(@RequestParam("filename") String filename, HttpServletRequest request, HttpServletResponse response) {
        try {
            // 假设临时目录是 /tmp
            String tempDirectory = "D:\\ce";
            Path filePath = Paths.get(tempDirectory, filename);

            // 检查文件是否存在并且文件名安全
            if ( Files.exists(filePath)) {
                // 读取文件
                File file = filePath.toFile();
                downloadFile(file, response);
            }
        } catch (IOException e) {
            // 更好的错误处理，比如记录日志
//            logger.error("Error occurred during file download: ", e);
        }
    }
    private void downloadFile(File file, HttpServletResponse response) throws IOException {
        try (
                FileInputStream inputStream = new FileInputStream(file);
                ServletOutputStream outputStream = response.getOutputStream()
        ) {
            // 设置响应的Header
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
            outputStream.flush();

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }
}
