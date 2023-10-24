package edu.ntnu.stud;

import java.time.LocalTime;

/**
 * This is the main class for the train dispatch application.
 */
public class TrainDispatchApp {
  public static void main(String[] args) {
    TrainDispatch a = new TrainDispatch(LocalTime.of(15, 34), LocalTime.of(0, 5),
            "Bergen", "L4", 343, 4);
  }


}
