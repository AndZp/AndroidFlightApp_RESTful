package ua.com.ukrelektro.flightclient.gui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;
import ua.com.ukrelektro.flightclient.R;
import ua.com.ukrelektro.flightclient.client.FlightRSClient;
import ua.com.ukrelektro.flightclient.client.actions.CheckReservationByCodeAction;
import ua.com.ukrelektro.flightclient.models.Reservation;

/**
 * Created by User on 30-Jan-16.
 */
public class CheckDialog extends DialogFragment {
@Bind(R.id.etCheckCode) EditText etCheckCode;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.input_check_code_dialog, null);
        ButterKnife.bind(this, view);

        builder.setView(view);


        builder.setTitle("Check reservation code")
                .setPositiveButton("Check", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String code = etCheckCode.getText().toString().trim();
                        FlightRSClient.getInstance().checkReservationByCode(code);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }



    @Override
    public void onResume() {
        super.onResume();
        FlightRSClient.getInstance().getSantaRest().subscribe(this);
            }

    @Override
    public void onPause() {
        super.onPause();
        FlightRSClient.getInstance().getSantaRest().unsubscribe(this);
    }
}