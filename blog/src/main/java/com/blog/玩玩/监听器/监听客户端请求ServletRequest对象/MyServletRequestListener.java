package com.blog.玩玩.监听器.监听客户端请求ServletRequest对象;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * 使用ServletRequestListener获取访问信息
 * @author shengwu ni
 * @date 2018/07/05
 */
@Component
public class MyServletRequestListener implements ServletRequestListener {

    private static final Logger logger = LoggerFactory.getLogger(MyServletRequestListener.class);

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
        logger.info("第一步servletRequest请求到这里");
        logger.info("session id为：{}", request.getRequestedSessionId());
        logger.info("request url为：{}", request.getRequestURL());
        //这里写死，但可以动态获取request对象里面的值进行设置
        request.setAttribute("name", request.getParameter("name")==null?"默认值=测试":request.getParameter("name"));
    }

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        logger.info("第三步servletRequest请求到这里");
        logger.info("request end");
        HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
        logger.info("request域中保存的name值为：{}", request.getAttribute("name"));

    }

}