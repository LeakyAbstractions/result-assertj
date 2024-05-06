
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
