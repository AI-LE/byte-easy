package com.mbyte.easy.admin.controller;

import com.mbyte.easy.admin.entity.RecordsSum;
import com.mbyte.easy.admin.service.IRecordsSumService;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 〈p〉
 * 数据合计
 * 〈/p〉
 *
 * @author 刘雪奇
 * @create 2019/5/29
 * @since 1.0.0
 */
@Controller
@RequestMapping("/admin/recordsSum")
public class RecordsSumController extends BaseController {

    private String prefix = "admin/recordsSum/";

    @Autowired
    private IRecordsSumService recordsSumService;

    /**
     * 查询总和
     */
    @PostMapping("selectSum")
    @ResponseBody
    public AjaxResult selectSum(String starttime, String endtime) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String nowday = DateUtil.format(LocalDateTime.now(),DateUtil.PATTERN_yyyyMMdd);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, - 6);
        Date lastday = c.getTime();
        String preMonday = sdf.format(lastday);
        starttime = preMonday + " 00:00:00";
        endtime = nowday + " 23:59:59";

        SimpleDateFormat fgh = new SimpleDateFormat("MM-dd");
        SimpleDateFormat fds = new SimpleDateFormat("yyyy-MM-dd");
        String seven = fgh.format(lastday);
        String qi = fds.format(lastday);
        String sevenstart = qi + " 00:00:00";
        String sevenend = qi + " 23:59:59";

        c.add(Calendar.DATE,1);
        Date sixday = c.getTime();
        String six = fgh.format(sixday);
        String liu = fds.format(sixday);
        String sixstart = liu + " 00:00:00";
        String sixend = liu + " 23:59:59";

        c.add(Calendar.DATE,1);
        Date fiveday = c.getTime();
        String five = fgh.format(fiveday);
        String wu = fds.format(fiveday);
        String fivestart = wu + " 00:00:00";
        String fiveend = wu + " 23:59:59";

        c.add(Calendar.DATE,1);
        Date fourday = c.getTime();
        String four = fgh.format(fourday);
        String si = fds.format(fourday);
        String fourstart = si + " 00:00:00";
        String fourend = si + " 23:59:59";


        c.add(Calendar.DATE,1);
        Date threeday = c.getTime();
        String three = fgh.format(threeday);
        String san = fds.format(threeday);
        String threestart = san + " 00:00:00";
        String threeend = san + " 23:59:59";


        c.add(Calendar.DATE,1);
        Date twoday = c.getTime();
        String two = fgh.format(twoday);
        String er = fds.format(twoday);
        String twostart = er + " 00:00:00";
        String twoend = er + " 23:59:59";


        c.add(Calendar.DATE,1);
        Date oneday = c.getTime();
        String one = fgh.format(oneday);
        String yi = fds.format(oneday);
        String nowstart = yi + " 00:00:00";
        String nowend = yi + " 23:59:59";



        int sum = recordsSumService.selectSum();
        List<RecordsSum> selectSan = recordsSumService.selectSort();
        List<RecordsSum> weekData = recordsSumService.weekData(starttime, endtime);
        List<RecordsSum> sevenData = recordsSumService.dayData(sevenstart, sevenend);
        List<RecordsSum> sixData = recordsSumService.dayData(sixstart, sixend);
        List<RecordsSum> fiveData = recordsSumService.dayData(fivestart, fiveend);
        List<RecordsSum> fourData = recordsSumService.dayData(fourstart, fourend);
        List<RecordsSum> threeData = recordsSumService.dayData(threestart, threeend);
        List<RecordsSum> twoData = recordsSumService.dayData(twostart, twoend);
        List<RecordsSum> nowData = recordsSumService.dayData(nowstart, nowend);

        Map<String,String> map = new HashMap<String, String>();
        map.put("sum",sum + "");
        for(int i = 0;i < selectSan.size();i++){
            if("微博".equals(selectSan.get(i).getType())){
                map.put("selectWeibo",selectSan.get(i).getRecords().toString());
            }
            if("百度知道".equals(selectSan.get(i).getType())){
                map.put("selectBaidu",selectSan.get(i).getRecords().toString());
            }
            if("百度关键字".equals(selectSan.get(i).getType())){
                map.put("selectBaiduKeyword",selectSan.get(i).getRecords().toString());
            }
            if("知乎".equals(selectSan.get(i).getType())){
                map.put("selectZhihu",selectSan.get(i).getRecords().toString());
            }
            if("微博关键字".equals(selectSan.get(i).getType())){
                map.put("selectWeiboKeyword",selectSan.get(i).getRecords().toString());
            }
            if("微博问答".equals(selectSan.get(i).getType())){
                map.put("selectWeiboQusetion",selectSan.get(i).getRecords().toString());
            }
            if("微博文章".equals(selectSan.get(i).getType())){
                map.put("selectWeiboMessage",selectSan.get(i).getRecords().toString());
            }
            if("微信".equals(selectSan.get(i).getType())){
                map.put("selectWechat",selectSan.get(i).getRecords().toString());
            }
        }
        for(int i = 0;i < weekData.size();i++){
            if("微博".equals(weekData.get(i).getType())){
                map.put("weekWeibo",weekData.get(i).getRecords().toString());
            }
            if("百度知道".equals(weekData.get(i).getType())){
                map.put("weekBaidu",weekData.get(i).getRecords().toString());
            }
            if("知乎".equals(weekData.get(i).getType())){
                map.put("weekZhihu",weekData.get(i).getRecords().toString());
            }
            if("微博关键字".equals(weekData.get(i).getType())){
                map.put("weekWeiboKeyword",weekData.get(i).getRecords().toString());
            }
            if("微博文章".equals(weekData.get(i).getType())){
                map.put("weekWeiboMeaasge",weekData.get(i).getRecords().toString());
            }
            if("微博问答".equals(weekData.get(i).getType())){
                map.put("weekWeiboQuestion",weekData.get(i).getRecords().toString());
            }
            if("百度关键字".equals(weekData.get(i).getType())){
                map.put("weekBaiduKeyword",weekData.get(i).getRecords().toString());
            }
            if("微信".equals(weekData.get(i).getType())){
                map.put("weekWechat",weekData.get(i).getRecords().toString());
            }
        }

        map.put("sevenday",seven);
        map.put("sixday",six);
        map.put("fiveday",five);
        map.put("fourday",four);
        map.put("threeday",three);
        map.put("twoday",two);
        map.put("nowday",one);

        for(int i = 0;i < sevenData.size();i++) {
            if("百度知道".equals(sevenData.get(i).getType())){
                map.put("seven1",sevenData.get(i).getRecords().toString());
            }
            if("知乎".equals(sevenData.get(i).getType())){
                map.put("seven2",sevenData.get(i).getRecords().toString());
            }
            if("微博".equals(sevenData.get(i).getType())){
                map.put("seven3",sevenData.get(i).getRecords().toString());
            }
            if("微博关键字".equals(sevenData.get(i).getType())){
                map.put("seven4",sevenData.get(i).getRecords().toString());
            }
            if("微博文章".equals(sevenData.get(i).getType())){
                map.put("seven5",sevenData.get(i).getRecords().toString());
            }
            if("微博问答".equals(sevenData.get(i).getType())){
                map.put("seven6",sevenData.get(i).getRecords().toString());
            }
            if("百度关键字".equals(sevenData.get(i).getType())){
                map.put("seven7",sevenData.get(i).getRecords().toString());
            }
            if("微信".equals(sevenData.get(i).getType())){
                map.put("seven8",sevenData.get(i).getRecords().toString());
            }
        }
        for(int i = 0;i < sixData.size();i++) {
            if("百度知道".equals(sixData.get(i).getType())){
                map.put("six1",sixData.get(i).getRecords().toString());
            }
            if("知乎".equals(sixData.get(i).getType())){
                map.put("six2",sixData.get(i).getRecords().toString());
            }
            if("微博".equals(sixData.get(i).getType())){
                map.put("six3",sixData.get(i).getRecords().toString());
            }
            if("微博关键字".equals(sixData.get(i).getType())){
                map.put("six4",sixData.get(i).getRecords().toString());
            }
            if("微博文章".equals(sixData.get(i).getType())){
                map.put("six5",sixData.get(i).getRecords().toString());
            }
            if("微博问答".equals(sixData.get(i).getType())){
                map.put("six6",sixData.get(i).getRecords().toString());
            }
            if("百度关键字".equals(sixData.get(i).getType())){
                map.put("six7",sixData.get(i).getRecords().toString());
            }
            if("微信".equals(sixData.get(i).getType())){
                map.put("six8",sixData.get(i).getRecords().toString());
            }

        }
        for(int i = 0;i < fiveData.size();i++) {
            if("百度知道".equals(fiveData.get(i).getType())){
                map.put("five1",fiveData.get(i).getRecords().toString());
            }
            if("知乎".equals(fiveData.get(i).getType())){
                map.put("five2",fiveData.get(i).getRecords().toString());
            }
            if("微博".equals(fiveData.get(i).getType())){
                map.put("five3",fiveData.get(i).getRecords().toString());
            }
            if("微博关键字".equals(fiveData.get(i).getType())){
                map.put("five4",fiveData.get(i).getRecords().toString());
            }
            if("微博文章".equals(fiveData.get(i).getType())){
                map.put("five5",fiveData.get(i).getRecords().toString());
            }
            if("微博问答".equals(fiveData.get(i).getType())){
                map.put("five6",fiveData.get(i).getRecords().toString());
            }
            if("百度关键字".equals(fiveData.get(i).getType())){
                map.put("five7",fiveData.get(i).getRecords().toString());
            }
            if("微信".equals(fiveData.get(i).getType())){
                map.put("five8",fiveData.get(i).getRecords().toString());
            }

        }
        for(int i = 0;i < fourData.size();i++) {
            if("百度知道".equals(fourData.get(i).getType())){
                map.put("four1",fourData.get(i).getRecords().toString());
            }
            if("知乎".equals(fourData.get(i).getType())){
                map.put("four2",fourData.get(i).getRecords().toString());
            }
            if("微博".equals(fourData.get(i).getType())){
                map.put("four3",fourData.get(i).getRecords().toString());
            }
            if("微博关键字".equals(fourData.get(i).getType())){
                map.put("four4",fourData.get(i).getRecords().toString());
            }
            if("微博文章".equals(fourData.get(i).getType())){
                map.put("four5",fourData.get(i).getRecords().toString());
            }
            if("微博问答".equals(fourData.get(i).getType())){
                map.put("four6",fourData.get(i).getRecords().toString());
            }
            if("百度关键字".equals(fourData.get(i).getType())){
                map.put("four7",fourData.get(i).getRecords().toString());
            }
            if("微信".equals(fourData.get(i).getType())){
                map.put("four8",fourData.get(i).getRecords().toString());
            }

        }
        for(int i = 0;i < threeData.size();i++) {
            if("百度知道".equals(threeData.get(i).getType())){
                map.put("three1",threeData.get(i).getRecords().toString());
            }
            if("知乎".equals(threeData.get(i).getType())){
                map.put("three2",threeData.get(i).getRecords().toString());
            }
            if("微博".equals(threeData.get(i).getType())){
                map.put("three3",threeData.get(i).getRecords().toString());
            }
            if("微博关键字".equals(threeData.get(i).getType())){
                map.put("three4",threeData.get(i).getRecords().toString());
            }
            if("微博文章".equals(threeData.get(i).getType())){
                map.put("three5",threeData.get(i).getRecords().toString());
            }
            if("微博问答".equals(threeData.get(i).getType())){
                map.put("three6",threeData.get(i).getRecords().toString());
            }
            if("百度关键字".equals(threeData.get(i).getType())){
                map.put("three7",threeData.get(i).getRecords().toString());
            }
            if("微信".equals(threeData.get(i).getType())){
                map.put("three8",threeData.get(i).getRecords().toString());
            }

        }
        for(int i = 0;i < twoData.size();i++) {
            if("百度知道".equals(twoData.get(i).getType())){
                map.put("two1",twoData.get(i).getRecords().toString());
            }
            if("知乎".equals(twoData.get(i).getType())){
                map.put("two2",twoData.get(i).getRecords().toString());
            }
            if("微博".equals(twoData.get(i).getType())){
                map.put("two3",twoData.get(i).getRecords().toString());
            }
            if("微博关键字".equals(twoData.get(i).getType())){
                map.put("two4",twoData.get(i).getRecords().toString());
            }
            if("微博文章".equals(twoData.get(i).getType())){
                map.put("two5",twoData.get(i).getRecords().toString());
            }
            if("微博问答".equals(twoData.get(i).getType())){
                map.put("two6",twoData.get(i).getRecords().toString());
            }
            if("百度关键字".equals(twoData.get(i).getType())){
                map.put("two7",twoData.get(i).getRecords().toString());
            }
            if("微信".equals(twoData.get(i).getType())){
                map.put("two8",twoData.get(i).getRecords().toString());
            }

        }
        for(int i = 0;i < nowData.size();i++) {
            if("百度知道".equals(nowData.get(i).getType())){
                map.put("now1",nowData.get(i).getRecords().toString());
            }
            if("知乎".equals(nowData.get(i).getType())){
                map.put("now2",nowData.get(i).getRecords().toString());
            }
            if("微博".equals(nowData.get(i).getType())){
                map.put("now3",nowData.get(i).getRecords().toString());
            }
            if("微博关键字".equals(nowData.get(i).getType())){
                map.put("now4",nowData.get(i).getRecords().toString());
            }
            if("微博文章".equals(nowData.get(i).getType())){
                map.put("now5",nowData.get(i).getRecords().toString());
            }
            if("微博问答".equals(nowData.get(i).getType())){
                map.put("now6",nowData.get(i).getRecords().toString());
            }
            if("百度关键字".equals(nowData.get(i).getType())){
                map.put("now7",nowData.get(i).getRecords().toString());
            }
            if("微信".equals(nowData.get(i).getType())){
                map.put("now8",nowData.get(i).getRecords().toString());
            }

        }
        return success(map);
    }

}
