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

import javax.annotation.Nullable;

/**
 * A <a href="http://en.wikipedia.org/wiki/BK-tree">BK-tree</a>.
 *
 * @param <E> type of elements in this tree
 */
public interface BkTree<E> {

    /** Returns the metric for elements in this tree. */
    Metric<? super E> getMetric();

    /** Returns the root node of this tree. */
    Node<E> getRoot();

    /**
     * A node in a {@link BkTree}.
     *
     * @param <E> type of elements in the tree to which this node belongs
     */
    interface Node<E> {
        /** Returns the element in this node. */
        E getElement();

        /** Returns the child node at the given distance, if any. */
        @Nullable Node<E> getChildNode(int distance);
    }
}
