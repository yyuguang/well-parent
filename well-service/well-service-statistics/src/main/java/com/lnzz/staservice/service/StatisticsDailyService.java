package com.lnzz.staservice.service;

import com.lnzz.staservice.pojo.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author 冷暖自知
 * @since 2020-04-27
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    /**
     * 统计某一天的注册人数
     *
     * @param day
     */
    void registerCount(String day);

    /**
     * 获取图表数据
     *
     * @return
     */
    Map<String, Object> getChatData(String type, String begin, String end);
}
