/**{% if false %}*/

package example;

import com.leakyabstractions.result.api.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.leakyabstractions.result.core.Results.success;

/** {% elsif true %}Import result assertions */
import static com.leakyabstractions.result.assertj.ResultAssertions.assertThat;

/*{% endif %}{% if false %}*/

@DisplayName("Example1")
class Example1_Test {

/** {% elsif true %} Use result assertions */
@Test
public void should_pass() {
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