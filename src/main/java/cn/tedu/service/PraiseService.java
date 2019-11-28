package cn.tedu.service;

import cn.tedu.common.UUIDUtil;
import cn.tedu.mapper.ArticleMapper;
import cn.tedu.mapper.PraiseMapper;
import cn.tedu.pojo.Collections;
import cn.tedu.pojo.Praise;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

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
}
