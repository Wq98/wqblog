package cn.tedu.mapper;

import cn.tedu.pojo.Article;

import java.util.List;

public interface ArticleMapper {
    void publish(Article article);


    List<Article> queryAll();

    boolean deleteArticle(String articleId);

    Article queryArticleById(String articleId);

    

    boolean updateArticle(Article article);


    List<Article> queryByUserPhone(String userPhone);
    void updatePraiseNum(Article article);
}
