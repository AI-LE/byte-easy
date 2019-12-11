package com.mbyte.easy.admin.entity;

import com.mbyte.easy.common.entity.BaseEntity;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 微博搜索关键字首页显示表
 * </p>
 *
 * @author 张虎
 * @since 2019-07-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SinaSerch extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 搜索关键字
     */
    @TableField("serchName")
    private String serchName;

    /**
     * 联想内容
     */
    private String content;

    /**
     * 创建时间
     */
    @TableField("creatTime")
    private LocalDateTime creatTime;


}
