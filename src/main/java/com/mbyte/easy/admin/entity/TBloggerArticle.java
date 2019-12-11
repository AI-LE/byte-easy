package com.mbyte.easy.admin.entity;

import com.mbyte.easy.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class TBloggerArticle extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 关键字
     */
    private String keywords;
    /**
     * 创建时间
     */
    private LocalDateTime createtime;

}
