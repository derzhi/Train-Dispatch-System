package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a group of train departures.
 * This class contains several method for manipulating a group.
 *
 * @author Adrian Aleksander Buczek
 * @since 0.2
 */
public class TrainDepartureGroup {
  private HashMap<Integer, TrainDeparture> trainDepartureGroup;

  public void validateTrainDepartureGroupDoesNotContainTrainNumberValue(int trainNumber)
          throws IllegalArgumentException {
    if (trainDepartureGroup.containsKey(trainNumber)) {
      throw new IllegalArgumentException("A train departure with this train number already exists,"
              + " please type in a another train number");
    }
  }

  /**
   * Constructs a new TrainDepartureGroup object which contains a
   * HashMap of TrainDeparture objects when initialized.
   */
  public TrainDepartureGroup() {
    trainDepartureGroup = new HashMap<>();
  }

  /**
   * Adds a train departure to the group.
   *
   * @param trainDeparture the train departure to add.
   */
  public void addTrainDeparture(TrainDeparture trainDeparture) {
    validateTrainDepartureGroupDoesNotContainTrainNumberValue(trainDeparture.getTrainNumber());
    trainDepartureGroup.put(trainDeparture.getTrainNumber(), trainDeparture);
  }

  /**
   * Gets a train departure by train number from train departure group.
   *
   * @param trainNumber the train number of a train you want to find.
   * @return the train departure with the given train number.
   */
  public TrainDeparture getTrainDepartureByTrainNumber(int trainNumber) {
    return trainDepartureGroup.get(trainNumber);
  }

  /**
   * Returns a list of TrainDepartures by a given destination in a
   * TrainDepartureGroup if they exist.
   *
   * @param destination the destination you want to search by
   * @return either a list of train departures or null.
   */
  public List<TrainDeparture> getTrainDepartureByDestination(String destination) {
    return trainDepartureGroup.values()
            .stream()
            .filter(trainDeparture -> trainDeparture.getDestination().equals(destination))
            .collect(Collectors.toList());
  }

  /**
   * Removes train departures from a train departure group that are before a certain time.
   *
   * @param time a LocalTime object selects the limit for what train departures to remove.
   */
  public void removeTrainDeparturesByTimeBefore(LocalTime time) {
    trainDepartureGroup.values()
            .removeIf(trainDeparture ->
                    trainDeparture.getDestinationTimePlusDelay().isBefore(time));
  }
  //TODO: What if there exists no train departures with the LocalTime provided?

  /**
   * Returns a list of train departures by the time ascending.
   *
   * @return a list of train departures by time ascending.
   */
  public List<TrainDeparture> getTrainDepartureGroupByTimeAscending() {
    return trainDepartureGroup.values()
            .stream()
            .sorted(Comparator.comparing(TrainDeparture::getDepartureTime))
            .collect(Collectors.toList());
  }

  /**
   * Prints out a list of train departures.
   *
   * @return a String of train departures seperated by a newline character.
   */
  @Override
  public String toString() {
    return trainDepartureGroup.values()
            .stream()
            .map(TrainDeparture::toString)
            .collect(Collectors.joining("\n"));
  }

}