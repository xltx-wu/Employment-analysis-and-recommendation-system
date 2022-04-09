package indi.xltx.earsystemserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import indi.xltx.earsystemserver.dao.UserInfoMapper;
import indi.xltx.earsystemserver.pojo.UserInfo;

@Service
public class UserInfoService {
    private UserInfoMapper userInfoMapper;

    UserInfo getUerInfoByUid(long uid) {
        return userInfoMapper.getUserInfoByUid(uid);
    }

    boolean updateUserInfo(UserInfo userInfo) {

        int i = userInfoMapper.updateUserInfo(
            userInfo.uid(),
            userInfo.name(),
            userInfo.gender(),
            userInfo.birthday(),
            userInfo.nationality(),
            userInfo.address(),
            userInfo.mobile(),
            userInfo.email()
        );
        return i == 1;
    }

    @Autowired
    public void setUserInfoMapper(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }
}
