---
title: Fluent Assertions for Result Objects
description: Result-AssertJ provides assertions to test Result objects
image: /result-banner-centered.png
---

# Result Assertions

This library provides fluent assertions (based on [AssertJ](https://assertj.github.io/doc/)) to test
[Result objects](https://dev.leakyabstractions.com/result/).


## Adding Assertions to Your Build

The library requires JDK 1.8 or higher. Other than that, it has no external dependencies and it is very lightweight.
Adding it to your build should be very easy.

Artifact coordinates:

- Group ID: `com.leakyabstractions`
- Artifact ID: `result`
- Version: `{{ site.current_version }}`

To add the dependency using [**Maven**](https://maven.apache.org/), use the following:

```xml
<dependency>
    <groupId>com.leakyabstractions</groupId>
    <artifactId>result-assertj</artifactId>
    <version>{{ site.current_version }}</version>
    <scope>test</scope>
</dependency>
```

To add the dependency using [**Gradle**](https://gradle.org/):

```gradle
dependencies {
    testImplementation 'com.leakyabstractions:result-assertj:{{ site.current_version }}'
}
```


## Asserting Result objects

You can use _Result_ assertions in your tests via `assertThat`:

```java
import static com.leakyabstractions.result.assertj.ResultAssertions.assertThat;

@Test
public void should_pass() {
    // Given
    final int number = someMethodReturningInt();
    // When
    final Result<String, Integer> result = someMethodReturningResult(number);
    // Then
    assertThat(number).isZero();
    assertThat(result).hasSuccess("OK");
}
```

If, for some reason, you cannot statically import static method `ResultAssertions.assertThat()` you can use static
method `ResultAssert.assertThatResult()` instead:

```java
import static com.leakyabstractions.result.assertj.ResultAssert.assertThatResult;
import static org.assertj.core.api.Assertions.assertThat;

@Test
public void should_pass_too() {
    // Given
    final int number = anotherMethodReturningInt();
    // When
    final Result<String, Integer> result = anotherMethodReturningResult(number);
    // Then
    assertThat(number).isOne();
    assertThatResult(result).hasFailure(1);
}
```


## Releases

This library adheres to [Pragmatic Versioning](https://pragver.github.io/).

Artifacts are available in [Maven Central](https://search.maven.org/artifact/com.leakyabstractions/result-assertj).


## Javadoc

Here's the full
[Result API documentation](https://dev.leakyabstractions.com/result-assertj/javadoc/{{ site.current_version }}/).


## Looking for Support?

We'd love to help. Check out the [support guidelines](SUPPORT.md).


## Contributions Welcome

If you'd like to contribute to this project, please [start here](CONTRIBUTING.md).


## Code of Conduct

This project is governed by the [Contributor Covenant Code of Conduct](CODE_OF_CONDUCT.md). By participating, you are
expected to uphold this code.
