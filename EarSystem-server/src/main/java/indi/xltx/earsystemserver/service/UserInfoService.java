package indi.xltx.earsystemserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import indi.xltx.earsystemserver.dao.UserInfoMapper;
import indi.xltx.earsystemserver.pojo.UserInfo;

@Service
public class UserInfoService {
    private UserInfoMapper userInfoMapper;

    public UserInfo getUerInfoByUid(long uid) {
        return userInfoMapper.getUserInfoByUid(uid);
    }

    public boolean updateUserInfo(UserInfo userInfo) {

        int i = userInfoMapper.updateUserInfo(
                userInfo.getUid(),
                userInfo.getName(),
                userInfo.getGender(),
                userInfo.getBirthday(),
                userInfo.getNationality(),
                userInfo.getAddress(),
                userInfo.getMobile(),
                userInfo.getEmail()
        );
        return i == 1;
    }

    @Autowired
    public void setUserInfoMapper(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }
}
