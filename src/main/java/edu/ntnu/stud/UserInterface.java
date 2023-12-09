package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.Scanner;

/**
 * Class for the user interface.
 */
public class UserInterface {
  private static final Scanner scanner = new Scanner(System.in);
  private TrainDepartureManager departures;

  /**
   * Runs the main menu of the terminal. Prints the main menu and interprets user input
   * while validating that the input is a valid integer and that the command exists.
   * Application closes when user inputs option 9.
   */
  public void run() {
    printTrainArtwork();
    printAppTitle();

    commandLine:
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
            break commandLine;
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
    System.out.println("Exiting Train Dispatch Application Alpha 0.1");
  }

  /**
   * test.
   */
  public void init() {
    departures = new TrainDepartureManager();

    TrainDeparture td1 = new TrainDeparture(LocalTime.of(15, 30), LocalTime.of(0, 15), "Bergen", "A4", 1, 5);
    TrainDeparture td2 = new TrainDeparture(LocalTime.of(12, 30), LocalTime.of(0, 0), "Trondheim", "A4", 5, 5);
    TrainDeparture td3 = new TrainDeparture(LocalTime.of(11, 20), LocalTime.of(0, 5), "Trondheim", "L4", 3, 3);
    TrainDeparture td4 = new TrainDeparture(LocalTime.of(07, 21), LocalTime.of(0, 0), "Kragerø", "A4", 21, -1);

    departures.addTrainDeparture(td1);
    departures.addTrainDeparture(td2);
    departures.addTrainDeparture(td3);
    departures.addTrainDeparture(td4);
  }

  private void printTrainArtwork() {
    System.out.println("___________   _______________________________________^__");
    System.out.println(" ___   ___ |||  ___   ___   ___    ___ ___  |   __  ,----\\");
    System.out.println("|   | |   |||| |   | |   | |   |  |   |   | |  |  | |_____|");
    System.out.println("|___| |___|||| |___| |___| |___|  | O | O | |  |  |        \\");
    System.out.println("           |||                    |___|___| |  |__|         )");
    System.out.println("___________|||______________________________|______________/");
  }

  public void printAppTitle() {
    System.out.println("\n--- Train Dispatch Application Alpha 0.1 ---\n");
  }

  public void printTimeOfDay() {
    System.out.println("Time: [ " + departures.getTimeOfDay() + " ]\n");
  }

  public void printMainMenu() {
    System.out.println("[1] - Display train departures");
    System.out.println("[2] - Add new train departure");
    System.out.println("[3] - Set track");
    System.out.println("[4] - Set delay");
    System.out.println("[5] - Search for a train departure by train number");
    System.out.println("[6] - Search for a train departure by destination");
    System.out.println("[7] - Update time");
    System.out.println("[9] - Exit application\n");

    System.out.println("Type in a number of an option below and press enter:");
  }

  public void printTrainDepartures() {
    printTimeOfDay();
    printTrainDeparturesHeader();
    departures.getDepartures().forEach(System.out::println);
  }

  public void printTrainDeparturesHeader() {
    System.out.printf("%-15s | %-5s | %-13s | %-12s | %-7s | %-5s%n",
            "Departure Time", "Line", "Train Number", "Destination", "Delay", "Track");
    System.out.println("------------------------------------------------------------------------");
  }

  public void addNewTrainDeparture() {
    while (true) {
      try {
        LocalTime departureTime = LocalTime.parse(getUserInputString("Type in departure time "
                + "in a 00:00 format"));
        LocalTime delay = LocalTime.parse(getUserInputString("Type in delay in a 00:00 format"));
        String destination = getUserInputString("Type in destination");
        String line = getUserInputString("Type in line");
        int trainNumber = getUserInputInt("Type in train number");
        int track = getUserInputInt("Type in track");

        departures.assertUniqueDepartureScheduling(departureTime
                .plusHours(delay.getHour())
                .plusMinutes(delay.getMinute()), line, track);
        //TODO final time function is already in TrainDeparture

        TrainDeparture newTrainDeparture = new TrainDeparture(departureTime, delay, destination,
                line, trainNumber, track);
        departures.addTrainDeparture(newTrainDeparture);

        System.out.println("Train departure added\n");
        printTrainDeparturesHeader();
        System.out.println(newTrainDeparture);
        break;
      } catch (Exception e) {
        System.out.println(e.getMessage());
        System.out.println("Not valid");
      }
    }
  }

  public void updateTrack() {
    while (true) {
      try {
        printTrainDepartures();
        int trainNumber = getUserInputInt("Type in train number");
        int track = getUserInputInt("Type in track");
        departures.getTrainDepartureByTrainNumber(trainNumber).setTrack(track);
        System.out.println("Track set");
        break;
      } catch (Exception e) {
        System.out.println(e.getMessage());
        System.out.println("Not valid");
      }
    }
  }

  public void updateDelay() {
    while (true) {
      try {
        printTrainDepartures();
        int trainNumber = getUserInputInt("Type in train number");
        LocalTime delay = LocalTime.parse(getUserInputString("Type in delay in a 00:00 format"));
        departures.getTrainDepartureByTrainNumber(trainNumber).setDelay(delay);
        System.out.println("Delay set");
        break;
      } catch (Exception e) {
        System.out.println(e.getMessage());
        System.out.println("Not valid");
      }
    }
  }

  public void searchByTrainNumber() {
    while (true) {
      try {
        int trainNumber = getUserInputInt("Type in train number");
        System.out.println(departures.getTrainDepartureByTrainNumber(trainNumber));
        break;
      } catch (Exception e) {
        System.out.println(e.getMessage());
        System.out.println("Not valid");
      }
    }
  }

  public void searchByDestination() {
    while (true) {
      try {
        String destination = getUserInputString("Type in destination");
        departures.getTrainDepartureByDestination(destination).forEach(System.out::println);
        break;
      } catch (Exception e) {
        System.out.println(e.getMessage());
        System.out.println("Not valid");
      }
    }
  }

  public void updateTime() {
    while (true) {
      try {
        LocalTime newTime = LocalTime.parse(getUserInputString("Type in new time in hh:mm format,"
                + " must be after current time of day"));
        departures.setTimeOfDay(newTime);
        departures.removeTrainDeparturesByTimeBefore(newTime);
        break;
      } catch (Exception e) {
        System.out.println(e.getMessage());
        System.out.println("Not valid");
      }
    }

  }

  private String getUserInputString(String message) {
    System.out.println(message);
    return scanner.nextLine();
  }

  private int getUserInputInt(String message) {
    System.out.println(message);
    return Integer.parseInt(scanner.nextLine());
  }

}
