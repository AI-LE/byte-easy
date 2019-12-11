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
 * @author 艾乐
 * @since 2019-07-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Baidukeyword extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 要抓取的关键字
     */
    private String keyword;

    /**
     * 关键字添加时间
     */
    private LocalDateTime createtime;

    /**
     * 创建
     */
    private String username;


}
