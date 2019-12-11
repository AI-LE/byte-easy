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
public class ZhihuRecords extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 创建时间
     */
    private LocalDateTime createtime;

    /**
     * 关键词
     */
    private String keyword;

    /**
     * 用户名
     */
    private String username;

    /**
     * 0不抓取答案 1抓取答案
     */
    private Integer typechoice;

    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getTypechoice() {
        return typechoice;
    }

    public void setTypechoice(Integer typechoice) {
        this.typechoice = typechoice;
    }
}
