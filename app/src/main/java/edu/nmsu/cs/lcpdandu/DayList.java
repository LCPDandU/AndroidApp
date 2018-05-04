package edu.nmsu.cs.lcpdandu;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class DayList extends AppCompatActivity {


    private ListView EListView; // Provides a list view to store objects inside.
    private EventListAdapter adapter; // Adapter to store textviews inside of listview with EventObjects.
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  // This is some magic for Android to load a previously saved state for when you are switching between actvities.
        setContentView(R.layout.activity_day);  // This links our code to our layout which we defined earlier.

        context = this;

        EListView = (ListView)findViewById(R.id.events_list_view);
        EListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                String Title = ((TextView) view.findViewById(R.id.events_list_title)).getText().toString();
                String Location= ((TextView) view.findViewById(R.id.events_list_location)).getText().toString();
                String Date = ((TextView) view.findViewById(R.id.events_list_date)).getText().toString();
                String Time = ((TextView) view.findViewById(R.id.events_list_time)).getText().toString();
                String AMPM = ((TextView) view.findViewById(R.id.events_list_ampm)).getText().toString();
                String Description = ((TextView) view.findViewById(R.id.events_list_description)).getText().toString();
                String Media1 = ((TextView) view.findViewById(R.id.events_list_media1)).getText().toString();
                String Media2 = ((TextView) view.findViewById(R.id.events_list_media2)).getText().toString();
                String Media3 = ((TextView) view.findViewById(R.id.events_list_media3)).getText().toString();

                Intent eventsIntent = new Intent();
                eventsIntent.putExtra("Title", Title);
                eventsIntent.putExtra("Location", Location);
                eventsIntent.putExtra("Date", Date);
                eventsIntent.putExtra("Time", Time);
                eventsIntent.putExtra("AMPM", AMPM);
                eventsIntent.putExtra("Description", Description);
                eventsIntent.putExtra("Media1", Media1);
                eventsIntent.putExtra("Media2", Media2);
                eventsIntent.putExtra("Media3", Media3);
                eventsIntent.setClass(DayList.this, EventDetails.class);
                startActivity(eventsIntent);
            }
        });

        adapter = new EventListAdapter(this, CompactCalendar.getDayListEvents());
        EListView.setAdapter(adapter);

        //SetBottomNavigation();

    }
/*
    //Bottom Navigation Bar contains five tabs that will lead to its intended pages: HomePage,
    //CompactCalendar, ContactUs, Notifications, and AskTheCity.
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent homeIntent = new Intent(DayList.this, HomePage.class);
                    startActivity(homeIntent);
                    return true;
                case R.id.navigation_events:
                    Intent eventsIntent = new Intent(DayList.this, CompactCalendar.class);
                    startActivity(eventsIntent);
                    return true;
                case R.id.navigation_contact:
                    Intent contactIntent = new Intent(DayList.this, ContactUs.class);
                    startActivity(contactIntent);
                    return true;
                case R.id.navigation_notifications:
                    Intent notificationsIntent = new Intent(DayList.this, Notifications.class);
                    startActivity(notificationsIntent);

                case R.id.navigation_navigation_ask_city:
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse("http://www.las-cruces.org/en/contact"));
                    startActivity(intent);
                    return true;
            }
            return false;
        }
    };

    private void SetBottomNavigation(){
        //Set BottomNavigation View
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.day_list_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
    }*/
}