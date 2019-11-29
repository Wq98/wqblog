package cn.tedu.mapper;

import cn.tedu.pojo.Collections;
import cn.tedu.pojo.Comment;
import cn.tedu.pojo.Praise;
import cn.tedu.pojo.Transmit;

import java.util.List;

/**
 * @ClassName: PraiseMapper
 * @Description: TODO
 * @author: 魏秦
 * @date: 2019/11/27  14:57
 */
public interface PraiseMapper {
    void insertPraise(Praise praise);
    Praise selectExist(Praise praise);

    Collections selectCollectionExist(Collections collection);

    void insertCollection(Collections collection);

    int praiseNum(Praise praise);

    List<Comment> queryByarticleId(String articleId);

    void insertComment(Comment comment);

    int commentNum(Comment comment);

    void insertTransmitContext(Transmit transmit);

    void insertTransmit(Transmit transmit);
}
