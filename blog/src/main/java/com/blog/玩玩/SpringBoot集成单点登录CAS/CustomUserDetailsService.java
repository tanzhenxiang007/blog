//package com.blog.玩玩.SpringBoot集成单点登录CAS;
//
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//@Component
//public class CustomUserDetailsService implements UserDetailsService {
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // 根据用户名查询用户信息
//        // 这里我们使用一个静态的用户信息进行演示
//        return User.builder()
//                .username("admin")
//                .password("{noop}password")
//                .authorities("ROLE_USER")
//                .build();
//    }
//}
//
