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
 * @since 2019-05-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class TRecordssum extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 记录
     */
    private Long records;

    /**
     * 抓取时间
     */
    private LocalDateTime createtime;

    /**
     * 抓取类型
     */
    private String type;


}
