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

import static com.leakyabstractions.result.assertj.AssertionsUtil.expectAssertionError;
import static com.leakyabstractions.result.assertj.ResultAssertions.assertThat;
import static com.leakyabstractions.result.assertj.ResultShouldBe.shouldBeSuccess;
import static com.leakyabstractions.result.test.Results.failure;
import static com.leakyabstractions.result.test.Results.success;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.util.FailureMessages.actualIsNull;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.leakyabstractions.result.api.Result;
import com.leakyabstractions.result.assertj.AssertionsUtil.NavigationMethodBaseTest;

/**
 * Tests for {@link ResultAssert#hasSuccessThat()}.
 *
 * @author Guillermo Calvo
 */
@DisplayName("ResultAssert hasSuccessThat")
class ResultAssert_hasSuccessThat_Test
        implements NavigationMethodBaseTest<ResultAssert<String, Integer>> {

    @Test
    void should_fail_if_result_is_null() {
        // Given
        final Result<String, Integer> result = null;
        // When
        final AssertionError assertionError = expectAssertionError(() -> assertThat(result).hasSuccessThat());
        // Then
        then(assertionError).hasMessage(actualIsNull());
    }

    @Test
    void should_fail_if_result_is_failure() {
        // Given
        final Result<String, Integer> result = failure(123);
        // When
        final AssertionError assertionError = expectAssertionError(() -> assertThat(result).hasSuccessThat());
        // Then
        then(assertionError).hasMessage(shouldBeSuccess(result).create());
    }

    @Test
    void should_pass_if_result_contains_a_value() {
        // Given
        final Result<String, Integer> result = success("Frodo");
        // When
        final ThrowingCallable callable = () -> assertThat(result).hasSuccessThat().isEqualTo("Frodo");
        // Then
        assertThatCode(callable).doesNotThrowAnyException();
    }

    @Override
    public ResultAssert<String, Integer> getAssertion() {
        final Result<String, Integer> result = success("Frodo");
        return assertThat(result);
    }

    @Override
    public AbstractAssert<?, ?> invoke_navigation_method(ResultAssert<String, Integer> assertion) {
        return assertion.hasSuccessThat();
    }
}
