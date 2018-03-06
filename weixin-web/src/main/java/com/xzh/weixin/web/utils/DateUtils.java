/*
 * Copyright (c) 2014 www.jd.com All rights reserved.
 * 本软件源代码版权归京东成都云平台所有,未经许可不得任意复制与传播.
 */
package com.xzh.weixin.web.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期工具类
 */
public class DateUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);

    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static int getWeekOfDate(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return w;
    }

    public static long getServerTime() {
        return System.currentTimeMillis();
    }

    /**
     * 格式化yyyy-MM-dd'T'HH:mm:ssZ并除以1000精确到秒
     *
     * @param time
     * @return
     */
    public static Long parseTime(String time) {
        if (StringUtils.isEmpty(time)) {
            return null;
        }
        Date date = parseDate(time, "yyyy-MM-dd'T'HH:mm:ssZ");
        return date.getTime() / 1000;
    }

    public static Date parseTimeToDate(String time) {
        if (StringUtils.isEmpty(time)) {
            return null;
        }
        return parseDate(time, "yyyy-MM-dd HH:mm:ss");
    }

    public static void main(String[] args) {
        // String ss = "2015-12-21T09:31:00Z";
        // String parseTimeToStande = parseEightZoneTimeToStande(ss);
        // System.out.println(parseTimeToStande);

        Date parseDate1 = parseDate("12-14 09:09", "MM-dd HH:mm");
        int weekOfDate1 = getWeekOfDate(parseDate1);
        System.out.println(weekOfDate1);

        Date parseDate2 = parseDate("12-15 09:09", "MM-dd HH:mm");
        int weekOfDate2 = getWeekOfDate(parseDate2);
        System.out.println(weekOfDate2);

        Date parseDate3 = parseDate("12-20 09:09", "MM-dd HH:mm");
        int weekOfDate3 = getWeekOfDate(parseDate3);
        System.out.println(weekOfDate3);

        int weekOfDate = getWeekOfDate(new Date());
        System.out.println(weekOfDate);

        String startTimeInDay = "00:00";
        String endTimeInDay = "24:00";

        Date date = new Date();

        String format = DateUtils.format(date, "HH:mm");
        if (format.compareTo(startTimeInDay) < 0 || format.compareTo(endTimeInDay) > 0) {
            System.out.println(false);
        }

    }

    /**
     * 格式化yyyy-MM-dd'T'HH:mm:ssZ
     *
     * @param time
     * @return
     */
    public static Long parseTimeToMilliSecond(String time) {
        if (StringUtils.isEmpty(time)) {
            return null;
        }
        Date date = parseDate(time, "yyyy-MM-dd'T'HH:mm:ssZ");
        return date.getTime();
    }

    /**
     * 格式化yyyy-MM-dd'T'HH:mm:ssZ 有些虚拟机不支持 直接转换东八区会报错
     *
     * @param time
     * @return
     */
    public static String parseEightZoneTimeToStande(String time) {
        if (StringUtils.isEmpty(time)) {
            return null;
        }
        return dateFormat(parseEightZone(time));
    }

    public static long parseEightZone(String time) {
        if (StringUtils.isEmpty(time)) {
            return 0;
        }
        time = time.replace("T", " ").replace("Z", "");
        Date date = parseDate(time, "yyyy-MM-dd HH:mm:ss");
        long hour_8 = 1000 * 60 * 60 * 8;
        long gettime = date.getTime() + hour_8;
        return gettime;
    }

    /**
     * 日期字符串格式转化
     *
     * @param datetime
     * @param partten
     * @return
     */
    public static String covertDateFormat(String datetime, String sourcePartten, String targetPartten) {
        SimpleDateFormat sdf = new SimpleDateFormat(sourcePartten);
        try {
            Date date = sdf.parse(datetime);
            sdf.applyPattern(targetPartten);
            return sdf.format(date);
        } catch (ParseException e) {
            LOGGER.warn("日期格式化失败.{}", e.getMessage());
        }
        return null;
    }

    /**
     * 字符串格式化为日期
     *
     * @param datetime
     * @param partten
     * @return
     */
    public static Date parseDate(String datetime, String partten) {
        SimpleDateFormat sdf = new SimpleDateFormat(partten);
        try {
            return sdf.parse(datetime);
        } catch (ParseException e) {
            LOGGER.error("日期格式化失败:" + datetime);
            LOGGER.warn("日期格式化失败.{}", e.getMessage());
        }
        return null;
    }

    /**
     * 格式化日期,默认返回yyyy-MM-dd'T'HH:mm:ssZ
     *
     * @param date
     * @return
     */
    public static String formatTZone(Date date) {
        return format(date, "yyyy-MM-dd'T'HH:mm:ssZ");
    }

    /**
     * 格式化日期,默认返回yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        return format(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 格式化显示当前日期
     *
     * @param format
     * @return
     */
    public static String format(String format) {
        return format(new Date(), format);
    }

    /**
     * 日期格式化
     *
     * @param date
     * @param format
     * @return
     */
    public static String format(Date date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } catch (Exception e) {
            LOGGER.warn("日期格式化失败.{}", e.getMessage());
        }
        return null;
    }

    /**
     * 时间格式化， 传入毫秒
     *
     * @param time
     * @return
     */
    public static String dateFormat(long time) {
        return format(new Date(time), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 东八区 时间格式化， 传入毫秒
     *
     * @param time
     * @return
     */
    public static String dateFormatEast(Long time) {
        if (null == time || time == 0) {
            return "";
        }
        return format(new Date(time), "yyyy-MM-dd'T'HH:mm:ssZ");
    }

    /**
     * 获取某一日期的起始时间（0点5分0秒），参数为null时则返回当前日期的起始时间
     *
     * @param date
     * @return Date
     */
    public static long getStartTime(Date date) {
        Calendar now = Calendar.getInstance();
        if (date != null) {
            now.setTime(date);
        }
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 15);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);
        return now.getTime().getTime() / 1000;
    }

    /**
     * 针对传入时间分钟进行加、减操作
     *
     * @param dateStr
     * @param offset
     * @return
     */
    public static String getTranformTime(String dateStr, int offset) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = dateFormat.parse(dateStr);

            Calendar calendar = Calendar.getInstance();

            calendar.setTime(date);

            calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + offset);

            date = calendar.getTime();

        } catch (ParseException e) {
            LOGGER.warn("计算日期分钟失败.{}", e.getMessage());
        }
        return dateFormat.format(date);
    }

    public static Date getGMTDate(String date) {

        Date d = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            // sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            d = sdf.parse(date);
        } catch (Exception e) {
            LOGGER.error("转换日期失败.{}", e.getMessage());
        }
        return d;
    }

}
