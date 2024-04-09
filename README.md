# GraphQL 101 - An Introduction to GraphQL in Java

## Purpose

This is a small project intended to introduce GraphQL to Java developers.

The project provides a baseline Spring Boot application with test data, ready for developers
to begin their GraphQL journey.

If you are a developer participating in a workshop that uses this project, please proceed
through the [Setting Up](#setting-up) section prior to the beginning of the workshop.

## Setting Up

This section will guide you through setting up and running the project.

It is assumed that you already have Java and Git installed.

This project uses Maven Wrapper.  You are welcome to use Gradle, Gradle Wrapper or
any other build engine.


### 1. Clone the repository

In a command-line window, navigate to a directory where the project will be stored, then run:

```
git clone https://github.com/jaywagnon/graphql101.git
cd graphql101
```

### 2. Compile the project

In the project directory, run:

```
./mvnw clean compile
```

The project should compile completely.

### 3. Run the project

In the project directory, run:

```
./mvnw spring-boot:run
```

The application should start up and begin listening for requests on port `9000`.

To verify that the application is running, open http://localhost:9000/actuator/health 
in a browser.  The page should show "status: UP".

To verify that the in-memory database is running, open http://localhost:9000/h2-console 
in a browser.  A log-in page should be displayed.  Use `sa` as the username and 
`jdbc:h2:mem:graphql101` as the JDBC URL (there is no password).  You should see 
the in-memory database with a few tables. Each table should have some pre-defined data.

You can stop the application by pressing `CTRL-C`.

### 4. Open the project in your preferred code editor

Open the `graphql101` project in your favorite code editor.  Make sure you can run
the project from your editor, if you will not be using the command-line.

### 5. Add some GraphQL!

You are ready to go!

Follow along in a workshop or read the [wiki](https://github.com/jaywagnon/graphql101/wiki) for the next steps.
