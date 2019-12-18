package buaa.group6.scienceshare.controller;

import buaa.group6.scienceshare.Result.Result;
import buaa.group6.scienceshare.Result.ResultFactory;
import buaa.group6.scienceshare.model.College;
import buaa.group6.scienceshare.model.ExpertApplication;
import buaa.group6.scienceshare.service.ExpertApplicationService;
import buaa.group6.scienceshare.service.mongoRepository.CollegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApplicationController {
    @Autowired
    ExpertApplicationService expertApplicationService;
    @Autowired
    CollegeRepository collegeRepository;

    @RequestMapping(value = "sendApplication", method = RequestMethod.GET)
    public Result sendApplication(@RequestParam String username, String content, String authorId, String affiliation){
        ExpertApplication newApplication = new ExpertApplication();
        newApplication.setAffiliation(affiliation);
        newApplication.setApplyUserName(username);
        newApplication.setAuthorId(authorId);
        expertApplicationService.updateApplication(newApplication);
        return ResultFactory.buildSuccessResult("提交申请成功!");
    }

    @RequestMapping(value = "allApplication", method = RequestMethod.GET)
    public List<ExpertApplication> allApplication(){
        return expertApplicationService.allApplication();
    }

    @RequestMapping(value = "getCollegeByKeyword", method = RequestMethod.GET)
    public List<College> getCollegeByKeyword(@RequestParam String keyword){
        return collegeRepository.getAllByNameContaining(keyword);
    }
}
