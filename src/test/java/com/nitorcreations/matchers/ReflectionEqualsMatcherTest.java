package com.nitorcreations.matchers;

import org.junit.Test;

import static com.nitorcreations.matchers.ReflectionEqualsMatcher.reflectEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
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
        assertThat(one, reflectEquals(one2));
    }

    @Test
    public void nonEqualFieldsDoesNotMatch() {
        assertThat(one, not(reflectEquals(two)));
    }

    @Test
    public void excludedNonEqualFieldsMatches() {
        assertThat(one, reflectEquals(two, "num"));
    }

    @Test
    public void hasCorrectDescription() {
        assertThat(reflectEquals(one).toString(), startsWith("reflectively equal to:"));
    }
    
    @SuppressWarnings("unused")
    private class TestObject {
        final String str;
        final int num;

        private TestObject(String str, int num) {
            this.str = str;
            this.num = num;
        }
    }
}
