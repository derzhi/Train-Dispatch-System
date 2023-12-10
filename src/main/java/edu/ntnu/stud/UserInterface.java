package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.Scanner;

/**
 * The UserInterface class is responsible for the user interface of the application.
 * It is responsible for printing the main menu and interpreting user input.
 *
 * @author Adrian Aleksander Buczek
 * @version 0.3
 * @since 0.3
 */
public class UserInterface {
  private static final Scanner scanner = new Scanner(System.in);
  private TrainDepartureRegister departures;

  /**
   * Runs the main menu of the terminal. Prints the main menu and interprets user input
   * while validating that the input is a valid integer and that the command exists.
   * Application closes when user inputs option 9.
   */
  public void run() {
    printTrainArtwork();
    System.out.println("\n--- Train Dispatch Application Version 1.0 ---\n");

    mainMenu:
    while (true) {
      printMainMenu();

      try {
        int choice = Integer.parseInt(scanner.nextLine());
        System.out.println();

        if (choice == 1 || choice == 3 || choice == 4 || choice == 5 || choice == 6) {
          departures.assertDeparturesNotEmpty();
        }

        switch (choice) {
          case 1 -> printTrainDepartures();
          case 2 -> addNewTrainDeparture();
          case 3 -> updateTrack();
          case 4 -> updateDelay();
          case 5 -> searchByTrainNumber();
          case 6 -> searchByDestination();
          case 7 -> updateTime();
          case 9 -> {
            break mainMenu;
          }
          default -> System.out.println("Invalid choice, "
                  + "please select an option between 1-8 or 9 to exit the application");
        }

      } catch (Exception e) {
        System.out.println(e.getMessage());
        System.out.println("Input is not a valid integer ");

      }
      System.out.println();

    }
    System.out.println("Exiting Train Dispatch Application Version 1.0");

  }

  /**
   * Initializes the application with a registry with some train departures.
   */
  public void init() {
    departures = new TrainDepartureRegister();

    departures.addTrainDeparture(LocalTime.of(15, 30), LocalTime.of(0, 15), "Bergen", "A4", 1, 5);
    departures.addTrainDeparture(LocalTime.of(12, 30), LocalTime.of(0, 0), "Trondheim", "A4", 5, 5);
    departures.addTrainDeparture(LocalTime.of(11, 20), LocalTime.of(0, 5), "Trondheim", "L4", 3, 3);
    departures.addTrainDeparture(LocalTime.of(7, 21), LocalTime.of(0, 0), "Kragerø", "A4", 21, -1);
  }

  /**
   * Gets user input as a string and prints a message to the user.
   *
   * @param message message to be printed to the user
   * @return user input as a string
   */
  private String getUserInputString(String message) {
    System.out.println(message);
    return scanner.nextLine();
  }

  /**
   * Gets user input as an integer and prints a message to the user.
   *
   * @param message message to be printed to the user
   * @return user input as an integer
   */
  private int getUserInputInt(String message) {
    System.out.println(message);
    return Integer.parseInt(scanner.nextLine());
  }

  /**
   * This method adds a new train departure to the registry. It ensures that the
   * provided input for the new train departure is valid. The validation process
   * includes checking that the train's scheduled departure is not set before the
   * current time of day, ensuring there is no scheduling collision, and verifying
   * that a departure with the same train number does not already exist.
   *
   * @throws IllegalArgumentException if the departure time is before the current time of day,
   *                                  if there is a scheduling conflict, or if a train
   *                                  with the same number already has a scheduled departure.
   */
  private void addNewTrainDeparture() {
    while (true) {
      try {
        LocalTime departureTime = LocalTime.parse(getUserInputString("Type in departure time "
                + "in a hh:mm format"));
        LocalTime delay = LocalTime.parse(getUserInputString("Type in delay in a hh:mm format"));
        String destination = getUserInputString("Type in the destination");
        String line = getUserInputString("Type in the line");
        int trainNumber = getUserInputInt("Type in the train number");
        int track = getUserInputInt("Type in track, -1 if unassigned");

        departures.addTrainDeparture(departureTime, delay, destination, line, trainNumber, track);

        System.out.println("Train departure has been added\n");
        printTrainDeparturesHeader();
        System.out.println(departures.getTrainDepartureByTrainNumber(trainNumber));
        break;
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }

  private void updateTrack() {
    while (true) {
      try {
        printTrainDepartures();

        int trainNumber = getUserInputInt("Type in the train number");
        int track = getUserInputInt("Type in the track");

        departures.assertAndSetTrack(trainNumber, track);

        System.out.println("Track for train with train number " + trainNumber
                + " has been set to track " + track);
        break;
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }

  private void updateDelay() {
    while (true) {
      try {
        printTrainDepartures();

        int trainNumber = getUserInputInt("Type in the train number");
        LocalTime delay = LocalTime.parse(getUserInputString("Type in delay in a hh:mm format"));

        departures.assertAndSetDelay(trainNumber, delay);

        System.out.println("Delay for train with train number " + trainNumber
                + "has been set to " + delay);
        break;
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }

  private void searchByTrainNumber() {
    while (true) {
      try {
        int trainNumber = getUserInputInt("Type in a train number");

        printTrainDeparturesHeader();
        System.out.println(departures.getTrainDepartureByTrainNumber(trainNumber));
        break;
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }

  private void searchByDestination() {
    while (true) {
      try {
        String destination = getUserInputString("Type in a destination");

        printTrainDeparturesHeader();
        departures.getDeparturesByDestination(destination).forEach(System.out::println);
        break;
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }

  private void updateTime() {
    while (true) {
      try {
        LocalTime newTime = LocalTime.parse(getUserInputString("Type in new time in hh:mm format,"
                + " must be after current time of day"));

        departures.setTimeOfDay(newTime);
        break;
      } catch (Exception e) {
        System.out.println(e.getMessage());
        System.out.println("Not valid");
      }
    }

  }

  /**
   * Prints the train artwork.
   * Source: <a href="https://www.asciiart.eu/vehicles/trains">...</a>
   */
  private void printTrainArtwork() {
    System.out.println("""
                        
            ________     _______________________________________^__
             ___   ___ |||  ___   ___   ___    ___ ___  |   __  ,----\\
            |   | |   |||| |   | |   | |   |  |   |   | |  |  | |_____|
            |___| |___|||| |___| |___| |___|  | O | O | |  |  |        \\
                       |||                    |___|___| |  |__|         )
            ___________|||______________________________|______________/""");
  }

  /**
   * Prints the main menu.
   */
  private void printMainMenu() {
    System.out.println("""
            [1] - Display train departures
            [2] - Add new train departure
            [3] - Set track
            [4] - Set delay
            [5] - Search for a train departure by train number
            [6] - Search for a train departure by destination
            [7] - Update time
            [9] - Exit application
                
            Type in a number of an option below and press enter:""");
  }

  /**
   * Prints the time of day.
   */
  private void printTimeOfDay() {
    System.out.println("Time: [ " + departures.getTimeOfDay() + " ]\n");
  }

  /**
   * Prints the train departure header.
   */
  private void printTrainDeparturesHeader() {
    System.out.printf("%-15s | %-5s | %-13s | %-12s | %-7s | %-5s%n",
            "Departure Time", "Line", "Train Number", "Destination", "Delay", "Track");
    System.out.println("------------------------------------------------------------------------");
  }

  /**
   * Prints train departures in ascending order by totalTime.
   */
  private void printTrainDepartures() {
    printTimeOfDay();
    printTrainDeparturesHeader();
    departures.getDepartures().forEach(System.out::println);
  }

}
