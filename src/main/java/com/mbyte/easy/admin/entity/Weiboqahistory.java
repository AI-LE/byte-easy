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
 * @since 2019-07-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Weiboqahistory extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 文件名
     */
    private String filename;

    /**
     * 文件创建时间
     */
    private LocalDateTime creattime;

    /**
     * 文件路径
     */
    private String path;


    private Boolean status;




}
