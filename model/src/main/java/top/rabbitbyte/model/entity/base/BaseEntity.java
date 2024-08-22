package top.rabbitbyte.model.entity.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.model.entity.base
 * @Author: nemo2048
 * @CreateTime: 2024-08-22  19:06
 * @Description: TODO
 * @Version: 1.0
 */

@Data
public class BaseEntity implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("create_time")
    private Date createTime;

    @JsonIgnore
    @TableField("update_time")
    private Date updateTime;

    @JsonIgnore
    @TableLogic
    @TableField("is_deleted")
    private Integer isDeleted;

    @JsonIgnore
    @TableField(exist = false)
    private Map<String,Object> param = new HashMap<>();
}