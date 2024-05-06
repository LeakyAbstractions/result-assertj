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

package com.leakyabstractions.result.test;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.leakyabstractions.result.api.Result;

public interface Results<S, F> extends Result<S, F> {

    @Override
    default boolean hasSuccess() {
        return getSuccess().isPresent();
    }

    @Override
    default boolean hasFailure() {
        return getFailure().isPresent();
    }

    @Override
    default Optional<S> getSuccess() {
        return Optional.empty();
    }

    @Override
    default Optional<F> getFailure() {
        return Optional.empty();
    }

    @Override
    default S orElse(S other) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    default S orElseMap(Function<? super F, ? extends S> mapper) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    default Stream<S> streamSuccess() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    default Stream<F> streamFailure() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    default Result<S, F> ifSuccess(Consumer<? super S> action) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    default Result<S, F> ifFailure(Consumer<? super F> action) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    default Result<S, F> ifSuccessOrElse(
            Consumer<? super S> successAction, Consumer<? super F> failureAction) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    default Result<S, F> filter(
            Predicate<? super S> isAcceptable, Function<? super S, ? extends F> mapper) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    default Result<S, F> recover(
            Predicate<? super F> isRecoverable, Function<? super F, ? extends S> mapper) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    default <S2> Result<S2, F> mapSuccess(Function<? super S, ? extends S2> mapper) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    default <F2> Result<S, F2> mapFailure(Function<? super F, ? extends F2> mapper) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    default <S2, F2> Result<S2, F2> map(
            Function<? super S, ? extends S2> successMapper,
            Function<? super F, ? extends F2> failureMapper) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    default <S2> Result<S2, F> flatMapSuccess(
            Function<? super S, ? extends Result<? extends S2, ? extends F>> mapper) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    default <F2> Result<S, F2> flatMapFailure(
            Function<? super F, ? extends Result<? extends S, ? extends F2>> mapper) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    default <S2, F2> Result<S2, F2> flatMap(
            Function<? super S, ? extends Result<? extends S2, ? extends F2>> successMapper,
            Function<? super F, ? extends Result<? extends S2, ? extends F2>> failureMapper) {
        throw new RuntimeException("Not implemented");
    }

    static <S, F> Results<S, F> success(S value) {
        return new Results<S, F>() {
            @Override
            public Optional<S> getSuccess() {
                return Optional.of(value);
            }
        };
    }

    static <S, F> Results<S, F> failure(F value) {
        return new Results<S, F>() {
            @Override
            public Optional<F> getFailure() {
                return Optional.of(value);
            }
        };
    }
}
