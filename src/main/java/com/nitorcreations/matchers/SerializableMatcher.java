package com.nitorcreations.matchers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;

import org.hamcrest.Description;
import org.hamcrest.DiagnosingMatcher;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

public class SerializableMatcher extends DiagnosingMatcher<Object> {

    @Override
    protected boolean matches(Object item, Description mismatchDescription) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            try {
                oos.writeObject(item);
            } catch (NotSerializableException nse) {
                mismatchDescription.appendText("not serializable");
                return false;
            } finally {
                oos.close();
            }

        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("serializable");
    }

    @Factory
    public static Matcher<Object> serializable() {
        return new SerializableMatcher();
    }
}
