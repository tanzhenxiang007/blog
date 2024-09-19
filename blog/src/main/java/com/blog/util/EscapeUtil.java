package com.blog.util;

import cn.hutool.http.HTMLFilter;

public class EscapeUtil {
    /**
     * 清理字符串中的HTML标签
     *
     * @param content 待清理的字符串
     * @return 清理后的字符串
     */
    public static String clean(String content){
        return new HTMLFilter().filter(content);
    }
}

