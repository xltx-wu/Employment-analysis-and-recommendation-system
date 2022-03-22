package indi.xltx.earsystemserver.dao;

import indi.xltx.earsystemserver.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Results(id = "user",value = {
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password")
    })
    @Select("select * from user")
    List<User> getUsers();

    @Delete("delete from user where username=#{username}")
    int deleteUser(String username);

    @Insert("insert into user (username,password) values(#{username},#{password})")
    int addUser(String username,String password);
}