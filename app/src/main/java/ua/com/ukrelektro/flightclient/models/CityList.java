package ua.com.ukrelektro.flightclient.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityList {

    public CityList() {
    }

    @SerializedName("cityList")
    private List<City> list;


    public List<City> getList() {
        return list;
    }

    public void setList(List<City> list) {
        this.list = list;
    }


}