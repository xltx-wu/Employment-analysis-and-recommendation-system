package indi.xltx.earsystemserver.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import indi.xltx.earsystemserver.dao.RecruitmentInfoMapper;

@Service
public class SituationAnalysisService {

    @Autowired
    private RecruitmentInfoMapper recruitmentInfoMapper;

    public List<Map<String, ?>> analyzeByWorkTime() {
        return recruitmentInfoMapper.getRequirementGroupByWorkTime();
    }

    public List<Map<String, ?>> analyzeSalaryByWorkTime(String mode) {
        return recruitmentInfoMapper.getSalaryGroupByWorkTime(mode);
    }

    public List<Map<String, ?>> analyzeByIndustry() {
        return recruitmentInfoMapper.getRequirementGroupByIndustry();
    }

    public List<Map<String, ?>> analyzeByProfession() {
        return recruitmentInfoMapper.getRequirementGroupByProfession();
    }

    public Map<String, ?> analyzeByIndustryAndWorkTime() {
        Map<String, Object> myMap = new HashMap<String, Object>();
        List<String> industryList = recruitmentInfoMapper.getIndustryList();

        for (String industry : industryList) {
            List<Map<String, ?>> myList = new ArrayList<Map<String, ?>>();
            for (int i = 0; i < 10; i++) {
                Integer count = recruitmentInfoMapper.getRequirementByIndustryAndWorkTime(industry, i);
                myList.add(Map.of("workTime", i, "value", count == null ? 0 : count));
            }
            myMap.put(industry, myList);
        }
        return myMap;
    }

    public List<Map<String, ?>> analyzeSalaryByIndustry() {
        return recruitmentInfoMapper.getSalaryGroupByIndustry();
    }

    public List<Map<String, ?>> analyzeSalaryByProfession() {
        return recruitmentInfoMapper.getSalaryGroupByProfession();
    }
}
