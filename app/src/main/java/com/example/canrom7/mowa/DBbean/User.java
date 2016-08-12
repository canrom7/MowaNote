package com.example.canrom7.mowa.DBbean;

import bean.Comment;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Canrom7 on 2016/8/12 .
 * Mailbox canrom7@163.com .
 */
public class User extends BmobUser{
    private Integer userId;
    private Integer userAge;
    private Integer userType;
    private String userSex;
    private String userCity;
    private Note userNote;
    private User userFriends;
    private Note userLikes;
    private Comment userComment;
    private BmobFile userLogo;

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public Note getUserNote() {
        return userNote;
    }

    public void setUserNote(Note userNote) {
        this.userNote = userNote;
    }

    public BmobFile getUserLogo() {
        return userLogo;
    }

    public void setUserLogo(BmobFile userLogo) {
        this.userLogo = userLogo;
    }

    public Note getUserLikes() {
        return userLikes;
    }

    public void setUserLikes(Note userLikes) {
        this.userLikes = userLikes;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public User getUserFriends() {
        return userFriends;
    }

    public void setUserFriends(User userFriends) {
        this.userFriends = userFriends;
    }

    public Comment getUserComment() {
        return userComment;
    }

    public void setUserComment(Comment userComment) {
        this.userComment = userComment;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    @Override
    public String toString() {
        return "User[" +
                "userAge=" + userAge +
                ", userId=" + userId +
                ", userType=" + userType +
                ", userSex='" + userSex + '\'' +
                ", getUsername='" + getUsername() + '\'' +
                ", getObjectId='" + getObjectId() + '\'' +
                ", userCity='" + userCity + '\'' +
                ", userNote=" + userNote +
                ", userFriends=" + userFriends +
                ", userLikes=" + userLikes +
                ", userComment=" + userComment +
                ", userLogo=" + userLogo +
                ']';
    }
}
