package edu.nmsu.cs.lcpdandu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.google.firebase.messaging.FirebaseMessaging;

public class HomePage extends AppCompatActivity {

    private TextView mTextMessage;
    private static ArrayList<EventObjects> EventObjectList = new ArrayList<>();
    private RequestQueue requestQueue;// This is our requests queue to process our HTTP requests.
    private String url = "http://tm4sp18.cs.nmsu.edu/public/api/events";   // This is the API base URL (GitHub API)

    private ViewPager viewPager;//Slider View
    private ViewPagerAdapter v;//Will place the xml layout in activity_main_slider within the xml of activity_home_page

    //Bottom Navigation Bar contains five tabs that will lead to its intended pages: HomePage,
    //CompactCalendar, ContactUs, Notifications, and AskTheCity.
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent homeIntent = new Intent(HomePage.this, HomePage.class);
                    startActivity(homeIntent);

                    return true;
                case R.id.navigation_events:
                    Intent eventsIntent = new Intent(HomePage.this, CompactCalendar.class);
                    startActivity(eventsIntent);
                    return true;
                case R.id.navigation_contact:
                    Intent contactIntent = new Intent(HomePage.this, ContactUs.class);
                    startActivity(contactIntent);
                    return true;
                case R.id.navigation_notifications:
                    Intent notificationsIntent = new Intent(HomePage.this, Notifications.class);
                    startActivity(notificationsIntent);
                    return true;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //FirebaseMessaging allows the app to send new notifications to users
        //phones without being within the app
        FirebaseMessaging.getInstance().subscribeToTopic("news");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        //Check if the EventObjectList has already been filled. If not then call requestQueue and getEventList
        if(EventObjectList.size()  == 0) {
            requestQueue = Volley.newRequestQueue(this);  // This setups up a new request queue which we will need to make HTTP requests.
            getEventList();
        }

        mTextMessage = (TextView) findViewById(R.id.message);

        //Takes the activity_main_slider xml and stores this inside of
        //activity_home_page's viewpager.
        v = new ViewPagerAdapter(this);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(v);

        //Set time interval for when next slide should appear on screen.
        //Initial delay is 4 seconds on first slide and 4 seconds onward
        //to following slides.
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SlideTimer(), 4000, 4000);

        SetBottomNavigation();
    }

    private void SetBottomNavigation(){
        //Set BottomNavigation View
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.home_page_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
    }

    private void getEventList() {

        // Creates a new JsonArrayRequest.
        // Uses Volley to make a HTTP request that expects a JSON Array Response.
        JsonArrayRequest arrReq = new JsonArrayRequest(Request.Method.GET, url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Check the length of our response (to see if the user has any events)
                        if (response.length() > 0) {
                            // The user does have events, so let's loop through them all.
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    // For each event, add a new line to our event list.
                                    JSONObject jsonObj = response.getJSONObject(i);
                                    String eventID = jsonObj.get("ID").toString();
                                    String eventTitle = jsonObj.get("Title").toString();
                                    String eventCategory = jsonObj.get("Category").toString();
                                    String eventDate = jsonObj.get("EventDate").toString();
                                    String eventStartTime = jsonObj.get("EventStartTime").toString();
                                    String eventStartTimeAMPM = jsonObj.get("EventStartTimeAMPM").toString();
                                    String eventLocation = jsonObj.get("Location").toString();
                                    String eventDescription = jsonObj.get("Description").toString();
                                    String eventMedia1 = jsonObj.get("Media1").toString();
                                    String eventMedia2 = jsonObj.get("Media2").toString();
                                    String eventMedia3 = jsonObj.get("Media3").toString();

                                    Log.i("Media1: ", eventMedia1 + "\n");
                                    Log.i("Media2: ", eventMedia2 + "\n");
                                    Log.i("Media3: ", eventMedia3 + "\n");
                                    EventObjects e = new EventObjects();
                                    e.ID = eventID;
                                    e.Title = eventTitle;
                                    e.Category = eventCategory;
                                    e.Date = eventDate;
                                    e.Time = eventStartTime;
                                    e.AMPM = eventStartTimeAMPM;
                                    e.Location = eventLocation;
                                    e.Description = eventDescription;
                                    e.Media1 = eventMedia1;
                                    e.Media2 = eventMedia2;
                                    e.Media3 = eventMedia3;
                                    EventObjectList.add(e);

                                } catch (JSONException e) {
                                    // If there is an error then output this to the logs.
                                    System.out.println("JSON ERROR");
                                    Log.e("Volley", "Invalid JSON Object.");
                                }

                            }
                        }

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // If there a HTTP error then log the error
                        Log.e("Volley", error.toString());
                    }
                }
        );
        // Add the request we just defined to our request queue.
        // The request queue will automatically handle the request as soon as it can.
        requestQueue.add(arrReq);
    }

    public static ArrayList<EventObjects> getEventObjectList() {
        return EventObjectList;
    }

    //Sets timer on viewPager and slider. If viewPager is on
    //first slide then move to second slide and then to the third.
    //When method is called set the time for period and delay.
    public class SlideTimer extends TimerTask {

        @Override
        public void run(){
            HomePage.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(viewPager.getCurrentItem()==0){
                        viewPager.setCurrentItem(1);
                    }else if(viewPager.getCurrentItem()==1){
                        viewPager.setCurrentItem(2);
                    }else{
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }
}

