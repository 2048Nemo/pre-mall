package top.rabbitbyte.goods.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.rabbitbyte.goods.mapper.GoodsCategoryMapper;
import top.rabbitbyte.goods.mapper.GoodsSkuMapper;
import top.rabbitbyte.goods.mapper.GoodsSpuMapper;
import top.rabbitbyte.goods.service.CategoryService;
import top.rabbitbyte.model.entity.goods.GoodsCategory;
import top.rabbitbyte.model.entity.goods.GoodsSku;
import top.rabbitbyte.model.entity.goods.GoodsSpu;
import top.rabbitbyte.model.vo.goods.CataLogPageVo;
import top.rabbitbyte.model.vo.goods.CategoryPageVo;
import top.rabbitbyte.model.vo.goods.CategoryVo;
import com.github.pagehelper.PageHelper;
import top.rabbitbyte.model.vo.goods.GoodsSkuVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.goods.service.imp
 * @Author: nemo2048
 * @CreateTime: 2024-09-11  23:09
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class CategoryServiceImp extends ServiceImpl<GoodsCategoryMapper, GoodsCategory> implements CategoryService {
    @Autowired
    GoodsCategoryMapper goodsCategoryMapper;

    @Autowired
    GoodsSpuMapper goodsSpuMapper;

    @Autowired
    GoodsSkuMapper goodsSkuMapper;

    @Override
    public CataLogPageVo getAllCatalog() {
        CataLogPageVo res = new CataLogPageVo();
        List<CategoryVo> sublist = null;
        List<CategoryVo> alllist = null;

        // 查询所有分类parentId == 0
        LambdaQueryWrapper<GoodsCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(GoodsCategory::getParentId,0)
                .orderByAsc(GoodsCategory::getSort);
        alllist =  goodsCategoryMapper.selectList(queryWrapper).stream()
                .map(goodsCategory -> {
                    CategoryVo categoryVo = new CategoryVo();
                    BeanUtils.copyProperties(goodsCategory,categoryVo);
                    categoryVo.setId(goodsCategory.getId().intValue());  // 手动复制 id 字段
                    return categoryVo;
                })
                .collect(Collectors.toList());
        //通过查询所有分类，查询出第一个子分类
        Optional<CategoryVo> first =  alllist.stream().findFirst();
        if (first.isPresent()){
            sublist = goodsCategoryMapper.selectList(new LambdaQueryWrapper<GoodsCategory>()
                    .eq(GoodsCategory::getParentId,first.get().getId())
                    .orderByAsc(GoodsCategory::getSort))
                    .stream()
                    .map(goodsCategory -> {
                        CategoryVo categoryVo = new CategoryVo();
                        BeanUtils.copyProperties(goodsCategory,categoryVo);
                        categoryVo.setId(goodsCategory.getId().intValue());  // 手动复制 id 字段
                        return categoryVo;
                    })
                    .collect(Collectors.toList());
        }
        res.setAllCategory(alllist);
        res.setSubCategory(sublist);
        return res;
    }

    @Override
    public List<CategoryVo> getCatgoryListById(Integer cataLogId) {
        List<CategoryVo> res = new ArrayList<>();
        res = goodsCategoryMapper.selectList(new LambdaQueryWrapper<GoodsCategory>()
                .eq(GoodsCategory::getParentId,cataLogId))
                .stream().map(goodsCategory -> {
                    CategoryVo categoryVo = new CategoryVo();
                    BeanUtils.copyProperties(goodsCategory,categoryVo);
                    categoryVo.setId(goodsCategory.getId().intValue());  // 手动复制 id 字段
                    return categoryVo;
                }).toList();
        return res;
    }

    @Override
    public CategoryPageVo getBroCateAndGoodsList(Integer cateid) {
        CategoryPageVo categoryPageVo = new CategoryPageVo();

        GoodsCategory temp =  goodsCategoryMapper.selectOne(new LambdaQueryWrapper<GoodsCategory>().eq(GoodsCategory::getId,cateid));
        List<GoodsCategory> bro= goodsCategoryMapper.selectList(new LambdaQueryWrapper<GoodsCategory>().eq(GoodsCategory::getParentId,temp.getParentId()));
        categoryPageVo.setBrotherCategory(bro);

        List<GoodsSkuVo> goodsList = goodsSkuMapper.getListByCateid(cateid)
                .stream().map(good -> {
                    GoodsSkuVo goodVo = new GoodsSkuVo();
                    BeanUtils.copyProperties(good,goodVo);
                    goodVo.setPrimaryPicUrl(good.getImage());
                    return goodVo;
                }).toList();
        categoryPageVo.setGoodsList(goodsList);
        return categoryPageVo;
    }

    @Override
    public CategoryPageVo getGoodsList(Integer cateid, Integer page, Integer size) {

        CategoryPageVo categoryPageVo = new CategoryPageVo();
        PageHelper.startPage(page,size);
        List<GoodsSkuVo>  skus = goodsSkuMapper.getListByCateid(cateid)
                .stream().map(good -> {
                    GoodsSkuVo goodVo = new GoodsSkuVo();
                    BeanUtils.copyProperties(good,goodVo);
                    goodVo.setPrimaryPicUrl(good.getImage());
                    return goodVo;
                }).toList();

        categoryPageVo.setGoodsList(skus);
        return categoryPageVo;
    }
}
