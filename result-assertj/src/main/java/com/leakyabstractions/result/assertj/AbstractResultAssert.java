/*
 * Copyright 2025 Guillermo Calvo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.leakyabstractions.result.assertj;

import static com.leakyabstractions.result.assertj.ResultShouldBe.shouldBeFailure;
import static com.leakyabstractions.result.assertj.ResultShouldBe.shouldBeSuccess;
import static com.leakyabstractions.result.assertj.ResultShouldHave.shouldHave;
import static com.leakyabstractions.result.assertj.ResultShouldHave.shouldHaveInstanceOf;
import static com.leakyabstractions.result.assertj.ResultShouldHave.shouldHaveSame;
import static org.assertj.core.api.ObjectAssertProxy.assertWithAssertionState;
import static org.assertj.core.util.Preconditions.checkArgument;

import java.util.function.Consumer;

import org.assertj.core.annotation.CheckReturnValue;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.assertj.core.api.InstanceOfAssertFactory;
import org.assertj.core.api.ObjectAssert;
import org.assertj.core.api.ObjectAssertProxy;
import org.assertj.core.internal.Conditions;
import org.assertj.core.internal.StandardComparisonStrategy;

import com.leakyabstractions.result.api.Result;

/**
 * Assertions for {@link Result}.
 *
 * @param <SELF> the "self" type of this assertion class.
 * @param <S> type of the success value contained in the {@link Result}.
 * @param <F> type of the failure value contained in the {@link Result}.
 * @author <a href="https://guillermo.dev/">Guillermo Calvo</a>
 */
@SuppressWarnings("java:S119") // Type parameter names should comply with a naming convention
abstract class AbstractResultAssert<SELF extends AbstractResultAssert<SELF, S, F>, S, F>
        extends AbstractAssert<SELF, Result<S, F>> {

    protected AbstractResultAssert(Result<S, F> actual, Class<?> selfType) {
        super(actual, selfType);
    }

    /**
     * Verifies that the actual {@link Result} is successful.
     * <p>
     * Assertions will pass:
     *
     * <pre class="row-color rowColor">
     * <code>&nbsp;
     * assertThat(Results.success("yay")).hasSuccess();
     * assertThat(Results.success(123)).hasSuccess();
     * </code>
     * </pre>
     *
     * Assertions will fail:
     *
     * <pre class="row-color rowColor">
     * <code>&nbsp;
     * assertThat(Results.failure("nay")).hasSuccess();
     * assertThat(Results.failure(123)).hasSuccess();
     * </code>
     * </pre>
     *
     * @return this assertion object.
     */
    public SELF hasSuccess() {
        this.assertHasSuccess();
        return myself;
    }

    /**
     * Verifies that the actual {@link Result} is a successful result containing the given value.
     * <p>
     * Assertions will pass:
     *
     * <pre class="row-color rowColor">
     * <code>&nbsp;
     * assertThat(Results.success("yay")).hasSuccess("yay");
     * assertThat(Results.success(1234)).hasSuccess(1234);
     * </code>
     * </pre>
     *
     * Assertions will fail:
     *
     * <pre class="row-color rowColor">
     * <code>&nbsp;
     * assertThat(Results.success("yay")).hasSuccess("nay");
     * assertThat(Results.failure("yay")).hasSuccess("yay");
     * </code>
     * </pre>
     *
     * @param expectedValue the expected success value inside the {@link Result}.
     * @return this assertion object.
     */
    public SELF hasSuccess(S expectedValue) {
        final S value = this.assertHasSuccess();
        this.checkNotNull(expectedValue);
        if (!StandardComparisonStrategy.instance().areEqual(value, expectedValue)) {
            throw this.assertionError(shouldHave(this.actual(), expectedValue, value));
        }
        return myself;
    }

    /**
     * Verifies that the actual {@link Result} is a successful result containing the instance given as an argument (i.e.
     * it must be the same instance).
     * <p>
     * Given:
     *
     * <pre class="row-color rowColor">
     * <code>&nbsp;
     * static {
     *     final String FOOBAR = "foobar";
     * }
     * </code>
     * </pre>
     *
     * Assertions will pass:
     *
     * <pre class="row-color rowColor">
     * <code>&nbsp;
     * assertThat(Results.success(FOOBAR)).hasSuccessSameAs(FOOBAR);
     * assertThat(Results.success(10)).hasSuccessSameAs(10);
     * </code>
     * </pre>
     *
     * Assertions will fail:
     *
     * <pre class="row-color rowColor">
     * <code>&nbsp;
     * assertThat(Results.success("yay")).hasSuccessSameAs("nay");
     * assertThat(Results.failure(FOOBAR)).hasSuccessSameAs(FOOBAR);
     * </code>
     * </pre>
     *
     * @param expectedValue the expected success value inside the {@link Result}.
     * @return this assertion object.
     */
    public SELF hasSuccessSameAs(S expectedValue) {
        final S value = this.assertHasSuccess();
        this.checkNotNull(expectedValue);
        if (value != expectedValue) {
            throw assertionError(shouldHaveSame(this.actual(), expectedValue));
        }
        return myself;
    }

    /**
     * Verifies that the actual {@link Result} is a successful result and invokes the given {@link Consumer} with the
     * success value for further assertions.
     * <p>
     * Should be used as a way of deeper asserting on the containing object, as further requirement(s) for the value.
     * <p>
     * Assertions will pass:
     *
     * <pre class="row-color rowColor">
     * <code>&nbsp;
     * // one requirement
     * assertThat(Results.success(10)).hasSuccessSatisfying(s -&gt; {
     *     assertThat(s).isGreaterThan(9);
     * });
     * assertThat(Results.success("yay")).hasSuccessSatisfying(s -&gt; {
     *     assertThat(s).isEqualTo("yay");
     * });
     *
     * // multiple requirements
     * assertThat(Results.success("hello")).hasSuccessSatisfying(s -&gt; {
     *     assertThat(s).isEqualTo("hello");
     *     assertThat(s).startsWith("h");
     *     assertThat(s).endsWith("o");
     * });
     * </code>
     * </pre>
     *
     * Assertions will fail:
     *
     * <pre class="row-color rowColor">
     * <code>&nbsp;
     * assertThat(Results.success("yay")).hasSuccessSatisfying(s -&gt; assertThat(s).isEqualTo("nay"));
     * assertThat(Results.failure("yay")).hasSuccessSatisfying(s -&gt; {});
     * </code>
     * </pre>
     *
     * @param requirement to further assert on the success value held by the {@link Result}
     * @return this assertion object.
     */
    public SELF hasSuccessSatisfying(Consumer<S> requirement) {
        final S value = this.assertHasSuccess();
        requirement.accept(value);
        return myself;
    }

    /**
     * Verifies that the actual {@link Result} is a successful result whose success value satisfies the given
     * {@link Condition}.
     * <p>
     * Given:
     *
     * <pre class="row-color rowColor">
     * <code>&nbsp;
     * static {
     *     final Condition&lt;Integer&gt; IS_NEGATIVE = new Condition&lt;&gt;(i -&gt; i &lt; 0, "a negative number");
     * }
     * </code>
     * </pre>
     *
     * Assertion will pass:
     *
     * <pre class="row-color rowColor">
     * <code>&nbsp;
     * assertThat(Results.success(-1)).hasSuccessSatisfying(IS_NEGATIVE);
     * </code>
     * </pre>
     *
     * Assertions will fail:
     *
     * <pre class="row-color rowColor">
     * <code>&nbsp;
     * assertThat(Results.success(1234)).hasSuccessSatisfying(IS_NEGATIVE);
     * assertThat(Results.failure(-123)).hasSuccessSatisfying(IS_NEGATIVE);
     * </code>
     * </pre>
     *
     * @param condition the given condition
     * @return this assertion object.
     */
    public SELF hasSuccessSatisfying(Condition<? super S> condition) {
        final S value = this.assertHasSuccess();
        Conditions.instance().assertIs(this.getWritableAssertionInfo(), value, condition);
        return myself;
    }

    /**
     * Verifies that the actual {@link Result} is a successful result containing a value that is an instance of the
     * given class.
     * <p>
     * Assertions will pass:
     *
     * <pre class="row-color rowColor">
     * <code>&nbsp;
     * assertThat(Results.success("hello"))
     *         .hasSuccessInstanceOf(String.class)
     *         .hasSuccessInstanceOf(Object.class);
     * assertThat(Results.success(1234)).hasSuccessInstanceOf(Integer.class);
     * </code>
     * </pre>
     *
     * Assertions will fail:
     *
     * <pre class="row-color rowColor">
     * <code>&nbsp;
     * assertThat(Results.success("hello")).hasSuccessInstanceOf(Integer.class);
     * assertThat(Results.failure("hello")).hasSuccessInstanceOf(String.class);
     * </code>
     * </pre>
     *
     * @param clazz the expected class of the success value inside the {@link Result}
     * @return this assertion object.
     */
    public SELF hasSuccessInstanceOf(Class<?> clazz) {
        final S value = this.assertHasSuccess();
        this.checkNotNull(clazz);
        if (!clazz.isInstance(value)) {
            throw assertionError(shouldHaveInstanceOf(this.actual(), clazz, value));
        }
        return myself;
    }

    /**
     * Verifies that the actual {@link Result} is a successful result and returns an Object assertion that allows
     * chaining (object) assertions on its success value.
     * <p>
     * Assertions will pass:
     *
     * <pre class="row-color rowColor">
     * <code>&nbsp;
     * assertThat(Results.success(0)).hasSuccessThat().isZero();
     * assertThat(Results.success(100)).hasSuccessThat().isGreaterThan(10);
     * </code>
     * </pre>
     *
     * Assertions will fail:
     *
     * <pre class="row-color rowColor">
     * <code>&nbsp;
     * assertThat(Results.failure(0)).hasSuccessThat().isZero();
     * assertThat(Results.success(1)).hasSuccessThat().isGreaterThan(10);
     * </code>
     * </pre>
     *
     * @return a new {@link ObjectAssert} for assertions chaining on the success value.
     * @see #hasSuccessThat(InstanceOfAssertFactory)
     */
    @CheckReturnValue
    public ObjectAssert<S> hasSuccessThat() {
        final S value = this.assertHasSuccess();
        return assertWithAssertionState(myself, value);
    }

    /**
     * Verifies that the actual {@link Result} is a successful result and returns a new assertion instance to chain
     * assertions on its success value.
     * <p>
     * The {@code assertFactory} parameter allows to specify an {@link InstanceOfAssertFactory}, which is used to get
     * the assertions narrowed to the factory type.
     * <p>
     * Wrapping the given {@link InstanceOfAssertFactory} with {@link Assertions#as(InstanceOfAssertFactory)} makes the
     * assertion more readable.
     * <p>
     * Assertions will pass:
     *
     * <pre class="row-color rowColor">
     * <code>&nbsp;
     * assertThat(Results.success(0)).hasSuccessThat(as(InstanceOfAssertFactories.INTEGER)).isZero();
     * assertThat(Results.success("hello")).hasSuccessThat(as(InstanceOfAssertFactories.STRING)).startsWith("h");
     * </code>
     * </pre>
     *
     * Assertions will fail:
     *
     * <pre class="row-color rowColor">
     * <code>&nbsp;
     * assertThat(Results.success("hello")).hasSuccessThat(as(InstanceOfAssertFactories.INTEGER)).isZero();
     * assertThat(Results.failure("hello")).hasSuccessThat(as(InstanceOfAssertFactories.STRING)).startsWith("h");
     * </code>
     * </pre>
     *
     * @param <T> the type of the resulting {@code Assert}
     * @param assertFactory the factory which verifies the type and creates the new {@code Assert}
     * @return a new narrowed {@link ObjectAssertProxy} instance for assertions chaining on the success value
     */
    @CheckReturnValue
    public <T extends AbstractAssert<?, ?>> T hasSuccessThat(
            InstanceOfAssertFactory<?, T> assertFactory) {
        final S value = this.assertHasSuccess();
        return assertWithAssertionState(myself, value).asInstanceOf(assertFactory);
    }

    /**
     * Verifies that the actual {@link Result} is failed.
     * <p>
     * Assertions will pass:
     *
     * <pre class="row-color rowColor">
     * <code>&nbsp;
     * assertThat(Results.failure("yay")).hasFailure();
     * assertThat(Results.failure(123)).hasFailure();
     * </code>
     * </pre>
     *
     * Assertions will fail:
     *
     * <pre class="row-color rowColor">
     * <code>&nbsp;
     * assertThat(Results.success("nay")).hasFailure();
     * assertThat(Results.success(123)).hasFailure();
     * </code>
     * </pre>
     *
     * @return this assertion object.
     */
    public SELF hasFailure() {
        this.assertHasFailure();
        return myself;
    }

    /**
     * Verifies that the actual {@link Result} is a failed result containing the given value.
     * <p>
     * Assertions will pass:
     *
     * <pre class="row-color rowColor">
     * <code>&nbsp;
     * assertThat(Results.failure("yay")).hasFailure("yay");
     * assertThat(Results.failure(1234)).hasFailure(1234);
     * </code>
     * </pre>
     *
     * Assertions will fail:
     *
     * <pre class="row-color rowColor">
     * <code>&nbsp;
     * assertThat(Results.failure("yay")).hasFailure("nay");
     * assertThat(Results.success("yay")).hasFailure("yay");
     * </code>
     * </pre>
     *
     * @param expectedValue the expected failure value inside the {@link Result}.
     * @return this assertion object.
     */
    public SELF hasFailure(F expectedValue) {
        final F value = this.assertHasFailure();
        this.checkNotNull(expectedValue);
        if (!StandardComparisonStrategy.instance().areEqual(value, expectedValue)) {
            throw this.assertionError(shouldHave(this.actual(), expectedValue, value));
        }
        return myself;
    }

    /**
     * Verifies that the actual {@link Result} is a failed result containing the instance given as an argument (i.e. it
     * must be the same instance).
     * <p>
     * Given:
     *
     * <pre class="row-color rowColor">
     * <code>&nbsp;
     * static {
     *     final String FOOBAR = "foobar";
     * }
     * </code>
     * </pre>
     *
     * Assertions will pass:
     *
     * <pre class="row-color rowColor">
     * <code>&nbsp;
     * assertThat(Results.failure(FOOBAR)).hasFailureSameAs(FOOBAR);
     * assertThat(Results.failure(10)).hasFailureSameAs(10);
     * </code>
     * </pre>
     *
     * Assertions will fail:
     *
     * <pre class="row-color rowColor">
     * <code>&nbsp;
     * // not equal:
     * assertThat(Results.failure("yay")).hasFailureSameAs("nay");
     * assertThat(Results.success(FOOBAR)).hasFailureSameAs(FOOBAR);
     * </code>
     * </pre>
     *
     * @param expectedValue the expected failure value inside the {@link Result}.
     * @return this assertion object.
     */
    public SELF hasFailureSameAs(F expectedValue) {
        final F value = this.assertHasFailure();
        this.checkNotNull(expectedValue);
        if (value != expectedValue) {
            throw assertionError(shouldHaveSame(this.actual(), expectedValue));
        }
        return myself;
    }

    /**
     * Verifies that the actual {@link Result} is a failed result and invokes the given {@link Consumer} with the
     * failure value for further assertions.
     * <p>
     * Should be used as a way of deeper asserting on the containing object, as further requirement(s) for the value.
     * <p>
     * Assertions will pass:
     *
     * <pre class="row-color rowColor">
     * <code>&nbsp;
     * // one requirement
     * assertThat(Results.failure(10)).hasFailureSatisfying(f -&gt; {
     *     assertThat(f).isGreaterThan(9);
     * });
     * assertThat(Results.failure("yay")).hasFailureSatisfying(f -&gt; {
     *     assertThat(f).isEqualTo("yay");
     * });
     *
     * // multiple requirements
     * assertThat(Results.failure("hello")).hasFailureSatisfying(f -&gt; {
     *     assertThat(f).isEqualTo("hello");
     *     assertThat(f).startsWith("h");
     *     assertThat(f).endsWith("o");
     * });
     * </code>
     * </pre>
     *
     * Assertions will fail:
     *
     * <pre class="row-color rowColor">
     * <code>&nbsp;
     * assertThat(Results.success("hello")).hasSuccessSatisfying(s -&gt; assertThat(s).isEqualTo("hello"));
     * assertThat(Results.failure("hello")).hasSuccessSatisfying(s -&gt; {});
     * </code>
     * </pre>
     *
     * @param requirement to further assert on the failure value held by the {@link Result}
     * @return this assertion object.
     */
    public SELF hasFailureSatisfying(Consumer<F> requirement) {
        final F value = this.assertHasFailure();
        requirement.accept(value);
        return myself;
    }

    /**
     * Verifies that the actual {@link Result} is a failed result whose failure value satisfies the given
     * {@link Condition}.
     * <p>
     * Given:
     *
     * <pre class="row-color rowColor">
     * <code>&nbsp;
     * static {
     *     final Condition&lt;Integer&gt; IS_NEGATIVE = new Condition&lt;&gt;(i -&gt; i &lt; 0, "a negative number");
     * }
     * </code>
     * </pre>
     *
     * Assertion will pass:
     *
     * <pre class="row-color rowColor">
     * <code>&nbsp;
     * assertThat(Results.failure(-1)).hasFailureSatisfying(IS_NEGATIVE);
     * </code>
     * </pre>
     *
     * Assertions will fail:
     *
     * <pre class="row-color rowColor">
     * <code>&nbsp;
     * assertThat(Results.failure(1234)).hasFailureSatisfying(IS_NEGATIVE);
     * assertThat(Results.success(-123)).hasFailureSatisfying(IS_NEGATIVE);
     * </code>
     * </pre>
     *
     * @param condition the given condition
     * @return this assertion object.
     */
    public SELF hasFailureSatisfying(Condition<? super F> condition) {
        final F value = this.assertHasFailure();
        Conditions.instance().assertIs(this.getWritableAssertionInfo(), value, condition);
        return myself;
    }

    /**
     * Verifies that the actual {@link Result} is a failed result containing a value that is an instance of the given
     * class.
     * <p>
     * Assertions will pass:
     *
     * <pre class="row-color rowColor">
     * <code>&nbsp;
     * assertThat(Results.failure("hello"))
     *         .hasFailureInstanceOf(String.class)
     *         .hasFailureInstanceOf(Object.class);
     * assertThat(Results.failure(123)).hasSuccessInstanceOf(Integer.class);
     * </code>
     * </pre>
     *
     * Assertions will fail:
     *
     * <pre class="row-color rowColor">
     * <code>&nbsp;
     * assertThat(Results.failure("hello")).hasFailureInstanceOf(Integer.class);
     * assertThat(Results.success("hello")).hasFailureInstanceOf(String.class);
     * </code>
     * </pre>
     *
     * @param clazz the expected class of the failure value inside the {@link Result}
     * @return this assertion object.
     */
    public SELF hasFailureInstanceOf(Class<?> clazz) {
        final F value = this.assertHasFailure();
        this.checkNotNull(clazz);
        if (!clazz.isInstance(value)) {
            throw assertionError(shouldHaveInstanceOf(this.actual(), clazz, value));
        }
        return myself;
    }

    /**
     * Verifies that the actual {@link Result} is a failed result and returns an Object assertion that allows chaining
     * (object) assertions on its failure value.
     * <p>
     * Assertions will pass:
     *
     * <pre class="row-color rowColor">
     * <code>&nbsp;
     * assertThat(Results.failure(10)).hasFailureThat().isZero();
     * assertThat(Results.failure(100)).hasFailureThat().isGreaterThan(10);
     * </code>
     * </pre>
     *
     * Assertions will fail:
     *
     * <pre class="row-color rowColor">
     * <code>&nbsp;
     * assertThat(Results.success(0)).hasFailureThat().isZero();
     * assertThat(Results.failure(1)).hasFailureThat().isGreaterThan(10);
     * </code>
     * </pre>
     *
     * @return a new {@link ObjectAssert} for assertions chaining on the success value.
     * @see #hasSuccessThat(InstanceOfAssertFactory)
     */
    @CheckReturnValue
    public ObjectAssert<F> hasFailureThat() {
        final F value = this.assertHasFailure();
        return assertWithAssertionState(myself, value);
    }

    /**
     * Verifies that the actual {@link Result} is a failed result and returns a new assertion instance to chain
     * assertions on its failure value.
     * <p>
     * The {@code assertFactory} parameter allows to specify an {@link InstanceOfAssertFactory}, which is used to get
     * the assertions narrowed to the factory type.
     * <p>
     * Wrapping the given {@link InstanceOfAssertFactory} with {@link Assertions#as(InstanceOfAssertFactory)} makes the
     * assertion more readable.
     * <p>
     * Assertions will pass:
     *
     * <pre class="row-color rowColor">
     * <code>&nbsp;
     * assertThat(Results.failure(0)).hasFailureThat(as(InstanceOfAssertFactories.INTEGER)).isZero();
     * assertThat(Results.failure("hello")).hasFailureThat(as(InstanceOfAssertFactories.STRING)).startsWith("h");
     * </code>
     * </pre>
     *
     * Assertions will fail:
     *
     * <pre class="row-color rowColor">
     * <code>&nbsp;
     * assertThat(Results.failure("hello")).hasFailureThat(as(InstanceOfAssertFactories.INTEGER)).isZero();
     * assertThat(Results.success("hello")).hasFailureThat(as(InstanceOfAssertFactories.STRING)).startsWith("h");
     * </code>
     * </pre>
     *
     * @param <T> the type of the resulting {@code Assert}
     * @param assertFactory the factory which verifies the type and creates the new {@code Assert}
     * @return a new narrowed {@link ObjectAssertProxy} instance for assertions chaining on the success value
     */
    @CheckReturnValue
    public <T extends AbstractAssert<?, ?>> T hasFailureThat(
            InstanceOfAssertFactory<?, T> assertFactory) {
        final F value = this.assertHasFailure();
        return assertWithAssertionState(myself, value).asInstanceOf(assertFactory);
    }

    private void checkNotNull(Object expectedValue) {
        checkArgument(expectedValue != null, "The expected value should not be <null>.");
    }

    private S assertHasSuccess() {
        isNotNull();
        return this.actual()
                .getSuccess()
                .orElseThrow(() -> this.assertionError(shouldBeSuccess(this.actual())));
    }

    private F assertHasFailure() {
        isNotNull();
        return this.actual()
                .getFailure()
                .orElseThrow(() -> this.assertionError(shouldBeFailure(this.actual())));
    }
}
