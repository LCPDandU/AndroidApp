package edu.nmsu.cs.lcpdandu;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class ContactUsTest {

    @Rule
    public ActivityTestRule<ContactUs> mActivityRule =
            new ActivityTestRule<>(ContactUs.class);


    //Test can click city of Las Cruces button
    @Test
    public void cityOfLasCruces(){
        onView(withId(R.id.LasCrucesPoliceWebsite)).perform(click());
    }

    //Test can click twitter button
    @Test
    public void twitterButton(){
        onView(withId(R.id.TwitterButton)).perform(click());
    }

    //Test can click facebook button
    @Test
    public void facebookButton(){
        onView(withId(R.id.FacebookButton)).perform(click());
    }

    //Test can click the call button
    @Test
    public void callButton(){
        onView(withId(R.id.PhoneButton)).perform(click());
    }

    //Test can get to ask the city from contact page
    @Test
    public void testNavigateAskCity(){
        onView(withId(R.id.navigation_navigation_ask_city)).perform(click());
    }

    //Test can get to notifications from contact page
    @Test
    public void loadToClickNotifications(){
        onView(withId(R.id.navigation_notifications)).perform(click());
    }

    //Test can get to events from contact page
    @Test
    public void loadToClickEvents(){
        onView(withId(R.id.navigation_events)).perform(click());
    }

    //Test that contact page can be clicked from contact page
    @Test
    public void loadToClickContact(){
        onView(withId(R.id.navigation_contact)).perform(click());
    }

    //Test can get to homepage from contact page then navigate to each page.
    @Test
    public void testClickFromEachNavigation(){
        onView(withId(R.id.navigation_home)).perform(click());
        onView(withId(R.id.navigation_events)).perform(click());
        onView(withId(R.id.navigation_notifications)).perform(click());
        onView(withId(R.id.navigation_contact)).perform(click());
        onView(withId(R.id.navigation_navigation_ask_city)).perform(click());
    }
}
