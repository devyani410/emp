//package com.empManagement.empAssignement.config;
//
//import com.fasterxml.jackson.databind.annotation.NoClass;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;
//
//@Configuration
//@EnableWebSecurity
//@EnableSwagger2
////@EnableSwagger2WebMvc
//public class SpringSecurity extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        String str[]=new String[]{
//                "/v3/api-docs",
//                "/api/v1/auth","/v2/api-docs",
//                "/swagger-resources/**",
//                "/swagger-ui/**",
//                "/webjars/**"
//        };
//        http
//                .authorizeRequests()
//                .antMatchers(str).permitAll()
////                .antMatchers("/v3/api-docs").permitAll()
//                .anyRequest().
//                authenticated().
//                and().
//                httpBasic();
//    }
//
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addRedirectViewController("/", "/api/swagger-ui.html");
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("deni").password("123@deni").roles("NORMAL");
//        auth.inMemoryAuthentication().withUser("dev").password("123@dev").roles("ADMIN");
//        auth.inMemoryAuthentication().withUser("springboot").password("123@springboot").roles("NORMAL");
//    }
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }
//}
