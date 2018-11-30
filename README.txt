This project is built using Java 8 and Gradle wrapper.

To build:

From the project root,
./gradlew build

This will create an executable jar in the /build/libs subdirectory.

To run the jar, as an example, navigate to the /build/libs directory and

java -jar spitspot-0.0.1.jar /path/to/DictionaryFile.txt Spin Spot results.txt

The gradle build executes the unit tests, of which the acceptance test is SpitSpotAppTest