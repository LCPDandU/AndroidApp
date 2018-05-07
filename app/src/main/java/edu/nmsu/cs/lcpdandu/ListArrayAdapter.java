package edu.nmsu.cs.lcpdandu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class ListArrayAdapter extends ArrayAdapter<NotificationObjects> {
    private TextView TitleTextView;
    private TextView DescriptionTextView;
    private TextView DateTextView;
    private TextView TimeTextView;
    private TextView AMPMTextView;

    public ListArrayAdapter(Context context, ArrayList<NotificationObjects> n) {
        super(context, 0, n);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        NotificationObjects n = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_notifications_list, parent, false);
        }

        // Lookup view for data population
        TitleTextView = (TextView) convertView.findViewById(R.id.notifications_list_title);
        DescriptionTextView = (TextView) convertView.findViewById(R.id.notifications_list_description);
        DateTextView = (TextView) convertView.findViewById(R.id.notifications_list_date);
        TimeTextView = (TextView) convertView.findViewById(R.id.notifications_list_time);
        AMPMTextView = (TextView) convertView.findViewById(R.id.notifications_list_ampm);

        // Return the completed view to render on screen
        TitleTextView.setText(n.Title);
        DescriptionTextView.setText(n.Description);
        DateTextView.setText(n.Date);
        TimeTextView.setText(n.Time);
        AMPMTextView.setText(n.AMPM);
        return convertView;
    }
}