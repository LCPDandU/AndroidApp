package edu.nmsu.cs.lcpdandu;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class HomePageTest {
    @Rule
    public ActivityTestRule<HomePage> mActivityRule =
            new ActivityTestRule<>(HomePage.class);

    @Rule
    public ActivityTestRule<ContactUs> mActivityContactRule =
            new ActivityTestRule<>(ContactUs.class);
    @Test
    public void loadToClickHome(){
        onView(withId(R.id.navigation_home)).perform(click());
    }

    @Test
    public void loadToClickEvents(){
        onView(withId(R.id.navigation_events)).perform(click());
    }

    @Test
    public void loadToClickContact(){
        onView(withId(R.id.navigation_contact)).perform(click());
    }

    @Test
    public void loadToClickNotifications(){
        onView(withId(R.id.navigation_notifications)).perform(click());
    }
    

}
