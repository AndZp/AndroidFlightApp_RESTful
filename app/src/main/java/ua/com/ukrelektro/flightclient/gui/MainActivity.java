package ua.com.ukrelektro.flightclient.gui;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ua.com.ukrelektro.flightclient.R;
import ua.com.ukrelektro.flightclient.client.FlightRSClient;
import ua.com.ukrelektro.flightclient.client.actions.CheckReservationByCodeAction;
import ua.com.ukrelektro.flightclient.client.actions.GetAllCitiesAction;
import ua.com.ukrelektro.flightclient.gui.adapters.SpinnerCitiesAdapter;
import ua.com.ukrelektro.flightclient.models.City;
import ua.com.ukrelektro.flightclient.models.Reservation;

public class MainActivity extends AppCompatActivity {

    private FlightRSClient flightRSClient;


    @Bind(R.id.spinnerCityFrom)
    Spinner spinnerFrom;
    @Bind(R.id.spinnerCityTo)
    Spinner spinnerTo;
    @Bind(R.id.etSetDate)
    EditText etSetDate;
    @Bind(R.id.btnCheck)
    Button btnCheck;

    private DatePickerDialog datePickerDialog;

    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                flightRSClient.checkReservationByCode("111");
                Snackbar.make(view, "Check reservation by code", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });


        FlightRSClient.getInstance().getAllCities();

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        etSetDate.setText(dateFormatter.format(Calendar.getInstance().getTime()));

        setDateTimeField();


    }

    @OnClick(R.id.btnSerach)
    void searchFlights() {



        Date date = null;
        City fromCity = null;
        City toCity = null;
        try {
            date = dateFormatter.parse(etSetDate.getText().toString());
            fromCity = (City) spinnerFrom.getSelectedItem();
            toCity = (City) spinnerTo.getSelectedItem();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null && fromCity != null && toCity != null) {

            Intent intent = new Intent(this, FlightResultsActivity.class);
            intent.putExtra("cityFrom", fromCity.getName());
            intent.putExtra("cityTo", toCity.getName());
            intent.putExtra("date", dateFormatter.format(date));
            startActivity(intent);
            FlightRSClient.getInstance().searchFlight(date, fromCity, toCity);
        }

    }

    @OnClick(R.id.btnCheck)
    void checkReservationByCode() {
        FragmentManager manager = getSupportFragmentManager();
        CheckDialog checkDialog = new CheckDialog();
        checkDialog.show(manager, "Check dialog");
    }


    @OnClick(R.id.etSetDate)
    void submit() {
        datePickerDialog.show();
    }


    private void setDateTimeField() {
        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                etSetDate.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }


    @Subscribe
    public void onGetAllCitiesAction(GetAllCitiesAction action) {
        if (action.isSuccess()) {
            ArrayList<City> cities = new ArrayList<>();
            cities.addAll(action.getCityList().getList());

            Toast.makeText(this, "" + cities.size(), Toast.LENGTH_LONG).show();
            initSpinnersCity(cities);
        }
    }


    private SpinnerCitiesAdapter adapter;

    private void initSpinnersCity(ArrayList<City> cities) {
        adapter = new SpinnerCitiesAdapter(this, R.layout.spinner_item, cities);
        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);


        AdapterView.OnItemSelectedListener selectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                City city = adapter.getItem(position);
                Toast.makeText(MainActivity.this, "Name: " + city.getName(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };

        spinnerFrom.setOnItemSelectedListener(selectedListener);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Subscribe
    public void onCheckReservationByCode(CheckReservationByCodeAction action) {
        if (action.isSuccess()) {
            Reservation reservation = action.getReservationResult().getReservation();

            if (reservation != null) {
                String passenger = "Passenger: " + reservation.getPassenger().getGivenName() + " " + reservation.getPassenger().getFamilyName();
                String cityFrom = "From: " + reservation.getFlight().getCityFrom().getName();
                String cityTo = "To: " + reservation.getFlight().getCityTo().getName();
                String aircraft = "Aircraft: " + reservation.getFlight().getAircraft().getName();
                String date = "Date: " + dateFormatter.format(new Date(reservation.getFlight().getDateDepart()));

                String messageString = passenger + "\n" + cityFrom + "\n" + cityTo + "\n" + aircraft + "\n" + date;
                showAlertDialog("Flight code: " + reservation.getCode(), messageString);
            } else {
                showAlertDialog("Results", "No reservations by this number");
            }

        } else {
            showAlertDialog("Error", "No connections");
        }
    }

    private void showAlertDialog(String title, String messageString) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(messageString)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        builder.create().show();
    }
}
