# qa-automation-coding-test
Skeleton project for the coding assessment. The tests are coded in Java 11/Selenium/TestNG, and setup as integration tests using [Maven Failsafe plugin](http://maven.apache.org/surefire/maven-failsafe-plugin/).

### Running tests
1. Install IntelliJ IDEA IDE from [here](https://www.jetbrains.com/idea/)
2. Install Apache Maven like `brew install maven` from Terminal
3. Make sure to have Java 11 installed, can be downloaded from [here](https://www.oracle.com/java/technologies/downloads)
4. Open the project in IntelliJ by doing a `File->Open` and selecting the `pom.xml` file. Make sure the Maven tab is open on the right-side of the IDE and lists the dependencies(selenium-java, testng, etc.)
5. Run the tests from the Terminal by running the command from project root:

`mvn verify -Dgroups=sample` -> this should run a test which navigates to https://www.boxed.com/ and asserts the page title.

### Notes
1. If you encounter a captcha page when running the above test, please let us know, and we will fix it!