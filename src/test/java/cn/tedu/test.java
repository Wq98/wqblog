package cn.tedu;

import cn.tedu.pojo.User;
import cn.tedu.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.Date;
@Slf4j
public class test{
    @Test
    public void test1(){
        Jedis jedis=new Jedis("49.233.145.110",6379);
        jedis.set("name","wei");
    }
}
