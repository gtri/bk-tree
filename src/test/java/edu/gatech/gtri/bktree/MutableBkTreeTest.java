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

import org.testng.annotations.Test;

import static edu.gatech.gtri.bktree.MutableBkTree.MutableNode;
import static org.testng.Assert.*;

public class MutableBkTreeTest {

    @Test
    public void add_should_result_in_correct_tree_structure() throws Exception {
        LengthDifference distance = new LengthDifference();
        MutableBkTree<String> tree = new MutableBkTree<>(distance);
        tree.add("book");
        tree.add("books");
        tree.add("bookies");
        tree.add("nook");
        tree.add("nooks");
        tree.add("noik");
        tree.add("noo");
        tree.add("roo");
        tree.add("roo");

        MutableNode<String> book = new MutableNode<>("book");
        MutableNode<String> books = new MutableNode<>("books");
        book.childrenByDistance.put(1, books);
        MutableNode<String> bookies = new MutableNode<>("bookies");
        book.childrenByDistance.put(3, bookies);
        MutableNode<String> nook = new MutableNode<>("nook");
        book.childrenByDistance.put(0, nook);
        MutableNode<String> nooks = new MutableNode<>("nooks");
        books.childrenByDistance.put(0, nooks);
        MutableNode<String> noik = new MutableNode<>("noik");
        nook.childrenByDistance.put(0, noik);
        MutableNode<String> noo = new MutableNode<>("noo");
        books.childrenByDistance.put(2, noo);
        MutableNode<String> roo = new MutableNode<>("roo");
        noo.childrenByDistance.put(0, roo);

        MutableBkTree<String> expected = new MutableBkTree<>(distance);
        expected.root = book;

        assertEquals(tree, expected);
    }

    @Test(expectedExceptions = IllegalMetricException.class)
    public void add_two_elements_with_negative_distance_should_throw() throws Exception {
        MutableBkTree<Object> tree = new MutableBkTree<>(new NegativeOneMetric());
        tree.add(new Object());
        tree.add(new Object());
    }

}
