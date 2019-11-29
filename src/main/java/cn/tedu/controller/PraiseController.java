package cn.tedu.controller;

import cn.tedu.common.SysResult;
import cn.tedu.pojo.Collections;
import cn.tedu.pojo.Comment;
import cn.tedu.pojo.Praise;
import cn.tedu.pojo.Transmit;
import cn.tedu.service.ArticleService;
import cn.tedu.service.PraiseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
    @ApiOperation(value = "从界面获取articleId",notes="根据articleId查找相关所有评论")
    @PostMapping("queryByarticleId{articleId}")
    public List<Comment> queryByarticleId(String articleId){
        List<Comment> comment=praiseService.queryByarticleId(articleId);
        if(comment!=null){
            return comment;
        }else{
            return null;
        }
    }
    @ApiOperation(value = "从界面获取articleId、userPhone和commentContext",notes="根据comment对象创建评论成功记录")
    @PostMapping("insertComment")
    public SysResult insertComment(Comment comment){
        if(comment.getArticleId()!=null&&comment.getUserPhone()!=null){
            praiseService.insertComment(comment);
            articleService.updateCommentNum(comment.getArticleId());
            return SysResult.ok();
        }else{
            return SysResult.build(201,"",null);
        }
    }
    @ApiOperation(value = "从界面获取articleId、userPhone、transmitPhone和transmitContext",notes="根据transmit对象创建评论成功记录")
    @PostMapping("insertTransmitContext")
    public SysResult insertTransmitContext(Transmit transmit){
        if(transmit.getArticleId()!=null&&transmit.getTransmitPhone()!=null&&transmit.getUserPhone()!=null){
            praiseService.insertTransmitContext(transmit);
            articleService.updateTransmitNum(transmit.getArticleId());
            return SysResult.ok();
        }else{
            return SysResult.build(201,"",null);
        }
    }
    @ApiOperation(value = "从界面获取articleId、userPhone、transmitPhone和transmitContext",notes="根据transmit对象创建评论成功记录")
    @PostMapping("insertTransmit")
    public SysResult insertTransmit(Transmit transmit){
        if(transmit.getArticleId()!=null&&transmit.getTransmitPhone()!=null&&transmit.getUserPhone()!=null){
            praiseService.insertTransmit(transmit);
            articleService.updateTransmitNum(transmit.getArticleId());
            return SysResult.ok();
        }else{
            return SysResult.build(201,"",null);
        }
    }
}
