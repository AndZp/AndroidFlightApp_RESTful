package ua.com.ukrelektro.flightclient.client.actions;

import com.santarest.annotations.Response;
import com.santarest.annotations.RestAction;
import com.santarest.annotations.Status;

import ua.com.ukrelektro.flightclient.models.CityList;


/**
 * Created by User on 28-Jan-16.
 */
@RestAction("/allcities")
public class GetAllCitiesAction {
    @Status
    boolean success;

    public boolean isSuccess() {
        return success;
    }

    @Response
    public CityList cityList;

    public CityList getCityList() {
        return cityList;
    }

}