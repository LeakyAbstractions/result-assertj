/*
 * Copyright 2024 Guillermo Calvo
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

package org.assertj.core.api;

/**
 * Proxy to access package-protected methods in {@code org.assertj.core.api}.
 *
 * @author <a href="https://guillermo.dev/">Guillermo Calvo</a>
 */
public interface ObjectAssertProxy {

    /**
     * Assert with assertion state.
     * <p>
     * Invokes {@link Assertions#assertThatObject(Object)} and then
     * {@link ObjectAssert#withAssertionState(AbstractAssert)}.
     *
     * @param <T> the generic type of the assert.
     * @param state the assertion state.
     * @param actual the actual value.
     * @return the created assertion object with the assertion state.
     */
    static <T> ObjectAssert<T> assertWithAssertionState(AbstractAssert<?, ?> state, T actual) {
        return Assertions.assertThatObject(actual).withAssertionState(state);
    }
}
