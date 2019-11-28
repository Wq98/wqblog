package cn.tedu.pojo;

import lombok.Data;

import java.util.Date;
@Data
public class LoginInfo {
    private String loginId;
    private String userPhone;
    private Integer loginFlag;
    private Integer errorNum;
    private Date loginTime;
}
