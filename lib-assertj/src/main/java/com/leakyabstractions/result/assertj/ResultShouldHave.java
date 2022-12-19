/*
 * Copyright 2022 Guillermo Calvo
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

import com.leakyabstractions.result.Result;

/**
 * Build error message when a {@link Result} should have a specific success/failure value.
 *
 * @author Guillermo Calvo
 */
class ResultShouldHave extends BasicErrorMessageFactory {

    private static final String EXPECTING_VALUE_BUT_DIFFERENT = "%nExpecting result:%n  <%s>%nto contain:%n  <%s>%nbut did contain:%n  <%s>.";
    private static final String EXPECTING_CLASS_BUT_DIFFERENT = "%nExpecting result:%n  <%s>%nto contain a value that is an instance of:%n <%s>%nbut did contain an instance of:%n  <%s>";
    private static final String EXPECTING_EXACT_BUT_DIFFERENT = "%nExpecting result:%n  <%s>%nto contain the instance (i.e. compared with ==):%n  <%s>%nbut did not.";

    private ResultShouldHave(String message, Result<?, ?> result, Object argument) {
        super(message, result, argument);
    }

    private ResultShouldHave(
            String message, Result<?, ?> result, Object argument1, Object argument2) {
        super(message, result, argument1, argument2);
    }

    static ResultShouldHave shouldHave(
            Result<?, ?> result, Object expectedValue, Object actualValue) {
        return new ResultShouldHave(EXPECTING_VALUE_BUT_DIFFERENT, result, expectedValue, actualValue);
    }

    static ResultShouldHave shouldHaveSame(Result<?, ?> result, Object expectedValue) {
        return new ResultShouldHave(EXPECTING_EXACT_BUT_DIFFERENT, result, expectedValue);
    }

    static ResultShouldHave shouldHaveInstanceOf(Result<?, ?> result, Class<?> clazz, Object value) {
        return new ResultShouldHave(
                EXPECTING_CLASS_BUT_DIFFERENT, result, clazz.getName(), value.getClass().getName());
    }
}
