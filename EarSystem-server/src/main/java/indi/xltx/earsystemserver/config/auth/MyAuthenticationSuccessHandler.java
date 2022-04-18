package indi.xltx.earsystemserver.config.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


//登录成功处理器
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(
                "{'userdetails':{'username':" + user.getUsername() // 用户名
                        + ",'Enabled':" + user.isEnabled() // 账号是否激活
                        + ",'AccountNonExpired':" + user.isAccountNonExpired() // 账号是否未过期
                        + ",'credentialsNonExpired':" + user.isCredentialsNonExpired()// 密码是否未过期
                        + ",'AccountNonLocked':" + user.isAccountNonLocked() // 账号是否未锁定
                        + ",'Authorities':" + user.getAuthorities() // 权限
                        + "},'success':ture}"
        );
        System.out.println(user);
    }

}
