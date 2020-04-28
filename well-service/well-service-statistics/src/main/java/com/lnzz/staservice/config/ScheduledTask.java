package com.lnzz.staservice.config;

import com.lnzz.commonutils.DateUtil;
import com.lnzz.staservice.service.StatisticsDailyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * ClassName：ScheduledTask
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/28 9:39
 * @Description
 */
@Component
@Slf4j
public class ScheduledTask {

    @Autowired
    private StatisticsDailyService dailyService;

    /**
     * 在每天凌晨1点，把前一天数据进行数据查询添加
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void task() {
        dailyService.registerCount(DateUtil.formatDate(DateUtil.addDays(new Date(), -1)));
    }
}
