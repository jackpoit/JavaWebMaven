package com.woniuxy.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 处理日期的工具类
 * @author
 * @date 2021/7/21 0021-15:58
 */
public class DateUtil {
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);

    /**
     * 将字符串转换成一个日期对象
     * @param str
     * @return
     */
    public static Date parse(String str){
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
