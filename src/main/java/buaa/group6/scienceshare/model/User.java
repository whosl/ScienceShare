package buaa.group6.scienceshare.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "user")
public class User {
    @Id
    private String id;

    private String username;

    private String password;

    private int identity = 1;//0为未认证专家，1为普通用户，2为已认证科技专家，3为管理员

    private List<Notification> notifications;//通知列表

    private int unreadNotification = 0;//未读消息数

    private String emailAddress;

    private int prestige;//todo

    //@CreatedDate
    private LocalDateTime createdDate;

    private String avatarUrl = "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png";

    private List<String> followers;

    private List<String> following;

    private String authorId;//普通用户为空，若为专家，则为专家id

    private String college;

    public String getId() {
        return id;
    }

    public void setId(String userId) {
        this.id = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avaterUrl) {
        this.avatarUrl = avaterUrl;
    }

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public int getUnreadNotification() {
        return unreadNotification;
    }

    public void setUnreadNotification(int unreadNotification) {
        this.unreadNotification = unreadNotification;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getPrestige() {
        return prestige;
    }

    public void setPrestige(int prestige) {
        this.prestige = prestige;
    }

    public List<String> getFollowers() {
        return followers;
    }

    public void setFollowers(List<String> followers) {
        this.followers = followers;
    }

    public List<String> getFollowing() {
        return following;
    }

    public void setFollowing(List<String> following) {
        this.following = following;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }
}
