package com.kristich.shiney;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Kristich on 5/25/16.
 */
public class CurrentWeather {

    private String icon;

    private long time;

    private double temprature;

    private double humidity;

    private double precipChance;

    private String summary;

    private String timeZone;




    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getTemprature() {
        return temprature;
    }

    public void setTemprature(double temprature) {
        this.temprature = temprature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getPrecipChance() {
        return precipChance;
    }

    public void setPrecipChance(double precipChance) {
        this.precipChance = precipChance;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }


    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getFormattedTime () {

        SimpleDateFormat formatter = new SimpleDateFormat("h:mm a");
        formatter.setTimeZone(TimeZone.getTimeZone(getTimeZone()));

        Date dateTime = new Date(getTime() * 1000);

        String timeString = formatter.format(dateTime);

        return timeString;

    }


    public int getIconId () {

        int iconId = R.drawable.clear_day;

        if (icon.equals("clear-day")) {

            iconId = R.drawable.clear_day;

        } else if (icon.equals("clear-night")) {

            iconId = R.drawable.clear_night;

        } else  if (icon.equals("rain")) {

            iconId = R.drawable.rain;

        } else if (icon.equals("snow")) {

            iconId = R.drawable.snow;

        } else if (icon.equals("sleet")) {

            iconId = R.drawable.sleet;

        } else if (icon.equals("wind")) {

            iconId = R.drawable.fog;

        } else if (icon.equals("cloudy")) {

            iconId = R.drawable.cloudy;

        } else if (icon.equals("partly-cloudy-day")) {

            iconId = R.drawable.partly_cloudy;

        } else if (icon.equals("partly-cloudy-night")) {

            iconId = R.drawable.partly_cloudy;

        }

        return iconId;
    }



}
