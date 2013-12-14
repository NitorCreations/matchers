package com.nitorcreations.matchers;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import static com.nitorcreations.matchers.IteratorMatcher.iteratorThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

public class IteratorMatcherTest {

    @Test
    public void matchesIfMatcherMatches() {
        assertThat(Arrays.asList(1, 2).listIterator(), iteratorThat(contains(1, 2)));
    }

    @Test
    public void canMatchEmptyCollection() {
        assertThat(new ArrayList<String>().listIterator(), iteratorThat(emptyCollectionOf(String.class)));
    }

    @Test
    public void doesNotMatchIfMatcherDoesNotMatch() {
        assertThat(Arrays.asList(1, 2, 3).listIterator(), not(iteratorThat(contains(1))));
    }

    @Test
    public void hasCorrectDescription() {
        assertThat(iteratorThat(contains(1, 2)).toString(), is("iterator of iterable containing [<1>, <2>]"));
        assertThat(iteratorThat(emptyCollectionOf(String.class)).toString(), is("iterator of an empty collection"));
    }
    
    
  

}
