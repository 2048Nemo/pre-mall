package top.rabbitbyte.model.vo.base;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.model.vo.base
 * @Author: nemo2048
 * @CreateTime: 2024-08-23  16:05
 * @Description: 分页数据包装
 * @Version: 1.0
 */
@Data
@Schema(description = "分页数据实体")
@Builder
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@NoArgsConstructor
public class PageVo<T> implements java.io.Serializable {
    @Schema(description = "当前页码", required = true)
    private Long page;

    @Schema(description = "每页记录数", required = true)
    private Long limit;

    @Schema(description = "总页数", required = true)
    private Long pages;

    @Schema(description = "总条目数", required = true)
    private Long total;

    @Schema(description = "数据列表", required = true)
    private List<T> records;

    public PageVo(List<T> list,Long pages,Long total){
        this.setRecords(list);
        this.setPages(pages);
        this.setTotal(total);
    }
}
