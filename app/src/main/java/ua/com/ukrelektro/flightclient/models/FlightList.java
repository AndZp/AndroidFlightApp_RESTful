package ua.com.ukrelektro.flightclient.models;

import java.util.List;

/**
 * Created by User on 30-Jan-16.
 */
public class FlightList {

    public FlightList() {
    }

    private List<Flight> flightList;

    public List<Flight> getFlightList() {
        return flightList;
    }

    public void setFlightList(List<Flight> flightList) {
        this.flightList = flightList;
    }

}