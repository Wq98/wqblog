package cn.tedu.service;

import cn.tedu.common.UUIDUtil;
import cn.tedu.mapper.ArticleMapper;
import cn.tedu.mapper.PraiseMapper;
import cn.tedu.pojo.Collections;
import cn.tedu.pojo.Comment;
import cn.tedu.pojo.Praise;
import cn.tedu.pojo.Transmit;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: PraiseService
 * @Description: TODO
 * @author: 魏秦
 * @date: 2019/11/27  14:56
 */
@Service
public class PraiseService {
    @Resource
    private PraiseMapper praiseMapper;

    public void insertPraise(Praise praise) {
            praise.setPraiseId(UUIDUtil.getUUID().replace("-", ""));
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            format.format(date);
            praise.setPraiseTime(date);
            praiseMapper.insertPraise(praise);
    }
    public Praise selectExist(Praise praise){
            return praiseMapper.selectExist(praise);
    }

    public Collections selectCollectionExist(Collections collection) {
        return praiseMapper.selectCollectionExist(collection);
    }

    public void insertCollection(Collections collection) {
        collection.setCollectionId(UUIDUtil.getUUID().replace("-", ""));
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.format(date);
        collection.setCollectionTime(date);
        praiseMapper.insertCollection(collection);


    }
    public int praiseNum(Praise praise){
        return praiseMapper.praiseNum(praise);
    }


    public List<Comment> queryByarticleId(String articleId) {
        List<Comment> comment=praiseMapper.queryByarticleId(articleId);
        return  comment;
    }

    public void insertComment(Comment comment) {
        comment.setCommentId(UUIDUtil.getUUID().replace("-",""));
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.format(date);
        comment.setCommentTime(date);
        praiseMapper.insertComment(comment);
    }

    public int commentNum(Comment comment){
        return praiseMapper.commentNum(comment);

    }

    public void insertTransmitContext(Transmit transmit) {
        transmit.setTransmitId(UUIDUtil.getUUID());
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.format(date);
        transmit.setTransmitTime(date);
        praiseMapper.insertTransmitContext(transmit);
    }

    public void insertTransmit(Transmit transmit) {
        transmit.setTransmitId(UUIDUtil.getUUID());
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.format(date);
        transmit.setTransmitTime(date);
        praiseMapper.insertTransmit(transmit);
    }
}
