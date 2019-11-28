package cn.tedu.pojo;

import lombok.Data;

import java.util.Date;


/**
 * @ClassName: Praise
 * @Description: TODO
 * @author: 魏秦
 * @date: 2019/11/27  14:54
 */
@Data
public class Praise {
    private String praiseId;
    private String articleId;
    private String userPhone;
    private Date praiseTime;
    private String praiseState;
}
