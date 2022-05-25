package indi.xltx.earsystemserver.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import indi.xltx.earsystemserver.pojo.RecruitmentInfo;

@MybatisTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class RecruitmentInfoMapperTest {

    @Autowired
    private SqlSessionFactory factory;

    @Autowired
    private RecruitmentInfoMapper mapper;

    @BeforeEach
    void start() {
        System.out.println("-----------------------开始---------------------------");

    }

    @AfterEach
    void end() {
        System.out.println("-----------------------结束---------------------------");
    }

    @Test
    @DisplayName("获取最新招聘信息")
    void testGetLatestInfo() {
        List<RecruitmentInfo> mList = new ArrayList<RecruitmentInfo>();
        try (SqlSession session = factory.openSession();
                Cursor<RecruitmentInfo> mCursor = session
                        .getMapper(RecruitmentInfoMapper.class)
                        .getLatestInfo(100)) {
            for (RecruitmentInfo recruitmentInfo : mCursor) {
                mList.add(recruitmentInfo);
            }
            System.out.println(mList.size());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    void testGetCityList() {

    }

    @Test
    void testGetCommonInfoById() {

    }

    @Test
    void testGetIndustryList() {

    }

    @Test
    void testGetInfoByKeyWord() {

    }

    @Test
    void testGetRequirementGroupByIndustry() {

    }

    @Test
    void testGetRequirementGroupByWorkTime() {
        System.out.println(mapper.getRequirementGroupByWorkTime());
    }

    @Test
    void testGetSalaryGroupByWorkTime() {

        System.out.println(mapper.getSalaryGroupByWorkTime("avg"));

    }
}
