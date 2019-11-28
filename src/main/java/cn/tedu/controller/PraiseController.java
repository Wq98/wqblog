package cn.tedu.controller;

import cn.tedu.common.SysResult;
import cn.tedu.pojo.Collections;
import cn.tedu.pojo.Praise;
import cn.tedu.service.ArticleService;
import cn.tedu.service.PraiseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName: PraiseController
 * @Description: TODO
 * @author: 魏秦
 * @date: 2019/11/27  14:57
 */
@RestController
@RequestMapping("/article")
@Api(description = "点赞转发评论API")
public class PraiseController {
    @Resource
    private PraiseService praiseService;
    @Resource
    private ArticleService articleService;
    @ApiOperation(value = "从界面获取articleId和userPhone",notes="根据praise对象创建点赞成功记录")
    @PostMapping("insertPraise")
    public SysResult praise(Praise praise){
            Praise praiseExist=praiseService.selectExist(praise);
            if(praiseExist==null&&praise.getArticleId()!=null&&praise.getUserPhone()!=null){
                praiseService.insertPraise(praise);
                articleService.updatePraiseNum(praise.getArticleId());
                return SysResult.ok();
            }else{
                return SysResult.build(201,"",null);
            }
    }
    @ApiOperation(value = "从界面获取articleId和userPhone",notes="根据collection对象创建点赞成功记录")
    @PostMapping("insertConllection")
    public SysResult collectioin(Collections collection){
        Collections collectionExist=praiseService.selectCollectionExist(collection);
        if(collectionExist==null&&collection.getArticleId()!=null&&collection.getUserPhone()!=null){
            praiseService.insertCollection(collection);
            return SysResult.ok();
        }else{
            return SysResult.build(201,"",null);
        }
    }

}
