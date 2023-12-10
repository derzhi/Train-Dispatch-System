# Portfolio project IDATA1003 - 2023

STUDENT NAME = Adrian Aleksander Buczek<br>
STUDENT ID = 111798

## Project description

A Java-based train dispatch application for managing train departures in a terminal.
The application is set to represent a train station within a 24-hour
operational window.

Key functionalities of the application include:

<ul>
    <li>Displaying departures</li>
    <li>Adding new departures</li>
    <li>Setting the track of a departure</li>
    <li>Setting the delay of a departure</li>
    <li>Searching for departures by a destination</li>
    <li>Searching for a departure by a train number</li>
    <li>Updating the time of day</li>
</ul>

## Project structure

### Source Code

The source code of the application is housed within the `src` directory, which is divided into two main subdirectories:

- `main/java/edu/ntnu/stud`: This directory contains the Java application's core source files.
    - `App.java`: Responsible for launching the application.
    - `Registry.java`: Manages the data and operations related to a registry of train departures.
    - `TrainDeparture.java`: Represents a train departure with relevant details.
    - `UserInterface.java`: Handles the user interaction for managing train departures.

### Tests

The tests for the application are organized within the `src` directory under `test`:

- `test/java/edu/ntnu/stud`: Contains the JUnit-test classes for the application.
    - `RegistryTest.java`: Includes tests for the Registry class.
    - `TrainDepartureTest.java`: Contains tests for the TrainDeparture class.

## Link to repository

Link to repository: https://github.com/derzhi/Train-Dispatch-System

## How to run the project

### Prerequisites

- Java Development Kit (JDK) 1.8 or above must be installed.
- Apache Maven must be installed.

### Running the Application

1. Open a terminal or command prompt.
2. Change the directory to the project root where the `pom.xml` file is located.
    ```sh
    cd path_to_project_folder
    ```
3. Use Maven to compile and run the application with the following commands:
    ```sh
    mvn clean install
    mvn exec:java -D"exec.mainClass"="edu.ntnu.stud.App"
    ```

## How to run the tests

This project uses Maven to manage the running of tests, which ensures that any developer can easily run the tests in a
consistent environment. To run the tests for the project, you'll need to have Maven installed and configured on your
system.

Follow these steps to execute the tests:

1. Open a terminal or command prompt.
2. Navigate to the root directory of the project where the `pom.xml` file is located.
3. Run the following command:

```bash
mvn test
```