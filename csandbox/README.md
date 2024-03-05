# CsandBox

This project is an application with a sandbox structure created in Java.

This application allows you to run your application in an environment with specific access restrictions.

## Feature Summary

- **Create a sandbox structure**: Provide a unique environment for each user with specific access restrictions.
- **Configure permissions for specific applications**: allow access to specific applications, e.g. `/media/*`.
- **Sandbox Administration**: Only the root user can administer the sandbox.
- **Implementation of a 7-layer sandbox structure**: allows for additional sandboxes to be created within the sandbox.
- **SHA256 encryption**: used to ensure the integrity of data processed within the sandbox.

## Setup.

### What you will need

- Java Development Kit (JDK) 1.8 or higher
- Git (used to clone source code)

### Installation

Clone the repository.
```
git clone https://github.com/SugiSaku8/ScoolToolKit.git
```

Move to the project directory.
cd ScoolToolKit/Csandbox

3. resolve dependencies (if you are using Maven or Gradle for this project)
```
mvn clean install
```
or
```
gradle build
```

## How to use

1. compile the application.
```
javac sandbox.java 
```
2. Run the application.
```
java sandbox
```

## Testing

Run the test script to verify the functionality of the application.

```
mvn test
```

## Contribute

Bug reports and feature requests are welcome in the [Issues](https://github.com/SugiSaku8/ScoolToolKit/issues) section.

Pull requests can be submitted through the [Pull Requests](https://github.com/SugiSaku8/ScoolToolKit/pulls) section.



## Contact

For more information about the project, you are welcome to contact us at [Github](https://github.com/SugiSaku8/ScoolToolKit).

## Copyright
©Carnation 2024 All rights reserved
