package com.blog.aspect;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect    //定义切面
@Component
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 定义一个切面，拦截com.blog.controller包和子包下的所有方法
     * 第一个 * 号的位置：表示返回值类型，* 表示所有类型
     * 包名：表示需要拦截的包名，后面的两个句点表示当前包和当前包的所有子包，com.itcodai.course09.controller 包、子包下所有类的方法
     * 第二个 * 号的位置：表示类名，* 表示所有类
     * *(..) ：这个星号表示方法名，* 表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数
     */
    @Pointcut("execution(* com.blog.controller.*.*(..))")        //定义切入点表达式
    public void log(){}

    @Pointcut("execution(* com.blog.玩玩.redis发布订阅.第二种redisTemplate.*.*(..))")        //定义切入点表达式
    public void log2(){}


    //annotation() 方式是针对某个注解来定义切面，比如我们对具有@GetMapping注解的方法做切面，可以如下定义切面
    //使用该切面的话，就会切入注解是 @GetMapping 的方法
    //因为在实际项目中，可能对于不同的注解有不同的逻辑处理，比如 @GetMapping、@PostMapping、@DeleteMapping 等。所以这种按照注解的切入方式在实际项目中也很常用
//    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
//    public void annotationCut() {}

    @Before("log() || log2()")    //引用切入点
    public void doBefore(JoinPoint joinPoint){

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        //获得类名.方法名
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        //获得方法参数
        Object[] args = joinPoint.getArgs();

        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);
        //打印请求信息
        System.out.println("------------doBefore------------");
        logger.info("Request: {}", requestLog);

    }

    @After("log() || log2()")
    public void doAfter(JoinPoint joinPoint){
        logger.info("------------doAfter------------");
        Signature signature = joinPoint.getSignature();
        String method = signature.getName();
        logger.info("方法{}已经执行完", method);

    }

    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterReturn(Object result){

        //打印返回值
        logger.info("Result: {}", result);
    }

    @Data
    @AllArgsConstructor
    public class RequestLog{      //用于封装请求信息
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;
    }
}
