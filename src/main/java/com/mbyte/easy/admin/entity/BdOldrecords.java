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
 * @since 2019-06-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class BdOldrecords extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 创建时间
     */
    private LocalDateTime createtime;

    /**
     * 关键词
     */
    private String keyword;

    /**
     * 用户名
     */
    private String username;

    /**
     * 百度历史记录连表id
     */
    private Long bdid;

    /**
     * 区分文件时间戳
     */
    private Long timejudge;


}
