package com.nitorcreations.matchers;

import static com.nitorcreations.matchers.FieldMatcher.hasField;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class FieldMatcherTest {

    MyClass myInstance = new MyClass();

    @Test
    public void findsField() {
        assertThat(myInstance, hasField("myField"));
    }

    @Test
    public void doesNotFindImaginaryFields() {
        assertThat(myInstance, not(hasField("noSuchField")));
    }

    @Test
    public void findsFieldAndMatchesValue() {
        myInstance.myField = "foo";
        assertThat(myInstance, hasField("myField", equalTo("foo")));
    }

    @Test
    public void doesNotFindFieldWithWrongValue() {
        myInstance.myField = "bar";
        assertThat(myInstance, not(hasField("myField", equalTo("foo"))));
    }

    public static class MyClass {
        public String myField;
    }
}
