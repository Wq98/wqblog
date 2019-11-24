package cn.tedu.pojo;

import lombok.Data;

import java.util.Date;
@Data
public class Article {
    private String articleId;
    private String userPhone;
    private Date latestTime;
    private String articleContext;
    private String articleState;
    private String picSrc;

}
