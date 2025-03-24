---
title: Fluent Assertions for Result Objects
description: Result-AssertJ provides assertions to test Result objects
image: https://dev.leakyabstractions.com/result/result-magic-ball.png
---

# Result Assertions

This library provides fluent assertions to test [Result objects][RESULT].

Fluent assertions enhance the readability and expressiveness of your unit tests. These assertions are based on
[AssertJ][ASSERTJ], an open-source Java library that offers a fluent API for writing assertions in test cases.

> [AssertJ][ASSERTJ] features a comprehensive and intuitive set of strongly-typed assertions for unit testing. It is a
> popular choice among Java developers due to its effective features and compatibility with various testing frameworks
> like [JUnit][JUNIT] and [TestNG][TESTNG].


## Adding Assertions to Your Build

Artifact coordinates:

- Group ID: `com.leakyabstractions`
- Artifact ID: `result-assertj`
- Version: `{{ site.current_version }}`

[Maven Central Repository][ARTIFACTS] provides snippets for different build tools to declare this dependency.


## Asserting Result objects

You can use [`ResultAssertions::assertThat`][ASSERT_THAT] in your tests to create fluent assertions for result objects.

```java
{% include_relative result-assertj/src/test/java/example/Example1_Test.java %}
```

If, for any reason, you cannot statically import `assertThat`, you can use
[`ResultAssert::assertThatResult`][ASSERT_THAT_RESULT] instead.

```java
{% include_relative result-assertj/src/test/java/example/Example2_Test.java %}
```

The full source code for the examples is [available on GitHub][SOURCE_CODE].


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

Copyright 2025 [Guillermo Calvo][AUTHOR].

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


[ARTIFACTS]:                    https://central.sonatype.com/artifact/com.leakyabstractions/result-assertj/
[ASSERTJ]:                      https://assertj.github.io/doc/
[ASSERT_THAT]:                  https://javadoc.io/doc/com.leakyabstractions/result-assertj/latest/com/leakyabstractions/result/assertj/ResultAssertions.html#assertThat-com.leakyabstractions.result.api.Result-
[ASSERT_THAT_RESULT]:           https://javadoc.io/doc/com.leakyabstractions/result-assertj/latest/com/leakyabstractions/result/assertj/ResultAssert.html#assertThatResult-com.leakyabstractions.result.api.Result-
[AUTHOR]:                       https://github.com/guillermocalvo/
[CODE_OF_CONDUCT]:              https://github.com/LeakyAbstractions/.github/blob/main/CODE_OF_CONDUCT.md
[CONTRIBUTING]:                 https://github.com/LeakyAbstractions/.github/blob/main/CONTRIBUTING.md
[GRADLE]:                       https://gradle.org/
[GUILLERMO]:                    https://guillermo.dev/
[GUILLERMO_IMAGE]:              https://guillermo.dev/assets/images/thumb.png
[JAVADOC]:                      https://javadoc.io/doc/com.leakyabstractions/result-assertj/
[JUNIT]:                        https://junit.org/
[MAVEN]:                        https://maven.apache.org/
[PRAGVER]:                      https://pragver.github.io/
[RESULT]:                       https://result.leakyabstractions.com/
[SOURCE_CODE]:                  https://github.com/LeakyAbstractions/result-assertj/tree/main/result-assertj/src/test/java/example
[SUPPORT]:                      https://github.com/LeakyAbstractions/.github/blob/main/SUPPORT.md
[TESTNG]:                       https://testng.org/
