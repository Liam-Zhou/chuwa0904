package org.fionagu.springsecuritydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration//配置类
@EnableWebSecurity//开启自定义的web security 配置

public class WebSecurityConfig  {
    /*默认情况下的ss配置*/
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        //开启授权保护
        http
                // CSRF configuration outside of authorizeRequests
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(
                        authorize -> authorize

                                //匹配这个url
                                .requestMatchers("/getLoginUser3")
                                //拥有这个权限的用户可以访问以上url
                                //URL-level security
                                //if a request doesn't pass the URL-level security filters, it will be blocked, and the method-level security will never even be reached.
                                .hasAuthority("Admin")
                                //所有请求
                                .anyRequest()
                                //都需要登陆，没有配置Matchers只要登录成功就可以访问
                                .authenticated()
                )
                .formLogin(withDefaults())
                .httpBasic(withDefaults());
        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService () {
        UserDetails user1 = User.builder()
                .username("student")
                .password(passwordEncoder().encode("88888888"))
                .roles("User")
                .build();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(user1);
        return manager;
    }

    /*密码编码器*/
    @Bean
    public PasswordEncoder passwordEncoder(){
        /*此处没有加密*/
        //return NoOpPasswordEncoder.getInstance();

        //加密
        return  new BCryptPasswordEncoder();
    }
}
