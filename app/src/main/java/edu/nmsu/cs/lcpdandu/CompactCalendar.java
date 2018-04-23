package edu.nmsu.cs.lcpdandu;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.Toast;
import android.content.Context;



public class CompactCalendar extends AppCompatActivity {
    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());
    CompactCalendarView compactCalendar;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());
    private static ArrayList<Event> eventList = new ArrayList<Event>();
    private static ArrayList<EventObjects> eventToSend = new ArrayList<EventObjects>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        final ActionBar actionBar = getSupportActionBar();
        final Button showPreviousMonthBut = (Button) findViewById(R.id.prev_button);
        final Button showNextMonthBut = (Button) findViewById(R.id.next_button);
        actionBar.setDisplayHomeAsUpEnabled(false);
        compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendar.setFirstDayOfWeek(Calendar.SUNDAY);
        compactCalendar.setUseThreeLetterAbbreviation(true);
        Date eventDay = new Date();
        Calendar calendar = new GregorianCalendar();
        actionBar.setTitle(dateFormatMonth.format(eventDay));



        for (EventObjects e : HomePage.getElist()) {
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
            eventList.add(ev1);
            compactCalendar.addEvent(ev1);
        }



        showPreviousMonthBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compactCalendar.showPreviousMonth();
            }
        });

        showNextMonthBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compactCalendar.showNextMonth();
            }
        });

        //The onDayClick listener
        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getApplicationContext();
                int setVariable = 0;
                String title = "";
                //Check if the eventday is the same as the day clicked. If it is then setVariable is 1
                for(int i = 0; i < eventList.size(); i++){
                    Date d = new Date(eventList.get(i).getTimeInMillis());
                    if ((dateClicked.compareTo(d)) == 0) {
                        setVariable = 1;
                        title = eventList.get(i).getData().toString();
                        if(i <= HomePage.getElist().size()) {
                            eventToSend.add(HomePage.getElist().get(i));
                        }
                        else{
                            eventList.clear();
                        }
                    }
                }//End For
                //If setVariable is 1 then show the title of the event
                if(setVariable == 1){
                    Toast.makeText(context, title, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CompactCalendar.this, DayList.class));
                }
                //Else There is no events
                else{
                    Toast.makeText(context, "There is no event today", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(dateFormatMonth.format(firstDayOfNewMonth));
            }
        });

    }
    //TODO: Switch the button from website to list format
    public void clickViewList(View v) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("http://www.las-cruces.org/departments/police-department"));
        startActivity(intent);
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

    private void setToMidnight(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    public static ArrayList<Event> getEventList() {
        return eventList;
    }
    public static ArrayList<EventObjects> getEventToSend() {
        return eventToSend;
    }
}




