package com.blog.controller.http;

import io.github.admin4j.http.core.Pair;
import io.github.admin4j.http.util.HttpUtil;
import okhttp3.Response;

public class httpTest {
    public void getHttp(){
        Response response = HttpUtil.get("https://github.com/search", Pair.of("q", "okhttp"));
        System.out.println("response = " + response);
    }
}
