package cn.tedu.controller;

import cn.tedu.common.SysResult;
import cn.tedu.common.WqBlogUIResult;
import cn.tedu.pojo.Article;
import cn.tedu.service.ArticleService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Resource
    private ArticleService articleService;
    @RequestMapping("publish")
    public SysResult publish(Article article){
        try {
            articleService.publish(article);
            return SysResult.ok();
        }catch (Exception e){
            e.printStackTrace();
            return SysResult.build(201,"",null);
        }
    }
    @RequestMapping("queryAll")
    public List<Article> queryAll(){
        List<Article> list=articleService.queryAll();
        return list;
    }

    @RequestMapping("queryArticleById{articleId}")
    public Article queryArticleById( String articleId){
        Article article=articleService.queryArticleById(articleId);
        return article;
    }
    @RequestMapping("deleteArticle{articleId}")
    public SysResult deleteArticle(String articleId){

        boolean b=articleService.deleteArticle(articleId);
        if(b) {
            return SysResult.ok();
        }else {
            return SysResult.build(201, "", null);
        }
    }
    @RequestMapping("updateArticle")
    public SysResult updateArticle(Article article){
        boolean b=articleService.updateArticle(article);
        if(b) {
            return SysResult.ok();
        }else {
            return SysResult.build(201, "", null);
        }
    }

    @RequestMapping("queryByUserPhone{userPhone}")
    public List<Article> queryByUserPhone(String userPhone){
        List<Article> list=articleService.queryByUserPhone(userPhone);
        return list;
    }
}
