package top.rabbitbyte.model.coverter;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.model.coverter
 * @Author: nemo2048
 * @CreateTime: 2024-09-19  21:47
 * @Description: TODO
 * @Version: 1.0
 */

import top.rabbitbyte.model.entity.goods.GoodsInfo;
import top.rabbitbyte.model.vo.goods.goodsDetailVo.GoodsInfoVo;

public class GoodsInfoConverter {

    public static GoodsInfoVo toVo(GoodsInfo entity) {
        if (entity == null) {
            return null;
        }

        GoodsInfoVo vo = new GoodsInfoVo();
        vo.setId(entity.getId());
        vo.setName(entity.getName());
        vo.setPrice(entity.getPrice());
        vo.setMarketPrice(entity.getMarketPrice());
        vo.setDesc(entity.getDescription()); // 注意：实体类中的 `description` 映射到视图类中的 `desc`
        vo.setLastEdit(entity.getUpdateTime());
        vo.setRegion(entity.getRegion());
        vo.setIsDelete(entity.getIsDeleted());
        vo.setIsSelling(entity.getIsSelling());
        vo.setAbleExpress(entity.getAbleExpress());
        vo.setAbleMeet(entity.getAbleMeet());
        vo.setAbleSelfTake(entity.getAbleSelfTake());
        vo.setPostage(entity.getPostage());
        vo.setWantCount(entity.getWantCount());
        vo.setBrowseCount(entity.getBrowseCount());

        return vo;
    }

//    public static EntityGoodsInfo toEntity(VoGoodsInfo vo) {
//        if (vo == null) {
//            return null;
//        }
//
//        EntityGoodsInfo entity = new EntityGoodsInfo();
//        entity.setId(vo.getId());
//        entity.setName(vo.getName());
//        entity.setPrice(vo.getPrice());
//        entity.setMarketPrice(vo.getMarketPrice());
//        entity.setDescription(vo.getDesc()); // 注意：视图类中的 `desc` 映射到实体类中的 `description`
//        entity.setLastEdit(vo.getLastEdit());
//        entity.setRegion(vo.getRegion());
//        entity.setIsDelete(vo.getIsDelete());
//        entity.setIsSelling(vo.getIsSelling());
//        entity.setAbleExpress(vo.getAbleExpress());
//        entity.setAbleMeet(vo.getAbleMeet());
//        entity.setAbleSelfTake(vo.getAbleSelfTake());
//        entity.setPostage(vo.getPostage());
//        entity.setWantCount(vo.getWantCount());
//        entity.setBrowseCount(vo.getBrowseCount());
//        entity.setGoodsDesc(vo.getGoodsDesc()); // 如果有这个字段
//
//        return entity;
//    }
}