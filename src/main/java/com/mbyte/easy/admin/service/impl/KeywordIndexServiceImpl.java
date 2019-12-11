package com.mbyte.easy.admin.service.impl;

import com.mbyte.easy.admin.entity.KeywordIndex;
import com.mbyte.easy.admin.mapper.KeywordIndexMapper;
import com.mbyte.easy.admin.service.IKeywordIndexService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 艾乐
 * @since 2019-07-15
 */
@Service
public class KeywordIndexServiceImpl extends ServiceImpl<KeywordIndexMapper, KeywordIndex> implements IKeywordIndexService {

    @Autowired
    private KeywordIndexMapper keywordIndexMapper;

    public int selectTotalNumber(String uuid){
        return keywordIndexMapper.selectTotalNumber(uuid);
    }

    public List<KeywordIndex> selectAll(Integer page, String downloadUUID){
        return keywordIndexMapper.selectAll(page * 50,downloadUUID);
    }

}
