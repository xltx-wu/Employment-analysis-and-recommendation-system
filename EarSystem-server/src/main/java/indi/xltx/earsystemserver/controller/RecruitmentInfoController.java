package indi.xltx.earsystemserver.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import indi.xltx.earsystemserver.dao.RecruitmentInfoMapper;
import indi.xltx.earsystemserver.pojo.RecruitmentInfo;

@RestController
@RequestMapping("/jobinfo")
public class RecruitmentInfoController {

    @Autowired
    SqlSessionFactory sessionFactory;

    @Autowired
    RecruitmentInfoMapper infoMapper;

    @RequestMapping(value = "/getbykey", method = RequestMethod.POST)
    public List<RecruitmentInfo> getInfoByKeyWord(
            @RequestParam("city") String city,
            @RequestParam("worktime") Integer workTime,
            @RequestParam("industry") String industry,
            @RequestParam("minSalary") Integer minSalary,
            @RequestParam("maxSalary") Integer maxSalary,
            @RequestParam("offset") Integer offset,
            @RequestParam("rows") Integer rows) {

        List<RecruitmentInfo> mList = new ArrayList<RecruitmentInfo>();
        try (
                SqlSession session = sessionFactory.openSession();
                Cursor<RecruitmentInfo> mCursor = session.getMapper(RecruitmentInfoMapper.class)
                        .getInfoByKeyWord(city, workTime, industry, minSalary, maxSalary, offset, rows)) {

            for (RecruitmentInfo recruitmentInfo : mCursor) {
                mList.add(recruitmentInfo);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return mList;
    }

    @RequestMapping(value = "/getindustrylist", method = RequestMethod.POST)
    public List<String> getIndustryList() {
        List<String> mList = new ArrayList<String>();
        mList = infoMapper.getIndustryList();
        return mList;
    }

    @RequestMapping(value = "/getcitylist", method = RequestMethod.POST)
    public List<String> getCityList() {
        List<String> mList = new ArrayList<String>();
        mList = infoMapper.getCityList();
        return mList;
    }
}
