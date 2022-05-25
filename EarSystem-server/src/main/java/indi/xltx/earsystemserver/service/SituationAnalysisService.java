package indi.xltx.earsystemserver.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import indi.xltx.earsystemserver.dao.RecruitmentInfoMapper;

@Service
public class SituationAnalysisService {

    @Autowired
    private RecruitmentInfoMapper recruitmentInfoMapper;

    public List<Map<String, Integer>> analyzeByWorkTime() {
        return recruitmentInfoMapper.getRequirementGroupByWorkTime();
    }

    public List<Map<String, Float>> analyzeSalaryByWorkTime(String mode) {
        return recruitmentInfoMapper.getSalaryGroupByWorkTime(mode);
    }
}
