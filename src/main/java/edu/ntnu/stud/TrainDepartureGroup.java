package edu.ntnu.stud;

import java.util.HashMap;

/**
 * Represents a train departure group.
 */
public class TrainDepartureGroup {
  private HashMap<Integer, TrainDeparture> trainDepartures = new HashMap<>();

  /**
   * Adds a train departure to the group.
   *
   * @param trainDeparture the train departure to add
   */
  public void addTrainDeparture(TrainDeparture trainDeparture) {
    trainDepartures.put(trainDeparture.getTrainNumber(), trainDeparture);
  }
}