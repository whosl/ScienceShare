package buaa.group6.scienceshare.controller;

import buaa.group6.scienceshare.Result.Result;
import buaa.group6.scienceshare.Result.ResultCode;
import buaa.group6.scienceshare.Result.ResultFactory;
import buaa.group6.scienceshare.model.User;
import buaa.group6.scienceshare.service.MailService;
import buaa.group6.scienceshare.service.UserService;
import buaa.group6.scienceshare.service.mongoRepository.UserRepository;
import buaa.group6.scienceshare.util.Md5SaltTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    MailService mailService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;

//    @CrossOrigin
//    @RequestMapping(value="addUser", method = RequestMethod.POST)
//    public int addUser(@RequestBody User user) {
//        return userService.addUser(user);
//    }

    @RequestMapping(value="/testEureka",method= RequestMethod.GET)
    public String serviceA() {
        String serviceA = restTemplate.getForEntity("http://bs/serviceB", String.class).getBody();
        return "SCIENCESHARE " + serviceA;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET, produces = "application/json; charset = UTF-8")
    public Result login(@RequestParam String username, String password){
        User user = userService.getUserByUsername(username);

        try{
            if(user == null){
                return ResultFactory.buildFailResult(ResultCode.NotExist);
            }else if(!Md5SaltTool.validPassword(password,user.getPassword())){
                return ResultFactory.buildFailResult(ResultCode.INVALID_PASSWORD);
            }
        }catch (NoSuchAlgorithmException | UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return ResultFactory.buildSuccessResult("登录成功");
    }

    @RequestMapping(value = "sendPin", method = RequestMethod.GET)
    public Result sendPin(@RequestParam String emailAddress){
        System.out.println(emailAddress);
        List<User> users = userRepository.getByEmailAddress(emailAddress);
        if(!users.isEmpty())return ResultFactory.buildFailResult(ResultCode.EMAILOCCUPIED);
        return mailService.sendPin(emailAddress);
    }


    @RequestMapping(value = "register", method = RequestMethod.GET)
    public Result register(@RequestParam String username, String password, String emailAddress){
        User user = userService.getUserByUsername(username);

        if(user != null){
            return ResultFactory.buildFailResult(ResultCode.HaveExist);
        }else if(username.equals("") || password.equals("")){
            return ResultFactory.buildFailResult(ResultCode.FAIL);
        }

        if(!rexCheckPassword(password))
            return ResultFactory.buildFailResult(ResultCode.INVALID_PASSWORD);

        User user1 = new User();
        String encryptedPwd = null;
        try{
            encryptedPwd = Md5SaltTool.getEncryptedPwd(password);
            user1.setUsername(username);
            user1.setPassword(encryptedPwd);
            user1.setEmailAddress(emailAddress);
        }catch (NoSuchAlgorithmException | UnsupportedEncodingException e){
            e.printStackTrace();
        }

        userService.addUser(user1);
        return ResultFactory.buildSuccessResult("注册成功");
    }

    /**
     * 正则表达式验证密码
     * @param input
     * @return
     */
    private static boolean rexCheckPassword(String input) {
        // 6-20 位，字母、数字、字符
        String regStr = "^([A-Z]|[a-z]|[0-9]|[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）――+|{}【】‘；：”“'。，、？]){6,20}$";
        return input.matches(regStr);
    }

    @RequestMapping(value = "changepswd", method = RequestMethod.GET)
    public Result changePassword(@RequestParam String username, String oldPassword, String newPassword){
        User user = userService.getUserByUsername(username);
        String encryptedPwd = null;
        try{
            if(!Md5SaltTool.validPassword(oldPassword,user.getPassword())){
                return ResultFactory.buildFailResult(ResultCode.FAIL);
            }
            if(!rexCheckPassword(newPassword)){
                return  ResultFactory.buildFailResult(ResultCode.INVALID_PASSWORD);
            }
            encryptedPwd = Md5SaltTool.getEncryptedPwd(newPassword);
            user.setPassword(encryptedPwd);
            userService.changePswd(user);
        }catch (NoSuchAlgorithmException | UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return ResultFactory.buildSuccessResult("修改密码成功");
    }
    /**
     *向前端返回注册时间
     * @return String
     */
    @RequestMapping(value = "getCreateTime", method = RequestMethod.GET)
    public String getTime(@RequestParam String username) {
        return userService.getUserByUsername(username).getCreatedDate().toString();
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public User getUser(@RequestParam String username){
        return userService.getUserByUsername(username);
    }

    @RequestMapping(value = "uploadAvatar", method = RequestMethod.GET)
    public Result uploadAvatar(@RequestParam String username, String avatarUrl){
        User user = userService.getUserByUsername(username);
        if(user == null) return ResultFactory.buildFailResult(ResultCode.NOT_FOUND);
        user.setAvatarUrl(avatarUrl);
        userService.uploadAvatar(user);
        return ResultFactory.buildSuccessResult("头像上传成功");
    }
//    @RequestMapping(value = "time", method = RequestMethod.POST)
//    public String getTime(@RequestBody String username) {
//        return userService.getUserByUsername(username).getCreatedDate().toString();
//    }

    @RequestMapping(value = "deleteUser", method = RequestMethod.POST)
    public void deleteUser(@RequestParam String username){
        System.out.println(userService.getUserByUsername(username).getId());
        userService.deleteUser(username);
    }

    @RequestMapping(value = "readNotifi", method = RequestMethod.GET)
    public Result readNotification(@RequestParam String username, @RequestParam int notiNo){
        return userService.readNotification(username, notiNo);
    }

    @RequestMapping(value = "getAvatarUrl", method = RequestMethod.GET)
    public String getAvatarByUsername(@RequestParam String username){
        return userRepository.getByUsername(username).getAvatarUrl();
    }

    @RequestMapping(value = "getUnreadNotifiNum", method = RequestMethod.GET)
    public int getUnreadNotifiNum(@RequestParam String username){
        User user = userRepository.getByUsername(username);
        return user.getUnreadNotification();
    }

    @RequestMapping(value = "readAllNotification", method = RequestMethod.GET)
    public Result readAllNotification(@RequestParam String username){
        return userService.readAllNotification(username);
    }

    @RequestMapping(value = "getPermission", method = RequestMethod.GET)
    public int getPermission(@RequestParam String username){
        return userService.getPermission(username);
    }

    @RequestMapping(value = "muteUser", method = RequestMethod.GET)
    public Result muteUser(@RequestParam String username){
        userService.notify("管理员", username, "已将你禁言！", 3, "mute");
        return userService.muteUser(username);
    }

    @RequestMapping(value = "unmuteUser", method = RequestMethod.GET)
    public Result unmuteUser(@RequestParam String username){
        userService.notify("管理员", username, "已将你解除禁言！", 6, "unmute");
        return userService.unmuteUser(username);
    }

    @RequestMapping(value = "allMutedUser", method = RequestMethod.GET)
    public List<User> allMutedUser(){
        return userRepository.getByIdentity(0);
    }
}
