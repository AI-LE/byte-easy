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
 * @author 艾乐
 * @since 2019-07-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class KeywordIndex extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 关键字
     */
    private String data;

    /**
     * 百度指数
     */
    private Integer bdindex;

    /**
     * 序号
     */
    private Integer number;

    /**
     * 给某个关键字的抓取添加唯一标识，这样可以并发同时抓取，同过该字段区分
     */
    private String uuid;


}
