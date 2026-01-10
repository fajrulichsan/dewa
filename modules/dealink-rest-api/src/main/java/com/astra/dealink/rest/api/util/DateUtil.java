package com.astra.dealink.rest.api.util;

import com.astra.dealink.rest.api.constants.DewaRestApiKeys;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static Date expiredDateToken(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, 1);

        Date newDate = calendar.getTime();
        return newDate;
    }

    public static Boolean dateformatDot (String date){
        SimpleDateFormat sdf = new SimpleDateFormat(DewaRestApiKeys.DATE_FORMAT_DOT);
        sdf.setLenient(false);

        try {
            Date parsedDate = sdf.parse(date);
            if (parsedDate != null) {
                return true;
            }
        } catch (ParseException e) {
            return false;
        }

        return false;
    }
}
