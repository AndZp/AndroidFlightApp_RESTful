package ua.com.ukrelektro.flightclient.models;

import java.util.List;

/**
 * Created by User on 30-Jan-16.
 */
public class FlightList {

    public FlightList() {
    }

    private List<Flight> list;

    public List<Flight> getList() {
        return list;
    }

    public void setList(List<Flight> list) {
        this.list = list;
    }

}