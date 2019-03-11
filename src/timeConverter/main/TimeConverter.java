package timeConverter.main;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.*;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import timeConverter.ui.TimeConverterGUI;

/**
 * @author dalichandramathi_s
 *
 */

public class TimeConverter
{

    ClassLoader cl;

    public TimeConverter()
    {
        cl = getClass().getClassLoader();
    }

    public static void main(String args[])
    {
        (new TimeConverter()).getCurrentTimeForTimezone("Asia/Kolkata");
        System.out.println((new Date()).getTime());
    }

    public void testCalendar()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add(12, -120);
        calendar.set(13, 0);
        System.out.println(calendar.getTimeInMillis());
    }

    public void getCurrentTimeForTimezone(String timezone)
    {
        System.out.println((new StringBuilder("inside getCurrentTimeForTimezone ")).append(timezone).toString());
        long currentTimeInMilliseconds = (new Date()).getTime();
        DateTime jodaTime = new DateTime(currentTimeInMilliseconds, DateTimeZone.forID("America/New_York"));
        DateTimeFormatter parser1 = DateTimeFormat.forPattern("dd-MMM-yyyy HH:mm:ss");
        System.out.println(parser1.print(jodaTime));
    }

    public void milliToDate()
    {
        try
        {
            long yourmilliseconds = Long.parseLong("1419832107558");
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
            DateTimeFormatter parser1 = DateTimeFormat.forPattern("dd-MMM-yyyy HH:mm:ss");
            DateTime jodaTime = new DateTime(yourmilliseconds, DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT")));
            System.out.println((new StringBuilder("1 : ")).append(parser1.print(jodaTime)).toString());
            System.out.println((new StringBuilder("2 : ")).append(String.valueOf(dateFormatter.parse(parser1.print(jodaTime)))).toString());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void dateToMilli()
    {
        String inputRaw = "2012-06-14 05:01:25";
        String input = inputRaw.replace(" ", "T");
        DateTime dateTime = new DateTime(input, DateTimeZone.UTC);
        DateTime dateTimeTopOfTheDay = dateTime.withTimeAtStartOfDay();
        long millisecondsSinceUnixEpoch = dateTimeTopOfTheDay.getMillis();
        System.out.println(millisecondsSinceUnixEpoch);
    }

    public String convertFromOneTimezoneToAnother(String fromDateTimeString, String fromTimeZoneString, String toTimeZoneString)
        throws Exception
    {
        DateTimeZone fromTimeZone;
        DateTimeZone toTimeZone;
        IllegalArgumentException e;
        fromTimeZone = null;
        toTimeZone = null;
        try
        {
            System.out.println((new StringBuilder("fromTimeZoneString = ")).append(fromTimeZoneString).toString());
            System.out.println((new StringBuilder("toTimeZoneString = ")).append(toTimeZoneString).toString());
            fromTimeZone = DateTimeZone.forID(fromTimeZoneString);
            toTimeZone = DateTimeZone.forID(toTimeZoneString);
            System.out.println((new StringBuilder("fromTimeZone = ")).append(fromTimeZone).toString());
            System.out.println((new StringBuilder("toTimeZone = ")).append(toTimeZone).toString());
        }
        // Misplaced declaration of an exception variable
        catch(IllegalArgumentException exe)
        {
            TimeConverterGUI.errorMessage = "Invalid TimeZone, make sure the timezone is in CamelCase";
            throw exe;
        }
        try{        
        	String toDateTimeString;
        	DateTimeFormatter inputFormatter = DateTimeFormat.forPattern("dd-MMM-yyyy HH:mm:ss").withZone(fromTimeZone);
        	DateTime fromDateTime = inputFormatter.parseDateTime(fromDateTimeString);
        	DateTimeFormatter outputFormatter = DateTimeFormat.forPattern("dd-MMM-yyyy HH:mm:ss").withZone(toTimeZone);
        	toDateTimeString = outputFormatter.print(fromDateTime);
        	return toDateTimeString;
        }
        
        catch(IllegalArgumentException exe){
        	System.out.println("inside the catch");
        	TimeConverterGUI.errorMessage = "Invalid Date Format, please enter the date in\"dd-mmm-yyyy HH:mm:ss\" format";
        	exe.printStackTrace();
        	throw exe;
        }
    }
}
