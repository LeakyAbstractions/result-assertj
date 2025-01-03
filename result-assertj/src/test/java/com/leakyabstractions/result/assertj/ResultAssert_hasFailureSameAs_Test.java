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

import static com.leakyabstractions.result.assertj.ResultAssertions.assertThat;
import static com.leakyabstractions.result.assertj.ResultShouldBe.shouldBeFailure;
import static com.leakyabstractions.result.assertj.ResultShouldHave.shouldHaveSame;
import static com.leakyabstractions.result.test.Results.failure;
import static com.leakyabstractions.result.test.Results.success;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.util.FailureMessages.actualIsNull;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.leakyabstractions.result.api.Result;

/**
 * Tests for {@link ResultAssert#hasFailureSameAs(Object)}.
 *
 * @author Guillermo Calvo
 */
@DisplayName("ResultAssert hasFailureSameAs")
class ResultAssert_hasFailureSameAs_Test {

    @Test
    void should_fail_when_result_is_null() {
        // Given
        final String expected = "something";
        final Result<Integer, String> result = null;
        // When
        final ThrowingCallable callable = () -> assertThat(result).hasFailureSameAs(expected);
        // Then
        assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(callable)
                .withMessage(actualIsNull());
    }

    @Test
    void should_fail_if_expected_value_is_null() {
        // Given
        final String expected = null;
        final String actual = "something";
        final Result<Integer, String> result = failure(actual);
        // When
        final ThrowingCallable callable = () -> assertThat(result).hasFailureSameAs(expected);
        // Then
        assertThatIllegalArgumentException()
                .isThrownBy(callable)
                .withMessage("The expected value should not be <null>.");
    }

    @Test
    void should_pass_if_result_contains_the_expected_object_reference() {
        // Given
        final String expected = "something";
        final String actual = expected;
        final Result<Integer, String> result = failure(actual);
        // When
        final ThrowingCallable callable = () -> assertThat(result).hasFailureSameAs(expected);
        // Then
        assertThatCode(callable).doesNotThrowAnyException();
    }

    @Test
    void should_fail_if_result_does_not_contain_the_expected_object_reference() {
        // Given
        final String expected = "something";
        final String actual = "not-expected";
        final Result<Integer, String> result = failure(actual);
        // When
        final ThrowingCallable callable = () -> assertThat(result).hasFailureSameAs(expected);
        // Then
        assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(callable)
                .withMessage(shouldHaveSame(result, expected).create());
    }

    @Test
    void should_fail_if_result_contains_equal_but_not_same_value() {
        // Given
        final String expected = "something";
        final String actual = new String("something");
        final Result<Integer, String> result = failure(actual);
        // When
        final ThrowingCallable callable = () -> assertThat(result).hasFailureSameAs(expected);
        // Then
        assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(callable)
                .withMessage(shouldHaveSame(result, expected).create());
    }

    @Test
    void should_fail_if_result_is_success() {
        // Given
        final String expected = "something";
        final Result<Integer, String> result = success(123);
        // When
        final ThrowingCallable callable = () -> assertThat(result).hasFailureSameAs(expected);
        // Then
        assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(callable)
                .withMessage(shouldBeFailure(result).create());
    }
}
