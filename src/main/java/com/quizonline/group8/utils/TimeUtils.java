package com.quizonline.group8.utils;

import java.sql.Timestamp;
import java.util.Date;

public class TimeUtils {
    public static Timestamp getCurrentTime(){
        return new Timestamp((new Date()).getTime());
    }
}
