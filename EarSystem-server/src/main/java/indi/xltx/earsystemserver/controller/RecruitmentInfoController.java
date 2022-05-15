package indi.xltx.earsystemserver.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public List<RecruitmentInfo> getInfoByKeyWord(@RequestBody tempJobInfo info) {
        System.out.println(info);

        List<RecruitmentInfo> mList = new ArrayList<RecruitmentInfo>();
        try (
                SqlSession session = sessionFactory.openSession();
                Cursor<RecruitmentInfo> mCursor = session.getMapper(RecruitmentInfoMapper.class)
                        .getInfoByKeyWord(info.city, info.workTime, info.industry, info.minSalary, info.maxSalary,
                                info.offset, info.rows)) {
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

    public static record tempJobInfo(
            String city,
            Integer workTime,
            String industry,
            Integer minSalary,
            Integer maxSalary,
            Integer offset,
            Integer rows) {
    }
}
