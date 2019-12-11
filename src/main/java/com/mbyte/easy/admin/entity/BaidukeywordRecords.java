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
 * @since 2019-07-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class BaidukeywordRecords extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 抓取的时间
     */
    private LocalDateTime catchtime;

    /**
     * 关键字对应的id
     */
    private Integer keywordid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 给每一个抓取记录唯一标识
     */
    private String uuid;


}
