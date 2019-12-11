package com.mbyte.easy.admin.entity;

import com.mbyte.easy.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 吴天豪
 * @since 2019-05-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class TBloggerContent extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 内容
     */
    private String content;

    /**
     * 文章和博主连表id
     */
    private Long bloggerid;

    /**
     * 转发
     */
    private String contentrealy;

    /**
     * 对应的回答及转发连
     */
    private Long onlyid;

    /**
     * 连表
     */
    private Long contentid;

    /**
     * 博主创建时间
     */
    private String createtime;


}
