package com.nitorcreations.matchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import static com.nitorcreations.matchers.test.DescriptionMatcher.hasDescription;
import static com.nitorcreations.matchers.HasItemAtIndex.hasFirstItem;
import static com.nitorcreations.matchers.HasItemAtIndex.hasItemAtIndex;
import static com.nitorcreations.matchers.HasItemAtIndex.hasLastItem;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

public class HasItemAtIndexTest {

    @Test
    public void matchesListOfLengthOne() {
        List<Integer> list = Arrays.asList(1);

        assertThat(list, hasFirstItem(equalTo(1)));
        assertThat(list, hasLastItem(equalTo(1)));
        assertThat(list, hasItemAtIndex(0, equalTo(1)));
    }

    @Test
    public void doesNotMatchIfInnerNotMatches() {
        List<Integer> list = Arrays.asList(1);

        assertThat(list, not(hasFirstItem(not(1))));
        assertThat(list, not(hasLastItem(not(1))));
        assertThat(list, not(hasItemAtIndex(0, not(1))));
    }

    @Test
    public void doesNotMatchIfIndexOutOfBounds() {
        List<Integer> list = Arrays.asList(1);
        assertThat(list, not(hasItemAtIndex(1, any(Integer.class))));
    }

    @Test
    public void doesNotMatchEmptyList() {
        List<Integer> list = new ArrayList<Integer>();

        assertThat(list, not(hasFirstItem(any(Integer.class))));
        assertThat(list, not(hasLastItem(any(Integer.class))));
        assertThat(list, not(hasItemAtIndex(0, any(Integer.class))));
    }

    @Test
    public void describeToForLastItem() {
        assertThat(hasLastItem(any(Integer.class)), hasDescription(
                equalTo("iterable with last item: an instance of java.lang.Integer")
        ));
    }

    @Test
    public void describeToForItemAtIndex() {
        assertThat(hasItemAtIndex(0, any(String.class)), hasDescription(
                equalTo("iterable with item at index 0: an instance of java.lang.String")
        ));
    }
}
