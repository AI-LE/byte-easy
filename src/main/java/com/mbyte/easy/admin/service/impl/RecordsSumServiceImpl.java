package com.mbyte.easy.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mbyte.easy.admin.entity.RecordsSum;
import com.mbyte.easy.admin.mapper.RecordsSumMapper;
import com.mbyte.easy.admin.service.IRecordsSumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈p〉
 *
 * 〈/p〉
 *
 * @author 刘雪奇
 * @create 2019/5/29
 * @since 1.0.0
 */
@Service
public class RecordsSumServiceImpl extends ServiceImpl<RecordsSumMapper, RecordsSum> implements IRecordsSumService {

    @Autowired
    private RecordsSumMapper recordsSumMapper;

    @Override
    public int selectSum() {
        return this.baseMapper.selectSum();
    }

    @Override
    public List<RecordsSum> selectSort(){
        return this.recordsSumMapper.selectSort();
    }

    @Override
    public List<RecordsSum> weekData(String starttime,String endtime){
        return this.recordsSumMapper.weekData(starttime,endtime);
    }

    @Override
    public List<RecordsSum> dayData(String starttime,String endtime){
        return this.recordsSumMapper.dayData(starttime,endtime);
    }


}
