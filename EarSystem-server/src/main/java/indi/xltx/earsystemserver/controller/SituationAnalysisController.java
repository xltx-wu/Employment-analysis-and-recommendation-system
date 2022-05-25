package indi.xltx.earsystemserver.controller;

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
    public String analyzeByWorkTime() {
        return analysisService.analyzeByWorkTime().toString();
    }

    @RequestMapping(value = "/analyzeSalaryByWorktime", method = RequestMethod.GET)
    public String analyzeSalaryByWorkTime(@RequestParam String mode) {
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
        return analysisService.analyzeSalaryByWorkTime(myMode).toString();
    }
}
