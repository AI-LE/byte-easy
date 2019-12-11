package com.mbyte.easy.admin.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mbyte.easy.admin.entity.KeywordIndex;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 艾乐
 * @since 2019-07-15
 */
public interface KeywordIndexMapper extends BaseMapper<KeywordIndex> {

    /**
     * 查询总数量
     * @return
     */
    int selectTotalNumber(String uuid);

    /**
     * 查询所有数据
     * @return
     */
    List<KeywordIndex> selectAll(Integer page, String downloadUUID);

}
