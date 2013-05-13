package com.nitorcreations.matchers;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public final class StringMatchers {

    private StringMatchers() {}

    /**
     * Matches a {@link String} containing the target string case-insensitively.
     */
    @Factory
    public static Matcher<String> containsIgnoreCase(final String substring) {
        return new TypeSafeDiagnosingMatcher<String>() {
            @Override
            protected boolean matchesSafely(String item, Description mismatchDescription) {
                mismatchDescription.appendValue(item).appendText(" did not contain ignoring case: ").appendValue(substring);
                return StringUtils.containsIgnoreCase(item, substring);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("contains ignoring case ").appendValue(substring);
            }
        };
    }
}
