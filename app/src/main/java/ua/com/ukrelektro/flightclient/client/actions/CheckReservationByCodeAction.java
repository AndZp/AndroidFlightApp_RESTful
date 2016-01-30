package ua.com.ukrelektro.flightclient.client.actions;

import com.santarest.annotations.Path;
import com.santarest.annotations.Response;
import com.santarest.annotations.RestAction;
import com.santarest.annotations.Status;

import ua.com.ukrelektro.flightclient.models.ReservationResult;

/**
 * Created by User on 30-Jan-16.
 */
@RestAction("/checkReservation/{code}")
public class CheckReservationByCodeAction {

    @Path("code")
    String code;

    @Status
    boolean success;

    @Response
    public ReservationResult reservationResult;


    public CheckReservationByCodeAction(String code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ReservationResult getReservationResult() {
        return reservationResult;
    }

    public void setReservationResult(ReservationResult reservationResult) {
        this.reservationResult = reservationResult;
    }
}
