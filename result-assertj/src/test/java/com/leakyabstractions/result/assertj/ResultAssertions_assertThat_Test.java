
package com.leakyabstractions.result.assertj;

import static com.leakyabstractions.result.test.Results.success;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link ResultAssertions}.
 *
 * @author Guillermo Calvo
 */
@DisplayName("ResultAssertions assertThat")
class ResultAssertions_assertThat_Test {

    @Test
    @SuppressWarnings("static")
    void should_return_result_assertions() {
        // When
        final ResultAssertions assertions = new ResultAssertions();
        // Then
        assertThat(assertions.assertThat(success("SUCCESS"))).isInstanceOf(ResultAssert.class);
    }
}
