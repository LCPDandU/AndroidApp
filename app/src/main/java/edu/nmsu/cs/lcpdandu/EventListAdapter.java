package edu.nmsu.cs.lcpdandu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;

public class EventListAdapter extends ArrayAdapter<EventObjects> {

    public EventListAdapter(Context context, ArrayList<EventObjects> e) {
        super(context, 0, e);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        EventObjects e = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_day_list, parent, false);
        }

        // Lookup view for data population
        TextView TitleTextView = (TextView) convertView.findViewById(R.id.events_list_title);
        TextView LocationTextView = (TextView) convertView.findViewById(R.id.events_list_location);
        TextView DateTextView = (TextView) convertView.findViewById(R.id.events_list_date);
        TextView TimeTextView = (TextView) convertView.findViewById(R.id.events_list_time);
        TextView AMPMTextView = (TextView) convertView.findViewById(R.id.events_list_ampm);

        // Return the completed view to render on screen
        TitleTextView.setText(e.Title);
        DateTextView.setText(e.Date);
        TimeTextView.setText(e.Time);
        AMPMTextView.setText(e.AMPM);
        LocationTextView.setText(e.Location);

        return convertView;
    }


}