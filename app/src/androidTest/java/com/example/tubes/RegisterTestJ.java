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
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RegisterTestJ {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void registerTestJ() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction materialTextView = onView(
                allOf(withId(R.id.link_signup), withText("No account yet? Create one"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                5)));
        materialTextView.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.input_username),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.username),
                                        0),
                                1)));
        textInputEditText.perform(scrollTo(), replaceText("rama"), closeSoftKeyboard());

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.input_number),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.number),
                                        0),
                                1)));
        textInputEditText2.perform(scrollTo(), replaceText("1234123412"), closeSoftKeyboard());

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.input_emailR),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.email),
                                        0),
                                1)));
        textInputEditText3.perform(scrollTo(), replaceText("ramap6933@gmail.com"), closeSoftKeyboard());

        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.input_passwordR),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.password),
                                        0),
                                1)));
        textInputEditText4.perform(scrollTo(), replaceText("naxklenk13"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.btn_signup), withText("Create Account"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        materialButton.perform(scrollTo(), click());

        ViewInteraction textInputEditText5 = onView(
                allOf(withId(R.id.input_name),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.name),
                                        0),
                                0)));
        textInputEditText5.perform(scrollTo(), replaceText("rama"), closeSoftKeyboard());

        ViewInteraction textInputEditText6 = onView(
                allOf(withId(R.id.input_username), withText("rama"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.username),
                                        0),
                                1)));
        textInputEditText6.perform(scrollTo(), replaceText(""));

        ViewInteraction textInputEditText7 = onView(
                allOf(withId(R.id.input_username),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.username),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText7.perform(closeSoftKeyboard());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.btn_signup), withText("Create Account"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        materialButton2.perform(scrollTo(), click());

        ViewInteraction textInputEditText8 = onView(
                allOf(withId(R.id.input_username),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.username),
                                        0),
                                1)));
        textInputEditText8.perform(scrollTo(), replaceText("rama"), closeSoftKeyboard());

        ViewInteraction textInputEditText9 = onView(
                allOf(withId(R.id.input_number), withText("1234123412"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.number),
                                        0),
                                1)));
        textInputEditText9.perform(scrollTo(), replaceText(""));

        ViewInteraction textInputEditText10 = onView(
                allOf(withId(R.id.input_number),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.number),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText10.perform(closeSoftKeyboard());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.btn_signup), withText("Create Account"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        materialButton3.perform(scrollTo(), click());

        ViewInteraction textInputEditText11 = onView(
                allOf(withId(R.id.input_number),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.number),
                                        0),
                                1)));
        textInputEditText11.perform(scrollTo(), replaceText("1231234123"), closeSoftKeyboard());

        ViewInteraction textInputEditText12 = onView(
                allOf(withId(R.id.input_emailR), withText("ramap6933@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.email),
                                        0),
                                1)));
        textInputEditText12.perform(scrollTo(), replaceText(""));

        ViewInteraction textInputEditText13 = onView(
                allOf(withId(R.id.input_emailR),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.email),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText13.perform(closeSoftKeyboard());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.btn_signup), withText("Create Account"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        materialButton4.perform(scrollTo(), click());

        ViewInteraction textInputEditText14 = onView(
                allOf(withId(R.id.input_emailR),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.email),
                                        0),
                                1)));
        textInputEditText14.perform(scrollTo(), replaceText("ramap6933@gmail.com"), closeSoftKeyboard());

        ViewInteraction textInputEditText15 = onView(
                allOf(withId(R.id.input_passwordR), withText("naxklenk13"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.password),
                                        0),
                                1)));
        textInputEditText15.perform(scrollTo(), replaceText(""));

        ViewInteraction textInputEditText16 = onView(
                allOf(withId(R.id.input_passwordR),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.password),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText16.perform(closeSoftKeyboard());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.btn_signup), withText("Create Account"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        materialButton5.perform(scrollTo(), click());

        ViewInteraction textInputEditText17 = onView(
                allOf(withId(R.id.input_passwordR),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.password),
                                        0),
                                1)));
        textInputEditText17.perform(scrollTo(), replaceText("naxklenk13"), closeSoftKeyboard());

        ViewInteraction textInputEditText18 = onView(
                allOf(withId(R.id.input_emailR), withText("ramap6933@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.email),
                                        0),
                                1)));
        textInputEditText18.perform(scrollTo(), replaceText(""));

        ViewInteraction textInputEditText19 = onView(
                allOf(withId(R.id.input_emailR),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.email),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText19.perform(closeSoftKeyboard());

        ViewInteraction textInputEditText20 = onView(
                allOf(withId(R.id.input_number), withText("1231234123"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.number),
                                        0),
                                1)));
        textInputEditText20.perform(scrollTo(), click());

        ViewInteraction textInputEditText21 = onView(
                allOf(withId(R.id.input_number), withText("1231234123"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.number),
                                        0),
                                1)));
        textInputEditText21.perform(scrollTo(), click());

        ViewInteraction textInputEditText22 = onView(
                allOf(withId(R.id.input_passwordR), withText("naxklenk13"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.password),
                                        0),
                                1)));
        textInputEditText22.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText23 = onView(
                allOf(withId(R.id.input_passwordR), withText("naxklenk13"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.password),
                                        0),
                                1)));
        textInputEditText23.perform(scrollTo(), replaceText(""));

        ViewInteraction textInputEditText24 = onView(
                allOf(withId(R.id.input_passwordR),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.password),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText24.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText25 = onView(
                allOf(withId(R.id.input_number), withText("1231234123"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.number),
                                        0),
                                1)));
        textInputEditText25.perform(scrollTo(), replaceText(""));

        ViewInteraction textInputEditText26 = onView(
                allOf(withId(R.id.input_number),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.number),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText26.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText27 = onView(
                allOf(withId(R.id.input_username), withText("rama"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.username),
                                        0),
                                1)));
        textInputEditText27.perform(scrollTo(), replaceText(""));

        ViewInteraction textInputEditText28 = onView(
                allOf(withId(R.id.input_username),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.username),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText28.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText29 = onView(
                allOf(withId(R.id.input_name), withText("rama"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.name),
                                        0),
                                0)));
        textInputEditText29.perform(scrollTo(), replaceText(""));

        ViewInteraction textInputEditText30 = onView(
                allOf(withId(R.id.input_name),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.name),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText30.perform(closeSoftKeyboard());

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.btn_signup), withText("Create Account"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        materialButton6.perform(scrollTo(), click());

        ViewInteraction textInputEditText31 = onView(
                allOf(withId(R.id.input_name),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.name),
                                        0),
                                0)));
        textInputEditText31.perform(scrollTo(), replaceText("rama andika"), closeSoftKeyboard());

        ViewInteraction textInputEditText32 = onView(
                allOf(withId(R.id.input_username),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.username),
                                        0),
                                1)));
        textInputEditText32.perform(scrollTo(), replaceText("rama"), closeSoftKeyboard());

        ViewInteraction textInputEditText33 = onView(
                allOf(withId(R.id.input_number),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.number),
                                        0),
                                1)));
        textInputEditText33.perform(scrollTo(), replaceText("1234123412"), closeSoftKeyboard());

        ViewInteraction textInputEditText34 = onView(
                allOf(withId(R.id.input_emailR),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.email),
                                        0),
                                1)));
        textInputEditText34.perform(scrollTo(), replaceText("ramap6933@gmail.com"), closeSoftKeyboard());

        ViewInteraction textInputEditText35 = onView(
                allOf(withId(R.id.input_passwordR),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.password),
                                        0),
                                0)));
        textInputEditText35.perform(scrollTo(), replaceText("naxklenk13"), closeSoftKeyboard());

        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.btn_signup), withText("Create Account"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        materialButton7.perform(scrollTo(), click());
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
