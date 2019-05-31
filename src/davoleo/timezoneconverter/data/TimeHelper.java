package davoleo.timezoneconverter.data;

import org.joda.time.LocalTime;
import org.joda.time.Period;

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

    public static Period delta(LocalTime time1, LocalTime time2)
    {
        int h1 = time1.getHourOfDay();
        int h2 = time2.getHourOfDay();

        int delta;
        if (h1 > h2)
            delta = h1 - h2;
        else
            delta = h2 - h1;

        return Period.hours(delta);

    }

}
