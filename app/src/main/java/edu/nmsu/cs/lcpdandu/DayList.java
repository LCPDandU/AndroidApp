package edu.nmsu.cs.lcpdandu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DayList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_list);
    }

    public void clickHome(View v) {
        startActivity(new Intent(DayList.this, HomePage.class));
    }
}
