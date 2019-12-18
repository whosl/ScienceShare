package buaa.group6.scienceshare.model;

public class UserWithAvatar {
    String username;

    String avatarUrl;

    public UserWithAvatar(String username, String avatarUrl){
        this.username = username;
        this.avatarUrl = avatarUrl;
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
