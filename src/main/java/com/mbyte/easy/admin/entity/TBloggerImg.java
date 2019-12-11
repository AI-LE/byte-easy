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
 * @since 2019-05-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class TBloggerImg extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 照片路径
     */
    private String imgsourceurl;

    /**
     * 本地照片路径
     */
    private String imglocalurl;

    /**
     * 连表id代表内容里的照片
     */
    private Long contentid;


}
