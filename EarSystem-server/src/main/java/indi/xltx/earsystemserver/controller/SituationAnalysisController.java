package indi.xltx.earsystemserver.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import indi.xltx.earsystemserver.service.SituationAnalysisService;

@RestController
@RequestMapping(value = "/situation", method = RequestMethod.GET)
public class SituationAnalysisController {

    @Autowired
    private SituationAnalysisService analysisService;

    @RequestMapping(value = "/analyzeByWorkTime", method = RequestMethod.GET)
    public List<Map<String, ?>> analyzeByWorkTime() {
        return analysisService.analyzeByWorkTime();
    }

    @RequestMapping(value = "/analyzeSalaryByWorktime", method = RequestMethod.GET)
    public List<Map<String, ?>> analyzeSalaryByWorkTime(@RequestParam String mode) {
        String myMode = "avg";
        switch (mode) {
            case "max":
                myMode = "max";
                break;
            case "min":
                myMode = "min";
                break;
            default:
                myMode = "avg";
                break;
        }
        return analysisService.analyzeSalaryByWorkTime(myMode);
    }

    @RequestMapping(value = "/analyzeByIndustry", method = RequestMethod.GET)
    public List<Map<String, ?>> analyzeByIndustry() {
        return analysisService.analyzeByIndustry();
    }

    @RequestMapping(value = "/analyzeByProfession", method = RequestMethod.GET)
    public List<Map<String, ?>> analyzeByProfession() {
        return analysisService.analyzeByProfession();
    }

    @RequestMapping(value = "/analyzeByIndustryAndWorktime", method = RequestMethod.GET)
    public Map<String, ?> analyzeByIndustryAndWorktime() {
        return analysisService.analyzeByIndustryAndWorkTime();
    }

    @RequestMapping(value = "/analyzeSalaryByIndustry", method = RequestMethod.GET)
    public List<Map<String, ?>> analyzeSalaryByIndustry() {
        return analysisService.analyzeSalaryByIndustry();
    }

    @RequestMapping(value = "/analyzeSalaryByProfession", method = RequestMethod.GET)
    public List<Map<String, ?>> analyzeSalaryByProfession() {
        return analysisService.analyzeSalaryByProfession();
    }
}
