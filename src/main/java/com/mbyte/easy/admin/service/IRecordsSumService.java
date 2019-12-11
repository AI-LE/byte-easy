package com.mbyte.easy.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mbyte.easy.admin.entity.RecordsSum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈p〉
 * <p>
 * 〈/p〉
 *
 * @author 刘雪奇
 * @create 2019/5/29
 * @since 1.0.0
 */
public interface IRecordsSumService extends IService<RecordsSum> {
    int selectSum();
    List<RecordsSum> selectSort();
    List<RecordsSum> weekData(String starttime, String endtime);
    List<RecordsSum> dayData(String starttime, String endtime);

}
