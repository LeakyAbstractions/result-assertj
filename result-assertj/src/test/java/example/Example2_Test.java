/**{% if false %}*/

package example;

import com.leakyabstractions.result.api.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.leakyabstractions.result.test.Results.success;

/** {% elsif true %} Import result assertions */
import static com.leakyabstractions.result.assertj.ResultAssert.assertThatResult;
import static org.assertj.core.api.Assertions.assertThat;

/*{% endif %}{% if false %}*/

@DisplayName("Example2")
class Example2_Test {

/** {% elsif true %} Use result assertions */
@Test
void testAssertThatResult() {
  // Given
  final int zero = 0;
  // When
  final Result<Integer, String> result = success(zero);
  // Then
  assertThat(zero).isZero();
  assertThatResult(result).hasSuccess(zero);
} // End{% endif %}{% if false %}

}
// {% endif %}