package com.kz.web.test;

import com.kz.web.test.tmp.TmoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
public class SimpleConfig {
    AuthenticationManager am;

    @Autowired
    AuthenticationConfiguration authConfig;


    @Bean
//    @Lazy
    public AuthenticationManager authenticationManager(
            ) throws Exception {
        am = authConfig.getAuthenticationManager();
        System.out.println(am);
        System.out.println("!!!!!manager !!!");
        return am;
    }


    @Bean
    @Lazy
//    @Order(0)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("securityFilterChain!!");
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login","/").permitAll() // 登录页放行
                        .requestMatchers("/public/**", "/error").permitAll() // 明确放行登录页和公共路径
                        .requestMatchers("/admin/**").hasAuthority("admin")    // 需要 ADMIN 角色
                        .anyRequest().authenticated()                     // 其他所有路径需要认证
                )
                .anonymous(anon -> anon
                        .principal("anonymousUser") // 匿名用户
                )
                .formLogin(form ->
                        form.disable()
                )
                .addFilterAfter(new SimpleFilter(authenticationManager()), LogoutFilter.class)
                .authenticationProvider(new MyAuthenticateProvider())
                .logout(logout -> logout
                        .logoutUrl("/logout")          // 登出URL
                        .logoutSuccessUrl("/login?logout") // 登出成功后跳转
                        .permitAll()
                );
        return http.build();
    }
}
