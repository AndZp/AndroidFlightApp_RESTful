package ua.com.ukrelektro.flightclient.client;

import com.santarest.RequestBuilder;
import com.santarest.SantaRest;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ua.com.ukrelektro.flightclient.client.actions.CheckReservationByCodeAction;
import ua.com.ukrelektro.flightclient.client.actions.GetAllCitiesAction;
import ua.com.ukrelektro.flightclient.client.actions.SearchFlightsAction;
import ua.com.ukrelektro.flightclient.models.City;

/**
 * Created by User on 30-Jan-16.
 */
public class FlightRSClient {
    private SantaRest santaRest;
    private final String URL = "http://10.0.3.2:8080/FlightRESTful/rest/flight";

    private static FlightRSClient flightRSClient;

    private FlightRSClient(){
    santaRest = initSantaRest();
    }

    public static FlightRSClient getInstance() {
        if (flightRSClient == null) {
            flightRSClient = new FlightRSClient();
        }

        return flightRSClient;
    }

    public void getAllCities() {
        santaRest.sendAction(new GetAllCitiesAction());
    }

    public void checkReservationByCode(String code){
        santaRest.sendAction(new CheckReservationByCodeAction(code));
    }



    public void searchFlight(Date date, City fromCity, City toCity) {

              santaRest.sendAction(new SearchFlightsAction(date.getTime(), fromCity, toCity));
    }

    private SantaRest initSantaRest() {

        return new SantaRest.Builder()
                .setServerUrl(URL)
                .addRequestInterceptor(new SantaRest.RequestInterceptor() {
                    @Override
                    public void intercept(RequestBuilder request) {
                        System.out.println(request);
                    }
                })
                .addResponseInterceptor(new SantaRest.ResponseListener() {
                    @Override
                    public void onResponseReceived(Object action, com.santarest.http.Request request, com.santarest.http.Response response) {
                        System.out.println(request);
                        System.out.println(response);
                    }
                })
                .build();
    }

    public SantaRest getSantaRest() {
        return santaRest;
    }

    public void setSantaRest(SantaRest santaRest) {
        this.santaRest = santaRest;
    }
}
