package com.nitorcreations.matchers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

/**
 * Matches an {@link java.util.Iterator} by converting its values to a {@link java.util.List},
 * and matching the list against a {@link org.hamcrest.Matcher} for an {@link Iterable}.
 *
 */
public final class IteratorMatcher<T extends Iterator<?>> extends TypeSafeDiagnosingMatcher<T> {

    private final Matcher<?> iterableMatcher;

    private IteratorMatcher(Matcher<?> iterableMatcher) {
        this.iterableMatcher = iterableMatcher;
    }

    @Override
    protected boolean matchesSafely(T item, Description mismatchDescription) {
        List<Object> list = getList(item);
        iterableMatcher.describeMismatch(list, mismatchDescription);
        return iterableMatcher.matches(list);
    }


    private static <T extends Iterator<?>> List<Object> getList(T iterator) {
        List<Object> list = new ArrayList<Object>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("iterator of ").appendDescriptionOf(iterableMatcher);
    }

    /**
     * @see IteratorMatcher
     * @param iterableMatcher the matcher for the converted list
     * @param <T> the type of a single element in the list
     * @return the matcher
     */
    @Factory
    public static <T extends Iterator<?>, X extends Iterable<?>> Matcher<? super T> iteratorThat(Matcher<X> iterableMatcher) {
        return new IteratorMatcher<T>(iterableMatcher);
    }
}
