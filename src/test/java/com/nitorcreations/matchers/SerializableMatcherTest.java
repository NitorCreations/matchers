package com.nitorcreations.matchers;

import java.io.Serializable;

import org.junit.Test;

import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

public class SerializableMatcherTest {

    @Test
    public void matchesSerializable() {
        TestSerializable ts = new TestSerializable();
        ts.setField("field");
        assertThat(ts, SerializableMatcher.serializable());
    }

    @Test
    public void doesNotMatchNotSerializable() {
        TestSerializable ts = new TestSerializable() {
            private static final long serialVersionUID = -7151331372959811626L;

            @Override
            public String getField() {
                return "foobar";
            }
        };
        assertThat(ts, not(SerializableMatcher.serializable()));
    }

    @SuppressWarnings("UnusedDeclaration")
    private static class TestSerializable implements Serializable {
        private static final long serialVersionUID = 7735266069597063313L;

        public TestSerializable() {}

        private String field;

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }
    }
}
