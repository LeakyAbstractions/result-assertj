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

import static com.leakyabstractions.result.assertj.ResultShouldBe.shouldBeFailure;
import static com.leakyabstractions.result.test.Results.success;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.leakyabstractions.result.api.Result;

/**
 * Tests for {@link ResultSoftAssertion#assertThat()}.
 *
 * @author Guillermo Calvo
 */
@DisplayName("ResultSoftAssertion assertThat")
class ResultSoftAssertion_assertThat_Test {

    @Test
    void should_fail_when_any_assertions_fails() {
        // Given
        final Result<String, Integer> result = success("hello");
        final ResultSoftAssertions softly = new ResultSoftAssertions();
        // When
        softly.assertThat(result).hasSuccess().hasFailure();
        final ThrowingCallable assertAll = () -> softly.assertAll();
        // Then
        final Throwable error = catchThrowable(assertAll);
        assertThat(error).hasMessageContaining(shouldBeFailure(result).create());
    }

    @Test
    void should_pass_when_all_assertions_pass() {
        // Given
        final Result<String, Integer> result = success("hello");
        final ResultSoftAssertions softly = new ResultSoftAssertions();
        // When
        softly.assertThat(result).hasSuccess().hasSuccess("hello");
        final ThrowingCallable assertAll = () -> softly.assertAll();
        // Then
        assertThatCode(assertAll).doesNotThrowAnyException();
    }
}
