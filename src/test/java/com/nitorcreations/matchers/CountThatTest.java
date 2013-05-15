package com.nitorcreations.matchers;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import static com.nitorcreations.matchers.CountThat.countThat;
import static com.nitorcreations.matchers.test.DescriptionMatcher.hasDescription;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

public class CountThatTest {

    public static final List<Integer> INTEGER_LIST = Arrays.asList(1, 2, 3, 4, 5);

    @Test
    public void matchesCorrectList() {
        assertThat(INTEGER_LIST, countThat(greaterThan(3), 2));
    }

    @Test
    public void doesNotMatchIncorrectCount() {
        assertThat(INTEGER_LIST, not(countThat(greaterThan(3), lessThan(1))));
    }

    @Test
    public void description() {
        assertThat(countThat(greaterThan(3), 5), hasDescription(
                is("iterable containing <5> items that a value greater than <3>")));
    }

}
