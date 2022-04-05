package indi.xltx.earsystemserver.service;

import indi.xltx.earsystemserver.dao.UserMapper;
import indi.xltx.earsystemserver.pojo.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService{
    private UserMapper userMapper;

    public MyUser getUserByName(String username) {
        return userMapper.getUser(username);
    }

    public boolean addUser(MyUser user) {
        //返回值为1则添加成功
        int i = userMapper.addUser(user.getUsername(), user.getPassword(), user.getRole());
        return i == 1;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}