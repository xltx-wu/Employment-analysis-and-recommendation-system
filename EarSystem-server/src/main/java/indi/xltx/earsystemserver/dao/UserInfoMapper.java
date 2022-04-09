package indi.xltx.earsystemserver.dao;

import java.sql.Date;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import indi.xltx.earsystemserver.pojo.UserInfo;

@Mapper
public interface UserInfoMapper {

    @Select("select * from user_info where uid=#{uid}")
    UserInfo getUserInfoByUid(long uid);

    @Update("update user_info set name=#{name},gender=#{gender},birthday=#{birthday},nationality=#{nationality},address=#{address},mobile=#{mobile},email=#{email} where uid=#{uid}")
    int updateUserInfo(
            @Param("uid") long uid,
            @Param("name") String username,
            @Param("gender") String gender,
            @Param("birthday") Date birthday,
            @Param("nationality") String nationality,
            @Param("address") String address,
            @Param("mobile") String mobile,
            @Param("email") String email
        );
}
