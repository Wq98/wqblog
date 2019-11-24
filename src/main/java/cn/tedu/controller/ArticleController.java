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
@Api(description = "博文相关操作API")
public class ArticleController {
    @Resource
    private ArticleService articleService;
    @ApiOperation(value = "发布article",notes="根据Article对象创建微博")
    @PostMapping("publish")
    public SysResult publish(Article article){
        try {
            System.out.println(article.getPicSrc());
            articleService.publish(article);
            return SysResult.ok();
        }catch (Exception e){
            e.printStackTrace();
            return SysResult.build(201,"",null);
        }
    }
    @ApiOperation(value = "获取所有article",notes = "无需参数")
    @PostMapping("queryAll")
    public List<Article> queryAll(){
        List<Article> list=articleService.queryAll();
        return list;
    }

    @ApiOperation(value = "获取某条article",notes = "需要传递articleId参数")
    @PostMapping("queryArticleById{articleId}")
    public Article queryArticleById( String articleId){
        Article article=articleService.queryArticleById(articleId);
        return article;
    }
    @ApiOperation(value = "删除个人某条article",notes = "需要传递articleId参数")
    @PostMapping("deleteArticle{articleId}")
    public SysResult deleteArticle(String articleId){

        boolean b=articleService.deleteArticle(articleId);
        if(b) {
            return SysResult.ok();
        }else {
            return SysResult.build(201, "", null);
        }
    }
    @ApiOperation(value = "修改某条article",notes = "需要传递articleId参数和articleContext参数")
    @PostMapping("updateArticle")
    public SysResult updateArticle(Article article){
        boolean b=articleService.updateArticle(article);
        if(b) {
            return SysResult.ok();
        }else {
            return SysResult.build(201, "", null);
        }
    }

    @ApiOperation(value = "获取个人所有article",notes = "需要传递userPhone参数")
    @PostMapping("queryByUserPhone{userPhone}")
    public List<Article> queryByUserPhone(String userPhone){
        List<Article> list=articleService.queryByUserPhone(userPhone);
        return list;
    }
}
