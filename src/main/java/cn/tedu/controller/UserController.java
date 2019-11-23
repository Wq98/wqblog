package cn.tedu.controller;


import cn.tedu.common.CookieUtils;
import cn.tedu.common.SysResult;
import cn.tedu.pojo.User;
import cn.tedu.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
@Api(value = "用户注册")
public class UserController {
    @Resource
    private UserService userService;
    @ApiOperation(value = "验证用户注册手机号是否存在",notes="从文本框中获取手机号")
    @ApiImplicitParam(name = "userPhone",value = "手机号", dataType = "String")
    @RequestMapping(value = "/checkUserPhone",method = RequestMethod.POST)
    public SysResult checkUsername(String userPhone){
        int exist=userService.checkUserPhone(userPhone);
        if(exist==0){
            return SysResult.ok();
        }else{
            return SysResult.build(201,"",null);
        }
    }
    @ApiOperation(value = "注册用户",notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "user",value = "用户详细实体user",required = true,dataType = "User")
    @RequestMapping(value = "save",method = RequestMethod.POST)
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
    @ApiImplicitParam(name = "user",dataType = "User")
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public SysResult login(User user, HttpServletRequest request, HttpServletResponse response){
            User exist = userService.login(user);
            if("".equals(exist)){
                return SysResult.build(201,"",null);
            }else{
               CookieUtils.setCookie(request,response,"ticket",exist.getUserPhone());
                return SysResult.ok();
            }
    }
    @RequestMapping("logout")
    public SysResult logout(HttpServletRequest request,HttpServletResponse response){
        //删除cookie
        CookieUtils.deleteCookie(request, response, "ticket");
        return SysResult.ok();
    }
}
