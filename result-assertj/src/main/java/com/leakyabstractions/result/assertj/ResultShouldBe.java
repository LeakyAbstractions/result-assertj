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

import org.assertj.core.error.BasicErrorMessageFactory;

import com.leakyabstractions.result.api.Result;

/**
 * Build error message when a {@link Result} should be a success or a failure.
 *
 * @author <a href="https://guillermo.dev/">Guillermo Calvo</a>
 */
class ResultShouldBe extends BasicErrorMessageFactory {

    private static final String EXPECTING_SUCCESS = "%nExpecting result:%n  <%s>%nto be a success but was not.";
    private static final String EXPECTING_FAILURE = "%nExpecting result:%n  <%s>%nto be a failure but was not.";

    private ResultShouldBe(String message, Result<?, ?> result) {
        super(message, result);
    }

    /**
     * Indicates that a {@link Result} should be a success.
     *
     * @return a error message factory.
     * @param result the result instance
     */
    static ResultShouldBe shouldBeSuccess(Result<?, ?> result) {
        return new ResultShouldBe(EXPECTING_SUCCESS, result);
    }

    /**
     * Indicates that a {@link Result} should be a failure.
     *
     * @return a error message factory.
     * @param result the result instance
     */
    static ResultShouldBe shouldBeFailure(Result<?, ?> result) {
        return new ResultShouldBe(EXPECTING_FAILURE, result);
    }
}
