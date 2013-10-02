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

import edu.gatech.gtri.stringmetric.StringMetric;

/**
 * Useful {@link Metric}s.
 */
public class Metrics {

    private Metrics() {}

    /** Returns a {@link Metric} that delegates to the given {@link StringMetric}. */
    public static Metric<CharSequence> charSequenceMetric(final StringMetric stringMetric) {
        return new Metric<CharSequence>() {
            @Override
            public int distance(CharSequence x, CharSequence y) {
                return stringMetric.distance(x, y);
            }
        };
    }
}
