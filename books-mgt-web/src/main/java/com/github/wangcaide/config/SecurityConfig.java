package com.github.wangcaide.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.wangcaide.common.enums.CommonStatus;
import com.github.wangcaide.entity.AccountEntity;
import com.github.wangcaide.entity.ResourceEntity;
import com.github.wangcaide.mapper.AccountMapper;
import com.github.wangcaide.mapper.ResourceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 权限配置类
 * </p>
 * @author caide
 * @version v1.0.0
 * @date 2022-10-15 01:17:35
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

    private final ResourceMapper resourceMapper;
    private final AccountMapper accountMapper;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests(authorize -> {
            authorize.antMatchers("/login", "/actuator/health", "/account/register").permitAll();
            // load all authority 加载所有的权限
            // 数据库resource表中配置的http method + url path，这些链接资源都是需要控制的，需要有权限的用户才能访问
            List<ResourceEntity> resourceList = resourceMapper.selectList(
                    new QueryWrapper<ResourceEntity>().isNotNull("resource_url")
                            .eq("status", CommonStatus.VALID.getDbValue())
            );
            for(ResourceEntity resource : resourceList) {
                String url = StringUtils.trim(resource.getResourceUrl());
                if (StringUtils.isBlank(resource.getResourceUrlMethod())) {
                    // 所有http method的链接都需要有 role_urlPath 的权限码才能访问
                    authorize.antMatchers(url, url+"/").hasAuthority(url);
                } else {
                    // 指定http method的链接都需要有 role_ + urlPath + method 的权限码才能访问
                    HttpMethod httpMethod = HttpMethod.resolve(resource.getResourceUrlMethod().toUpperCase());
                    String authority = resource.getResourceUrlMethod().toUpperCase() + "_" + url;
                    log.debug("----------------> authorize  method [{}], url[{}], authority[{}]",
                            httpMethod.name(), url, authority);
                    authorize.antMatchers(httpMethod, url, url+"/").hasAuthority(authority);
                }
            }
            authorize.anyRequest().authenticated();
        });

        http.formLogin()
//                .loginPage("/login")//用户未登录时，访问任何资源都转跳到该路径，即登录页面
                .loginProcessingUrl("/login")//登录表单form中action的地址，也就是处理认证请求的路径
                .defaultSuccessUrl("/doc.html")//登录认证成功后默认转跳的路径
                .and()
                .userDetailsService(userDetailsService())
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutUrl("/logout")
                .permitAll()
                .and()
                .csrf().disable()
                ;

        return http.build();
    }

    private UserDetailsService userDetailsService() {
        return username -> {
            // 认证
            AccountEntity account = accountMapper.selectOne(
                    new QueryWrapper<AccountEntity>()
                            .eq("username", username)
                            .eq("status", CommonStatus.VALID.getDbValue())
            );
            if (Objects.isNull(account)) {
                throw new UsernameNotFoundException("用户不存在");
            }
            // 授权
            List<SimpleGrantedAuthority> authorityList = resourceMapper.selectByUsername(username)
                    .stream().map(resource -> {
                        log.debug("----------------> username:{}, method:{}, url:{}", username, resource.getResourceUrlMethod(), resource.getResourceUrl());
                if (StringUtils.isBlank(resource.getResourceUrlMethod())) {
                    return new SimpleGrantedAuthority(resource.getResourceUrl());
                } else {
                    return new SimpleGrantedAuthority(
                            resource.getResourceUrlMethod().toUpperCase() + "_" + resource.getResourceUrl());
                }
            }).toList();
            return new User(username, account.getPassword(), authorityList);
        };
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> {
            web.ignoring()
                    .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
            web.ignoring().antMatchers(
                    "/swagger-resources/**",
                    "/swagger/**",
                    "/doc.html",
                    "/webjars/**",
                    "/static/**"
            );
        });
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //不使用密码加密
        //return NoOpPasswordEncoder.getInstance();
        //strength=10，即密钥的迭代次数(strength取值在4~31之间，默认为10)
        //return new BCryptPasswordEncoder(10);
        //利用工厂类PasswordEncoderFactories实现,工厂类内部采用的是委派密码编码方案.
        //return PasswordEncoderFactories.createDelegatingPasswordEncoder();

        //使用默认的BCryptPasswordEncoder加密方案
        return new BCryptPasswordEncoder();
    }


}
