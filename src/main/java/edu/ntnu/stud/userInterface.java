package edu.ntnu.stud;

import java.time.LocalTime;

/**
 * Class for the user interface.
 */
public class userInterface {


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
    System.out.println(td1.toString());
    System.out.println(td2.toString());
    System.out.println(td3.toString());
  }

  /**
   * test.
   */
  public void start() {
  }
}
