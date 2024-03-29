
package com.leakyabstractions.result.assertj;

import static com.leakyabstractions.result.assertj.ResultAssertions.assertThat;
import static com.leakyabstractions.result.assertj.ResultShouldBe.shouldBeSuccess;
import static com.leakyabstractions.result.assertj.ResultShouldHave.shouldHaveSame;
import static com.leakyabstractions.result.core.Results.failure;
import static com.leakyabstractions.result.core.Results.success;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.util.FailureMessages.actualIsNull;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.leakyabstractions.result.api.Result;

/**
 * Tests for {@link ResultAssert#hasSuccessSameAs(Object)}.
 *
 * @author Guillermo Calvo
 */
@DisplayName("ResultAssert hasSuccessSameAs")
class ResultAssert_hasSuccessSameAs_Test {

    @Test
    void should_fail_when_result_is_null() {
        // Given
        final String expected = "something";
        final Result<String, Integer> result = null;
        // When
        final ThrowingCallable callable = () -> assertThat(result).hasSuccessSameAs(expected);
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
        final Result<String, Integer> result = success(actual);
        // When
        final ThrowingCallable callable = () -> assertThat(result).hasSuccessSameAs(expected);
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
        final Result<String, Integer> result = success(actual);
        // When
        final ThrowingCallable callable = () -> assertThat(result).hasSuccessSameAs(expected);
        // Then
        assertThatCode(callable).doesNotThrowAnyException();
    }

    @Test
    void should_fail_if_result_does_not_contain_the_expected_object_reference() {
        // Given
        final String expected = "something";
        final String actual = "not-expected";
        final Result<String, Integer> result = success(actual);
        // When
        final ThrowingCallable callable = () -> assertThat(result).hasSuccessSameAs(expected);
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
        final Result<String, Integer> result = success(actual);
        // When
        final ThrowingCallable callable = () -> assertThat(result).hasSuccessSameAs(expected);
        // Then
        assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(callable)
                .withMessage(shouldHaveSame(result, expected).create());
    }

    @Test
    void should_fail_if_result_is_failure() {
        // Given
        final String expected = "something";
        final Result<String, Integer> result = failure(123);
        // When
        final ThrowingCallable callable = () -> assertThat(result).hasSuccessSameAs(expected);
        // Then
        assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(callable)
                .withMessage(shouldBeSuccess(result).create());
    }
}
