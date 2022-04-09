package indi.xltx.earsystemserver.pojo;

import java.sql.Date;

public record UserInfo(
        long uid,
        String name,
        String gender,
        Date birthday,
        String nationality,
        String address,
        String mobile,
        String email
    ){

}
