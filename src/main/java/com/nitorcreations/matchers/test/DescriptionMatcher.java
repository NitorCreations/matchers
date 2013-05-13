package com.nitorcreations.matchers.test;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public class DescriptionMatcher extends TypeSafeDiagnosingMatcher<Matcher<?>> {
    private final Matcher<?> descriptionMatcher;

    public DescriptionMatcher(Matcher<?> description) {
        this.descriptionMatcher = description;
    }

    @Override
    protected boolean matchesSafely(Matcher<?> item, Description mismatchDescription) {
        StringDescription desc = new StringDescription();
        item.describeTo(desc);
        descriptionMatcher.describeMismatch(item, mismatchDescription);
        return descriptionMatcher.matches(desc.toString());
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("matcher whose description ").appendDescriptionOf(descriptionMatcher);
    }

    @Factory
    public static Matcher<Matcher<?>> hasDescription(Matcher<?> description) {
        return new DescriptionMatcher(description);
    }
}
