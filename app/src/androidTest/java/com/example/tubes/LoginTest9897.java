package com.example.tubes;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

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

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginTest9897 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void loginTest9897() {
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
                                1)));
        textInputEditText.perform(scrollTo(), replaceText("ramaandika@gmail.com"), closeSoftKeyboard());

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.input_passwordR),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.password),
                                        0),
                                1)));
        textInputEditText2.perform(scrollTo(), replaceText("naxklenk13"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.btn_login), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                4)));
        materialButton.perform(scrollTo(), click());

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.input_emailR), withText("ramaandika@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.email),
                                        0),
                                1)));
        textInputEditText3.perform(scrollTo(), replaceText("ramaandika"));

        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.input_emailR), withText("ramaandika"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.email),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText4.perform(closeSoftKeyboard());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.btn_login), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                4)));
        materialButton2.perform(scrollTo(), click());

        ViewInteraction textInputEditText5 = onView(
                allOf(withId(R.id.input_emailR), withText("ramaandika"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.email),
                                        0),
                                1)));
        textInputEditText5.perform(scrollTo(), replaceText("ramaandika@gmail.com"));

        ViewInteraction textInputEditText6 = onView(
                allOf(withId(R.id.input_emailR), withText("ramaandika@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.email),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText6.perform(closeSoftKeyboard());

        ViewInteraction textInputEditText7 = onView(
                allOf(withId(R.id.input_passwordR), withText("naxklenk13"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.password),
                                        0),
                                1)));
        textInputEditText7.perform(scrollTo(), replaceText("123"));

        ViewInteraction textInputEditText8 = onView(
                allOf(withId(R.id.input_passwordR), withText("123"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.password),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText8.perform(closeSoftKeyboard());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.btn_login), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                4)));
        materialButton3.perform(scrollTo(), click());

        ViewInteraction textInputEditText9 = onView(
                allOf(withId(R.id.input_emailR), withText("ramaandika@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.email),
                                        0),
                                1)));
        textInputEditText9.perform(scrollTo(), replaceText("ramaandika31@gmail.com"));

        ViewInteraction textInputEditText10 = onView(
                allOf(withId(R.id.input_emailR), withText("ramaandika31@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.email),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText10.perform(closeSoftKeyboard());

        ViewInteraction textInputEditText11 = onView(
                allOf(withId(R.id.input_passwordR), withText("123"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.password),
                                        0),
                                1)));
        textInputEditText11.perform(scrollTo(), replaceText("naxklenk13"));

        ViewInteraction textInputEditText12 = onView(
                allOf(withId(R.id.input_passwordR), withText("naxklenk13"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.password),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText12.perform(closeSoftKeyboard());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.btn_login), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                4)));
        materialButton4.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction overflowMenuButton = onView(
                allOf(withContentDescription("More options"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        1),
                                0)));
        overflowMenuButton.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction materialTextView = onView(
                allOf(withId(R.id.title), withText("Logout"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        materialTextView.perform(click());

        ViewInteraction materialButton5 = onView(
                allOf(withId(android.R.id.button1), withText("Yes"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton5.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText13 = onView(
                allOf(withId(R.id.input_emailR),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.email),
                                        0),
                                0)));
        textInputEditText13.perform(scrollTo(), replaceText("rama@gmail.com"), closeSoftKeyboard());

        ViewInteraction textInputEditText14 = onView(
                allOf(withId(R.id.input_passwordR),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.password),
                                        0),
                                1)));
        textInputEditText14.perform(scrollTo(), replaceText("1234"), closeSoftKeyboard());

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.btn_login), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                4)));
        materialButton6.perform(scrollTo(), click());

        ViewInteraction textInputEditText15 = onView(
                allOf(withId(R.id.input_passwordR), withText("1234"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.password),
                                        0),
                                1)));
        textInputEditText15.perform(scrollTo(), replaceText("1234567"));

        ViewInteraction textInputEditText16 = onView(
                allOf(withId(R.id.input_passwordR), withText("1234567"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.password),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText16.perform(closeSoftKeyboard());

        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.btn_login), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                4)));
        materialButton7.perform(scrollTo(), click());

        ViewInteraction textInputEditText17 = onView(
                allOf(withId(R.id.input_passwordR), withText("1234567"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.password),
                                        0),
                                1)));
        textInputEditText17.perform(scrollTo(), replaceText("naxklenk13"));

        ViewInteraction textInputEditText18 = onView(
                allOf(withId(R.id.input_passwordR), withText("naxklenk13"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.password),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText18.perform(closeSoftKeyboard());

        ViewInteraction materialButton8 = onView(
                allOf(withId(R.id.btn_login), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                4)));
        materialButton8.perform(scrollTo(), click());
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
