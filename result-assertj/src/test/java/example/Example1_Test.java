/**{% if false %}*/

package example;

import com.leakyabstractions.result.api.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.leakyabstractions.result.test.Results.success;

/** {% elsif true %} Import result assertions */
import static com.leakyabstractions.result.assertj.ResultAssertions.assertThat;

/*{% endif %}{% if false %}*/

@DisplayName("Example1")
class Example1_Test {

/** {% elsif true %} Use result assertions */
@Test
void testAssertThat() {
  // Given
  final int zero = 0;
  // When
  final Result<Integer, String> result = success(zero);
  // Then
  assertThat(zero).isZero();
  assertThat(result).hasSuccess(zero);
} // End{% endif %}{% if false %}

}
// {% endif %}