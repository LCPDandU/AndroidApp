package edu.nmsu.cs.lcpdandu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class EventListAdapter extends ArrayAdapter<EventObjects> {
    private Context context;

    public EventListAdapter(Context context, ArrayList<EventObjects> e) {
        super(context, 0, e);
    }

    // Hold views of the ListView to improve its scrolling performance
    public static class ViewHolder {
        public TextView TitleTextView;
        public TextView LocationTextView;
        public TextView DescriptionTextView;
        public TextView DateTextView;
        public TextView TimeTextView;
        public TextView AMPMTextView;
        public TextView Media1TextView;
        public TextView Media2TextView;
        public TextView Media3TextView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        EventObjects e = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_day_list, parent, false);

            ViewHolder viewHolder = new ViewHolder();

            // Lookup view for data population
            viewHolder.TitleTextView = (TextView) convertView.findViewById(R.id.events_list_title);
            viewHolder.LocationTextView = (TextView) convertView.findViewById(R.id.events_list_location);
            viewHolder.DescriptionTextView = (TextView) convertView.findViewById(R.id.events_list_description);
            viewHolder.DateTextView = (TextView) convertView.findViewById(R.id.events_list_date);
            viewHolder.TimeTextView = (TextView) convertView.findViewById(R.id.events_list_time);
            viewHolder.AMPMTextView = (TextView) convertView.findViewById(R.id.events_list_ampm);

            viewHolder.Media1TextView = (TextView) convertView.findViewById(R.id.events_list_media1);
            viewHolder.Media2TextView = (TextView) convertView.findViewById(R.id.events_list_media2);
            viewHolder.Media3TextView = (TextView) convertView.findViewById(R.id.events_list_media3);
            convertView.setTag(viewHolder);

        }
        // Return the completed view to render on screen
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.TitleTextView.setText(e.Title);
        holder.DateTextView.setText(e.Date + " ");
        holder.TimeTextView.setText(e.Time);
        holder.AMPMTextView.setText(e.AMPM);
        holder.LocationTextView.setText(e.Location);
        holder.DescriptionTextView.setText(e.Description);
        holder.Media1TextView.setText(e.Media1);
        holder.Media2TextView.setText(e.Media2);
        holder.Media3TextView.setText(e.Media3);

        return convertView;
    }
}