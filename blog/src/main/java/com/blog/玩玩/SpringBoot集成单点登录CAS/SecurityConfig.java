//package com.blog.玩玩.SpringBoot集成单点登录CAS;
//
//import org.jasig.cas.client.session.SingleSignOutFilter;
//import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/css/**", "/js/**", "/images/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .exceptionHandling()
//                .authenticationEntryPoint(casAuthenticationEntryPoint())
//                .and()
//                .addFilter(casAuthenticationFilter())
//                .addFilterBefore(singleSignOutFilter(), CasAuthenticationFilter.class);
//    }
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .authenticationProvider(casAuthenticationProvider());
//    }
//    public CasAuthenticationFilter casAuthenticationFilter() throws Exception {
//        CasAuthenticationFilter filter = new CasAuthenticationFilter();
//        filter.setAuthenticationManager(authenticationManager());
//        return filter;
//    }
//    public CasAuthenticationEntryPoint casAuthenticationEntryPoint() {
//        CasAuthenticationEntryPoint entryPoint = new CasAuthenticationEntryPoint();
//        entryPoint.setLoginUrl(casProperties.getServerUrlPrefix() + "/login");
//        entryPoint.setServiceProperties(serviceProperties());
//        return entryPoint;
//    }
//    public ServiceProperties serviceProperties() {
//        ServiceProperties serviceProperties = new ServiceProperties();
//        serviceProperties.setService(casProperties.getService());
//        serviceProperties.setSendRenew(false);
//        return serviceProperties;
//    }
//    public Cas20ServiceTicketValidator cas20ServiceTicketValidator() {
//        return new Cas20ServiceTicketValidator(casProperties.getServerUrlPrefix());
//    }
//
//    public CasAuthenticationProvider casAuthenticationProvider() {
//        CasAuthenticationProvider provider = new CasAuthenticationProvider();
//        provider.setAuthenticationUserDetailsService(customUserDetailsService());
//        provider.setServiceProperties(serviceProperties());
//        provider.setTicketValidator(cas20ServiceTicketValidator());
//        provider.setKey("an_id_for_this_auth_provider_only");
//        return provider;
//    }
//    public SingleSignOutFilter singleSignOutFilter() {
//        SingleSignOutFilter filter = new SingleSignOutFilter();
//        filter.setCasServerUrlPrefix(casProperties.getServerUrlPrefix());
//        filter.setIgnoreInitConfiguration(true);
//        return filter;
//    }
//    @Bean
//    public CustomUserDetailsService customUserDetailsService() {
//        return new CustomUserDetailsService();
//    }
//    @Bean
//    public CasProperties casProperties() {
//        return new CasProperties();
//    }
//}
//
