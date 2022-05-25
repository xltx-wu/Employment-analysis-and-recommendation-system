package indi.xltx.earsystemserver.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import indi.xltx.earsystemserver.dao.RecruitmentInfoMapper;
import indi.xltx.earsystemserver.pojo.RecruitmentInfo;

@Service
public class RecruitmentInfoService {

    @Autowired
    private SqlSessionFactory factory;

    @Autowired
    private RecruitmentInfoMapper recruitmentInfoMapper;

    private List<RecruitmentInfo> latestInfoList;
    private List<String> cityList;
    private List<String> industryList;

    /**
     * @return the industryList
     */
    public List<String> getIndustryList() {
        return industryList;
    }

    @Scheduled(cron = "0 0 0/1 * * ?")
    @Async("myTaskAsyncPool")
    public void setIndustryList() {
        this.industryList = recruitmentInfoMapper.getIndustryList();
    }

    /**
     * @return the cityList
     */
    public List<String> getCityList() {
        return cityList;
    }

    @Scheduled(cron = "0 0 0/1 * * ?")
    @Async("myTaskAsyncPool")
    public void setCityList() {
        this.cityList = recruitmentInfoMapper.getCityList();
    }

    /**
     * @return the latestInfoList
     */
    public List<RecruitmentInfo> getLatestInfoList() {
        return latestInfoList;
    }

    // 每一个小时执行一次
    @Scheduled(cron = "0 0 0/1 * * ?")
    @Async("myTaskAsyncPool")
    public void setLatestInfoList() {

        List<RecruitmentInfo> mList = new ArrayList<RecruitmentInfo>();
        try (SqlSession session = factory.openSession();
                Cursor<RecruitmentInfo> cursor = session
                        .getMapper(RecruitmentInfoMapper.class)
                        .getLatestInfo(100)) {
            for (RecruitmentInfo recruitmentInfo : cursor) {
                mList.add(recruitmentInfo);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        if (mList.size() < 1) {
            this.latestInfoList = null;
        } else {
            this.latestInfoList = mList;
        }
    }

}
