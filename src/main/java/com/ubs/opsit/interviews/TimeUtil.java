package com.ubs.opsit.interviews;

public class TimeUtil {
    public enum TimeUnit{HOURS,MINUTES,SECONDS};
    private static TimeUtil instance = new TimeUtil();

    public static TimeUtil getInstance(){
        return instance;
    }

    private int getTime(String timeString, TimeUnit timeUnit){
        String[] _time = timeString.split(":");
        String value = null;
        switch(timeUnit){
            case HOURS:value=_time[0];break;
            case MINUTES:value=_time[1];break;
            case SECONDS:value=_time[2];
        }
        return Integer.parseInt(value);
    }

    public int getHours(String timeString){
        return getTime(timeString, TimeUnit.HOURS);
    }

    public int getMinutes(String timeString){
        return getTime(timeString, TimeUnit.MINUTES);
    }

    public int getSeconds(String timeString){
        return getTime(timeString, TimeUnit.SECONDS);
    }
}
