package com.blog.config.xss过滤;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/admin")
public class XssFilter implements Filter {
   //注意：通过上面的过滤器可以发现我们并没有在过滤器中直接进行请求参数的过滤清理，而是直接放行了，那么我们还怎么进行请求参数的过滤清理呢？
    // 其实过滤清理的工作是在另外一个类XssRequestWrapper中进行的，当上面的过滤器放行时需要调用filterChain.doFilter()方法，
    // 此方法需要传入请求Request对象，此时我们可以将当前的request对象进行包装，而XssRequestWrapper就是Request对象的包装类，
    // 在过滤器放行时会自动调用包装类的getParameterValues方法，我们可以在包装类的getParameterValues方法中进行统一的请求参数过滤清理。

    public void init(FilterConfig config) throws ServletException {
    }

    // 这是一个Servlet过滤器（Filter）的方法实现，每当有请求到达时，都会调用这个方法。
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // 创建一个XssHttpServletRequestWrapper对象，它是一个自定义的请求包装器类。
        // 这个类主要用于防止跨站脚本攻击（XSS attack）。
        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(
                // 将原始的ServletRequest对象强制转换为HttpServletRequest类型，并传递给XssHttpServletRequestWrapper构造函数。
                (HttpServletRequest) request);

        // 调用FilterChain的doFilter方法，将包装后的XssHttpServletRequestWrapper对象和原始的ServletResponse对象传递给它。
        // 这会导致请求继续向下传递到下一个过滤器（如果有）或者最终到达目标Servlet。
        // 在整个处理过程中，所有后续的代码都将使用经过XSS防护的请求对象，从而提高了应用的安全性。
//        chain.doFilter(xssRequest, response);
    }

    @Override
    public void destroy() {
    }
}




