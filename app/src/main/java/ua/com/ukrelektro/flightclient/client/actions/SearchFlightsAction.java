package ua.com.ukrelektro.flightclient.client.actions;

import com.santarest.annotations.Path;
import com.santarest.annotations.Response;
import com.santarest.annotations.RestAction;
import com.santarest.annotations.Status;

import ua.com.ukrelektro.flightclient.models.City;
import ua.com.ukrelektro.flightclient.models.FlightList;

/**
 * Created by User on 30-Jan-16.
 */
@RestAction(value = "/searchFlight/{date}/{cityFromId}/{cityToId}",
            method = RestAction.Method.POST,
            type = RestAction.Type.FORM_URL_ENCODED)
public class SearchFlightsAction {

    public SearchFlightsAction(long date, City fromCity, City toCity) {
        this.date = date;
        this.cityFromId = fromCity.getId();
        this.cityToId = toCity.getId();
    }

    @Path("date")
    Long date;

    @Path("cityFromId")
    Long cityFromId;

    @Path("cityToId")
    Long cityToId;

    @Status
    boolean success;

    @Response
    FlightList flightList;

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Long getCityFromId() {
        return cityFromId;
    }

    public void setCityFromId(Long cityFromId) {
        this.cityFromId = cityFromId;
    }

    public Long getCityToId() {
        return cityToId;
    }

    public void setCityToId(Long cityToId) {
        this.cityToId = cityToId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public FlightList getFlightList() {
        return flightList;
    }

    public void setFlightList(FlightList flightList) {
        this.flightList = flightList;
    }
}
