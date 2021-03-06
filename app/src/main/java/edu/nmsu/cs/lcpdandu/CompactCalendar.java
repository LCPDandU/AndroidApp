package edu.nmsu.cs.lcpdandu;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import android.widget.CalendarView;
import android.widget.Toast;
import android.content.Context;


/*
CompactCalendar takes from the Homepage where the Rest API is called in order to get the events from the database.
These events are stored in HomePage.getElist(). For each EventObject that is in this arraylist an Calendar event is created.
These events are then added the calendar that is displayed and they are stored in CalendarEventList.
*/
public class CompactCalendar extends AppCompatActivity {
    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());
    CompactCalendarView compactCalendar;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());
    private static ArrayList<Event> CalendarEventList = new ArrayList<Event>();
    private static ArrayList<EventObjects> dayListEvents = new ArrayList<EventObjects>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        final ActionBar actionBar = getSupportActionBar();

        //Buttons
        final Button showPreviousMonthBut = (Button) findViewById(R.id.prev_button);
        final Button showNextMonthBut = (Button) findViewById(R.id.next_button);
        actionBar.setDisplayHomeAsUpEnabled(false);

        //Calendar settings
        compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendar.setFirstDayOfWeek(Calendar.SUNDAY);
        compactCalendar.setUseThreeLetterAbbreviation(true);
        compactCalendar.shouldDrawIndicatorsBelowSelectedDays(true);
        Date eventDay = new Date();
        Calendar calendar = new GregorianCalendar();
        actionBar.setTitle(dateFormatMonth.format(eventDay));


        for (EventObjects e : HomePage.getEventObjectList()) {
            //Converting the day from the object format of yyyy-MM-dd to a date
            eventDay = convertDate(e.Date);
            calendar.setTime(eventDay);

            //Getting the day month and year
            int day = calendar.get(Calendar.DATE);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);

            //Setting day month year and era for the calendar
            currentCalender.set(Calendar.DAY_OF_MONTH, day);
            currentCalender.set(Calendar.MONTH, month);
            currentCalender.set(Calendar.YEAR, year);
            currentCalender.set(Calendar.ERA, GregorianCalendar.AD);
            setToMidnight(currentCalender);

            //Convert to milliseconds
            long timeInMillis = currentCalender.getTimeInMillis();

            //Create and add event with the title
            Event ev1 = new Event(Color.RED, timeInMillis, e.Title);
            compactCalendar.addEvent(ev1);
            CalendarEventList.add(ev1);
        }

        //Previous month button
        showPreviousMonthBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compactCalendar.showPreviousMonth();
            }
        });

        //Next month button
        showNextMonthBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compactCalendar.showNextMonth();
            }
        });

        /*
        OnDayClick listener. Search through HomePage.getElist() which has all the events from the database.
        If the dateClicked matches with a day from the database then setVariable is set to 1. and dayListEvents
        (The ArrayList that stores the events to be showed for the specific day) adds that specific event
        to its Arraylist. Then check if setVariable is 1. if it is one then start the DayList display, if it
        is not then flash that there is no event today
         */
        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getApplicationContext();
                int setVariable = 0;
                //Check if the eventday is the same as the day clicked. If it is then setVariable is 1
                for(int i = 0; i < HomePage.getEventObjectList().size(); i++){
                    Date d = new Date(CalendarEventList.get(i).getTimeInMillis());
                    if ((dateClicked.compareTo(d)) == 0) {
                        setVariable = 1;
                        dayListEvents.add(HomePage.getEventObjectList().get(i));
                    }
                }//End For
                //If setVariable is 1 then show the title of the event
                if(setVariable == 1){
                    startActivity(new Intent(CompactCalendar.this, DayList.class));
                }
                //Else There is no events
                else{
                    Toast.makeText(context, "There is no event today", Toast.LENGTH_SHORT).show();
                }

            }

            //Month change from swiping
            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(dateFormatMonth.format(firstDayOfNewMonth));
            }
        });

        //Set BottomNavigationView
        SetBottomNavigation();

    }


    //Clear dayListEvents on each resume so that it does not hold the events from other days.
    @Override
    public void onResume(){
        super.onResume();
        dayListEvents.clear();
    }



    //Used for Taking the date from the database which would be a string and converting from the string
    //into a Date Object.
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

    private void setToMidnight(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    //Accessor getDayListEvents
    public static ArrayList<EventObjects> getDayListEvents() {
        return dayListEvents;
    }

    //Bottom Navigation Bar contains five tabs that will lead to its intended pages: HomePage,
    //CompactCalendar, ContactUs, Notifications, and AskTheCity.
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent homeIntent = new Intent(CompactCalendar.this, HomePage.class);
                    startActivity(homeIntent);
                    return true;
                case R.id.navigation_events:
                    return true;
                case R.id.navigation_contact:
                    Intent contactIntent = new Intent(CompactCalendar.this, ContactUs.class);
                    startActivity(contactIntent);
                    return true;
                case R.id.navigation_notifications:
                    Intent notificationsIntent = new Intent(CompactCalendar.this, Notifications.class);
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

    private void SetBottomNavigation(){
        //Set BottomNavigation View
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.calendar_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
    }

    //Accessor getDayListEvents
    public static ArrayList<Event> getCalendarEventList() {
        return CalendarEventList;
    }

}




