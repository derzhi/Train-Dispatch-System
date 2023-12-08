package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

/**
 * Class for the user interface.
 */
public class UserInterface {

  private static LocalTime timeOfDay = LocalTime.of(0, 0);
  private static final Scanner scanner = new Scanner(System.in);
  private static final TrainDepartureGroup tdg = new TrainDepartureGroup();
  public void run() {
    boolean exit = false;
    printAppTitle();

    while (!exit) {
      printMainMenu();

      try {
        int choice = Integer.parseInt(scanner.nextLine());
        System.out.println();

        switch (choice) {
          case 1 -> printTrainDepartures();
          case 2 -> addNewTrainDeparture();
          case 3 -> setTrack();
          case 4 -> setDelay();
          case 5 -> searchByTrainNumber();
          case 6 -> searchByDestination();
          case 7 -> updateTime();
          case 9 -> exit = true;
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
    System.exit(0);
  }

  /**
   * test.
   */
  public void init() {
    //Test values for TrainDeparture object
    TrainDeparture td1 = new TrainDeparture(LocalTime.of(15, 34), LocalTime.of(0, 5),
            "Bergen", "F4", 1, 2);
    TrainDeparture td2 = new TrainDeparture(LocalTime.of(18, 43), LocalTime.of(0, 0),
            "Stavanger", "F4", 2, -1);
    TrainDeparture td3 = new TrainDeparture(LocalTime.of(11, 12), LocalTime.of(0, 0),
            "Helvete", "F4", 3, 4);
    TrainDeparture td4 = new TrainDeparture(LocalTime.of(11, 12), LocalTime.of(0, 0),
            "Helvete", "F4", 4, 5);

    //Test values for TrainDepartureGroup object
    tdg.addTrainDeparture(td1);
    tdg.addTrainDeparture(td2);
    tdg.addTrainDeparture(td3);
    tdg.addTrainDeparture(td4);
  }

  public void printAppTitle() {
    System.out.println("\n--- Train Dispatch Application Alpha 0.1 ---\n");
  }

  public void printTimeOfDay() {
    System.out.println("Time: [ " + timeOfDay + " ]\n");
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

  //TODO: Separate header and body?
  public void printTrainDepartures() {
    printTimeOfDay();
    printTrainDeparturesHeader();
    tdg.getTrainDepartureGroupByTimeAscending().forEach(System.out::println);
  }

  public void printTrainDeparturesHeader() {
    System.out.printf("%-15s | %-5s | %-13s | %-12s | %-7s | %-5s%n",
            "Departure Time", "Line", "Train Number", "Destination", "Delay", "Track");
    System.out.println("------------------------------------------------------------------------");
  }

  public void addNewTrainDeparture() {
    while (true) {
      try {
        LocalTime time = LocalTime.parse(getUserInputString("Type in departure time in a 00:00 format"));
        LocalTime delay = LocalTime.parse(getUserInputString("Type in delay in a 00:00 format"));
        String destination = getUserInputString("Type in destination");
        String line = getUserInputString("Type in line");
        int trainNumber = getUserInputInt("Type in train number");
        int track = getUserInputInt("Type in track");

        TrainDeparture newTrainDeparture = new TrainDeparture(time, delay, destination, line, trainNumber, track);
        
        tdg.addTrainDeparture(newTrainDeparture);
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

  public void setTrack() {
    while (true) {
      try {
        printTrainDepartures();
        int trainNumber = getUserInputInt("Type in train number");
        int track = getUserInputInt("Type in track");
        tdg.getTrainDepartureByTrainNumber(trainNumber).setTrack(track);
        System.out.println("Track set");
        break;
      } catch (Exception e) {
        System.out.println(e.getMessage());
        System.out.println("Not valid");
      }
    }
  }

  public void setDelay() {
    while (true) {
      try {
        printTrainDepartures();
        int trainNumber = getUserInputInt("Type in train number");
        LocalTime delay = LocalTime.parse(getUserInputString("Type in delay in a 00:00 format"));
        tdg.getTrainDepartureByTrainNumber(trainNumber).setDelay(delay);
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
        System.out.println(tdg.getTrainDepartureByTrainNumber(trainNumber));
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
        tdg.getTrainDepartureByDestination(destination).forEach(System.out::println);;
        break;
      } catch (Exception e) {
        System.out.println(e.getMessage());
        System.out.println("Not valid");
      }
    }
  }

  public String getUserInputString(String message) {
    System.out.println(message);
    return scanner.nextLine();
  }

  public int getUserInputInt(String message) {
    System.out.println(message);
    return Integer.parseInt(scanner.nextLine());
  }

}
