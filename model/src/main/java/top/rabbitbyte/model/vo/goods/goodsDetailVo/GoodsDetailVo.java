package top.rabbitbyte.model.vo.goods.goodsDetailVo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.model.vo.goods
 * @Author: nemo2048
 * @CreateTime: 2024-09-19  17:44
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class GoodsDetailVo  {
    private GoodsInfoVo info;
    private List<GoodsGallery> gallery;
    private SellerInfo seller;
    private List<Comment> comment;
    private List<RelatedGoods> relatedGoods;
    private Boolean userHasCollect;

    public GoodsDetailVo(GoodsInfoVo info, List<GoodsGallery> gallery, SellerInfo seller, List<Comment> comment, List<RelatedGoods> relatedGoods, Boolean userHasCollect) {
        this.info = info;
        this.gallery = gallery;
        this.seller = seller;
        this.comment = comment;
        this.relatedGoods = relatedGoods;
        this.userHasCollect = userHasCollect;
    }
    public GoodsDetailVo(){
        this.info =  new GoodsInfoVo();
        this.gallery = new ArrayList<GoodsGallery>();
        this.seller = new SellerInfo();
        this.comment = new ArrayList<Comment>();
        this.relatedGoods = new ArrayList<RelatedGoods>();
        this.userHasCollect = Boolean.FALSE;
    }
}