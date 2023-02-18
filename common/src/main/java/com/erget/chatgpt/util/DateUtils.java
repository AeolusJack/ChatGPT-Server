package com.erget.chatgpt.util;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;



public class DateUtils {
    public static final String[] PARSE_PATTERNS = new String[]{"yyyy-MM-dd", "yyyyMMdd", "yyyy年MM月dd日"};
    private static final Logger LOG = LoggerFactory.getLogger(DateUtils.class);

    public DateUtils() {
    }

    public static String getDateTime() {
        return StringUtils.remove(DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date()), "T");
    }

    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / 86400000L;
        return Integer.parseInt(String.valueOf(between_days));
    }

    public static int daysBetween(String smdate, String bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / 86400000L;
        return Integer.parseInt(String.valueOf(between_days));
    }

    public static String getNowDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取当天日期的Sting字符串，年月日组合，无其他符号
     * @return
     */
    public static String getNowDateShortNum() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
    public static String getNowDateShort(String format) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static String dayBefore(String format) {
        if (StringUtils.isBlank(format)) {
            format = "yyy-MM-dd";
        }
        SimpleDateFormat dft = new SimpleDateFormat(format);
        Date now = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(now);
        date.set(5, date.get(5) - 1);
        String daybefor = "";

        try {
            daybefor = dft.format(date.getTime());
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return daybefor;
    }

    public static boolean isDayAfter(String dateStr, String format) {
        SimpleDateFormat dft = new SimpleDateFormat(format);
        Date now = new Date();
        Calendar date1 = Calendar.getInstance();
        try {
            date1.setTime(parse(dateStr + " 00:00:00", format));
        } catch (Exception var6) {
            return false;
        }
        Calendar date2 = Calendar.getInstance();
        date2.setTime(now);
        return date2.getTimeInMillis() > date1.getTimeInMillis();
    }


    public static String dayafter(String dateStr, String format) {
        SimpleDateFormat dft = new SimpleDateFormat(format);
        new Date();
        Date now;
        if (StringUtils.isNotBlank(dateStr)) {
            try {
                now = parse(dateStr, format);
            } catch (ParseException var8) {
                now = new Date();
            }
        } else {
            now = new Date();
        }

        Calendar date = Calendar.getInstance();
        date.setTime(now);
        date.set(5, date.get(5) + 1);
        String daybefor = "";

        try {
            daybefor = dft.format(date.getTime());
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        return daybefor;
    }

    /**
     * 指定日期往前几天
     *
     * @param date
     * @param day
     * @return
     */
    public static Date dayBefore(Date date, long day) {
        long time = date.getTime(); // 得到指定日期的毫秒数
        day = day * 24 * 60 * 60 * 1000; // 要加上的天数转换成毫秒数
        time -= day; // 相减得到新的毫秒数
        // time -= day; // 相加得到新的毫秒数
        return new Date(time); // 将毫秒数转换成日期
    }

    /**
     * 指定日期往后几天
     *
     * @param date
     * @param day
     * @return
     */
    public static Date dayAfter(Date date, long day) {
        long time = date.getTime(); // 得到指定日期的毫秒数
        day = day * 24 * 60 * 60 * 1000; // 要加上的天数转换成毫秒数
        time += day; // 相减得到新的毫秒数
        // time -= day; // 相加得到新的毫秒数
        return new Date(time); // 将毫秒数转换成日期
    }

    public static String DateToStr(Date date, String format) {
        if (StringUtils.isBlank(format)) {
            format = "yyyy-MM-dd";
        }

        if (date == null) {
            date = new Date();
        }

        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(date);
        return dateString;
    }


    public static Date parse(String strDate, String pattern) throws ParseException {
        return StringUtils.isBlank(strDate) ? null : (new SimpleDateFormat(pattern)).parse(strDate);
    }

    public static Date parse5Num(String dateNum) {
        Calendar calendar = new GregorianCalendar(1900, 0, 0);
        calendar.add(6, Integer.parseInt(dateNum));
        return calendar.getTime();
    }

    public static void pause() {
        while (needPause()) {
            LOG.warn("禁止在工作时间采集人行数据, 10分钟后重试");

            try {
                TimeUnit.MINUTES.sleep(10L);
            } catch (InterruptedException var1) {
                LOG.error("线程暂停异常", var1);
            }
        }

    }

    public static boolean needPause() {
        Calendar calendar = Calendar.getInstance();
        return needPause(calendar);
    }

    public static boolean needPause(Calendar calendar) {
        int year = calendar.get(1);
        int month = calendar.get(2);
        int day = calendar.get(6);
        int dayOfWeek = calendar.get(7);
        int hourOfDay = calendar.get(11);
        int minute = calendar.get(12);
        if (dayOfWeek != 7 && dayOfWeek != 1) {
            Integer[] days = new Integer[]{2, 3, 4, 5, 6};
            if (year == 2017 && month == 9 && Arrays.asList(days).contains(day)) {
                return false;
            } else {
                return hourOfDay <= 17 && (hourOfDay != 17 || minute <= 30);
            }
        } else {
            return year == 2017 && month == 8 && day == 30;
        }
    }


    public Calendar initHolidayList(String dateStr) {
        try {
            Date date = org.apache.commons.lang.time.DateUtils.parseDate(dateStr, PARSE_PATTERNS);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar;
        } catch (ParseException var4) {
            LOG.warn("添加节假日日期格式错误，请使用yyyy-MM-dd格式", var4);
            return null;
        }
    }

    public boolean checkHoliday(Calendar calendar) throws Exception {
        List<Calendar> holidayList = new ArrayList();
        Iterator var3 = holidayList.iterator();

        Calendar ca;
        do {
            if (!var3.hasNext()) {
                return false;
            }

            ca = (Calendar) var3.next();
        } while (ca.get(1) != calendar.get(1) || ca.get(2) != calendar.get(2) || ca.get(5) != calendar.get(5));

        return true;
    }

    public static boolean dateIsLarge(String d1, String d2) throws Exception {
        Date date1 = org.apache.commons.lang.time.DateUtils.parseDate(d1, PARSE_PATTERNS);
        Date date2 = org.apache.commons.lang.time.DateUtils.parseDate(d2, PARSE_PATTERNS);
        return date1.before(date2);
    }

    public static Date getDateTime(Long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }


    public static boolean isBetween(Date date, Date sdate, Date edate) {
        long dateTimes = date.getTime();
        long sdateTimes = sdate.getTime();
        long edateTimes = edate.getTime();
        return edateTimes >= dateTimes && sdateTimes <= dateTimes;
    }


    public static Date parseDate(String dateStr) throws ParseException {
        return org.apache.commons.lang.time.DateUtils.parseDate(dateStr, PARSE_PATTERNS);
    }

    public static boolean isInInvalidTime(String startTime, String endTime) {
        if (StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime)) {
            return true;
        } else {
            Date date = new Date();
            SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
            String nowTime = sdfTime.format(date);
            if (startTime.compareTo(nowTime) <= 0 && endTime.compareTo(nowTime) >= 0) {
                return true;
            } else {
                return false;
            }
        }
    }


    public static String lastYearLastDay() {
        Calendar cal = Calendar.getInstance();
        return (cal.get(Calendar.YEAR) - 1) + "1231";
    }

    public static String thisYearMidDay() {
        Calendar cal = Calendar.getInstance();
        return (cal.get(Calendar.YEAR)) + "0701";
    }

    public static String thisYearNowDay(){
        return DateToStr(new Date(),"yyyy-MM-dd");
    }

    public static String strToStrAsFormat(String dateStr,String format) throws ParseException {
        if(StringUtils.isBlank(dateStr)){
            return null;
        }else{
            return DateUtils.DateToStr(DateUtils.parseDate(dateStr), format);
        }
    }

    /**
     * 生产年月日时分秒毫秒+随机数
     *
     * @param randomLenth
     * @return
     */
    public static String currentLongTimeToStr(int randomLenth) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String dateString = formatter.format(date) + RandomUtil.getMixNumStr(randomLenth);
        return dateString;
    }


    /**
     * 判断是否是对应的时间格式
     * @param dateStr
     * @param format
     * @return
     */
    public static boolean isDateFormat(String dateStr,String format){
        if(StringUtils.isBlank(dateStr)){
            return false;
        }else{
            try{
                return true;
            }catch (Exception e){
                return false;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(isDateFormat("2019-12-31 00:00:00", "yyyy-MM-dd HH:mm:ss"));
        System.out.println(isDateFormat("2019-12-31 00:00", "yyyy-MM-dd HH:mm:ss"));
    }
    /**
     * 当前时间比预设的时间之后
     * @param dateStr
     * @param format
     * @return
     */
    public static boolean isMinuteAfter(String dateStr, String format){
        Date now = new Date();
        Calendar date1 = Calendar.getInstance();
        try {
            date1.setTime(parse(dateStr, format));
        } catch (Exception var6) {
            return false;
        }
        Calendar date2 = Calendar.getInstance();
        date2.setTime(now);
        return date2.getTimeInMillis() > date1.getTimeInMillis();
    }

    /**
     * 当前时间比预设的时间之后
     * @param date
     * @return
     */
    public static boolean isMinuteAfter(Date date){
        Calendar date1 = Calendar.getInstance();
        date1.setTime(date);
        Date now = new Date();
        Calendar date2 = Calendar.getInstance();
        date2.setTime(now);
        return date2.getTimeInMillis() > date1.getTimeInMillis();
    }

    /**
     * 当前时间是否是预设时间second秒后。
     * @param date
     * @param second
     * @return
     */
    public static boolean isSecondAfter(Date date, long second) {
        long time = date.getTime(); // 得到指定日期的毫秒数
        time += second * 1000; // 相加得到新的毫秒数
        return System.currentTimeMillis() >= time;
    }

    /**
     * 判断当前日期是周几(1 代表周日，7代表周六)
     * @param date
     * @return
     */
    public static int getWeekOfDate(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK);
        return w;
    }
}
