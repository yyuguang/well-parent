package com.lnzz.commonutils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * ClassName：OrderNoUtil
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/25 18:09
 * @Description 生成订单号
 */
public class OrderNoUtil {
    /**
     * 获取订单号
     *
     * @return
     */
    public static String getOrderNo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate = sdf.format(new Date());
        String result = "";
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            result += random.nextInt(10);
        }
        return newDate + result;
    }
}
