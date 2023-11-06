/**
 * Provides assertions for {@link com.leakyabstractions.result.api.Result} type.
 * <h2>AssertJ assertions for {@code Result}</h2>
 * <p>
 * If you think some assertion is missing, please <a href="https://github.com/LeakyAbstractions/result/issues">create an
 * issue</a> or even better <a href="https://dev.leakyabstractions.com/result/CONTRIBUTING.html">contribute</a>!
 * <p>
 * Result Library for Java is hosted on <a href="https://github.com/LeakyAbstractions/result">GitHub</a>.
 * <p>
 * To quickly start using Result assertions, follow these steps:
 * <ol>
 * <li>Statically import {@code com.leakyabstractions.result.assertj.ResultAssertions.assertThat}.
 * <li>Pass the result under test as the sole {@code assertThat} parameter.
 * <li>Use code completion to discover assertions.
 * </ol>
 * <h3>Statically import {@code assertThat}</h3>
 *
 * <pre class="row-color rowColor">
 * <code>&nbsp;
 * import static com.leakyabstractions.result.assertj.ResultAssertions.assertThat;
 * </code>
 * </pre>
 *
 * <h3>Pass the result under test as the sole {@code assertThat} parameter</h3>
 *
 * <pre class="row-color rowColor">
 * <code>&nbsp;
 * assertThat(result)
 * </code>
 * </pre>
 *
 * <h3>Use code completion to discover assertions</h3>
 * <p>
 * Use your preferred IDE code completion after
 * {@link ResultAssertions#assertThat(com.leakyabstractions.result.api.Result) assertThat} to discover and call
 * assertions.
 *
 * <pre class="row-color rowColor">
 * <code>&nbsp;
 * &amp;Test
 * public void should_pass() {
 *     // Given
 *     final int number = someMethodReturningInt();
 *     // When
 *     final Result&lt;String, Integer&gt; result = someMethodReturningResult(number);
 *     // Then
 *     assertThat(number).isZero();
 *     assertThat(result).hasSuccess("OK");
 * }
 * </code>
 * </pre>
 *
 * @author Guillermo Calvo
 * @see ResultAssertions
 * @see com.leakyabstractions.result.api.Result
 */

package com.leakyabstractions.result.assertj;
