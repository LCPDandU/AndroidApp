package edu.nmsu.cs.lcpdandu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Notifications extends AppCompatActivity {


    private TextView tvNotificationList;  // This will reference our notification list text box.
    private ArrayList<NotificationObjects> NList = new ArrayList<>(); // Place to store all notification objects
    private ListView NListView; // Provides a list view to store objects inside.
    private RequestQueue requestQueue;  // This is our requests queue to process our HTTP requests.
    private ListArrayAdapter adapter; // Adapter to store textviews inside of listview with NotificationsObjects.
    private String url = "http://tm4sp18.cs.nmsu.edu/public/api/notifications/order/PostDate/sort/desc";  // This is the API base URL (GitHub API)
    private Date day = new Date(); //Today's date

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  // This is some magic for Android to load a previously saved state for when you are switching between actvities.
        setContentView(R.layout.activity_notifications);  // This links our code to our layout which we defined earlier.

        //this.tvNotificationList = (TextView) findViewById(R.id.tv_notification_list);  // Link our notificationsitory list text output box.
        //this.tvNotificationList.setMovementMethod(new ScrollingMovementMethod());  // This makes our text box scrollable, for those big GitHub contributors with lots of notifications :)

        requestQueue = Volley.newRequestQueue(this);  // This setups up a new request queue which we will need to make HTTP requests.
        getNotificationList();
        NListView = (ListView) findViewById(R.id.notifications_list_view);
        adapter = new ListArrayAdapter(this, NList);
        NListView.setAdapter(adapter);

        //Create calendar to manipulate day
        Calendar c = Calendar.getInstance();
        c.setTime(day);
        //Subtract how many days wanted for the cutoff of notifications
        c.add(Calendar.DATE, -3);
        day = c.getTime();
    }

    private void addToNotificationList(String notificationID, String notificationTitle, String notificationDescription, String notificationDate, String notificationTime, String notificationAMPM) {
        String strRow = notificationID + "\n" + notificationTitle + "\n" + notificationDescription + "\n" + notificationDate + "\n" + notificationTime + " " + notificationAMPM;
        //String currentText = tvNotificationList.getText().toString();
        //this.tvNotificationList.setText(currentText + "\n\n" + strRow);
    }

    private void setNotificationListText(String str) {

        this.tvNotificationList.setText(str);
    }

    private void getNotificationList() {

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
                                    String notificationID = jsonObj.get("ID").toString();
                                    String notificationTitle = jsonObj.get("Title").toString();
                                    String notificationDescription = jsonObj.get("Description").toString();
                                    String notificationDate = jsonObj.get("PostDate").toString();
                                    String notificationTime = jsonObj.get("PostTime").toString();
                                    String notificationAMPM = jsonObj.get("PostTimeAMPM").toString();
                                    addToNotificationList(notificationID, notificationTitle, notificationDescription, notificationDate, notificationTime, notificationAMPM);

                                    NotificationObjects n = new NotificationObjects(notificationID, notificationTitle, notificationDescription, notificationDate, notificationTime, notificationAMPM);
                                    //Convert the date pulled from the database
                                    Date convertedDate = convertDate(notificationDate);

                                    //If the date set is before the date from the database then add it to the adapter and Nlist
                                    if(day.before(convertedDate)) {
                                        adapter.add(n);
                                        NList.add(n);
                                    }

                                } catch (JSONException e) {
                                    // If there is an error then output this to the logs.
                                    Log.e("Volley", "Invalid JSON Object.");
                                }

                            }
                        } else {
                            // The user didn't have any notifications.
                            setNotificationListText("No notifications found.");
                        }

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // If there a HTTP error then add a note to our notification list.
                        //setNotificationListText("Error while calling REST API");
                        Log.e("Volley", error.toString());
                    }
                }
        );
        // Add the request we just defined to our request queue.
        // The request queue will automatically handle the request as soon as it can.
        requestQueue.add(arrReq);
    }

    private static Date convertDate(String eventDate){
        //Setting the date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date convertedDate = new Date();
        //Try to format passed date with the dateformat
        try{
            convertedDate = dateFormat.parse(eventDate);
        }catch(ParseException e){
            e.printStackTrace();
        }
        return convertedDate;
    }
}