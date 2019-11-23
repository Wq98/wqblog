package cn.tedu.pojo;

import java.util.Date;

public class User {
    private String UserId;
    private String UserPhone;
    private String Password;
    private String UserBirth;
    private String Nickname;
    private Date RegisterTime;
    private String UserState;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(String userPhone) {
        UserPhone = userPhone;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUserBirth() {
        return UserBirth;
    }

    public void setUserBirth(String userBirth) {
        UserBirth = userBirth;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }

    public Date getRegisterTime() {
        return RegisterTime;
    }

    public void setRegisterTime(Date registerTime) {
        RegisterTime = registerTime;
    }

    public String getUserState() {
        return UserState;
    }

    public void setUserState(String userState) {
        UserState = userState;
    }

    public User() {
    }

    public User(String userId, String userPhone, String password, String userBirth, String nickname, Date registerTime, String userState) {
        UserId = userId;
        UserPhone = userPhone;
        Password = password;
        UserBirth = userBirth;
        Nickname = nickname;
        RegisterTime = registerTime;
        UserState = userState;
    }

    @Override
    public String toString() {
        return "User{" +
                "UserId='" + UserId + '\'' +
                ", UserPhone='" + UserPhone + '\'' +
                ", Password='" + Password + '\'' +
                ", UserBirth='" + UserBirth + '\'' +
                ", Nickname='" + Nickname + '\'' +
                ", RegisterTime=" + RegisterTime +
                ", UserState='" + UserState + '\'' +
                '}';
    }
}
