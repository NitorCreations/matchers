package com.nitorcreations.matchers;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

public class ReflectionEqualsMatcherTest {

    private TestObject one = new TestObject("One", 1);
    private TestObject one2 = new TestObject("One", 1);
    private TestObject two = new TestObject("One", 2);

    @Test
    public void equalFieldsMatches() {
        assertThat(one, not(equalTo(one2)));
        assertThat(one, ReflectionEqualsMatcher.reflectEquals(one2));
    }

    @Test
    public void nonEqualFieldsDoesNotMatch() {
        assertThat(one, not(ReflectionEqualsMatcher.reflectEquals(two)));
    }

    @Test
    public void excludedNonEqualFieldsMatches() {
        assertThat(one, ReflectionEqualsMatcher.reflectEquals(two, "num"));
    }

    private class TestObject {
        final String str;
        final int num;

        private TestObject(String str, int num) {
            this.str = str;
            this.num = num;
        }
    }
}
