package com.nitorcreations.matchers;

import static com.nitorcreations.matchers.FieldMatcher.hasField;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.mockito.Mockito;

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
    public void doesNotExplodeOnNull() {
        assertThat(myInstance, not(hasField(null)));
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

    @SuppressWarnings("unchecked")
    @Test
    public void testIllegalAccess() {
        Matcher<String> badMatcher = Mockito.mock(Matcher.class);
        when(badMatcher.matches(any(String.class))).thenThrow(IllegalAccessException.class);
        
        assertThat(myInstance, not(hasField("myField", badMatcher)));
    }
    
    
    @Test
    public void worksWithHasItem() {
        TestHelper foo = new TestHelper();
        foo.myField = "foo";
        TestHelper bar = new TestHelper();
        bar.myField = "bar";
        TestHelper baz = new TestHelper();
        baz.myField = "baz";
        List<TestHelper> list = Arrays.asList(foo,bar,baz);
        
        assertThat(list, hasItem(FieldMatcher.<TestHelper>hasField("myField", equalTo("bar"))));
    }

    @SuppressWarnings("unused")
    private static class TestHelper {
        public String myField;
        private String myPrivateField;
        public static String myStaticField = "staticValue";
    }
}
