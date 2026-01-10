package com.astra.dewa.utils;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

    private static final Log _log = LogFactoryUtil.getLog(DateUtil.class);

    public final static String[] MONTH_INDONESIA = {"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};
    public final static String[] DAY_INDONESIA = {"Minggu", "Senin", "Selasa", "Rabu", "Kamis", "Jum'at", "Sabtu"};

    /**
     * @return the list of month in Indonesia
     */
    public static JSONArray getMonthList() {
        JSONArray jsonData = JSONFactoryUtil.createJSONArray();
        int i = 0;
        for (String month : MONTH_INDONESIA) {
            JSONObject dto = JSONFactoryUtil.createJSONObject();
            dto.put("id", ++i);
            dto.put("text", month);
            jsonData.put(dto);
        }
        return jsonData;
    }

    /**
     * @param id the month index in calendar
     * @return month name of the selected index
     */
    public static String getMonthById(int id) {
        try {
            return MONTH_INDONESIA[id - 1];
        } catch (IndexOutOfBoundsException e) {
            return "Bulan tidak valid!";
        }
    }

    public static String getDayNameIndonesia(Date date) {
        String dayName = "";
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            dayName = DAY_INDONESIA[dayOfWeek];
        } catch (Exception e) {
            _log.error(e.getMessage(), e);
        }
        return dayName;
    }

    public static String convertDateToStringIndo(Date date, boolean includeDay) {
        SimpleDateFormat shortDate = new SimpleDateFormat("dd");
        String dateString = "";
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            String day = shortDate.format(date);
            String month = MONTH_INDONESIA[calendar.get(Calendar.MONTH)];
            String year = String.valueOf(calendar.get(Calendar.YEAR));
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            String dayName = DAY_INDONESIA[dayOfWeek];

            if (includeDay) {
                dateString = dayName + ", " + day + " " + month + " " + year;
            } else {
                dateString = day + " " + month + " " + year;
            }
        } catch (Exception e) {
            _log.warn(e.getMessage(), e);
            return "-";
        }
        return dateString;
    }

    public static String dateToString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return simpleDateFormat.format(date);
    }

    public static Date stringToDate(String dateString) {
        return Date.from(LocalDate.parse(dateString).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static LocalDateTime convertToLocalDateTimeViaMilisecond(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    public static Date convertToDateViaInstant(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public static Date convertToDateViaSqlTimestamp(LocalDateTime dateToConvert) {
        return java.sql.Timestamp.valueOf(dateToConvert);
    }

    public static Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return java.util.Date
                .from(dateToConvert.atZone(ZoneId.systemDefault())
                        .toInstant());
    }

    public static LocalDateTime stringToLocalDateTime(String value) {
        String[] ones = value.split(" ");      /* 06/05/2023 09:30 */
        String[] twos = ones[0].split("/");    /* 06/05/2023 */
        String[] threes = ones[1].split(":");  /* 06/05/2023 09:30 */
        return LocalDateTime.of(
                Integer.parseInt(twos[2]),    // year
                Integer.parseInt(twos[1]),    // month
                Integer.parseInt(twos[0]),    // day
                Integer.parseInt(threes[0]),  // hour
                Integer.parseInt(threes[1])   // minute
        );
    }

    public static String localDateTimeToStringHours(LocalDateTime value) {
        return addFirstZero(value.getHour()) + ":" + addFirstZero(value.getMinute());
    }

    @SuppressWarnings("deprecation")
    public static String localDateTimeToStringHours(Date date) {
        return addFirstZero(date.getHours()) + ":" + addFirstZero(date.getMinutes());
    }

    private static String addFirstZero(int value) {
        if (value < 10) {
            return "0" + value;
        } else {
            return "" + value;
        }
    }

    /**
     * @param dateString raw date as string
     * @param format     format of date to be parsed
     * @return date with selected format
     */
    public static Date stringToDate(String dateString, String format) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            simpleDateFormat.setLenient(false);
            return simpleDateFormat.parse(dateString);
        } catch (ParseException pe) {
            _log.error("Error parsing date: " + pe.getMessage());
            return null;
        }
    }

    /**
     * @param date   date with default date format
     * @param format format of date to be parsed
     * @return date as string with selected format
     */
    public static String dateToString(Date date, String format) {
        if (date == null) {
            return "-";
        }
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * @param date
     * @param days
     * @return
     */
    public static Date addDays(Date date, int days) {
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); // minus number would decrement the days
        return cal.getTime();
    }

    /**
     * @param date
     * @return
     */
    public static Date toDate(final String date) {
        return toDate(date, "00:00.00.000");
    }

    public static Date toDate(final String date, final String time) {
        try {
            return new SimpleDateFormat("yyyy/dd/MM").parse(date + " " + time);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Date convertDate(String strDate) {
        SimpleDateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat changeFormat = new SimpleDateFormat("yyyy/dd/MM");
        Date date = null;
        try {
            date = originalFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date convert = DateUtil.toDate(changeFormat.format(date));
        return convert;
    }

    public static String convertDateToString(Date date) {
        String result = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            result = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String convertDateToStringYear(Date date) {
        String result = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            result = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String dateToMonthName(Date date) {
        String result = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
            result = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getDays(Date aDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(aDate);
        int dayOfMonth = cal.get(Calendar.DAY_OF_WEEK);
        String dayOfMonthStr = null;
        switch (dayOfMonth) {
            case 1:
                dayOfMonthStr = "Minggu";
                break;
            case 2:
                dayOfMonthStr = "Senin";
                break;
            case 3:
                dayOfMonthStr = "Selasa";
                break;
            case 4:
                dayOfMonthStr = "Rabu";
                break;
            case 5:
                dayOfMonthStr = "Kamis";
                break;
            case 6:
                dayOfMonthStr = "Jumat";
                break;
            case 7:
                dayOfMonthStr = "Sabtu";
        }
        return dayOfMonthStr;
    }

    public static Integer getMonthNumberByName(String month) {
        Integer result = 0;

        switch (month) {
            case "Januari":
                result = 1;
                break;
            case "Februari":
                result = 2;
                break;
            case "Maret":
                result = 3;
                break;
            case "April":
                result = 4;
                break;
            case "Mei":
                result = 5;
                break;
            case "Juni":
                result = 6;
                break;
            case "Juli":
                result = 7;
                break;
            case "Agustus":
                result = 8;
                break;
            case "September":
                result = 9;
                break;
            case "Oktober":
                result = 10;
                break;
            case "November":
                result = 11;
                break;
            case "Desember":
                result = 12;
                break;
        }

        return result;
    }

    public static String changeDateToSDF(Date date, String hour) {
        String result = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdfDateMinute = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String dateAfter = sdfDate.format(date) + " " + hour;
            Date dateFinal = sdfDateMinute.parse(dateAfter);
            result = sdf.format(dateFinal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Date expiredDateOTP(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, 10);
        return calendar.getTime();
    }

    public static String formatDate(Date date, String pattern, ZoneId zoneId) {
        if (Validator.isNull(date) || Validator.isNull(pattern)) {
            return "";
        }

        Instant instant = date.toInstant();
        LocalDateTime ldt = LocalDateTime.ofInstant(instant, zoneId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return ldt.format(formatter);
    }
}
