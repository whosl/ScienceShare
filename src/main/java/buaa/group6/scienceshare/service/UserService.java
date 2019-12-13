package buaa.group6.scienceshare.service;

import buaa.group6.scienceshare.Result.Result;
import buaa.group6.scienceshare.model.User;

public interface UserService {
    /**
     * 添加用户
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 修改密码
     * @param user
     */
    void changePswd(User user);

    /**
     * 通过用户名查找用户
     * @param username
     * @return User
     */
    User getUserByUsername(String username);

    void uploadAvatar(User user);

    void deleteUser(String username);

    void notify(String username1, String username2, String message, int type, String fatherId);

    void updateNotification(User user);

    Result readNotification(String username, int notiNo);

    Result readAllNotification(String username);

    void updatePrestige(String username, int val);

    int getIdentify(String username);

    Result authenticateExpert(String username);


}
