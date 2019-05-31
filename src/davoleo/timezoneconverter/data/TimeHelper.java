package davoleo.timezoneconverter.data;

import org.joda.time.LocalTime;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 19/05/2019 / 20:26
 * Class: TimeHelper
 * Project: TimeZoneConverter
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class TimeHelper {

    public static String timeToString(LocalTime time)
    {
        String hour = Integer.toString(time.getHourOfDay());
        String minute = Integer.toString(time.getMinuteOfHour());

        if (minute.length() < 2)
            minute = "0" + minute;

        if (hour.length() < 2)
            hour = "0" + hour;

        return hour + ":" + minute;
    }
//    TODO Delta Calculations
//    public static ZoneOffset delta(OffsetTime time1, OffsetTime time2)
//    {
//        int h1 = time1.getHour();
//        int h2 = time2.getHour();
//
//
//    }

}
