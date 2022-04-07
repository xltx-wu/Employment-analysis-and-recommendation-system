package indi.xltx.earsystemserver.config.auth;


import indi.xltx.earsystemserver.service.impl.MyUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
@EnableWebSecurity
@Configuration
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private MyUserDetailsService myUserDetailsService;
    private MyPasswordEncode myPasswordEncode;
    private MyAuthenticationFailureHandler authenticationFailureHandler;
    private MyAuthenticationSuccessHandler authenticationSuccessHandler;
    private MyLogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    public void setAuthenticationFailureHandler(MyAuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    @Autowired
    public void setAuthenticationSuccessHandler(MyAuthenticationSuccessHandler authenticationSuccessHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Autowired
    public void setLogoutSuccessHandler(MyLogoutSuccessHandler logoutSuccessHandler) {
        this.logoutSuccessHandler = logoutSuccessHandler;
    }

    @Autowired
    public void setMyUserDetailsService(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @Autowired
    public void setMyPasswordEncode(MyPasswordEncode myPasswordEncode) {
        this.myPasswordEncode = myPasswordEncode;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().permitAll() // 表单登录
            .loginProcessingUrl("/login")
            .failureHandler(authenticationFailureHandler)//登录失败处理
            .successHandler(authenticationSuccessHandler)//登录成功处理
        .and()
            .logout().logoutSuccessHandler(logoutSuccessHandler).deleteCookies("JSESSIONID")//登出成功处理
        .and()
            .authorizeRequests().antMatchers("/register").permitAll()
            .anyRequest()  // 所有请求
            .authenticated()// 都需要认证
        .and()
            .csrf().disable(); //禁用CSRF

        http.sessionManagement().maximumSessions(1);//session限定
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth.userDetailsService(myUserDetailsService).passwordEncoder(myPasswordEncode);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
}