package cn.tedu.service;

import cn.tedu.common.MD5Util;
import cn.tedu.common.UUIDUtil;
import cn.tedu.mapper.UserMapper;
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
        user.setPassword(MD5Util.md5(user.getPassword()));
        User userExist=userMapper.queryExist(user);
        if(userExist==null){
            return null;
        }else{
            return userExist;
        }

    }
}
