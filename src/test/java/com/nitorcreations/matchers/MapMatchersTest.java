package com.nitorcreations.matchers;

import java.util.HashMap;

import net.sf.qualitytest.CoverageForPrivateConstructor;

import org.junit.Test;

import static com.nitorcreations.matchers.MapMatchers.emptyMap;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

public class MapMatchersTest {
    @Test
    public void emptyMapMatchesEmpty() {
        assertThat(new HashMap<String, Object>(), emptyMap());
    }

    @Test
    public void emptyMapDoesNotMatchNonEmpty() {
        HashMap<String, Object> actual = new HashMap<String, Object>();
        actual.put("Foo", 1);
        assertThat(actual, not(emptyMap()));
    }

    @Test
    public void hasCorrectDescription() {
        assertThat(emptyMap().toString(), is("empty map"));
    }
    
    @Test
    public void coverPrivateConstructor() {
        CoverageForPrivateConstructor.giveMeCoverage(MapMatchers.class);
    }

}
