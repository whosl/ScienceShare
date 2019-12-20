package buaa.group6.scienceshare.controller;

import buaa.group6.scienceshare.Result.Result;
import buaa.group6.scienceshare.Result.ResultCode;
import buaa.group6.scienceshare.Result.ResultFactory;
import buaa.group6.scienceshare.model.College;
import buaa.group6.scienceshare.model.ExpertApplication;
import buaa.group6.scienceshare.model.User;
import buaa.group6.scienceshare.service.ExpertApplicationService;
import buaa.group6.scienceshare.service.UserService;
import buaa.group6.scienceshare.service.mongoRepository.CollegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ApplicationController {
    @Autowired
    ExpertApplicationService expertApplicationService;
    @Autowired
    CollegeRepository collegeRepository;
    @Autowired
    UserService userService;

    @RequestMapping(value = "sendApplication", method = RequestMethod.GET)
    public Result sendApplication(@RequestParam String username, String content, String authorId,
                                  String affiliation, String realName){
        ExpertApplication newApplication = new ExpertApplication();
        newApplication.setAffiliation(affiliation);
        newApplication.setApplyUserName(username);
        newApplication.setContent(content);
        newApplication.setAuthorId(authorId);
        newApplication.setRealName(realName);
        expertApplicationService.updateApplication(newApplication);
        return ResultFactory.buildSuccessResult("提交申请成功!");
    }

    @RequestMapping(value = "allApplication", method = RequestMethod.GET)
    public List<ExpertApplication> allApplication(){
        return expertApplicationService.allApplication();
    }

    @RequestMapping(value = "getCollegeByKeyword", method = RequestMethod.GET)
    public List<College> getCollegeByKeyword(@RequestParam String keyword){
        return collegeRepository.getTop5ByNameStartingWith(keyword);
    }
    @RequestMapping(value = "passApplication", method = RequestMethod.GET)
    public Result passApplication(@RequestParam String applyUserName,String authorId){
        User user = userService.getUserByUsername(applyUserName);
        if(user == null) return ResultFactory.buildResult(ResultCode.NOT_FOUND, "申请的用户不存在");
        user.setIdentity(2);
        user.setExpertID(authorId);
        userService.updateUser(user);
        List<ExpertApplication> expertApplications=expertApplicationService.getApplicationByApplyUserNameAndAuthorId(applyUserName,authorId);
        expertApplicationService.deleteApplication(expertApplications);
        return ResultFactory.buildSuccessResult("审核通过！");
    }
    @RequestMapping(value = "denyApplication", method = RequestMethod.GET)
    public Result denyApplication(@RequestParam String applyUserName,String authorId){
        User user = userService.getUserByUsername(applyUserName);
        if(user == null) return ResultFactory.buildResult(ResultCode.NOT_FOUND, "申请的用户不存在");
        List<ExpertApplication> expertApplications=expertApplicationService.getApplicationByApplyUserNameAndAuthorId(applyUserName,authorId);
        expertApplicationService.deleteApplication(expertApplications);
        return ResultFactory.buildSuccessResult("审核拒绝！");
    }
}
