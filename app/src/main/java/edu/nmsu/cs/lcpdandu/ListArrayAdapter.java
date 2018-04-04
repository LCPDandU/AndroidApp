package edu.nmsu.cs.lcpdandu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tonib on 3/25/2018.
 */

public class ListArrayAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<NotificationObjects> mDataSource;

    public ListArrayAdapter(Context context, ArrayList<NotificationObjects> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    //2
    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    //3
    @Override
    public long getItemId(int position) {
        return position;
    }

    //4
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get view for row item
        View rowView = mInflater.inflate(R.layout.activity_notifications_list, parent, false);
        TextView IDTextView = (TextView) rowView.findViewById(R.id.notifications_list_id);
        TextView TitleTextView = (TextView) rowView.findViewById(R.id.notifications_list_title);
        TextView DescriptionTextView = (TextView) rowView.findViewById(R.id.notifications_list_description);
        TextView DateTextView = (TextView) rowView.findViewById(R.id.notifications_list_date);
        TextView TimeTextView = (TextView) rowView.findViewById(R.id.notifications_list_time);
        TextView AMPMTextView = (TextView) rowView.findViewById(R.id.notifications_list_ampm);


        NotificationObjects n = (NotificationObjects) getItem(position);
        IDTextView.setText(n.ID);
        TitleTextView.setText(n.Title);
        DescriptionTextView.setText(n.Description);
        DateTextView.setText(n.Date);
        TimeTextView.setText(n.Time);
        AMPMTextView.setText(n.AMPM);
        return rowView;
    }

}
