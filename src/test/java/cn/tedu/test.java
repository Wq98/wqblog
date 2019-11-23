package cn.tedu;

import cn.tedu.pojo.User;
import cn.tedu.service.UserService;
import org.junit.Test;

import java.util.Date;

public class test{

    @Test
    public void test1(){
        /*UserController userController=new UserController();
        userController.checkUsername("111");*/
        UserService userService=new UserService();
        User user=new User();
        user.setUserId("getwtf");
        user.setUserPhone("777");
        user.setPassword("777");
        user.setUserBirth("2019-11-21");
        user.setNickname(null);
        user.setRegisterTime(new Date());
        user.setUserState(null);
        System.out.println(user);
        userService.saveUser(user);

        /*ArticleService articleService=new ArticleService();
        Article article=new Article();
        article.setArticleId("fheihie");
        article.setUserPhone("222");
        article.setLatestTime(new Date());
        article.setArticleContext("fdiwehiuh");
        article.setArticleState(null);
        System.out.println(article+"******test");
        articleService.publish(article);*/

    }
}
