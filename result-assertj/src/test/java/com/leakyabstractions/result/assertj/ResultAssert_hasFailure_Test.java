
package com.leakyabstractions.result.assertj;

import static com.leakyabstractions.result.assertj.ResultAssertions.assertThat;
import static com.leakyabstractions.result.assertj.ResultShouldBe.shouldBeFailure;
import static com.leakyabstractions.result.test.Results.failure;
import static com.leakyabstractions.result.test.Results.success;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.util.FailureMessages.actualIsNull;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.leakyabstractions.result.api.Result;

/**
 * Tests for {@link ResultAssert#hasFailure()}.
 *
 * @author Guillermo Calvo
 */
@DisplayName("ResultAssert hasFailure")
class ResultAssert_hasFailure_Test {

    @Test
    void should_fail_when_result_is_null() {
        // Given
        final Result<String, Integer> result = null;
        // When
        final ThrowingCallable callable = () -> assertThat(result).hasFailure();
        // Then
        assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(callable)
                .withMessage(actualIsNull());
    }

    @Test
    void should_fail_if_result_is_success() {
        // Given
        final Result<String, Integer> result = success("hello");
        // When
        final ThrowingCallable callable = () -> assertThat(result).hasFailure();
        // Then
        assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(callable)
                .withMessage(shouldBeFailure(result).create());
    }

    @Test
    void should_pass_when_result_is_failure() {
        // Given
        final Result<String, Integer> result = failure(123);
        // When
        final ThrowingCallable callable = () -> assertThat(result).hasFailure();
        // Then
        assertThatCode(callable).doesNotThrowAnyException();
    }
}
