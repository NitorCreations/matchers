package com.nitorcreations.matchers;

import java.util.Map;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public final class MapMatchers {
    private MapMatchers() { /** prevent instantiation */ }

    @Factory
    public static <T extends Map<?,?>> Matcher<T> emptyMap() {
        return new TypeSafeDiagnosingMatcher<T>() {
            @Override
            protected boolean matchesSafely(T item, Description mismatchDescription) {
                mismatchDescription.appendText("was not empty, size was ").appendValue(item.size());
                return item.isEmpty();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("empty map");
            }
        };
    }
}
