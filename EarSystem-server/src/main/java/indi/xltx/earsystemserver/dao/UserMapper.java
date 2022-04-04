package indi.xltx.earsystemserver.dao;

import indi.xltx.earsystemserver.pojo.MyUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Results(id = "users",value = {
            @Result(property = "uid",column = "uid"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "role",column = "role")
    })
    @Select("select * from users")
    List<MyUser> getUsers();

    @Delete("delete from users where username=#{username}")
    int deleteUser(String username);

    @Delete("delete from users where uid=#{uid}")
    int deleteUserByUid(long uid);

    @Insert("insert into users (username,password,role) values(#{username},#{password},#{role})")
    int addUser(String username,String password,String role);

    @Update("update users set username=#{username},password=#{password},role=#{role} where uid=#{uid}")
    int updateUser(long uid,String username,String password,String role);

    @ResultMap("users")
    @Select("select * from users where username=#{username}")
    MyUser getUser(String username);
}