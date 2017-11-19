package com.example.tylerivory.aggiebluebikes;


import android.widget.DatePicker;

/**
 * Created by iNaS2 on 11/17/2017.
 */

public class TimeConstants {

    public static final long ONE_SECOND = 1000L;
    public static final long ONE_MINUTE =  60 * 1000L;
    public static final long ONE_HOUR = 60 * 60 * 1000L;
    public static final long ONE_DAY = 24 * 60 * 60 * 1000L;
    public static final long THIRY_DAYS = 30 *24*60*60*1000L;



public static String[] formatIntTime(long theTime){
    String[] formatted = new String[4];
    long days,hours,minutes,seconds;


    days = (theTime)/ONE_DAY;
    theTime = (theTime)%ONE_DAY;
    hours = (theTime)/ONE_HOUR;
    theTime = (theTime)%ONE_HOUR;
    minutes = theTime/ONE_MINUTE;
    theTime = theTime%ONE_MINUTE;
    seconds = theTime/ONE_SECOND;

    //consider using the StringBuilder for better performance

    formatted[0] = Long.toString(days);
    formatted[1] = Long.toString(hours);
    formatted[2] = Long.toString(minutes);
    formatted[3] = Long.toString(seconds);

    return formatted;
}


}
