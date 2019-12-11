package com.mbyte.easy.admin.entity;

import com.mbyte.easy.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;


/**
 * 〈p〉
 * <p>
 * 〈/p〉
 *
 * @author 刘雪奇
 * @create 2019/5/29
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RecordsSum extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 记录
     */
    private Long records;

    /**
     * 抓取时间
     */
    private LocalDateTime createTime;

    /**
     * 抓取类型
     */
    private String type;

}
