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
 * @since 2019-07-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Weiboqa extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 问题
     */
    private String question;

    /**
     * 路径
     */
    private String path;

    /**
     * 问题发表时间
     */
    private String createtime;

    /**
     * 爬取内容类别
     */

    private Boolean content;


}
