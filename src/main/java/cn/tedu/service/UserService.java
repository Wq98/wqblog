package cn.tedu.service;

import cn.tedu.common.MD5Util;
import cn.tedu.common.UUIDUtil;
import cn.tedu.mapper.UserMapper;
import cn.tedu.pojo.LoginInfo;
import cn.tedu.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;
    public int checkUserPhone(String userPhone) {
        return userMapper.checkUserPhone(userPhone);
    }

    public void saveUser(User user) {
        Date date=new Date();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.format(date);
        user.setUserId(UUIDUtil.getUUID());
        String md5Password= MD5Util.md5(user.getPassword());
        user.setPassword(md5Password);
        user.setRegisterTime(date);
        userMapper.saveUser(user);
    }

    public User login(User user) {
        Integer count=0;
        String userPhone=user.getUserPhone();
        user.setPassword(MD5Util.md5(user.getPassword()));
        User userExist=userMapper.queryExist(user);
        Date date=new Date();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.format(date);
        LoginInfo loginInfo=new LoginInfo();
        loginInfo.setLoginTime(date);
        loginInfo.setUserPhone(userPhone);
        loginInfo.setLoginId(UUIDUtil.getUUID().replace("-",""));
        if(userExist==null){
            count+=1;
            loginInfo.setErrorNum(count);
            loginInfo.setLoginFlag(1);
            userMapper.insertLogin(loginInfo);
            int countError =this.getCount(userPhone);
            if(countError>=3){
                boolean b = userMapper.updateUserState(userPhone);
                return null;
            }else{
                return null;
            }
        }else{
            boolean a = userMapper.updateUserState1(userPhone);
            loginInfo.setErrorNum(count);
            loginInfo.setLoginFlag(0);
            userMapper.insertLogin(loginInfo);
            User userExist1=userMapper.queryExistStateNot1(user);
            if(userExist1!=null) {
                return user;
            } else {
                return null;
            }
        }
    }
    public int getCount(String userPhone){
        int countError = userMapper.countError(userPhone);
        return countError;
    }
}
