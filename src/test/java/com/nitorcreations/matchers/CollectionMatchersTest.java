package com.nitorcreations.matchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import static com.nitorcreations.matchers.CollectionMatchers.containsElements;
import static com.nitorcreations.matchers.CollectionMatchers.containsElementsInAnyOrder;
import static com.nitorcreations.matchers.CollectionMatchers.hasItemsOf;
import static com.nitorcreations.matchers.CollectionMatchers.hasNoDuplicates;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class CollectionMatchersTest {

    static final String S1 = "String one";
    static final String S2 = "String two";
    static final String S3 = "String three";
    static final List<String> COLL = Arrays.asList(S1, S2);

    @Test
    public void containsElementsMatchesCorrectOrder() {
        assertTrue(containsElements(asList(S1, S2)).matches(COLL));
    }

    @Test
    public void containsElementsDoesNotMatchOnlyOneElement() {
        assertFalse(containsElements(asList(S1)).matches(COLL));
    }

    @Test
    public void containsElementsDoesNotMatchWrongOrder() {
        assertFalse(containsElements(asList(S2, S1)).matches(COLL));
    }

    @Test
    public void containsElementsDoesNotMatchAdditionalItem() {
        assertFalse(containsElements(asList(S1, S2, S3)).matches(COLL));
    }

    @Test
    public void containsElementsInAnyOrder_otherOrder() {
        assertTrue(containsElementsInAnyOrder(asList(S2, S1)).matches(COLL));
    }

    @Test
    public void containsElementsInAnyOrder_missingElement() {
        assertFalse(containsElementsInAnyOrder(asList(S2)).matches(COLL));
    }

    @Test
    public void containsElementsInAnyOrder_additionalElement() {
        assertFalse(containsElementsInAnyOrder(asList(S2, S1, S3)).matches(COLL));
    }

    @Test
    public void hasItemsOfMatchesBothElementsInAnyOrder() {
        assertTrue(hasItemsOf(asList(S2, S1)).matches(COLL));
        assertTrue(hasItemsOf(asList(S1, S2)).matches(COLL));
    }

    @Test
    public void hasItemsOfMatchesOnlyOneItem() {
        assertTrue(hasItemsOf(asList(S1)).matches(COLL));
    }

    @Test
    public void hasItemsOfDoesNotMatchAdditionalItem() {
        assertFalse(hasItemsOf(asList(S3)).matches(COLL));
    }

    @Test
    public void hasItemsOfMatchesEmptyCollection() {
        assertTrue(hasItemsOf(new ArrayList<String>()).matches(COLL));
    }

    @Test
    public void hasNoDuplicatesMatchesListWithNoDuplicates() {
        assertThat(Arrays.asList(1, 2, 3, 4), hasNoDuplicates(Integer.class));
    }

    @Test
    public void hasNoDuplicatesDoesNotMatchListWithDuplicates() {
        assertThat(Arrays.asList(1, 2, 3, 4, 4), not(hasNoDuplicates(Integer.class)));
    }

}
