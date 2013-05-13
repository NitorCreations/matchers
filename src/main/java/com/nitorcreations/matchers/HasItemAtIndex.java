package com.nitorcreations.matchers;


import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public final class HasItemAtIndex<X> extends TypeSafeDiagnosingMatcher<Iterable<X>> {

    /**
     * If null, check for the last element
     */
    private final Integer index;

    private final Matcher<X> itemMatcher;

    private HasItemAtIndex(Integer index, Matcher<X> itemMatcher) {
        this.index = index;
        this.itemMatcher = itemMatcher;
    }

    private static <T> T getItem(List<T> list, Integer index) {
        if (index == null) {
            return list.get(list.size() - 1);
        }
        return list.get(index);
    }

    private static <T> List<T> getList(Iterable<T> iterable) {
        List<T> list = new ArrayList<T>();
        for (T t : iterable) {
            list.add(t);
        }
        return list;
    }

    @Factory
    public static <T> Matcher<Iterable<T>> hasFirstItem(Matcher<T> item) {
        return new HasItemAtIndex<T>(0, item);
    }

    @Factory
    public static <T> Matcher<Iterable<T>> hasLastItem(Matcher<T> item) {
        return new HasItemAtIndex<T>(null, item);
    }

    @Factory
    public static <T> Matcher<Iterable<T>> hasItemAtIndex(int index, Matcher<T> item) {
        return new HasItemAtIndex<T>(index, item);
    }

    @Override
    protected boolean matchesSafely(Iterable<X> iterable, Description mismatchDescription) {
        final List<X> list = getList(iterable);
        if (list.isEmpty()) {
            mismatchDescription.appendText("iterable was empty");
            return false;
        } else if (index != null && index >= list.size()) {
            mismatchDescription.appendText("size was ").appendValue(list.size());
            return false;
        } else {
            final X item = getItem(list, index);
            itemMatcher.describeMismatch(item, mismatchDescription);
            return itemMatcher.matches(item);
        }
    }

    @Override
    public void describeTo(Description description) {
        if (index == null) {
            description.appendText("iterable with last item: ");
        } else {
            description.appendText(String.format("iterable with item at index %d: ", index));
        }
        description.appendDescriptionOf(itemMatcher);
    }

}