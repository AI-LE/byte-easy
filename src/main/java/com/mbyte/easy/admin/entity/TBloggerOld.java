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
 * @since 2019-06-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class TBloggerOld extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 博主名字
     */
    private String bloggername;

    /**
     * 博主的uid
     */
    private Long uid;

    /**
     * 博主的containerid
     */
    private Long containerid;

    /**
     * 创建时间
     */
    private LocalDateTime createtime;

    /**
     * 最大字数
     */
    private Long wordmax;

    /**
     * 最小字数
     */
    private Long wordmin;

    /**
     * 抓取时间节点
     */
    private LocalDateTime timestart;

    /**
     * 抓取时间尾节点
     */
    private LocalDateTime timeend;

    /**
     * 用户名
     */
    private String username;

    /**
     * 0等于no 1等于yes
     */
    private Integer commitchioce;

    /**
     * 点赞数
     */
    private Long pointersum;

    /**
     * 0等于纯文字 1等于文字加图片
     */
    private Integer judge;

    /**
     * 时间阶段
     */
    private String timeduring;


}
