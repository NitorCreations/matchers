package com.nitorcreations.matchers;

import org.apache.commons.lang3.Validate;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import static org.hamcrest.Matchers.equalTo;

/**
 * Matches that the target {@link Iterable} contains a certain amount of items that match
 * the given matcher.
 * @param <T> the type of a single item in the {@link Iterable}
 */
public final class CountThat<T> extends TypeSafeDiagnosingMatcher<Iterable<T>> {

    private final Matcher<T> itemMatcher;

    private final Matcher<Integer> size;

    private CountThat(Matcher<T> itemMatcher, Matcher<Integer> size) {
        this.itemMatcher = Validate.notNull(itemMatcher);
        this.size = Validate.notNull(size);
    }

    /** @see CountThat */
    @Factory
    public static <T> Matcher<Iterable<T>> countThat(Matcher<T> itemMatcher, Matcher<Integer> size) {
        return new CountThat<T>(itemMatcher, size);
    }

    /** @see CountThat */
    @Factory
    public static <T> Matcher<Iterable<T>> countThat(Matcher<T> itemMatcher, int size) {
        return countThat(itemMatcher, equalTo(size));
    }


    @Override
    protected boolean matchesSafely(Iterable<T> item, Description mismatchDescription) {
        int count = 0;
        for (T t : item) {
            if (itemMatcher.matches(t)) {
                count++;
            }
        }
        mismatchDescription.appendText("contained ").appendValue(count).appendText(" items that ").appendDescriptionOf(itemMatcher);
        return size.matches(count);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("iterable containing ")
                .appendDescriptionOf(size)
                .appendText(" items that ")
                .appendDescriptionOf(itemMatcher);
    }
}
