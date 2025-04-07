package com.kz.web.config.secure;

import com.kz.auth.context.KAccessDeniedHandler;
import com.kz.auth.context.KAuthenticationEntryPoint;
import com.kz.auth.context.TokenAuthUtil;
import com.kz.auth.context.filters.KAuthFilter;
import com.kz.web.config.secure.context.AuthSuccessHandler;
import com.kz.web.config.secure.context.providers.KAuthenticationProvider;
import com.kz.web.config.secure.context.providers.KUserAuthenticationProvider;
import com.kz.web.config.secure.context.users.UserService;
import com.kz.web.config.secure.filter.GlobalExceptionHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.session.DisableEncodeUrlFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.io.IOException;

@Configuration
public class SecurityConfig {
    @Autowired
    TokenAuthUtil authUtil;

    @Autowired
    private AuthSuccessHandler authSuccessHandler;

    @Autowired
    private KAuthenticationProvider kAuthenticationProvider;
//
//    @Autowired
//    private KUserAuthenticationProvider kUserAuthenticationProvider;

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        ProviderManager providerManager = new ProviderManager(kAuthenticationProvider, daoAuthenticationProvider());
        return providerManager;
    }


    // 配置安全过滤器链（Spring Security 5.7+ 推荐方式）
//    @Bean
    @Order(0)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/index", "/forum").permitAll() // 首页放行
                        .requestMatchers("/blog/get", "/blog/list").permitAll()

                        .requestMatchers("/login", "/register").permitAll() // 登录页放行
                        .requestMatchers("/public/**", "/error").permitAll() // 明确放行登录页和公共路径
                        .requestMatchers("/admin/**").hasAuthority("admin")    // 需要 ADMIN 角色
                        .requestMatchers("/static/**",
                                "**.html",
                                "/css/**",
                                "/js/**",
                                "/fonts/**",
                                "/lib/**",
                                "/plugins/**").permitAll()
                        .anyRequest().authenticated()                     // 其他所有路径需要认证
                )
                .cors(
                        cors -> cors
                                .configurationSource(request -> {
                                    var config = new CorsConfiguration();
                                    config.addAllowedOrigin("*");
                                    config.addAllowedMethod("*");
                                    config.addAllowedHeader("*");
                                    return config;
                                })
                )
                .anonymous(anon -> anon
                        .principal("anonymousUser") // 匿名用户
                        .authorities("ROLE_ANONYMOUS") // 匿名用户角色
                )
                .formLogin(
                        form -> form.loginProcessingUrl("/login")
                                .successHandler(authSuccessHandler)
                                .failureHandler(new AuthenticationFailureHandler() {
                                    @Override
                                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                                        // 处理登录失败
                                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                                        response.getWriter().write("Login failed: " + exception.getMessage());
                                    }
                                })
                )
                .csrf(AbstractHttpConfigurer::disable) // 禁用 CSRF 保护
//                .oneTimeTokenLogin(oneTimeToken -> oneTimeToken
//                        .authenticationConverter(authUtil)
//                        .tokenGeneratingUrl("/login/token") // 生成Token URL
//                        .loginProcessingUrl("/login") // 登录URL
//                        .tokenGenerationSuccessHandler()
//                        .successHandler(new KAuthenticationSuccessHandler(authUtil)) // 登录成功处理器
//                        .failureHandler(new KAuthenticationFailureHandler()) // 登录失败处理器
//                        .permitAll()
//                )
                .addFilterAfter(new GlobalExceptionHandler(), LogoutFilter.class)

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
//                        .accessDeniedHandler(new KAccessDeniedHandler())
                                .authenticationEntryPoint(new KAuthenticationEntryPoint())
                );
        return http.build();
    }

    // 配置内存用户（仅示例，生产环境需用数据库）
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserService();
    }

    // 密码编码器（必须配置）
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}
