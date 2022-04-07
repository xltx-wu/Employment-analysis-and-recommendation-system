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
                "{username:" + user.getUsername()
                        + ",Enabled:" + user.isEnabled()
                        + ",AccountNonExpired:" + user.isAccountNonExpired()
                        + ",credentialsNonExpired:" + user.isCredentialsNonExpired()
                        + ",AccountNonLocked:" + user.isAccountNonLocked()
                        + ",Authorities:" + user.getAuthorities()
                        + ",success:ture}"
        );
    }

}
