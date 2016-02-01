package ua.com.ukrelektro.flightclient.gui.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import ua.com.ukrelektro.flightclient.R;
import ua.com.ukrelektro.flightclient.models.Flight;

/**
 * Created by User on 30-Jan-16.
 */
public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.ViewHolder> {

    private List<Flight> flights;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);


    public FlightAdapter(List<Flight> flights) {
        this.flights = flights;
    }

    /**
     * Создание новых View и ViewHolder элемента списка, которые впоследствии могут переиспользоваться.
     */
    @Override
    public FlightAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item, viewGroup, false);
        return new ViewHolder(v);
    }


    /**
     * Заполнение виджетов View данными из элемента списка с номером i
     */
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Flight flight = flights.get(i);
        viewHolder.tvCode.setText(flight.getCode());
        viewHolder.tvAircraft.setText(flight.getAircraft().getName());
        viewHolder.tvDuraction.setText(flight.getDuration());
        viewHolder.tvArrivel.setText(dateFormatter.format(new Date(flight.getDateCome())));
    }

    @Override
    public int getItemCount() {
        return flights.size();
    }

    /**
     * Реализация класса ViewHolder, хранящего ссылки на виджеты.
     */
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.tvCodeRI)
        TextView tvCode;
        @Bind(R.id.tvAircraftRI)
        TextView tvAircraft;
        @Bind(R.id.tvDurationRI)
        TextView tvDuraction;
        @Bind(R.id.tvArriverRI)
        TextView tvArrivel;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
        }
    }
}