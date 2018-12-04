This project is built using Java 8 and Gradle wrapper.

To build:

From the project root,
./gradlew build

This will create an executable jar in the /build/libs subdirectory.

To run the jar, as an example, navigate to the /build/libs directory and

java -jar spitspot-0.0.1.jar /path/to/DictionaryFile.txt Spin Spot results.txt

The gradle build executes the unit tests, of which the acceptance test is SpitSpotAppTest

I treated the problem as a chain of functions: Sort, Trim, and Filter.

The filter function treats the problem as a function of 2 lists; what has been filtered and what has yet to be filtered.

It is not a recursive function, but iterates over the filter function until nothing left to filter.

It takes a tuple composed of filtered and yet-to-be-filtered and returns a new tuple whose contents depend on whether the function of the last element of the filtered, and the first of the unfiltered, contains one, or other differences.
