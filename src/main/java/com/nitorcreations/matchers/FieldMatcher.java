package com.nitorcreations.matchers;

import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public final class FieldMatcher<T> extends TypeSafeDiagnosingMatcher<T> {
    private final String fieldName;
    private final Matcher<?> valueMatcher;

    public FieldMatcher(String fieldName, Matcher<?> valueMatcher) {
        this.fieldName = fieldName;
        this.valueMatcher = valueMatcher;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("has field \"").appendText(fieldName).appendText("\"");
        if (valueMatcher != null) {
            description.appendText(" with value ").appendDescriptionOf(valueMatcher);
        }
    }

    @Override
    protected boolean matchesSafely(T item, Description mismatchDescription) {
        Field field = findField(item.getClass(), fieldName);
        if (field == null)
            return false;
        if (valueMatcher == null)
            return true;
        try {
            return valueMatcher.matches(field.get(item));
        } catch (IllegalAccessException e) {
            return false;
        }
    }

    private static Field findField(Class<?> clazz, String name) {
        Class<?> searchType = clazz;
        while (!Object.class.equals(searchType)) {
            Field[] fields = searchType.getDeclaredFields();
            for (Field field : fields) {
                if (StringUtils.equals(name,field.getName())) {
                    return field;
                }
            }
            searchType = searchType.getSuperclass();
        }
        return null;
    }

    /**
     * Creates a matcher that matches when the examined object has a field
     * with the specified name.
     * <p/>
     * For example:
     * <pre>assertThat(myBean, hasField("foo")</pre>
     */
    @Factory
    public static <T> FieldMatcher<T> hasField(String fieldName) {
        return new FieldMatcher<T>(fieldName, null);
    }

    /**
     * Creates a matcher that matches when the examined object has a field
     * with the specified name whose value satisfies the specified matcher.
     * <p/>
     * For example:
     * <pre>assertThat(myBean, hasField("foo", equalTo("bar"))</pre>
     */
    @Factory
    public static <T> FieldMatcher<T> hasField(String fieldName, Matcher<?> withValue) {
        return new FieldMatcher<T>(fieldName, withValue);
    }
}
