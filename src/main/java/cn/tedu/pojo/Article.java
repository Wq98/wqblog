package cn.tedu.pojo;

import java.util.Date;

public class Article {
    private String articleId;
    private String userPhone;
    private Date latestTime;
    private String articleContext;
    private String articleState;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Date getLatestTime() {
        return latestTime;
    }

    public void setLatestTime(Date latestTime) {
        this.latestTime = latestTime;
    }

    public String getArticleContext() {
        return articleContext;
    }

    public void setArticleContext(String articleContext) {
        this.articleContext = articleContext;
    }

    public String getArticleState() {
        return articleState;
    }

    public void setArticleState(String articleState) {
        this.articleState = articleState;
    }

    public Article() {
    }

    public Article(String articleId, String userPhone, Date latestTime, String articleContext, String articleState) {
        this.articleId = articleId;
        this.userPhone = userPhone;
        this.latestTime = latestTime;
        this.articleContext = articleContext;
        this.articleState = articleState;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId='" + articleId + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", latestTime=" + latestTime +
                ", articleContext='" + articleContext + '\'' +
                ", articleState='" + articleState + '\'' +
                '}';
    }
}
