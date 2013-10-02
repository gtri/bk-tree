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

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import static edu.gatech.gtri.bktree.BkTree.Node;
import static java.lang.Math.max;
import static java.lang.String.format;

/**
 * Searches a {@link BkTree}.
 *
 * @param <E> type of elements in the searched tree
 */
public final class BkTreeSearcher<E> {

    private final BkTree<E> tree;

    /**
     * Constructs a searcher that orders matches in increasing order of
     * distance from the query.
     *
     * @param tree tree to search
     */
    public BkTreeSearcher(BkTree<E> tree) {
        if (tree == null) throw new NullPointerException();
        this.tree = tree;
    }

    /**
     * Searches the tree for elements whose distance from the given query
     * is less than or equal to the given maximum distance.
     *
     * @param query query against which to match tree elements
     * @param maxDistance non-negative maximum distance of matching elements from query
     * @return matching elements in no particular order
     */
    public Set<Match<? extends E>> search(E query, int maxDistance) {
        if (query == null) throw new NullPointerException();
        if (maxDistance < 0) throw new IllegalArgumentException("maxDistance must be non-negative");

        Metric<? super E> metric = tree.getMetric();

        Set<Match<? extends E>> matches = new HashSet<>();

        Queue<Node<E>> queue = new ArrayDeque<>();
        queue.add(tree.getRoot());

        while (!queue.isEmpty()) {
            Node<E> node = queue.remove();
            E element = node.getElement();

            int distance = metric.distance(element, query);
            if (distance < 0) {
                throw new IllegalMetricException(
                    format("negative distance (%d) defined between element `%s` and query `%s`",
                        distance, element, query));
            }

            if (distance <= maxDistance) {
                matches.add(new Match<>(element, distance));
            }

            int minSearchDistance = max(distance - maxDistance, 0);
            int maxSearchDistance = distance + maxDistance;

            for (int searchDistance = minSearchDistance; searchDistance <= maxSearchDistance; ++searchDistance) {
                Node<E> childNode = node.getChildNode(searchDistance);
                if (childNode != null) {
                    queue.add(childNode);
                }
            }
        }

        return matches;
    }

    /** Returns the tree searched by this searcher. */
    public BkTree<E> getTree() {
        return tree;
    }

    /**
     * An element matching a query.
     *
     * @param <E> type of matching element
     */
    public static final class Match<E> {

        private final E match;
        private final int distance;

        /**
         * @param match matching element
         * @param distance distance of the matching element from the search query
         */
        public Match(E match, int distance) {
            if (match == null) throw new NullPointerException();
            if (distance < 0) throw new IllegalArgumentException("distance must be non-negative");

            this.match = match;
            this.distance = distance;
        }

        /** Returns the matching element. */
        public E getMatch() {
            return match;
        }

        /** Returns the matching element's distance from the search query. */
        public int getDistance() {
            return distance;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Match that = (Match) o;

            if (distance != that.distance) return false;
            if (!match.equals(that.match)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = match.hashCode();
            result = 31 * result + distance;
            return result;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Match{");
            sb.append("match=").append(match);
            sb.append(", distance=").append(distance);
            sb.append('}');
            return sb.toString();
        }
    }
}
