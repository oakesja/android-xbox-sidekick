package com.example.joakes.xbox_sidekick;

import android.test.AndroidTestCase;

import com.example.joakes.xbox_sidekick.utils.CurrentTime;
import com.example.joakes.xbox_sidekick.utils.DateConverter;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by joakes on 6/20/15.
 */
public class DateConverterTest extends AndroidTestCase {
    private long now;
    private DateConverter dateconverter;
    private GregorianCalendar calendar;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        CurrentTime currentTime = mock(CurrentTime.class);
        now = System.currentTimeMillis();
        when(currentTime.nowMilliseconds()).thenReturn(now);
        dateconverter = new DateConverter(currentTime);
        calendar = new GregorianCalendar();
        calendar.setTimeInMillis(now);
    }

    public void testNow(){
        String expected = "0 seconds ago";
        assertEquals(expected, dateconverter.toTimeAgo(calendar));
    }

    public void testSecondsAgo(){
        assertTimeRangeAgo(1, 59, 1000, "second");
    }

    public void testMinutesAgo(){
        assertTimeRangeAgo(1, 59, 60000, "minute");
    }

    public void testHoursAgo(){
        assertTimeRangeAgo(1, 23, 3600000, "hour");
    }

    public void testUpTo7DaysAgo(){
        assertTimeRangeAgo(1, 7, 86400000, "day");
    }

    public void testOver7DaysAgo(){
        calendar.setTimeInMillis(now - 86400000 * 8);
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        String expected = String.format("on %s", formatter.format(calendar.getTime()));
        assertEquals(expected, dateconverter.toTimeAgo(calendar));
    }

    private void assertTimeRangeAgo(int start, int end, long timeDiff, String unit){
        for (int i = start; i < end; i++) {
            String expected = getExpectedOutput(unit, i);
            assertOutputEqualExpected(timeDiff, i, expected);
        }
    }

    private String getExpectedOutput(String unit, int value) {
        if(value > 1){
            return String.format("%d %ss ago", value, unit);
        } else {
            return String.format("%d %s ago", value, unit);
        }
    }

    private void assertOutputEqualExpected(long timeDiff, int value, String expected) {
        calendar.setTimeInMillis(now - timeDiff * value);
        assertEquals(expected, dateconverter.toTimeAgo(calendar));
    }
}
