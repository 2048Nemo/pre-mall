package top.rabbitbyte.serviceutil.query;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.rabbitbyte.serviceutil.config.Constant;
import top.rabbitbyte.serviceutil.filter.SQLFilter;

import java.util.Map;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.comon.utils
 * @Author: nemo2048
 * @CreateTime: 2024-09-16  13:01
 * @Description: TODO
 * @Version: 1.0
 */
public class Query<T> {

    public IPage<T> getPage(Map<String, Object> params) {
        return this.getPage(params, null, false);
    }

    public IPage<T> getPage(Map<String, Object> params,  // 参数有curPage limit order  sidx  asc
                            String defaultOrderField,// 默认排序字段
                            boolean isAsc) { // 默认降序
        //分页参数
        long curPage = 1;
        long limit = 10;
        // new Page<>(curPage, limit);   .
        // page.addOrder(OrderItem.asc(orderField));
        // page.addOrder(OrderItem.desc(orderField));
        // page.addOrder(OrderItem.asc(defaultOrderField));
        // page.addOrder(OrderItem.desc(defaultOrderField));

        // 页码
        if(params.get(Constant.PAGE) != null){
            curPage = Long.parseLong((String)params.get(Constant.PAGE));
        }
        // 偏移
        if(params.get(Constant.LIMIT) != null){
            limit = Long.parseLong((String)params.get(Constant.LIMIT));
        }

        // 分页对象  mybatis-plus内容，实现Ipage
        Page<T> page = new Page<>(curPage, limit);

        // 分页参数
        params.put(Constant.PAGE, page);

        // 排序字段
        // 防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
        String orderField = SQLFilter.sqlInject((String)params.get(Constant.ORDER_FIELD));
        String order = (String)params.get(Constant.ORDER);


        // 前端字段排序
        if(StringUtils.isNotEmpty(orderField) && StringUtils.isNotEmpty(order)){
            if(Constant.ASC.equalsIgnoreCase(order)) {
                return  page.addOrder(OrderItem.asc(orderField));
            }else {
                return page.addOrder(OrderItem.desc(orderField));
            }
        }
        // 如果已经传来了排序字段，已经返回了

        // 没有排序字段，则不排序
        if(StringUtils.isBlank(defaultOrderField)){
            return page;
        }

        // 默认排序
        if(isAsc) {
            page.addOrder(OrderItem.asc(defaultOrderField));
        }else {
            page.addOrder(OrderItem.desc(defaultOrderField));
        }

        return page;
    }
}