package cn.tedu.controller;
import cn.tedu.common.CookieUtils;
import cn.tedu.common.SysResult;
import cn.tedu.pojo.User;
import cn.tedu.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
@Api(description = "用户相关操作API")
public class UserController {
    @Resource
    private UserService userService;
    @ApiOperation(value = "从数据库获取userPhone",notes="需要传递userPhone参数")
    @PostMapping("/checkUserPhone")
    public SysResult checkUsername(String userPhone){
        int exist=userService.checkUserPhone(userPhone);
        if(exist==0){
            return SysResult.ok();
        }else{
            return SysResult.build(201,"",null);
        }
    }
    @ApiOperation(value = "注册用户",notes = "根据User对象创建用户")
    @PostMapping("save")
    public SysResult saveUser(User user){
        try {
            userService.saveUser(user);
            return SysResult.ok();
        }catch (Exception e){
            e.printStackTrace();
            return  SysResult.build(201,"",null);
        }
    }
    @ApiOperation(value = "用户登录",notes = "根据手机号和密码验证登录")
    @GetMapping("login")
    public SysResult login(User user, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
            User exist = userService.login(user);
            if("".equals(exist)){
                return SysResult.build(201,"",null);
            }else if(userService.getCount(exist.getUserPhone())>=3){
                return SysResult.build(202,"",null);
            }else{
               CookieUtils.setCookie(request,response,"ticket",exist.getUserPhone());
                return SysResult.ok();
            }
    }
    @GetMapping("query/{ticket}")
    public SysResult queryUserInfo(@PathVariable String ticket){
        String userList=userService.queryUserInfo(ticket);
        if(userList==null){
            return SysResult.build(201,"",null);
        }else{
            return SysResult.build(200,"",userList);
        }
    }


    @ApiIgnore
    @RequestMapping("logout")
    public SysResult logout(HttpServletRequest request,HttpServletResponse response){
        //删除cookie
        CookieUtils.deleteCookie(request, response, "ticket");
        return SysResult.ok();
    }
}
