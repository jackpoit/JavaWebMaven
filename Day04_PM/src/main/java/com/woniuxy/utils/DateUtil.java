package com.woniuxy.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *将字符串转换成日期
 * @Author: jackpoit
 * @Date: 2021/07/21/15:59
 * @Description:
 */
public class DateUtil {
	public static final String DATE_PATTERN = "yyyy-MM-dd";
	public static SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);

	public static Date parse(String str) {
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
