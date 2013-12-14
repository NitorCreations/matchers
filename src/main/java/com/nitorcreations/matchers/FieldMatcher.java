package com.nitorcreations.matchers;

import java.lang.reflect.Field;

import org.hamcrest.Description;
import org.hamcrest.DiagnosingMatcher;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

public final class FieldMatcher extends DiagnosingMatcher<Object> {

    private final String fieldName;
    private final Matcher<?> valueMatcher;

    public FieldMatcher(String fieldName, Matcher<?> valueMatcher) {
        this.fieldName = fieldName;
        this.valueMatcher = valueMatcher;
    }
    
    @Override
    public void describeTo(Description description) {
        description.appendText("has field ").appendText(fieldName);
        if (valueMatcher != null) {
            description.appendText(" with value ").appendDescriptionOf(valueMatcher);
        }
    }

    @Override
    protected boolean matches(Object item, Description mismatchDescription) {
        Field field = findField(item.getClass(), fieldName);
        if (field == null) return false;
        if (valueMatcher == null) return true;
        try {
            return valueMatcher.matches(field.get(item));
        } catch (IllegalArgumentException e) {
            return false;
        } catch (IllegalAccessException e) {
            return false;
        }
    }

    private static Field findField(Class<?> clazz, String name) {
        Class<?> searchType = clazz;
        while (!Object.class.equals(searchType) && searchType != null) {
            Field[] fields = searchType.getDeclaredFields();
            for (Field field : fields) {
                if (name == null || name.equals(field.getName())) {
                    return field;
                }
            }
            searchType = searchType.getSuperclass();
        }
        return null;
    }

    @Factory
    public static FieldMatcher hasField(String fieldName) {
        return new FieldMatcher(fieldName, null);
    }
    
    @Factory
    public static FieldMatcher hasField(String fieldName, Matcher<?> withValue) {
        return new FieldMatcher(fieldName, withValue);
    }
    
}