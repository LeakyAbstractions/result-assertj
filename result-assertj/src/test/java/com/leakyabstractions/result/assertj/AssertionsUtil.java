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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.presentation.UnicodeRepresentation.UNICODE_REPRESENTATION;
import static org.assertj.core.util.introspection.PropertyOrFieldSupport.EXTRACTION;

import java.util.Comparator;
import java.util.UUID;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Condition;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.assertj.core.api.ThrowableAssertAlternative;
import org.junit.jupiter.api.Test;

class AssertionsUtil {

    static AssertionError expectAssertionError(ThrowingCallable shouldRaiseAssertionError) {
        AssertionError error = catchThrowableOfType(AssertionError.class, shouldRaiseAssertionError);
        assertThat(error).as("The code under test should have raised an AssertionError").isNotNull();
        return error;
    }

    static ThrowableAssertAlternative<AssertionError> assertThatAssertionErrorIsThrownBy(
            ThrowingCallable shouldRaiseAssertionError) {
        return assertThatExceptionOfType(AssertionError.class).isThrownBy(shouldRaiseAssertionError);
    }

    static class TestCondition<T> extends Condition<T> {

        private final boolean matches;

        TestCondition(boolean matches) {
            this.matches = matches;
        }

        @Override
        public boolean matches(T value) {
            return matches;
        }
    }

    static class AlwaysEqualComparator<T> implements Comparator<T> {

        static final AlwaysEqualComparator<Object> INSTANCE = new AlwaysEqualComparator<>();

        @Override
        public int compare(T o1, T o2) {
            return 0;
        }

        @Override
        public String toString() {
            return "AlwaysEqualComparator";
        }
    }

    static interface NavigationMethodBaseTest<ASSERT extends AbstractAssert<ASSERT, ?>> {

        ASSERT getAssertion();

        AbstractAssert<?, ?> invoke_navigation_method(ASSERT assertion);

        @Test
        default void should_honor_registered_comparator() {
            // Given
            ASSERT assertion = getAssertion().usingComparator(AlwaysEqualComparator.INSTANCE);
            // When
            AbstractAssert<?, ?> result = invoke_navigation_method(assertion);
            // Then
            result.isEqualTo(UUID.randomUUID()); // random value to avoid false positives
        }

        @Test
        default void should_keep_existing_assertion_state() {
            // Given
            ASSERT assertion = getAssertion()
                    .as("description")
                    .withFailMessage("error message")
                    .withRepresentation(UNICODE_REPRESENTATION)
                    .usingComparator(AlwaysEqualComparator.INSTANCE);
            // When
            AbstractAssert<?, ?> result = invoke_navigation_method(assertion);
            // Then
            then(result)
                    .hasFieldOrPropertyWithValue("objects", EXTRACTION.getValueOf("objects", assertion))
                    .extracting(AbstractAssert::getWritableAssertionInfo)
                    .usingRecursiveComparison()
                    .isEqualTo(assertion.info);
        }
    }
}
