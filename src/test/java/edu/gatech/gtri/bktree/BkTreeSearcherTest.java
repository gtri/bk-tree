/*
 * Copyright 2013 Georgia Tech Applied Research Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.gatech.gtri.bktree;

import com.google.common.collect.ImmutableSet;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static edu.gatech.gtri.bktree.BkTreeSearcher.Match;
import static org.testng.Assert.*;

public class BkTreeSearcherTest {

    BkTreeSearcher<String> searcher;

    @BeforeMethod
    public void setUp() throws Exception {
        MutableBkTree<String> tree = new MutableBkTree<>(new LengthDifference());
        tree.addAll(
            "book",
            "books",
            "nook",
            "nooks",
            "b",
            "boo",
            "bo",
            "bookies"
        );

        searcher = new BkTreeSearcher<>(tree);
    }

    @Test(expectedExceptions = IllegalMetricException.class)
    public void search_encountering_element_with_negative_distance_to_query_should_throw() throws Exception {
        MutableBkTree<Object> tree = new MutableBkTree<>(new NegativeOneMetric());
        tree.add(new Object());
        new BkTreeSearcher<>(tree).search(new Object(), 0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void search_negative_distance_should_throw() throws Exception {
        searcher.search("book", -1);
    }

    @Test
    public void search_b_distance_0_should_match_only_itself() throws Exception {
        assertEquals(searcher.search("b", 0), ImmutableSet.of(
            new Match<>("b", 0)
        ));
    }

    @Test
    public void search_distance_0_should_match_elements_at_distance_0() throws Exception {
        assertEquals(searcher.search("hook", 0), ImmutableSet.of(
            new Match<>("book", 0),
            new Match<>("nook", 0)
        ));
    }

    @Test
    public void search_distance_1_should_match_elements_at_or_closer_than_distance_1() throws Exception {
        assertEquals(searcher.search("hook", 1), ImmutableSet.of(
            new Match<>("book", 0),
            new Match<>("books", 1),
            new Match<>("nook", 0),
            new Match<>("nooks", 1),
            new Match<>("boo", 1)
        ));
    }

    @Test
    public void search_distance_2_should_match_elements_at_or_closer_than_distance_2() throws Exception {
        assertEquals(searcher.search("hook", 2), ImmutableSet.of(
            new Match<>("book", 0),
            new Match<>("books", 1),
            new Match<>("nook", 0),
            new Match<>("nooks", 1),
            new Match<>("boo", 1),
            new Match<>("bo", 2)
        ));
    }

    @Test
    public void search_distance_3_should_match_elements_at_or_closer_than_distance_3() throws Exception {
        assertEquals(searcher.search("hook", 3), ImmutableSet.of(
            new Match<>("book", 0),
            new Match<>("books", 1),
            new Match<>("nook", 0),
            new Match<>("nooks", 1),
            new Match<>("b", 3),
            new Match<>("boo", 1),
            new Match<>("bo", 2),
            new Match<>("bookies", 3)
        ));
    }

    @Test
    public void search_empty_string_distance_0_should_match_nothing() throws Exception {
        assertEquals(searcher.search("", 0), ImmutableSet.of());
    }

    @Test
    public void search_empty_string_distance_1_should_match_length_1_strings() throws Exception {
        assertEquals(searcher.search("", 1), ImmutableSet.of(
            new Match<>("b", 1)
        ));
    }

    @Test
    public void search_empty_string_distance_2_should_match_length_2_or_less_strings() throws Exception {
        assertEquals(searcher.search("", 2), ImmutableSet.of(
            new Match<>("b", 1),
            new Match<>("bo", 2)
        ));
    }

    @Test
    public void search_empty_string_distance_3_should_match_length_3_or_less_strings() throws Exception {
        assertEquals(searcher.search("", 3), ImmutableSet.of(
            new Match<>("b", 1),
            new Match<>("bo", 2),
            new Match<>("boo", 3)
        ));
    }

    @Test
    public void search_empty_string_distance_4_should_match_length_4_or_less_strings() throws Exception {
        assertEquals(searcher.search("", 4), ImmutableSet.of(
            new Match<>("b", 1),
            new Match<>("bo", 2),
            new Match<>("boo", 3),
            new Match<>("book", 4),
            new Match<>("nook", 4)
        ));
    }

    @Test
    public void search_empty_string_distance_5_should_match_length_5_or_less_strings() throws Exception {
        assertEquals(searcher.search("", 5), ImmutableSet.of(
            new Match<>("b", 1),
            new Match<>("bo", 2),
            new Match<>("boo", 3),
            new Match<>("book", 4),
            new Match<>("nook", 4),
            new Match<>("books", 5),
            new Match<>("nooks", 5)
        ));
    }

    @Test
    public void search_empty_string_distance_6_should_match_length_6_or_less_strings() throws Exception {
        assertEquals(searcher.search("", 6), ImmutableSet.of(
            new Match<>("b", 1),
            new Match<>("bo", 2),
            new Match<>("boo", 3),
            new Match<>("book", 4),
            new Match<>("nook", 4),
            new Match<>("books", 5),
            new Match<>("nooks", 5)
        ));
    }

    @Test
    public void search_empty_string_distance_7_should_match_length_7_or_less_strings() throws Exception {
        assertEquals(searcher.search("", 7), ImmutableSet.of(
            new Match<>("b", 1),
            new Match<>("bo", 2),
            new Match<>("boo", 3),
            new Match<>("book", 4),
            new Match<>("nook", 4),
            new Match<>("books", 5),
            new Match<>("nooks", 5),
            new Match<>("bookies", 7)
        ));
    }

    @Test
    public void search_empty_string_distance_8_should_length_8_or_less_strings() throws Exception {
        assertEquals(searcher.search("", 8), ImmutableSet.of(
            new Match<>("b", 1),
            new Match<>("bo", 2),
            new Match<>("boo", 3),
            new Match<>("book", 4),
            new Match<>("nook", 4),
            new Match<>("books", 5),
            new Match<>("nooks", 5),
            new Match<>("bookies", 7)
        ));
    }

}
