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
import indi.xltx.earsystemserver.service.RecruitmentInfoService;

@RestController
@RequestMapping("/jobinfo")
public class RecruitmentInfoController {

    @Autowired
    SqlSessionFactory sessionFactory;

    @Autowired
    RecruitmentInfoMapper infoMapper;

    @Autowired
    RecruitmentInfoService service;

    // 通过关键字段筛选
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

    // 获取行业列表
    @RequestMapping(value = "/getindustrylist", method = RequestMethod.POST)
    public List<String> getIndustryList() {
        List<String> mList = new ArrayList<String>();
        mList = infoMapper.getIndustryList();
        return mList;
    }

    // 获取城市列表
    @RequestMapping(value = "/getcitylist", method = RequestMethod.POST)
    public List<String> getCityList() {
        return service.getCityList();
    }

    // 获取最新的招聘信息
    @RequestMapping(value = "/getlatestinfo", method = RequestMethod.POST)
    public List<RecruitmentInfo> getLatestInfo() {
        return service.getLatestInfoList();
    }
}
