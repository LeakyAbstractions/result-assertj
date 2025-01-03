/*
 * Copyright 2025 Guillermo Calvo
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

import org.assertj.core.api.SoftAssertions;

import com.leakyabstractions.result.api.Result;

/**
 * Soft assertions for {@link Result}.
 *
 * @author <a href="https://guillermo.dev/">Guillermo Calvo</a>
 */
public class ResultSoftAssertions extends SoftAssertions {

    /**
     * Create soft assertion for {@link Result}.
     *
     * @param <S> type of the success value contained in the {@link Result}.
     * @param <F> type of the failure value contained in the {@link Result}.
     * @param actual the actual value.
     * @return the created soft assertion object.
     */
    @SuppressWarnings("unchecked")
    public <S, F> ResultAssert<S, F> assertThat(Result<S, F> actual) {
        return this.proxy(ResultAssert.class, Result.class, actual);
    }
}
