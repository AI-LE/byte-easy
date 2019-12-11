package com.mbyte.easy.admin.entity;

import com.mbyte.easy.common.entity.BaseEntity;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 微信文章抓取历史记录表
 * </p>
 *
 * @author 张松哲
 * @since 2019-07-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class WechatOldrecords extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 创建时间
     */
    private LocalDateTime createtime;

    /**
     * 抓取状态
     */
    private Integer status;

    /**
     * 爬取微信id
     */
    private long wechatid;

    /**
     * 爬取路径
     */
    private String path;

}
