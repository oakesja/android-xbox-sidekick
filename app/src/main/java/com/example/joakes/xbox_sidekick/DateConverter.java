package com.example.joakes.xbox_sidekick;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * Created by joakes on 6/20/15.
 */
public class DateConverter {
    CurrentTime time;

    public DateConverter(){
        this.time = new CurrentTime();
    }

    public DateConverter(CurrentTime time) {
        this.time = time;
    }

    public String toTimeAgo(GregorianCalendar date) {
        long diff = getMillisecondDiffFromNow(date);
        if(isBetween(diff, 0, 59)){
            return getTimeAgoString(diff, "second");
        }
        diff /= 60;
        if(isBetween(diff, 1, 59)){
            return getTimeAgoString(diff, "minute");
        }
        diff /= 60;
        if(isBetween(diff, 1, 23)){
            return getTimeAgoString(diff, "hour");
        }
        diff /= 24;
        if(isBetween(diff, 1, 7)){
            return getTimeAgoString(diff, "day");
        }
        return getDateString(date);
    }

    private long getMillisecondDiffFromNow(GregorianCalendar date) {
        long now = time.nowMilliseconds();
        return (now - date.getTimeInMillis()) / 1000;
    }

    private boolean isBetween(long x, long lower, long upper) {
        return lower <= x && x <= upper;
    }

    private String getTimeAgoString(long diff, String unit){
        if(diff == 1){
            return String.format("%d %s ago", diff, unit);
        } else {
            return String.format("%d %ss ago", diff, unit);
        }
    }

    private String getDateString(GregorianCalendar date) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        return String.format("on %s", formatter.format(date.getTime()));
    }
}
