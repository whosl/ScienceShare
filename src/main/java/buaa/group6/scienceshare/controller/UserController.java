package buaa.group6.scienceshare.controller;

import buaa.group6.scienceshare.Result.Result;
import buaa.group6.scienceshare.Result.ResultCode;
import buaa.group6.scienceshare.Result.ResultFactory;
import buaa.group6.scienceshare.model.ExpertApplication;
import buaa.group6.scienceshare.model.MagPaper;
import buaa.group6.scienceshare.model.User;
import buaa.group6.scienceshare.model.UserWithAvatar;
import buaa.group6.scienceshare.service.ExpertApplicationService;
import buaa.group6.scienceshare.service.MailService;
import buaa.group6.scienceshare.service.UserService;
import buaa.group6.scienceshare.service.mongoRepository.PaperRepository;
import buaa.group6.scienceshare.service.mongoRepository.UserRepository;
import buaa.group6.scienceshare.util.Md5SaltTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
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



    @RequestMapping(value = "login", method = RequestMethod.GET, produces = "application/json; charset = UTF-8")
    public Result login(@RequestParam String username, String password){
        User user = userService.getUserByUsername(username);

        try{
            if(user == null){
                return ResultFactory.buildResult(ResultCode.NotExist, "用户不存在！");
            }else if(!Md5SaltTool.validPassword(password,user.getPassword())){
                return ResultFactory.buildResult(ResultCode.INVALID_PASSWORD, "密码错误！");
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
            return ResultFactory.buildResult(ResultCode.HaveExist, "用户已存在！");
        }else if(username.equals("") || password.equals("")){
            return ResultFactory.buildFailResult(ResultCode.FAIL);
        }

        if(!rexCheckPassword(password))
            return ResultFactory.buildResult(ResultCode.INVALID_PASSWORD, "密码错误！");

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

    @RequestMapping(value = "changePassword", method = RequestMethod.GET)
    public Result changePassword(@RequestParam String username, String oldPassword, String newPassword){
        User user = userService.getUserByUsername(username);
        String encryptedPwd = null;
        try{
            if(!Md5SaltTool.validPassword(oldPassword,user.getPassword())){
                return ResultFactory.buildResult(ResultCode.INVALID_PASSWORD, "密码错误！");
            }
            if(!rexCheckPassword(newPassword)){
                return  ResultFactory.buildResult(ResultCode.INVALID_PASSWORD, "密码格式错误！");
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

    @RequestMapping(value = "getUser", method = RequestMethod.GET)
    public User getUser(@RequestParam String username){
        return userService.getUserByUsername(username);
    }

    @RequestMapping(value = "uploadAvatar", method = RequestMethod.GET)
    public Result uploadAvatar(@RequestParam String username, String avatarUrl){
        User user = userService.getUserByUsername(username);
        if(user == null) return ResultFactory.buildResult(ResultCode.NOT_FOUND, "用户不存在");
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

    @RequestMapping(value = "getIdentify", method = RequestMethod.GET)
    public int getPermission(@RequestParam String username){
        return userService.getIdentify(username);
    }

    @RequestMapping(value = "authenticateExpert", method = RequestMethod.GET)
    public Result changeToExpert(@RequestParam String username, String authorId){
        int p = userService.getIdentify(username);
        if(p == 2)return ResultFactory.buildFailResult("该专家已是已认证专家");
        userService.notify("我们", username, "已通过您的专家认证申请！", 1, "auth");
        return userService.authenticateExpert(username, authorId);
    }

    @RequestMapping(value = "allUnAuthExpert", method = RequestMethod.GET)
    public List<User> allUnAuthExpert(){
        return userRepository.getByIdentity(0);
    }

    @RequestMapping(value = "allAuthExpert", method = RequestMethod.GET)
    public List<User> allAuthExpert(){
        return userRepository.getByIdentity(2);
    }

    @RequestMapping(value = "follow", method = RequestMethod.GET)
    public Result follow(@RequestParam String followUserName, String myUserName){
        User followUser = userService.getUserByUsername(followUserName);
        if(followUser == null) return ResultFactory.buildResult(ResultCode.NOT_FOUND, "要关注的用户不存在");
        User myUser = userService.getUserByUsername(myUserName);
        if(myUser == null) return ResultFactory.buildResult(ResultCode.NOT_FOUND, "用户不存在");
        //update follow User
        List<String> followers =followUser.getFollowers();
        if(followers == null) followers = new ArrayList<>();
        else if(followers.contains(myUserName))
            return ResultFactory.buildResult(ResultCode.HaveExist, "已关注过该用户！");
        followers.add(myUserName);
        followUser.setFollowers(followers);
        userService.updateUser(followUser);
        //update my user
        List<String> following = myUser.getFollowing();
        if(following == null) following = new ArrayList<>();
        following.add(followUserName);
        myUser.setFollowing(following);
        userService.updateUser(myUser);
        return ResultFactory.buildSuccessResult("关注成功！");
    }

    @RequestMapping(value = "unFollow", method = RequestMethod.GET)
    public Result unFollow(@RequestParam String followUserName, String myUserName){
        User followUser = userService.getUserByUsername(followUserName);
        if(followUser == null) return ResultFactory.buildResult(ResultCode.NOT_FOUND, "要取消关注的用户不存在");
        User myUser = userService.getUserByUsername(myUserName);
        if(myUser == null) return ResultFactory.buildResult(ResultCode.NOT_FOUND, "用户不存在");
        //update follow User
        List<String> followers =followUser.getFollowers();
        if(followers == null || !followers.contains(myUserName))
            return ResultFactory.buildResult(ResultCode.NotExist, "未关注该用户");
        else followers.remove(myUserName);
        followUser.setFollowers(followers);
        userService.updateUser(followUser);
        //update my user
        List<String> following = myUser.getFollowing();
        if(following == null || !following.contains(followUserName))
            return ResultFactory.buildResult(ResultCode.NotExist, "未关注该用户");
        else following.remove(followUserName);
        myUser.setFollowing(following);
        userService.updateUser(myUser);
        return ResultFactory.buildSuccessResult("取消关注成功！");
    }


    /**
     * 判断当前是否follow某个用户
     * @param followUserName ：被follow的用户
     * @Param myUserName ：follow的用户
     * @return int : 若已follow，返回1，否则返回0
     */
    @RequestMapping(value = "isFollowing", method = RequestMethod.GET)
    public int isFollowing(@RequestParam String followUserName, String myUserName){
        List<String> followers = userRepository.getByUsername(followUserName).getFollowers();
        if(followers.contains(myUserName)) return 1;
        else return 0;
    }

    @RequestMapping(value = "allFollowing", method = RequestMethod.GET)
    public List<UserWithAvatar> allFollowing(@RequestParam String username){
        List<String> following = userService.getUserByUsername(username).getFollowing();
        List<UserWithAvatar> users = new ArrayList<>();
        for(String name : following){
            UserWithAvatar userWithAvatar = new UserWithAvatar(name, getAvatarByUsername(name));
            users.add(userWithAvatar);
        }
        return users;
    }

    @RequestMapping(value = "allFollowers", method = RequestMethod.GET)
    public List<UserWithAvatar> allFollowers(@RequestParam String username){
        List<String> followers = userService.getUserByUsername(username).getFollowers();
        List<UserWithAvatar> users = new ArrayList<>();
        for(String name : followers){
            UserWithAvatar userWithAvatar = new UserWithAvatar(name, getAvatarByUsername(name));
            users.add(userWithAvatar);
        }
        return users;
    }

}
