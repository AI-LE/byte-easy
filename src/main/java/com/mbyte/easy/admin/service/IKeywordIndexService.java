package com.mbyte.easy.admin.service;

import com.mbyte.easy.admin.entity.KeywordIndex;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Mapper;
import scala.util.parsing.combinator.testing.Str;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 艾乐
 * @since 2019-07-15
 */
public interface IKeywordIndexService extends IService<KeywordIndex> {

    int selectTotalNumber(String uuid);

    List<KeywordIndex> selectAll(Integer page, String downloadUUID);
}
