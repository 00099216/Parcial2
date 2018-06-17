package com.andres00099216.parcial2;

import android.arch.persistence.room.TypeConverter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Andres on 16/6/2018.
 */

public class Ordenar {
    @TypeConverter
    public static Integer Orden(String rawDate) {
        int finalDate;
        if (rawDate != null) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
            long longDate;
            try {
                longDate = dateFormat.parse(rawDate).getTime();
                finalDate = (int) longDate;
                return finalDate;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}
