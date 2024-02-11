
package com.leakyabstractions.result.assertj;

import static com.leakyabstractions.result.assertj.ResultAssertions.assertThat;
import static com.leakyabstractions.result.assertj.ResultShouldBe.shouldBeSuccess;
import static com.leakyabstractions.result.core.Results.failure;
import static com.leakyabstractions.result.core.Results.success;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.util.FailureMessages.actualIsNull;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.leakyabstractions.result.api.Result;

/**
 * Tests for {@link ResultAssert#hasSuccess()}.
 *
 * @author Guillermo Calvo
 */
@DisplayName("ResultAssert hasSuccess")
class ResultAssert_hasSuccess_Test {

    @Test
    void should_fail_when_result_is_null() {
        // Given
        final Result<String, Integer> result = null;
        // When
        final ThrowingCallable callable = () -> assertThat(result).hasSuccess();
        // Then
        assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(callable)
                .withMessage(actualIsNull());
    }

    @Test
    void should_fail_when_result_is_failure() {
        // Given
        final Result<String, Integer> result = failure(123);
        // When
        final ThrowingCallable callable = () -> assertThat(result).hasSuccess();
        // Then
        assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(callable)
                .withMessage(shouldBeSuccess(result).create());
    }

    @Test
    void should_pass_when_result_is_success() {
        // Given
        final Result<String, Integer> result = success("present");
        // When
        final ThrowingCallable callable = () -> assertThat(result).hasSuccess();
        // Then
        assertThatCode(callable).doesNotThrowAnyException();
    }
}
