package com.nitorcreations.matchers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsCollectionContaining;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.AllOf.allOf;

public final class CollectionMatchers {

    /**
     * Matches an {@link Iterable} that contains exactly the elements of the given collection
     * in the same order.
     */
    @Factory
    public static <E> Matcher<Iterable<? extends E>> containsElements(Collection<E> coll) {
        List<Matcher<? super E>> matchers = new ArrayList<Matcher<? super E>>();
        for (Object e : coll) {
            matchers.add(equalTo(e));
        }
        return Matchers.contains(matchers);
    }

    /**
     * Matches an {@link Iterable} that contains exactly the elements of the given collection
     * in any order.
     */
    @Factory
    public static <E> Matcher<Iterable<? extends E>> containsElementsInAnyOrder(Collection<E> coll) {
        List<Matcher<? super E>> matchers = new ArrayList<Matcher<? super E>>();
        for (Object e : coll) {
            matchers.add(equalTo(e));
        }
        return Matchers.containsInAnyOrder(matchers);
    }

    /**
     * Matches an {@link Iterable} that contains all elements of the given collection in any order.
     */
    @Factory
    public static <T> Matcher<Iterable<T>> hasItemsOf(Collection<T> items) {
        List<Matcher<? super Iterable<T>>> all = new ArrayList<Matcher<? super Iterable<T>>>(items.size());

        for (Object element : items) {
            // Doesn't forward to hasItem() method so compiler can sort out generics.
            all.add(new IsCollectionContaining<T>(equalTo(element)));
        }

        return allOf(all);
    }

    /**
     * Matches an {@link Iterable} that contains no duplicate elements.
     */
    @Factory
    public static <T, S extends Collection<T>> Matcher<? super S> hasNoDuplicates(Class<T> ofClass) {
        return new TypeSafeMatcher<S>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("without duplicates");
            }

            @Override
            public boolean matchesSafely(S item) {
                return new HashSet<T>(item).size() == item.size();
            }
        };
    }

    private CollectionMatchers() {
        // Prevent instantiation
    }

}
