package cn.tedu.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName: Collection
 * @Description: TODO
 * @author: 魏秦
 * @date: 2019/11/28  11:05
 */
@Data
public class Collections {
    private String collectionId;
    private String articleId;
    private String userPhone;
    private Date collectionTime;
    private String collectionState;
}
