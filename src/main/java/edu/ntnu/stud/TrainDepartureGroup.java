package edu.ntnu.stud;

import java.util.HashMap;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a train departure group.
 */
public class TrainDepartureGroup {
  private HashMap<Integer, TrainDeparture> trainDepartureGroup = new HashMap<>();




  /**
   * Adds a train departure to the group.
   *
   * @param trainDeparture the train departure to add
   */
  public void addTrainDeparture(TrainDeparture trainDeparture) {
    trainDepartureGroup.put(trainDeparture.getTrainNumber(), trainDeparture);
  }

  public void removeTrainDeparture(int trainNumber) {
    trainDepartureGroup.remove(trainNumber);
  }

  public TrainDeparture getTrainDepartureByTrainNumber(int trainNumber) {
    return trainDepartureGroup.get(trainNumber);
  }

  public TrainDeparture getTrainDepartureByDestination(String destination) {
    for (TrainDeparture trainDeparture : trainDepartureGroup.values()) {
      if (trainDeparture.getDestination().equals(destination)) {
        return trainDeparture;
      }
    }
    return null;
  }

  public void removeTrainDeparturesByTimeBefore(int hour, int minute) {
    for (TrainDeparture trainDeparture : trainDepartureGroup.values()) {
      if (trainDeparture.getDepartureTime().getHour() < hour) {
        trainDepartureGroup.remove(trainDeparture.getTrainNumber());
      } else if (trainDeparture.getDepartureTime().getHour() == hour) {
        if (trainDeparture.getDepartureTime().getMinute() < minute) {
          trainDepartureGroup.remove(trainDeparture.getTrainNumber());
        }
      }
    }
  }

  public List<TrainDeparture> getTrainDepartureGroupByTimeAscending() {
    return trainDepartureGroup.values()
            .stream()
            .sorted(Comparator.comparing(TrainDeparture::getDepartureTime))
            .collect(Collectors.toList());
  }



}