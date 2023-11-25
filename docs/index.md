---
title: Fluent Assertions for Result Objects
description: Result-AssertJ provides assertions to test Result objects
image: https://dev.leakyabstractions.com/result/result-magic-ball.png
---

# Result Assertions

This library provides fluent assertions (based on [AssertJ][ASSERTJ]) to test [Result objects][RESULT].


## Adding Assertions to Your Build

Artifact coordinates:

- Group ID: `com.leakyabstractions`
- Artifact ID: `result-assertj`
- Version: `{{ site.current_version }}`

To add the dependency using [**Maven**][MAVEN], use the following:

```xml
<dependency>
    <groupId>com.leakyabstractions</groupId>
    <artifactId>result-assertj</artifactId>
    <version>{{ site.current_version }}</version>
    <scope>test</scope>
</dependency>
```

To add the dependency using [**Gradle**][GRADLE]:

```gradle
dependencies {
    testImplementation 'com.leakyabstractions:result-assertj:{{ site.current_version }}'
}
```


## Asserting Result objects

You can use _Result_ assertions in your tests via [`assertThat`][ASSERT_THAT]:

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

If, for some reason, you cannot statically import static method [`ResultAssertions.assertThat()`][ASSERT_THAT] you can
use static method [`assertThatResult`][ASSERT_THAT_RESULT] instead:

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


# Additional Info

## Releases

This library adheres to [Pragmatic Versioning][PRAGVER].

Artifacts are available in [Maven Central][ARTIFACTS].


## Javadoc

Here you can find the full [Javadoc documentation][JAVADOC].


## Looking for Support?

We'd love to help. Check out the [support guidelines][SUPPORT].


## Contributions Welcome

If you'd like to contribute to this project, please [start here][CONTRIBUTING].


## Code of Conduct

This project is governed by the [Contributor Covenant Code of Conduct][CODE_OF_CONDUCT].
By participating, you are expected to uphold this code.


## Author

Copyright 2023 [Guillermo Calvo][AUTHOR].

[![][GUILLERMO_IMAGE]][GUILLERMO]


## License

This library is licensed under the *Apache License, Version 2.0* (the "License");
you may not use it except in compliance with the License.

You may obtain a copy of the License at <http://www.apache.org/licenses/LICENSE-2.0>

Unless required by applicable law or agreed to in writing, software distributed under the License
is distributed on an "AS IS" BASIS, **WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND**, either express or implied.

See the License for the specific language governing permissions and limitations under the License.


**Permitted:**

- **Commercial Use**: You may use this library and derivatives for commercial purposes.
- **Modification**: You may modify this library.
- **Distribution**: You may distribute this library.
- **Patent Use**: This license provides an express grant of patent rights from contributors.
- **Private Use**: You may use and modify this library without distributing it.

**Required:**

- **License and Copyright Notice**: If you distribute this library you must include a copy of the license and copyright
  notice.
- **State Changes**: If you modify and distribute this library you must document changes made to this library.

**Forbidden:**

- **Trademark use**: This license does not grant any trademark rights.
- **Liability**: The library author cannot be held liable for damages.
- **Warranty**: This library is provided without any warranty.


[ARTIFACTS]:                    https://search.maven.org/artifact/com.leakyabstractions/result-assertj/
[ASSERTJ]:                      https://assertj.github.io/doc/
[ASSERT_THAT]:                  https://dev.leakyabstractions.com/result-assertj/javadoc/{{ site.current_version }}/com/leakyabstractions/result/assertj/ResultAssertions.html#assertThat-com.leakyabstractions.result.Result-
[ASSERT_THAT_RESULT]:           https://dev.leakyabstractions.com/result-assertj/javadoc/{{ site.current_version }}/com/leakyabstractions/result/assertj/ResultAssert.html#assertThatResult-com.leakyabstractions.result.Result-
[AUTHOR]:                       https://github.com/guillermocalvo/
[CODE_OF_CONDUCT]:              https://dev.leakyabstractions.com/result/CODE_OF_CONDUCT.html
[CONTRIBUTING]:                 https://dev.leakyabstractions.com/result/CONTRIBUTING.html
[GRADLE]:                       https://gradle.org/
[GUILLERMO]:                    https://guillermo.dev/
[GUILLERMO_IMAGE]:              https://guillermo.dev/assets/images/thumb.png
[JAVADOC]:                      https://javadoc.io/doc/com.leakyabstractions/result-assertj/
[MAVEN]:                        https://maven.apache.org/
[PRAGVER]:                      https://pragver.github.io/
[RESULT]:                       https://dev.leakyabstractions.com/result/
[SUPPORT]:                      https://dev.leakyabstractions.com/result/SUPPORT.html
