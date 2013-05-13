package com.nitorcreations.matchers;

import org.junit.Test;

import static com.nitorcreations.matchers.StringMatchers.containsIgnoreCase;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

public class StringMatchersTest {

    @Test
    public void containsIgnoreCaseMatches() {
        assertThat("FooBarBaz", containsIgnoreCase("oobarbaz"));
    }

    @Test
    public void containsIgnoreCaseDoesNotMatch_notMatching() {
        assertThat("foo", not(containsIgnoreCase("fooz")));
    }

    @Test
    public void containsIgnoreCaseDoesNotMatch_nullNeedle() {
        assertThat("foo", not(containsIgnoreCase(null)));
    }

    @Test
    public void containsIgnoreCaseDoesNotMatch_nullHaystack() {
        assertThat(null, not(containsIgnoreCase("fooz")));
    }

}
