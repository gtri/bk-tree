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

/**
 * {@linkplain edu.gatech.gtri.bktree.BkTree BK-trees} offer a simple index of
 * elements in a {@linkplain edu.gatech.gtri.bktree.Metric metric space} that
 * allows for {@linkplain edu.gatech.gtri.bktree.BkTreeSearcher searching} the
 * tree for elements within a certain {@linkplain edu.gatech.gtri.bktree.Metric
 * distance} of the search query with sub-linear efficiency.
 *
 * <p>For example, a BK-tree with {@link java.lang.String} elements and a metric like the
 * <a href="http://en.wikipedia.org/wiki/Damerau%E2%80%93Levenshtein_distance">Damerau–Levenshtein
 * distance</a> can serve as a <a href="http://en.wikipedia.org/wiki/Approximate_string_matching">fuzzy
 * search</a> index.
 */
package edu.gatech.gtri.bktree;
