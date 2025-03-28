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

import static com.leakyabstractions.result.assertj.InstanceOfResultAssertFactories.RESULT;
import static com.leakyabstractions.result.assertj.InstanceOfResultAssertFactories.STRING;
import static com.leakyabstractions.result.test.Results.success;
import static java.util.Collections.singletonMap;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link InstanceOfResultAssertFactories#RESULT}.
 *
 * @author Guillermo Calvo
 */
@DisplayName("InstanceOfResultAssertFactories RESULT")
class InstanceOfResultAssertFactories_RESULT_Test {

    @Test
    void test_instance_of_generic_result_assert_factory() {
        // Given
        final Map<String, Object> actual = singletonMap("foo", success("anything"));
        // Then
        assertThat(actual)
                .extracting("foo", RESULT)
                .hasSuccessThat(STRING)
                .startsWith("any")
                .endsWith("thing");
    }
}
