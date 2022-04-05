package indi.xltx.earsystemserver.config.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyPasswordEncode implements PasswordEncoder {

    private BCryptPasswordEncoder bEncoder=new BCryptPasswordEncoder();

    @Override
    public String encode(CharSequence rawPassword) {
        return bEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return bEncoder.matches(rawPassword, encodedPassword);
    }

}
