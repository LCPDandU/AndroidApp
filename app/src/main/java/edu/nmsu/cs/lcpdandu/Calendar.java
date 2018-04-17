package edu.nmsu.cs.lcpdandu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;

import android.content.Intent;
import android.view.View;

public class Calendar extends AppCompatActivity {
    CalendarView calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_calendar);
        //DisplayCalendar();
    }

    public void DisplayCalendar(){
        //calendar = (CalendarView)findViewById(R.id.calendarView);
       // calendar.setShowWeekNumber(false);
    }


}