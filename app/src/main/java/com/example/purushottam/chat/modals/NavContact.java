package com.example.purushottam.chat.modals;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by purushottam on 27/4/17.
 */

public class NavContact {

    public long userId;
    public String name = "Raj";
    public String about = "No details";

    public double lat = 72.234d;
    public double lng = 24.005d;

    public boolean isOnline = true;


    public NavContact() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("utc"));
        userId = calendar.getTimeInMillis();
    }

    public NavContact(String name, String about, double lat, double lng) {
        this();
        this.name = name;
        this.about = about;
        this.lat = lat;
        this.lng = lng;
    }
}
