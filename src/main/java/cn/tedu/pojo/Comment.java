package cn.tedu.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName: Comment
 * @Description: TODO
 * @author: 魏秦
 * @date: 2019/11/29  8:55
 */@Data
public class Comment {
     private String commentId;
     private String articleId;
     private String userPhone;
     private String commentContext;
     private Date commentTime;
     private String commentState;
}
