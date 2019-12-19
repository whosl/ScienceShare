package buaa.group6.scienceshare.service;

import buaa.group6.scienceshare.Result.Result;
import buaa.group6.scienceshare.Result.ResultCode;
import buaa.group6.scienceshare.Result.ResultFactory;
import buaa.group6.scienceshare.model.Notification;
import buaa.group6.scienceshare.model.User;
import buaa.group6.scienceshare.service.mongoRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class UserServiceImpl implements UserService{
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    UserRepository userRepository;

    @Override
    public int addUser(User user) {
        user.setCreatedDate(LocalDateTime.now());//register date
        mongoTemplate.insert(user);
        return 1;
    }

    @Override
    public void changePswd(User user) {
        userRepository.save(user);//可能存在稳定性的问题，后期考虑是否修改。
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    @Override
    public void uploadAvatar(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(String username) {
        userRepository.delete(getUserByUsername(username));
    }

    @Override
    public void notify(String actionUsername, String notifyUsername, String message, int type, String fatherId) {//user1 操作的用户，user2 接受通知的用户
        if(actionUsername.equals(notifyUsername));
        else{
            Notification notification = new Notification();
            notification.setNotifiDate(LocalDateTime.now());
            notification.setMessage(message);
            notification.setUsername(actionUsername);
            notification.setFatherId(fatherId);
            notification.setType(type);
            User user = getUserByUsername(notifyUsername);//接受通知的用户
            user.setUnreadNotification(user.getUnreadNotification()+1);//未读通知加1
            List<Notification> notifications = user.getNotifications();
            if(notifications == null){
                List<Notification> notificationList = new ArrayList<>();
                notificationList.add(notification);
                user.setNotifications(notificationList);
                notification.setNotifiNo(0);
            }else{
                notification.setNotifiNo(notifications.size());
                notifications.add(notification);
            }
            updateNotification(user);//更新数据库中的通知列表
        }
    }

    public void updateNotification(User user){
        userRepository.save(user);
    }

    @Override
    public Result readNotification(String username, int notiNo) {
        User user = getUserByUsername(username);
        Notification notification = user.getNotifications().get(notiNo);
        if(notification == null)return ResultFactory.buildFailResult(ResultCode.NOT_FOUND);
        if(notification.getRead() == 1)return ResultFactory.buildFailResult(ResultCode.HaveExist);//已读，操作失败
        notification.setRead(1);
        user.setUnreadNotification(user.getUnreadNotification()-1);
        updateNotification(user);
        return ResultFactory.buildSuccessResult("信息已读");
    }

    @Override
    public Result readAllNotification(String username){
        User user = getUserByUsername(username);
        List<Notification> notifications = user.getNotifications();
        if(notifications == null)ResultFactory.buildResult(ResultCode.NOT_FOUND, "未找到任何消息");//
        for(Notification n : notifications){
            if(n.getRead() == 0) n.setRead(1);//标为已读
            user.setUnreadNotification(user.getUnreadNotification()-1);
        }
        if(user.getUnreadNotification() == 0){
            updateNotification(user);
            return ResultFactory.buildSuccessResult("全部标为已读！");
        }
        else return ResultFactory.buildFailResult("操作失败！");
    }

    @Override
    public void updatePrestige(String username, int val) {
        User user = getUserByUsername(username);
        user.setPrestige(user.getPrestige() + val);
        userRepository.save(user);
    }

    @Override
    public int getIdentify(String username) {
        return getUserByUsername(username).getIdentity();
    }

    @Override
    public Result authenticateExpert(String username, String authorId) {
        User user = getUserByUsername(username);
        user.setIdentity(2);
        user.setAuthorId(authorId);
        userRepository.save(user);
        return ResultFactory.buildSuccessResult("专家认证通过！");
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void changeMail(User user) {
        userRepository.save(user);
    }

    @Override
    public void changeUsername(User user) {
        userRepository.save(user);
    }
}
