package edu.nmsu.cs.lcpdandu;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Set;

public class ContactUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        SetBottomNavigation();
    }

    private void SetBottomNavigation(){
        //Set BottomNavigation View
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.contact_us_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
    }

    //Bottom Navigation Bar contains five tabs that will lead to its intended pages: HomePage,
    //CompactCalendar, ContactUs, Notifications, and AskTheCity.
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent homeIntent = new Intent(ContactUs.this, HomePage.class);
                    startActivity(homeIntent);
                    return true;
                case R.id.navigation_events:
                    Intent eventsIntent = new Intent(ContactUs.this, CompactCalendar.class);
                    startActivity(eventsIntent);
                    return true;
                case R.id.navigation_contact:
                    Intent contactIntent = new Intent(ContactUs.this, ContactUs.class);
                    startActivity(contactIntent);
                    return true;
                case R.id.navigation_notifications:
                    Intent notificationsIntent = new Intent(ContactUs.this, Notifications.class);
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

    //Moves from the app to browser. Browser destination is set to the
    //Las Cruces Police Department.
    public void clickPoliceWebsite(View v) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("http://www.las-cruces.org/departments/police-department"));
        startActivity(intent);
    }

    //Moves from the app to browser. Browser destination is set to the
    //Las Cruces Police Department's Facebook.
    public void clickPoliceFacebook(View v) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("https://www.facebook.com/TheLasCrucesPoliceDepartment"));
        startActivity(intent);
    }

    //Moves from the app to browser. Browser destination is set to the
    //Las Cruces Police Department's Twitter.
    public void clickPoliceTwitter(View v) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("https://twitter.com/lascrucespolice"));
        startActivity(intent);
    }

    //Moves from app to phone app. Number is set to dial Las Cruces
    //Police Department.
    public void callPhone(View V) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:5756400344"));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(callIntent);
    }
}
