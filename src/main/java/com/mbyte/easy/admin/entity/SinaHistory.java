package com.mbyte.easy.admin.entity;

import com.mbyte.easy.common.entity.BaseEntity;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author 张虎
 * @since 2019-07-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SinaHistory extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 联想 关键字
     */
    @TableField("serchKeyword")
    private String serchKeyword;

    /**
     * 爬取时间
     */
    @TableField("crawlerTime")
    private LocalDateTime crawlerTime;

    /**
     * 与sina_serch对应关键字的id
     */
    private Long uuid;

    /**
     * 导出路径
     */
    private String path;

}
