package edu.ntnu.stud;

import java.time.LocalTime;

/**
 * Class for the user interface.
 */
public class UserInterface {


  /**
   * test.
   */
  public void init() {
    //Test values for TrainDeparture object
    TrainDeparture td1 = new TrainDeparture(LocalTime.of(15, 34), LocalTime.of(0, 5),
            "Bergen", "F4", 3434, 2);
    TrainDeparture td2 = new TrainDeparture(LocalTime.of(18, 43), null,
            "Stavanger", "F4", 3434, 0);
    TrainDeparture td3 = new TrainDeparture(LocalTime.of(11, 12), LocalTime.of(0, 0),
            "Helvete", "F4", 3434, 0);

    //Displaying test values
    System.out.println(td1);
    System.out.println(td2);
    System.out.println(td3);
  }

  /**
   * test.
   */
  public void start() {
    displayAppTitle();
    displayMainMenu();
  }

  public void displayAppTitle() {
    System.out.println("--- Train Dispatch Application Alpha 0.1 ---\n");
  }
  public void displayMainMenu() {
    System.out.println("[1] - Display train departures");
    System.out.println("[2] - Add new train departure");
    System.out.println("[3] - Add new delay");
    System.out.println("[4] - Add delay");
    System.out.println("[5] - Search for train departure by train number");
    System.out.println("[6] - Search for train departure by destination");
    System.out.println("[7] - Update time");
    System.out.println("[9] - Exit application");

    // Rapport: Hvorfoor gå rett til 9? Hvorfor ikke 8? eller 0?
  }
}
