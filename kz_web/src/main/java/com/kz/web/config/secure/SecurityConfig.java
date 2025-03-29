package com.kz.web.config.secure;

import com.kz.web.config.secure.context.*;
import com.kz.web.config.secure.context.filters.KAuthFilter;
import com.kz.web.config.secure.context.providers.KAuthenticationProvider;
import com.kz.web.config.secure.context.providers.KUserAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
public class SecurityConfig {
    @Autowired
    TokenAuthUtil authUtil;

    @Autowired
    private KAuthenticationProvider kAuthenticationProvider;

    @Autowired
    private KUserAuthenticationProvider kUserAuthenticationProvider;

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        ProviderManager providerManager = new ProviderManager(kAuthenticationProvider, kUserAuthenticationProvider);
        return providerManager;
    }

    // 配置安全过滤器链（Spring Security 5.7+ 推荐方式）
//    @Bean
    @Order(0)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login").permitAll() // 登录页放行
                        .requestMatchers("/public/**", "/error").permitAll() // 明确放行登录页和公共路径
                        .requestMatchers("/admin/**").hasAuthority("admin")    // 需要 ADMIN 角色
                        .anyRequest().authenticated()                     // 其他所有路径需要认证
                )
                .anonymous(anon -> anon
                        .principal("anonymousUser") // 匿名用户
                )
                .formLogin(form -> form.disable()  // 登录失败跳转
                )
                .addFilterAfter(new KAuthFilter(authUtil, authenticationManager()), LogoutFilter.class) // 自定义认证过滤器
//                .authenticationProvider(new KAuthenticationProvider()) // 自定义认证提供者
                .authenticationProvider(new KAuthenticationProvider()) // 自定义认证提供者
                .logout(logout -> logout
                        .logoutUrl("/logout")          // 登出URL
                        .logoutSuccessUrl("/login?logout") // 登出成功后跳转
                        .permitAll()
                )
//                .authenticationManager(authenticationManager())
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(new KAccessDeniedHandler())
                                .authenticationEntryPoint(new KAuthenticationEntryPoint())
                        // 权限不足时跳转
                );
        return http.build();
    }

    // 配置内存用户（仅示例，生产环境需用数据库）
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("123456"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    // 密码编码器（必须配置）
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
