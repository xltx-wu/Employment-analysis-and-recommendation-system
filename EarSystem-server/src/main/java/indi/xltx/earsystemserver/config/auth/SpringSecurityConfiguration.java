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
        http.formLogin().permitAll() // ????????????
            .loginProcessingUrl("/login")
            .failureHandler(authenticationFailureHandler)//??????????????????
            .successHandler(authenticationSuccessHandler)//??????????????????
        .and()
            .logout().logoutSuccessHandler(logoutSuccessHandler).deleteCookies("JSESSIONID")//??????????????????
        .and()
                .authorizeRequests()
                .anyRequest().permitAll() // ????????????
                // .authenticated()// ???????????????
        .and()
            .csrf().disable(); //??????CSRF

        // http.sessionManagement().maximumSessions(1);//session??????
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