package edu.nmsu.cs.lcpdandu;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.openLink;
import static android.support.test.espresso.action.ViewActions.openLinkWithText;
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

    //Test can click on home page from home page
    @Test
    public void loadToClickHome(){
        onView(withId(R.id.navigation_home)).perform(click());
    }

    //Test can get to events from homepage
    @Test
    public void loadToClickEvents(){
        onView(withId(R.id.navigation_events)).perform(click());
    }

    //Test can get to contact page from homepage
    @Test
    public void loadToClickContact(){
        onView(withId(R.id.navigation_contact)).perform(click());
    }

    //Test can get to notifications from homepage
    @Test
    public void loadToClickNotifications(){
        onView(withId(R.id.navigation_notifications)).perform(click());
    }

    //Test can get to ask the city from homepage
    @Test
    public void askTheCity(){
        onView(withId(R.id.navigation_navigation_ask_city)).perform(click());
    }

    //Test can click homepage from homepage then navigate each page
    @Test
    public void testClickFromEachNavigation(){
        onView(withId(R.id.navigation_home)).perform(click());
        onView(withId(R.id.navigation_events)).perform(click());
        onView(withId(R.id.navigation_notifications)).perform(click());
        onView(withId(R.id.navigation_contact)).perform(click());
        onView(withId(R.id.navigation_navigation_ask_city)).perform(click());
    }

}
