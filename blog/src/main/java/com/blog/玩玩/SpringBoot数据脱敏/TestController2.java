package com.blog.玩玩.SpringBoot数据脱敏;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class TestController2 {
    // 正则表达式用于校验IPv4地址
    private static final String IPV4_REGEX =
            "^((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$";

    // 正则表达式用于校验端口号（1到65535之间）
    private static final String PORT_REGEX = "^(6553[0-5]|655[0-2]\\d|65[0-4]\\d{2}|6[0-4]\\d{3}|[1-5]\\d{4}|[1-9]\\d{1,3}|[1-9])$";

    @RequestMapping("/tuoMin")
    @ResponseBody
    public Person test(){
        Person user = new Person();
        try {

            user.setRealName("阿Q");
            user.setPhoneNumber("13588888888");
            user.setIdCard("370213199204174235");
            System.out.println(user);
            return user;
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
        return user;
    }

    // 校验IP地址和端口号的方法
    public static boolean isValidIP(String ip) {
        return Pattern.matches(IPV4_REGEX, ip);
    }

    public static boolean isValidPort(String port) {
        return Pattern.matches(PORT_REGEX, port);
    }

    public static boolean validateLandlineNumber(String phoneNumber) {
        // 固定电话号码的正则表达式
        String regex = "^(0\\d{2,3}-?)?\\d{7,8}$";

        // 编译正则表达式
        Pattern pattern = Pattern.compile(regex);

        // 匹配输入的电话号码
        Matcher matcher = pattern.matcher(phoneNumber);

        // 返回匹配结果
        return matcher.matches();
    }

    public static void main(String[] args) {
        // 测试电话号码
        String[] testNumbers = {"020-2336252", "0202336252", "021-12345678", "13426", "010-87654321"};

        for (String number : testNumbers) {
            boolean isValid = validateLandlineNumber(number);
            System.out.println("Phone number " + number + " is valid: " + isValid);
        }
    }

    private static java.util.Date parseTimeStamp(String timeStampStr) {
        SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMddHHmm");
        try {
            return sdf.parse(timeStampStr);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    private static void intoRedisData(Integer type) {
    for (int i = 0; i < 10; i++)
    {
        System.out.println(Thread.currentThread().getName()+"=="+i+"=="+type);
    }
    }
}
