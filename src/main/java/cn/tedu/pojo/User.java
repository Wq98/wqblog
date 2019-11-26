package cn.tedu.pojo;

import lombok.Data;

import java.util.Date;
@Data
public class User {
    private String userId;
    private String userPhone;
    private String password;
    private String userBirth;
    private String nickname;
    private Date registerTime;
    private Integer userState;
    private String token;
}
