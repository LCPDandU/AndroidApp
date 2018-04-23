package edu.nmsu.cs.lcpdandu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import java.util.ArrayList;

public class DayList extends AppCompatActivity {


    private ArrayList<EventObjects> NList = new ArrayList<>(); // Place to store all notification objects
    private ListView NListView; // Provides a list view to store objects inside.
    private EventListAdapter adapter; // Adapter to store textviews inside of listview with NotificationsObjects.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  // This is some magic for Android to load a previously saved state for when you are switching between actvities.
        setContentView(R.layout.activity_day);  // This links our code to our layout which we defined earlier.

        NListView = (ListView) findViewById(R.id.events_list_view);
        adapter = new EventListAdapter(this, CompactCalendar.getEventToSend());
        NListView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        CompactCalendar.getEventToSend().clear();
    }


    //TODO: Switch the intent from the contactUs page to the eventDetails
    public void clickEventDetails(View v) {
        Intent eventsIntent = new Intent(DayList.this, ContactUs.class);
        startActivity(eventsIntent);
    }
}