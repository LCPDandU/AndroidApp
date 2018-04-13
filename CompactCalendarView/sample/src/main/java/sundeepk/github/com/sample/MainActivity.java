package sundeepk.github.com.sample;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

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
import java.util.EventObject;


public class MainActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private ViewPager pager;
    private ViewPagerAdapter adapter;
    private SlidingTabLayout tabs;
    static ArrayList<EventObjects> Elist = new ArrayList<>();
    private CharSequence titles[]= {"Home","Events"};
    private int numberOfTabs = 2;
    RequestQueue requestQueue;
    String url = "http://tm4sp18.cs.nmsu.edu:8000/public/api/events";   // This is the API base URL (GitHub API)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter =  new ViewPagerAdapter(getSupportFragmentManager(), titles, numberOfTabs);
        requestQueue = Volley.newRequestQueue(this);  // This setups up a new request queue which we will need to make HTTP requests.
        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width
        System.out.println("Elist size " + Elist.size());
        System.out.println("Elist empty " + Elist.isEmpty());
        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.black);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        System.out.println("Elist size " + Elist.size());
        System.out.println("Elist empty " + Elist.isEmpty());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getEventList() {

        // Creates a new JsonArrayRequest.
        // Uses Volley to make a HTTP request that expects a JSON Array Response.
        JsonArrayRequest arrReq = new JsonArrayRequest(Request.Method.GET, url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Check the length of our response (to see if the user has any notifications)
                        if (response.length() > 0) {
                            // The user does have notifications, so let's loop through them all.
                            for (int i = 0; i < response.length(); i++) {
                                try {


                                    // For each notification, add a new line to our notification list.
                                    JSONObject jsonObj = response.getJSONObject(i);
                                    String eventID = jsonObj.get("ID").toString();
                                    //System.out.println("ID " + eventID);
                                    String eventTitle = jsonObj.get("Title").toString();
                                   // System.out.println("Title " + eventTitle);
                                    String eventCategory = jsonObj.get("Category").toString();
                                    String eventDate = jsonObj.get("EventDate").toString();
                                    String eventStartTime = jsonObj.get("EventStartTime").toString();
                                    String eventStartTimeAMPM = jsonObj.get("EventStartTimeAMPM").toString();
                                    String eventLocation = jsonObj.get("Location").toString();
                                    String eventDescription = jsonObj.get("Description").toString();

                                    EventObjects e = new EventObjects();
                                    e.ID = eventID;
                                    e.Title = eventTitle;
                                    e.Category = eventCategory;
                                    e.Date = eventDate;
                                    //System.out.println(e.Date);
                                    e.Time = eventStartTime;
                                    e.AMPM = eventStartTimeAMPM;
                                    e.Location = eventLocation;
                                    e.Description = eventDescription;
                                    Elist.add(e);
                                    System.out.println(Elist.size() + " dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");

                                } catch (JSONException e) {
                                    // If there is an error then output this to the logs.
                                    System.out.println("JSON ERROR");
                                    Log.e("Volley", "Invalid JSON Object.");
                                }

                            }
                        } else {
                            System.out.println("NO EVENTS");
                            // The user didn't have any notifications.
                            //setNotificationListText("No notifications found.");
                        }

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // If there a HTTP error then add a note to our notification list.
                        // setNotificationListText("Error while calling REST API");
                        System.out.println("VOLLEY HAS SOME ERRORS");
                        Log.e("Volley", error.toString());
                    }
                }
        );
        // Add the request we just defined to our request queue.
        // The request queue will automatically handle the request as soon as it can.
        requestQueue.add(arrReq);
    }

}
