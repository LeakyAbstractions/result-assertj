/*
 * Copyright 2026 Guillermo Calvo
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
import static com.leakyabstractions.result.assertj.ResultShouldBe.shouldBeSuccess;
import static com.leakyabstractions.result.test.Results.failure;
import static com.leakyabstractions.result.test.Results.success;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.util.FailureMessages.actualIsNull;

import java.util.function.Consumer;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.leakyabstractions.result.api.Result;

/**
 * Tests for {@link ResultAssert#hasSuccessSatisfying(Consumer)}.
 *
 * @author Guillermo Calvo
 */
@DisplayName("ResultAssert hasSuccessSatisfying(Consumer)")
class ResultAssert_hasSuccessSatisfying_with_Consumer_Test {

    @Test
    void should_fail_when_result_is_null() {
        // Given
        final Result<String, Integer> result = null;
        final Consumer<String> consumer = s -> {};
        // When
        final ThrowingCallable callable = () -> assertThat(result).hasSuccessSatisfying(consumer);
        // Then
        assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(callable)
                .withMessage(actualIsNull());
    }

    @Test
    void should_fail_when_result_is_failure() {
        // Given
        final Result<String, Integer> result = failure(123);
        final Consumer<String> consumer = s -> {};
        // When
        final ThrowingCallable callable = () -> assertThat(result).hasSuccessSatisfying(consumer);
        // Then
        assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(callable)
                .withMessage(shouldBeSuccess(result).create());
    }

    @Test
    void should_pass_when_consumer_passes() {
        // Given
        final Result<String, Integer> result = success("something");
        final Consumer<String> consumer = s -> assertThat(s).isEqualTo("something").startsWith("so").endsWith("ng");
        // When
        final ThrowingCallable callable = () -> assertThat(result).hasSuccessSatisfying(consumer);
        // Then
        assertThatCode(callable).doesNotThrowAnyException();
    }

    @Test
    void should_fail_from_consumer() {
        // Given
        final Result<String, Integer> actual = success("something else");
        final Consumer<String> consumer = s -> assertThat(s).isEqualTo("something");
        // When
        final ThrowingCallable callable = () -> assertThat(actual).hasSuccessSatisfying(consumer);
        // Then
        assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(callable)
                .withMessage(format("%nexpected: \"something\"%n but was: \"something else\""));
    }
}
