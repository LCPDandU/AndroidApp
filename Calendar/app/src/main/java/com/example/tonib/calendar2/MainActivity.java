package com.example.tonib.calendar2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;

public class MainActivity extends AppCompatActivity {
    CalendarView calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayCalendar();
    }

    public void DisplayCalendar(){
        calendar = (CalendarView)findViewById(R.id.calendarView);
        calendar.setShowWeekNumber(false);
    }


}
