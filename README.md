User Guide

This application displays the street and building name (if present) for a given postcode.

To operate:
Navigate to the project root directory:

If you have gradle installed you can run:
gradle clean bootRun

Or you can run the wrapper:
gradlew clean bootRun

The application should start on port 8080

To view address information enter the following url replacing 'postcode' for your desired postcode:

localhost:8080/address/postcode
