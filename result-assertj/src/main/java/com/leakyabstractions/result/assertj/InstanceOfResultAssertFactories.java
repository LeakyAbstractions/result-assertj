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

package com.leakyabstractions.result.assertj;

import java.util.function.Function;

import org.assertj.core.api.AbstractIterableAssert;
import org.assertj.core.api.AbstractMapAssert;
import org.assertj.core.api.AbstractObjectAssert;
import org.assertj.core.api.AbstractOptionalAssert;
import org.assertj.core.api.Assert;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.assertj.core.api.InstanceOfAssertFactory;

import com.leakyabstractions.result.api.Result;

/**
 * {@link InstanceOfAssertFactory} instances for {@link Result}.
 *
 * @author Guillermo Calvo
 * @see InstanceOfAssertFactories
 * @see Assert#asInstanceOf(InstanceOfAssertFactory)
 * @see AbstractObjectAssert#extracting(String, InstanceOfAssertFactory)
 * @see AbstractObjectAssert#extracting(Function, InstanceOfAssertFactory)
 * @see AbstractMapAssert#extractingByKey(Object, InstanceOfAssertFactory)
 * @see AbstractOptionalAssert#get(InstanceOfAssertFactory)
 * @see AbstractIterableAssert#first(InstanceOfAssertFactory)
 * @see AbstractIterableAssert#last(InstanceOfAssertFactory)
 * @see AbstractIterableAssert#element(int, InstanceOfAssertFactory)
 */
public interface InstanceOfResultAssertFactories extends InstanceOfAssertFactories {

    /**
     * {@link InstanceOfAssertFactory} for a {@link Result}, assuming {@code Object} as success and failure types.
     *
     * @see #resultOf(Class, Class)
     */
    @SuppressWarnings("rawtypes")
    InstanceOfAssertFactory<Result, ResultAssert<Object, Object>> RESULT = resultOf(Object.class, Object.class);

    /**
     * {@link InstanceOfAssertFactory} for a {@link Result}.
     *
     * @param <S> the success type.
     * @param <F> the failure type.
     * @param success the success type instance.
     * @param failure the failure type instance.
     * @return the factory instance.
     * @see #RESULT
     */
    @SuppressWarnings({ "rawtypes", "unused", "unchecked" })
    static <S, F> InstanceOfAssertFactory<Result, ResultAssert<S, F>> resultOf(
            Class<S> success, Class<F> failure) {
        return new InstanceOfAssertFactory<>(Result.class, ResultAssert::<S, F> assertThatResult);
    }
}
