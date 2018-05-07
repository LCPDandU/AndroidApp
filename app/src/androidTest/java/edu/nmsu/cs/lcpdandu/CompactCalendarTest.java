package edu.nmsu.cs.lcpdandu;

import android.support.test.rule.ActivityTestRule;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import org.junit.Rule;
import org.junit.Test;

import java.util.Date;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class CompactCalendarTest {
    @Rule
    public ActivityTestRule<CompactCalendar> mActivityRule =
            new ActivityTestRule<>(CompactCalendar.class);


    //Test can click back button
    @Test
    public void eventBack(){
        onView(withId(R.id.prev_button)).perform(click());
    }

    //Test can click next button
    @Test
    public void eventNext(){
        onView(withId(R.id.next_button)).perform(click());
    }

    //Test can get to ask the city from events
    @Test
    public void testNavigateAskCity(){
        onView(withId(R.id.navigation_navigation_ask_city)).perform(click());
    }

    //Test can get to contact page from events
    @Test
    public void loadToClickContact(){
        onView(withId(R.id.navigation_contact)).perform(click());
    }


    //Test can click on event page from event page
    @Test
    public void loadToClickEvents(){
        onView(withId(R.id.navigation_events)).perform(click());
    }

    //Test can get to homepage from events then navigate to each page.
    @Test
    public void testClickFromEachNavigation(){
        onView(withId(R.id.navigation_home)).perform(click());
        onView(withId(R.id.navigation_events)).perform(click());
        onView(withId(R.id.navigation_notifications)).perform(click());
        onView(withId(R.id.navigation_contact)).perform(click());
        onView(withId(R.id.navigation_navigation_ask_city)).perform(click());
    }

}
