package com.nitorcreations.matchers;

import static com.nitorcreations.matchers.FieldMatcher.hasField;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class FieldMatcherTest {

    TestHelper myInstance = new TestHelper();

    @Test
    public void findsField() {
        assertThat(myInstance, hasField("myField"));
    }

    @Test
    public void findsStaticField() {
        assertThat(myInstance, hasField("myStaticField"));
    }

    @Test
    public void doesNotFindImaginaryField() {
        assertThat(myInstance, not(hasField("noSuchField")));
    }

    @Test
    public void findsPrivateField() {
        assertThat(myInstance, hasField("myPrivateField"));
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

    @Test
    public void hasCorrectDescription() {
        assertThat(hasField("myField").toString(), is("has field \"myField\""));
        assertThat(hasField("myField", equalTo("foo")).toString(), is("has field \"myField\" with value \"foo\""));
    }

    @SuppressWarnings("unused")
    private static class TestHelper {
        public String myField;
        private String myPrivateField;
        public static String myStaticField = "staticValue";
    }
}
