package edu.nmsu.cs.lcpdandu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


public class DayList extends AppCompatActivity {


    private ListView EListView; // Provides a list view to store objects inside.
    private EventListAdapter adapter; // Adapter to store textviews inside of listview with EventObjects.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  // This is some magic for Android to load a previously saved state for when you are switching between actvities.
        setContentView(R.layout.activity_day);  // This links our code to our layout which we defined earlier.

        EListView = (ListView) findViewById(R.id.events_list_view);
        adapter = new EventListAdapter(this, CompactCalendar.getDayListEvents());
        EListView.setAdapter(adapter);
    }


    //TODO: Switch the intent from the contactUs page to the eventDetails
    public void clickEventDetails(View v) {
        Intent eventsIntent = new Intent(DayList.this, ContactUs.class);
        startActivity(eventsIntent);
    }
}