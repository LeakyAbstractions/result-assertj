
package com.leakyabstractions.result.assertj;

import static com.leakyabstractions.result.assertj.AssertionsUtil.assertThatAssertionErrorIsThrownBy;
import static com.leakyabstractions.result.assertj.ResultAssertions.assertThat;
import static com.leakyabstractions.result.assertj.ResultShouldBe.shouldBeFailure;
import static com.leakyabstractions.result.assertj.ResultShouldHave.shouldHave;
import static com.leakyabstractions.result.core.Results.failure;
import static com.leakyabstractions.result.core.Results.success;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.util.FailureMessages.actualIsNull;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.leakyabstractions.result.api.Result;

/**
 * Tests for {@link ResultAssert#hasFailure(Object)}.
 *
 * @author Guillermo Calvo
 */
@DisplayName("ResultAssert hasFailure with Object")
class ResultAssert_hasFailure_with_Object_Test {

    @Test
    void should_fail_when_result_is_null() {
        // Given
        final String expected = "something";
        final Result<Integer, String> result = null;
        // When
        final ThrowingCallable callable = () -> assertThat(result).hasFailure(expected);
        // Then
        assertThatAssertionErrorIsThrownBy(callable).withMessage(actualIsNull());
    }

    @Test
    void should_fail_if_expected_value_is_null() {
        // Given
        final String expected = null;
        final String actual = "something";
        final Result<Integer, String> result = failure(actual);
        // When
        final ThrowingCallable callable = () -> assertThat(result).hasFailure(expected);
        // Then
        assertThatIllegalArgumentException()
                .isThrownBy(callable)
                .withMessage("The expected value should not be <null>.");
    }

    @Test
    void should_pass_if_result_has_expected_value() {
        // Given
        final String expected = "something";
        final String actual = expected;
        final Result<Integer, String> result = failure(actual);
        // When
        final ThrowingCallable callable = () -> assertThat(result).hasFailure(expected);
        // Then
        assertThatCode(callable).doesNotThrowAnyException();
    }

    @Test
    void should_fail_if_result_does_not_contain_expected_value() {
        // Given
        final String expected = "something";
        final String actual = "not-expected";
        final Result<Integer, String> result = failure(actual);
        // When
        final ThrowingCallable callable = () -> assertThat(result).hasFailure(expected);
        // Then
        final Throwable error = catchThrowable(callable);
        assertThat(error).hasMessage(shouldHave(result, expected, actual).create());
    }

    @Test
    void should_fail_if_result_is_success() {
        // Given
        final String expected = "something";
        final Result<Integer, String> result = success(123);
        // When
        final ThrowingCallable callable = () -> assertThat(result).hasFailure(expected);
        // Then
        final Throwable error = catchThrowable(callable);
        assertThat(error).hasMessage(shouldBeFailure(result).create());
    }
}
