package cn.tedu.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName: Transmit
 * @Description: TODO
 * @author: 魏秦
 * @date: 2019/11/29  15:38
 */
@Data
public class Transmit {
    private String transmitId;
    private String articleId;
    private String userPhone;
    private String transmitPhone;
    private String transmitContext;
    private Date transmitTime;
    private String transmitSate;
}
