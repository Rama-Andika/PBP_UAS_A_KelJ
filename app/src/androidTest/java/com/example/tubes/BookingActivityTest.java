package com.example.tubes;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class BookingActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void bookingActivityTest() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.input_emailR),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.email),
                                        0),
                                0)));
        textInputEditText.perform(scrollTo(), replaceText("admin"), closeSoftKeyboard());

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.input_passwordR),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.password),
                                        0),
                                0)));
        textInputEditText2.perform(scrollTo(), replaceText("admin"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.btn_login), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                4)));
        materialButton.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction linearLayout = onView(
                childAtPosition(
                        allOf(withId(R.id.cvCreateUser),
                                childAtPosition(
                                        withClassName(is("android.widget.GridLayout")),
                                        1)),
                        0));
        linearLayout.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pressBack();

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.btn_book), withText("Booking"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        materialButton2.perform(scrollTo(), click());

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.input_name),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.name),
                                        0),
                                0)));
        textInputEditText3.perform(scrollTo(), click());

        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.input_name),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.name),
                                        0),
                                0)));
        textInputEditText4.perform(scrollTo(), replaceText("a"), closeSoftKeyboard());

        pressBack();

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.btn_book), withText("Booking"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        materialButton3.perform(scrollTo(), click());

        ViewInteraction textInputEditText5 = onView(
                allOf(withId(R.id.input_name), withText("a"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.name),
                                        0),
                                0)));
        textInputEditText5.perform(scrollTo(), click());

        ViewInteraction textInputEditText6 = onView(
                allOf(withId(R.id.input_name), withText("a"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.name),
                                        0),
                                0)));
        textInputEditText6.perform(scrollTo(), replaceText("Radra"));

        ViewInteraction textInputEditText7 = onView(
                allOf(withId(R.id.input_name), withText("Radra"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.name),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText7.perform(closeSoftKeyboard());

        pressBack();

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.btn_book), withText("Booking"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        materialButton4.perform(scrollTo(), click());

        ViewInteraction materialAutoCompleteTextView = onView(
                allOf(withId(R.id.edRoom),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.room),
                                        0),
                                0)));
        materialAutoCompleteTextView.perform(scrollTo(), click());

        DataInteraction frameLayout = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(0);
        frameLayout.perform(click());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.btn_book), withText("Booking"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        materialButton5.perform(scrollTo(), click());

        ViewInteraction textInputEditText8 = onView(
                allOf(withId(R.id.input_date),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.date),
                                        0),
                                0)));
        textInputEditText8.perform(scrollTo(), click());

        ViewInteraction materialButton6 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton6.perform(scrollTo(), click());

        pressBack();

        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.btn_book), withText("Booking"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        materialButton7.perform(scrollTo(), click());

        ViewInteraction textInputEditText9 = onView(
                allOf(withId(R.id.input_adult),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.adult),
                                        0),
                                0)));
        textInputEditText9.perform(scrollTo(), replaceText("2"), closeSoftKeyboard());

        pressBack();

        ViewInteraction materialButton8 = onView(
                allOf(withId(R.id.btn_book), withText("Booking"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        materialButton8.perform(scrollTo(), click());

        ViewInteraction materialButton9 = onView(
                allOf(withId(R.id.btn_book), withText("Booking"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        materialButton9.perform(scrollTo(), click());

        ViewInteraction textInputEditText10 = onView(
                allOf(withId(R.id.input_child),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.child),
                                        0),
                                0)));
        textInputEditText10.perform(scrollTo(), replaceText("1"), closeSoftKeyboard());

        pressBack();

        ViewInteraction materialButton10 = onView(
                allOf(withId(R.id.btn_book), withText("Booking"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        materialButton10.perform(scrollTo(), click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
