package cn.tedu.service;

import cn.tedu.common.MD5Util;
import cn.tedu.common.TypeTransformMapper;
import cn.tedu.common.UUIDUtil;
import cn.tedu.mapper.UserMapper;
import cn.tedu.pojo.LoginInfo;
import cn.tedu.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private ShardedJedisPool pool;
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


    public User login(User user) throws JsonProcessingException {
        ShardedJedis jedis=pool.getResource();
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
                //token生成规则：key->token+userPhone经过md5加密,value->用户ID
                String token=MD5Util.md5("token"+userExist1.getUserPhone());
                //登录顶替，redis中设置一个.lock，登录的时候如果存在.lock则删除再创建新的，如果不存在说明第一次登录
                String loginLock="user_login"+userExist1.getUserId()+".lock";
                if(jedis.exists(loginLock)){
                    //存在即说明登录过且没过期
                    String oldToken=jedis.get(loginLock);
                    jedis.del(oldToken);
                }
                //不存在说明没登录
                jedis.setex(loginLock,60*30,token);
                jedis.setex(token,60*30,userExist1.getUserId());
                return user;
            } else {
                return null;
            }
        }
    }

    public String queryUserInfo(String ticket) {
        ShardedJedis jedis=pool.getResource();
        try {
            //根据前端传来的“令牌”转换成redis中存储的token
            String token=MD5Util.md5("token"+ticket);
            //判断token剩余时间
            Long leftTime=jedis.pttl(token);
            //取token的value值->用户ID
            String userId=jedis.get(token);
            User user=userMapper.queryUserInfo(userId);
            String userList=TypeTransformMapper.OM.writeValueAsString(user);
            if(user!=null){
            Long leaseTime=1000*60*2L;
            if(leftTime<=leaseTime){
                //续租
                leftTime=leftTime+1000*60*20L;
                jedis.pexpire(token,leftTime);
            }
            return userList;
            }else{

                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public int getCount(String userPhone){
        int countError = userMapper.countError(userPhone);
        return countError;
    }

}
