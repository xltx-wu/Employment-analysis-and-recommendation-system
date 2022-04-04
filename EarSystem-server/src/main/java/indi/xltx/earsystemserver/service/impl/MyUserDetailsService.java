package indi.xltx.earsystemserver.service.impl;

import indi.xltx.earsystemserver.pojo.MyUser;
import indi.xltx.earsystemserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser =userService.getUserByName(username);
        if (myUser ==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        return new User(myUser.getUsername(),myUser.getPassword(),createAuthority(myUser.getRole()));
    }

    //这里是将数据库的角色分割，构造GrantedAuthority
    private List<SimpleGrantedAuthority> createAuthority(String roles) {
        String[] roleArray = roles.split(",");
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        for (String role : roleArray) {
            authorityList.add(new SimpleGrantedAuthority(role));
        }
        return authorityList;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}