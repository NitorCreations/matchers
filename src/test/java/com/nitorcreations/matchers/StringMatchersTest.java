package com.nitorcreations.matchers;

import net.sf.qualitytest.CoverageForPrivateConstructor;

import org.junit.Test;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

import static com.nitorcreations.matchers.StringMatchers.containsIgnoreCase;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

public class StringMatchersTest {

    @Test
    public void containsIgnoreCaseMatches() {
        assertThat("FooBarBaz", containsIgnoreCase("oobarbaz"));
    }

    @Test
    public void containsIgnoreCaseDoesNotMatch_notMatching() {
        assertThat("foo", not(containsIgnoreCase("fooz")));
    }

    @Test
    public void containsIgnoreCaseDoesNotMatch_nullNeedle() {
        assertThat("foo", not(containsIgnoreCase(null)));
    }

    @Test
    public void containsIgnoreCaseDoesNotMatch_nullHaystack() {
        assertThat(null, not(containsIgnoreCase("fooz")));
    }

    @Test
    public void hasCorrectDescription() {
        assertThat(containsIgnoreCase("foo").toString(), is("contains ignoring case \"foo\""));
    }
    
    @Test
    public void coverPrivateConstructor() {
        CoverageForPrivateConstructor.giveMeCoverage(StringMatchers.class);
    }
}
