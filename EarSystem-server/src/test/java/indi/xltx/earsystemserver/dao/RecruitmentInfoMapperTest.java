package indi.xltx.earsystemserver.dao;

import org.junit.jupiter.api.Test;

public class RecruitmentInfoMapperTest {
    @Test
    void testGetLatestInfo() {
        String a = RecruitmentInfoMapper.RecruitmentInfoSqlProvider.getLatestInfo(30);
        System.out.println(a);
    }
}
