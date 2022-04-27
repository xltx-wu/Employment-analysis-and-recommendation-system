package indi.xltx.earsystemserver.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import indi.xltx.earsystemserver.dao.RecruitmentInfoMapper;
import indi.xltx.earsystemserver.pojo.RecruitmentInfo;

@RestController
@RequestMapping("/jobinfo")
public class RecruitmentInfoController {

    @Autowired
    SqlSessionFactory sessionFactory;

    @RequestMapping("/getbykey")
    public List<RecruitmentInfo> getInfoByKeyWord(
            @RequestParam("city") String city,
            @RequestParam("worktime") int workTime,
            @RequestParam("industry") String industry,
            @RequestParam("offset") int offset,
            @RequestParam("rows") int rows) {

        List<RecruitmentInfo> mList = new ArrayList<RecruitmentInfo>();
        try (
                SqlSession session = sessionFactory.openSession();
                Cursor<RecruitmentInfo> mCursor = session.getMapper(RecruitmentInfoMapper.class)
                        .getInfoByKeyWord(city, workTime, industry, offset, rows)) {

            for (RecruitmentInfo recruitmentInfo : mCursor) {
                mList.add(recruitmentInfo);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return mList;
    }
}
