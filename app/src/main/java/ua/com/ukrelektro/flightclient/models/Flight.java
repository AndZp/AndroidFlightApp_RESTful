package ua.com.ukrelektro.flightclient.models;

import java.util.Calendar;

/**
 * Created by User on 30-Jan-16.
 */
public class Flight {

    public Flight(){

    }

    private String duration;// only in Java (this field is not present in the DB)

    private boolean existFreePlaces;

    private long id;
    private String code;
    private long dateDepart;
    private long dateCome;
    private Aircraft aircraft;
    private City cityFrom;
    private City cityTo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(long dateDepart) {
        this.dateDepart = dateDepart;
    }

    public long getDateCome() {
        return dateCome;
    }

    public void setDateCome(long dateCome) {
        this.dateCome = dateCome;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public City getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(City cityFrom) {
        this.cityFrom = cityFrom;
    }

    public City getCityTo() {
        return cityTo;
    }

    public void setCityTo(City cityTo) {
        this.cityTo = cityTo;
    }

    public boolean isExistFreePlaces() {
        return existFreePlaces;
    }

    public void setExistFreePlaces(boolean existFreePlaces) {
        this.existFreePlaces = existFreePlaces;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}