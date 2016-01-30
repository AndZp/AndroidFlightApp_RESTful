package ua.com.ukrelektro.flightclient.gui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ua.com.ukrelektro.flightclient.models.City;

/**
 * Created by User on 30-Jan-16.
 */
public class SpinnerCitiesAdapter extends ArrayAdapter {

    private Context context;

    private ArrayList<City> cities;

    public SpinnerCitiesAdapter(Context context, int textViewResourceId,
                                ArrayList<City> cities) {
        super(context, textViewResourceId, cities);
        this.context = context;
        this.cities = cities;
    }

    public int getCount(){
        return cities.size();
    }

    public City getItem(int position){
        return cities.get(position);
    }

    public long getItemId(int position){
        return position;
    }


    // And the "magic" goes here
    // This is for the "passive" state of the spinner
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        // Then you can get the current item using the values array (Users array) and the current position
        // You can NOW reference each method you has created in your bean object (User class)
        label.setText(cities.get(position).getName());

        // And finally return your dynamic (or custom) view for each spinner item
        return label;
    }

    // And here is when the "chooser" is popped up
    // Normally is the same view, but you can customize it if you want
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setText(cities.get(position).getName());

        return label;
    }




}