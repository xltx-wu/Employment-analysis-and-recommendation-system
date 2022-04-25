package indi.xltx.earsystemserver.dao;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;

public class RecruitmentInfoMapperTest {
    @Test
    void testGetLatestInfo() {
        String a = RecruitmentInfoMapper.RecruitmentInfoSqlProvider.getLatestInfo(30);
        System.out.println(a);
    }
}
