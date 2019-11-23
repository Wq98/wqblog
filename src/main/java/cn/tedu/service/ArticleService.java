package cn.tedu.service;

import cn.tedu.common.UUIDUtil;
import cn.tedu.mapper.ArticleMapper;
import cn.tedu.pojo.Article;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ArticleService {
    @Resource
    private ArticleMapper articleMapper;
    public void publish(Article article) {
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.format(date);
        article.setArticleId(UUIDUtil.getUUID().replace("-",""));
        article.setLatestTime(date);
        article.setArticleState("0");
        articleMapper.publish(article);
    }


    public List<Article> queryAll() {
        List<Article> list= articleMapper.queryAll();
       return list;

    }

    public boolean deleteArticle(String articleId) {
        return articleMapper.deleteArticle(articleId);
    }

    public Article queryArticleById(String articleId) {
        Article article=articleMapper.queryArticleById(articleId);
        return article;
    }



    public boolean updateArticle(Article article) {
        Date date=new Date();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.format(date);
        article.setLatestTime(date);
        return articleMapper.updateArticle(article);
    }


    public List<Article> queryByUserPhone(String userPhone) {
        return articleMapper.queryByUserPhone(userPhone);
    }
}
