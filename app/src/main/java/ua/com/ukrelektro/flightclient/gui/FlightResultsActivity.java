package ua.com.ukrelektro.flightclient.gui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.squareup.otto.Subscribe;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ua.com.ukrelektro.flightclient.R;
import ua.com.ukrelektro.flightclient.client.FlightRSClient;
import ua.com.ukrelektro.flightclient.client.actions.SearchFlightsAction;
import ua.com.ukrelektro.flightclient.gui.adapters.FlightAdapter;
import ua.com.ukrelektro.flightclient.models.Flight;

public class FlightResultsActivity extends AppCompatActivity {
    @Bind(R.id.flightRecyclerView)
    RecyclerView flightRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Subscribe
    public void onSearchFlightsAction(SearchFlightsAction action) {
        if (action.isSuccess()) {
            List<Flight> flights = action.getFlightList().getList();
            FlightAdapter flightAdapter = new FlightAdapter(flights);
            flightRecyclerView.setAdapter(flightAdapter);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        FlightRSClient.getInstance().getSantaRest().subscribe(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        FlightRSClient.getInstance().getSantaRest().unsubscribe(this);
    }

}
