package Utilities;

import java.util.Date;

public class Utils {

    public static String generateEmailWithDateTimeStamp(){
        Date date = new Date();
        String timestamp = date.toString().replace(" ", "_").replace(":","_");
        return "seleniumpanda"+timestamp+"@rediffmail.com";
    }

    public static final int IMPLICIT_WAIT_TIME = 10;
    public static final int PAGELOAD_TIME = 100;
    public static final int SCRIPT_TIME = 1000;

}
