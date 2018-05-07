package edu.nmsu.cs.lcpdandu;

import android.app.Notification;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.widget.AdapterView;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
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
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.collection.IsEmptyCollection;

import edu.nmsu.cs.lcpdandu.ContactUs;
import edu.nmsu.cs.lcpdandu.HomePage;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.MatcherAssert.assertThat;

public class NotificationPageTest {
    @Rule
    public ActivityTestRule<Notifications> mActivityRule =
            new ActivityTestRule<>(Notifications.class);


    //Will fail if new notifications are added
    @Test
    public void testNotificationListSize() {
        assertThat(Notifications.getNList().size(), is(19));
    }

    //Test can get to events from notifications
    @Test
    public void testNavigateEvent(){
        onView(withId(R.id.navigation_events)).perform(click());
    }

    //Test can get to ask the city from notifications
    @Test
    public void testNavigateAskCity(){
        onView(withId(R.id.navigation_navigation_ask_city)).perform(click());
    }

    //Test that notifications page can be clicked from notifications
    @Test
    public void loadToClickNotifications(){
        onView(withId(R.id.navigation_notifications)).perform(click());
    }

    //Test can get to homepage from notifications then navigate to each page.
    @Test
    public void testClickFromEachNavigation(){
        onView(withId(R.id.navigation_home)).perform(click());
        onView(withId(R.id.navigation_events)).perform(click());
        onView(withId(R.id.navigation_notifications)).perform(click());
        onView(withId(R.id.navigation_contact)).perform(click());
        onView(withId(R.id.LasCrucesPoliceWebsite)).perform(click());
    }
}