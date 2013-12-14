package com.nitorcreations.matchers.test;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Test;

import static com.nitorcreations.matchers.test.DescriptionMatcher.hasDescription;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

public class DescriptionMatcherTest {

    @Test
    public void matches_correctDescription() {
        String description = "my description";
        assertThat(new MatcherWithDescription(description), hasDescription(is(description)));
    }

    @Test
    public void doesNotMatch_incorrectDescription() {
        assertThat(new MatcherWithDescription("not this"), not(hasDescription(is("description"))));
    }

    @Test
    public void hasCorrectDescription() {
        assertThat(hasDescription(is("foo")).toString(), is("matcher whose description is \"foo\""));
    }
    
    private static class MatcherWithDescription extends BaseMatcher<Object> {

        private final String description;

        public MatcherWithDescription(String description) {
            this.description = description;
        }

        @Override
        public boolean matches(Object item) {
            return true;
        }

        @Override
        public void describeTo(Description description) {
            description.appendText(this.description);
        }
    }
}
