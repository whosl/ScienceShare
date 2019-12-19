package buaa.group6.scienceshare.model;

public class UserWithAvatar {
    String username;

    String avatarUrl;

    String college;

    int isFollowing;

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public int getIsFollowing() {
        return isFollowing;
    }

    public void setIsFollowing(int isFollowing) {
        this.isFollowing = isFollowing;
    }

    public UserWithAvatar(String username, String avatarUrl, int isFollowing, String college){
        this.username = username;
        this.avatarUrl = avatarUrl;
        this.isFollowing = isFollowing;
        this.college = college;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
