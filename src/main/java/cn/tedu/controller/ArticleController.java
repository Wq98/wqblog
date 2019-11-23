package cn.tedu.controller;

import cn.tedu.common.SysResult;
import cn.tedu.pojo.Article;
import cn.tedu.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/article")
@Api(value = "博文相关操作API")
public class ArticleController {
    @Resource
    private ArticleService articleService;
    @ApiOperation(value = "发布微博",notes="根据Article对象创建微博")
    @RequestMapping(value = "publish",method = RequestMethod.POST)
    public SysResult publish(Article article){
        try {
            articleService.publish(article);
            return SysResult.ok();
        }catch (Exception e){
            e.printStackTrace();
            return SysResult.build(201,"",null);
        }
    }
    @ApiOperation(value = "获取所有article",notes = "无需参数")
    @RequestMapping(value = "queryAll",method = RequestMethod.POST)
    public List<Article> queryAll(){
        List<Article> list=articleService.queryAll();
        return list;
    }

    @ApiOperation(value = "获取个人article",notes = "需要传递articleId参数")
    @RequestMapping(value = "queryArticleById{articleId}",method = RequestMethod.POST)
    public Article queryArticleById( String articleId){
        Article article=articleService.queryArticleById(articleId);
        return article;
    }
    @ApiOperation(value = "删除个人某条article",notes = "需要传递articleId参数")
    @RequestMapping(value = "deleteArticle{articleId}",method = RequestMethod.POST)
    public SysResult deleteArticle(String articleId){

        boolean b=articleService.deleteArticle(articleId);
        if(b) {
            return SysResult.ok();
        }else {
            return SysResult.build(201, "", null);
        }
    }
    @ApiOperation(value = "修改某条article",notes = "需要传递articleId参数和articleContext参数")
    @RequestMapping(value = "updateArticle",method = RequestMethod.POST)
    public SysResult updateArticle(Article article){
        boolean b=articleService.updateArticle(article);
        if(b) {
            return SysResult.ok();
        }else {
            return SysResult.build(201, "", null);
        }
    }

    @ApiOperation(value = "获取个人所有article",notes = "需要传递userPhone参数")
    @RequestMapping(value = "queryByUserPhone{userPhone}",method = RequestMethod.POST)
    public List<Article> queryByUserPhone(String userPhone){
        List<Article> list=articleService.queryByUserPhone(userPhone);
        return list;
    }
}
