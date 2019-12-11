package com.mbyte.easy.admin.entity;

import com.mbyte.easy.common.entity.BaseEntity;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 吴天豪
 * @since 2019-06-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ZhihuOldrecords extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 创建时间
     */
    private LocalDateTime createtime;

    /**
     * 知乎关键词
     */
    private String keyword;

    /**
     * 用户名
     */
    private String username;

    /**
     * 连表id
     */
    private Long zhihuid;

    /**
     * 文件名
     */
    private String uuid;


}
