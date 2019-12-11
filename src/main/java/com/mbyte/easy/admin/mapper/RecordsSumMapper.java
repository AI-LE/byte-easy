package com.mbyte.easy.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mbyte.easy.admin.entity.RecordsSum;

import java.time.LocalDateTime;
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
public interface RecordsSumMapper extends BaseMapper<RecordsSum> {
     int selectSum();
     List<RecordsSum> selectSort();
     List<RecordsSum> weekData(String starttime, String endtime);
     List<RecordsSum> dayData(String starttime, String endtime);

}
