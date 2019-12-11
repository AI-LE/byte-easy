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
 * @since 2019-06-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class TBloggerPoint extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 评论
     */
    private String comment;

    /**
     * 点赞数
     */
    private Long pointsum;

    /**
     * 连表内容id代表该内容的点赞数和评论
     */
    private Long contentid;

    /**
     * 回复者名字
     */
    private String commitername;


}
