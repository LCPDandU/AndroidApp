package edu.nmsu.cs.lcpdandu;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toolbar;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.widget.Toast;
import android.content.Context;


public class CompactCalendar extends AppCompatActivity {
    // TextView tvNotificationList;
    private static final String TAG = "MainActivity";
    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());
    private SimpleDateFormat dateFormatForDisplaying = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a", Locale.getDefault());
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.getDefault());
    private boolean shouldShow = false;
    private CompactCalendarView compactCalendarView;
    private ActionBar toolbar;
    static ArrayList<EventObjects> events = HomePage.Elist;
    //private Toolbar toolbar;
    private String day = "2018-04-10";
    //RequestQueue requestQueue;
    //String url = "http://tm4sp18.cs.nmsu.edu:8000/public/api/notifications";   // This is the API base URL (GitHub API)

    CompactCalendarView compactCalendar;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle(null);
        ////actionBar.setTitle(dateFormatMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));
        compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendar.setUseThreeLetterAbbreviation(true);
       Date eventday = new Date();
        Calendar calendar = new GregorianCalendar();
        eventday = convertDate(day);
        calendar.setTime(eventday);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        int day = calendar.get(Calendar.DATE);
        //addEvent(month, year, day);
        //Set an event for Teachers' Professional Day 2016 which is 21st of October

        for (EventObjects e : HomePage.Elist) {
            System.out.println(e.Date);
            eventday = convertDate(e.Date);
            calendar.setTime(eventday);
            month = calendar.get(Calendar.MONTH);
            year = calendar.get(Calendar.YEAR);
            day = calendar.get(Calendar.DATE);
            currentCalender.setTime(new Date());
            currentCalender.set(Calendar.DAY_OF_MONTH, day);
            Date firstDayOfMonth = currentCalender.getTime();
            currentCalender.set(Calendar.MONTH, month);
            currentCalender.set(Calendar.ERA, GregorianCalendar.AD);
            currentCalender.set(Calendar.YEAR, year);
            setToMidnight(currentCalender);
            long timeInMillis = currentCalender.getTimeInMillis();
            Event ev1 = new Event(Color.RED, timeInMillis, "Teachers' Professional Day");
            compactCalendar.addEvent(ev1);
        }
/*
        currentCalender.setTime(new Date());
        currentCalender.set(Calendar.DAY_OF_MONTH, day);
        Date firstDayOfMonth = currentCalender.getTime();
        currentCalender.set(Calendar.MONTH, month);
        currentCalender.set(Calendar.ERA, GregorianCalendar.AD);
        currentCalender.set(Calendar.YEAR, year);
        setToMidnight(currentCalender);
        long timeInMillis = currentCalender.getTimeInMillis();
        Event ev1 = new Event(Color.RED, timeInMillis, "Teachers' Professional Day");
        compactCalendar.addEvent(ev1);*/
        printer();

        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getApplicationContext();

                if (dateClicked.toString().compareTo("Fri Oct 21 00:00:00 AST 2016") == 0) {
                    Toast.makeText(context, "Teachers' Professional Day", Toast.LENGTH_SHORT).show();
                }else {
                    System.out.println(dateClicked);
                    Toast.makeText(context, "No Events Planned for that day", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(dateFormatMonth.format(firstDayOfNewMonth));
            }
        });

    }

    private static Date convertDate(String eventDate){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date convertedDate = new Date();
        Calendar calendar = new GregorianCalendar();

        try{
            convertedDate = dateFormat.parse(eventDate);
        }catch(ParseException e){
            e.printStackTrace();
        }
        calendar.setTime(convertedDate);
        System.out.println(convertedDate + " converted date");
        System.out.println(calendar.get(Calendar.DATE) + " converted date day");
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH) + " Day of month");
        System.out.println(calendar.get(Calendar.YEAR) + " converted date year");
        System.out.println(calendar.get(Calendar.MONTH) + " converted date month");


        //  Arrays.toString(events.toArray());


        return convertedDate;
    }

    private void parseDay(){

        System.out.println(events.size() + " size");
        for(int i =0; i < events.size(); i++){
            System.out.println(events.get(i).Date);
            System.out.println("nothing?");
        }
        for(EventObjects e: events){
            System.out.println(e.Date);
            System.out.println("nothing?");
        }
        System.out.println("THe parse Day)");
    }

    private void addEvent(int month, int year, int day) {
        currentCalender.setTime(new Date());
        //currentCalender.set(Calendar.DAY_OF_MONTH, 1);
        //Date firstDayOfMonth = currentCalender.getTime();
        // for (int i = 0; i < 6; i++) {
        //   currentCalender.setTime(firstDayOfMonth);
        // if (month > -1) {
        currentCalender.set(Calendar.MONTH, month);
        // }
        // if (year > -1) {
        currentCalender.set(Calendar.ERA, GregorianCalendar.AD);
        currentCalender.set(Calendar.YEAR, year);
        //}
        currentCalender.add(Calendar.DATE, day);
        System.out.println("this day is in the addevent " + day);
        setToMidnight(currentCalender);
        long timeInMillis = currentCalender.getTimeInMillis();

        List<Event> events = getEvents(timeInMillis, day);

        compactCalendarView.addEvents(events);

    }

    private void addEvents(int month, int year) {
        currentCalender.setTime(new Date());
        currentCalender.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = currentCalender.getTime();
        for (int i = 0; i < 6; i++) {
            currentCalender.setTime(firstDayOfMonth);
            if (month > -1) {
                currentCalender.set(Calendar.MONTH, month);
            }
            if (year > -1) {
                currentCalender.set(Calendar.ERA, GregorianCalendar.AD);
                currentCalender.set(Calendar.YEAR, year);
            }
            currentCalender.add(Calendar.DATE, i);
            setToMidnight(currentCalender);
            long timeInMillis = currentCalender.getTimeInMillis();

            List<Event> events = getEvents(timeInMillis, i);

            //compactCalendarView.addEvents(events);
        }
    }

    private List<Event> getEvents(long timeInMillis, int day) {
        if (day < 2) {
            return Arrays.asList(new Event(Color.argb(255, 169, 68, 65), timeInMillis, "Event here at " + new Date(timeInMillis)));
        } else if ( day > 2 && day <= 4) {
            return Arrays.asList(
                    new Event(Color.argb(255, 169, 68, 65), timeInMillis, "Event at " + new Date(timeInMillis)),
                    new Event(Color.argb(255, 100, 68, 65), timeInMillis, "Event 2 hmm at " + new Date(timeInMillis)));
        } else {
            return Arrays.asList(
                    new Event(Color.argb(255, 169, 68, 65), timeInMillis, "Event at " + new Date(timeInMillis) ),
                    new Event(Color.argb(255, 100, 68, 65), timeInMillis, "Event change at " + new Date(timeInMillis)),
                    new Event(Color.argb(255, 70, 68, 65), timeInMillis, "Event 3 at " + new Date(timeInMillis)));
        }
    }

    public void printer() {
        System.out.println("ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss dont stop bruhhhhhhhhhhhhhhhhhhhh");
        for (EventObjects e : HomePage.Elist) {
            System.out.println(e.Date);
            System.out.println("nothing?");
        }
    }

    private void setToMidnight(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }


}




