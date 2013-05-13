package com.nitorcreations.matchers;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

/**
 * Uses {@link org.apache.commons.lang3.builder.EqualsBuilder#reflectionEquals(Object, Object, java.util.Collection)}
 * to compare the matched item with the given parameter. Excludes fields given in {@code excludedFields} from comparison.
 *
 * @param <T> the type of the base object to match
 */
public final class ReflectionEqualsMatcher<T> extends TypeSafeDiagnosingMatcher<T> {
    private final String[] excludedFields;

    private final T object;

    private ReflectionEqualsMatcher(T object, String... excludedFields) {
        this.excludedFields = excludedFields;
        this.object = object;
    }

    @Factory
    public static <T> Matcher<T> reflectEquals(T target, String... excludedFields) {
        return new ReflectionEqualsMatcher<T>(target, excludedFields);
    }

    @Override
    protected boolean matchesSafely(T item, Description mismatchDescription) {
        if (EqualsBuilder.reflectionEquals(item, object, excludedFields)) {
            return true;
        } else {
            mismatchDescription.appendText("was: ").appendText(getStringRepresentation(item, excludedFields));
            return false;
        }
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("reflectively equal to: " + getStringRepresentation(object, excludedFields));
    }

    private String getStringRepresentation(Object o, String... excludedFields) {
        return new ReflectionToStringBuilder(o, ToStringStyle.MULTI_LINE_STYLE).setExcludeFieldNames(
                excludedFields).toString();
    }
}
