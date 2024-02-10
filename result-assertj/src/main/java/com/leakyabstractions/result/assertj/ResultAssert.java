/*
 * Copyright 2024 Guillermo Calvo
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

package com.leakyabstractions.result.assertj;

import com.leakyabstractions.result.api.Result;

/**
 * Assertions for {@link Result}.
 *
 * @param <S> type of the success value contained in the {@link Result}.
 * @param <F> type of the failure value contained in the {@link Result}.
 * @author Guillermo Calvo
 */
public class ResultAssert<S, F> extends AbstractResultAssert<ResultAssert<S, F>, S, F> {

    ResultAssert(Result<S, F> actual) {
        super(actual, ResultAssert.class);
    }

    /**
     * Create assertion for {@link Result}.
     * <p>
     * This static method is provided for convenience, in case {@link ResultAssertions#assertThat(Result)} can't be
     * statically imported.
     *
     * @param <S> type of the success value contained in the {@link Result}.
     * @param <F> type of the failure value contained in the {@link Result}.
     * @param actual the actual value.
     * @return the created assertion object.
     */
    public static <S, F> ResultAssert<S, F> assertThatResult(Result<S, F> actual) {
        return new ResultAssert<>(actual);
    }
}
