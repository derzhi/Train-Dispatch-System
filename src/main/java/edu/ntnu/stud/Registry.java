package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a group of train departures with a time of day.
 * This class contains several methods for manipulating a group and validation.
 *
 * @author Adrian Aleksander Buczek
 * @version 0.3
 * @since 0.2
 */
public class Registry {
  private final HashMap<Integer, TrainDeparture> departures;
  private LocalTime timeOfDay;

  /**
   * Constructs a new TrainDepartureRegister object.
   * Upon initialization it sets the time of day to 00:00 and creates a new HashMap.
   */
  public Registry() {
    this.timeOfDay = LocalTime.of(0, 0);
    this.departures = new HashMap<>();
  }

  /**
   * Returns the time of day of an existing TrainDepartureRegister.
   *
   * @return a LocalTime object containing the time of day
   */
  public LocalTime getTimeOfDay() {
    return timeOfDay;
  }

  /**
   * Returns a List of train departures by the time ascending.
   *
   * @return a List of train departures by time ascending.
   */
  public List<TrainDeparture> getDepartures() {
    return departures
            .values()
            .stream()
            .sorted(Comparator.comparing(TrainDeparture::getFinalDepartureTime))
            .collect(Collectors.toList());
  }

  /**
   * Returns a list of TrainDepartures by a given destination in a ascending order
   * if train departures exist.
   *
   * @param destination the destination you want to search by
   * @return either a list of train departures or null.
   */
  public List<TrainDeparture> getDeparturesByDestination(String destination) {
    return departures
            .values()
            .stream()
            .filter(trainDeparture -> trainDeparture.getDestination().equals(destination))
            .toList();

  }

  /**
   * Gets a train departure by train number from train departure manager.
   *
   * @param trainNumber the train number of a train you want to find
   * @return the train departure with the given train number
   * @throws IllegalArgumentException if TrainNumber does not exist
   */
  public TrainDeparture getTrainDepartureByTrainNumber(int trainNumber) {
    assertTrainNumberExists(trainNumber);
    return departures.get(trainNumber);
  }

  /**
   * Adds a train departure to the departures. The departure is added if the departure time
   * is not before the time of day, if the departure time and line is unique,
   * and if the train number is unique.
   *
   * @param departureTime a LocalTime object of a trains departure time.
   * @param delay         a LocalTime object of a trains delay.
   * @param destination   a String object of a trains destination.
   * @param line          a String object of a trains line.
   * @param trainNumber   an integer of a trains train number.
   * @param track         an integer of the track the train is supposed to arrive at, -1 means that
   *                      the train is unassigned.
   * @throws IllegalArgumentException if final departure time is before time of day, if there exists
   *                                  a train departure with the same departure time and line/track,
   *                                  or if a train departure with the same train number already
   *                                  exists.
   */
  public void addTrainDeparture(LocalTime departureTime, LocalTime delay, String destination,
                                String line, int trainNumber, int track)
          throws IllegalArgumentException {

    LocalTime finalDepartureTime = departureTime
            .plusHours(delay.getHour()).plusMinutes(delay.getMinute());

    assertDepartureTimeIsNotBeforeTimeOfDay(finalDepartureTime);
    assertUniqueDepartureScheduling(finalDepartureTime, line, track);
    assertTrainNumberDoesNotExist(trainNumber);

    TrainDeparture newTrainDeparture = new TrainDeparture(
            departureTime, delay, destination, line, trainNumber, track
    );

    departures.put(trainNumber, newTrainDeparture);
  }

  /**
   * Removes train departures from a train departure manager that are before a certain time.
   *
   * @param time a LocalTime object selects the limit for what train departures to remove.
   */
  private void removeTrainDeparturesByTimeBefore(LocalTime time) {
    departures.values()
            .removeIf(trainDeparture ->
                    trainDeparture.getFinalDepartureTime().isBefore(time));
  }

  /**
   * Sets the time of day of an existing TrainDepartureRegister
   * if new time of day is after current time of day.
   *
   * @param newTime a LocalTime object of the new time the time of day is to be set to
   * @throws IllegalArgumentException if newTime is before current timeOfDay
   */
  public void setTimeOfDay(LocalTime newTime) throws IllegalArgumentException {
    assertTimeOfDayIsAfterCurrentTimeOfDay(newTime);
    removeTrainDeparturesByTimeBefore(newTime);
    this.timeOfDay = newTime;
  }

  /**
   * Returns true if departures contain the trainNumber.
   *
   * @param trainNumber the trainNumber of a train
   * @return true if departures contain the trainNumber, otherwise false
   */
  private boolean doesTrainNumberExist(int trainNumber) {
    return departures.containsKey(trainNumber);
  }

  /**
   * Checks if a departure exists with a specified final departure time and train line.
   *
   * @param finalDepartureTime a LocalTime object adjusted departure time accounting for any delays.
   * @param line               a String object of a train departure line.
   * @return true if a departure matches both the finalDepartureTime and line, otherwise false
   */
  private boolean existsDepartureWithTimeAndLine(LocalTime finalDepartureTime, String line) {
    return departures
            .values()
            .stream()
            .anyMatch(trainDeparture ->
                    trainDeparture.getFinalDepartureTime().equals(finalDepartureTime)
                            && trainDeparture.getLine().equals(line));
  }

  /**
   * Checks if a departure exists with a specified final departure time and train track,
   * unless track is unassigned (-1).
   *
   * @param finalDepartureTime a LocalTime object adjusted departure time accounting for any delays
   * @param track              the track the train is supposed to arrive at,
   *                           -1 means that the train is unassigned
   * @return true if a departure matches both the finalDepartureTime and track, unless track is -1
   */
  private boolean existsDepartureWithTimeAndLine(LocalTime finalDepartureTime, int track) {
    if (track == -1) {
      return true;
    }

    return departures.values()
            .stream()
            .noneMatch(trainDeparture -> trainDeparture.getFinalDepartureTime()
                    .equals(finalDepartureTime) && trainDeparture.getTrack() == track);
  }

  /**
   * Asserts that final departure time and line is a unique combination in departures.
   *
   * @param finalDepartureTime a LocalTime object adjusted departure time accounting for any delays
   * @param line               a String object of a train departure line.
   * @throws IllegalArgumentException if there exists a train departure with
   *                                  the same departure time and line.
   */
  private void assertLineNotOccupied(LocalTime finalDepartureTime, String line)
          throws IllegalArgumentException {
    if (existsDepartureWithTimeAndLine(finalDepartureTime, line)) {
      throw new IllegalArgumentException("A train departure with this departure time and line "
              + "already exists");
    }
  }

  /**
   * Asserts that final departure time and track is a unique combination in departures.
   * Unless track is unassigned (-1).
   *
   * @param finalDepartureTime a LocalTime object adjusted departure time accounting for any delays
   * @param track              the track the train is supposed to arrive at,
   *                           -1 means that the train is unassigned
   * @throws IllegalArgumentException if there exists a train departure with the same
   *                                  departure time and track. Unless the track is unassigned (-1).
   */
  private void assertTrackNotOccupied(LocalTime finalDepartureTime, int track)
          throws IllegalArgumentException {
    if (!existsDepartureWithTimeAndLine(finalDepartureTime, track)) {
      throw new IllegalArgumentException("A train departure with this departure time and track "
              + "already exists");
    }
  }

  /**
   * Asserts that a train number exists in departures.
   *
   * @param trainNumber the train number that is checked to exist
   * @throws IllegalArgumentException if a train number does not exist
   */
  private void assertTrainNumberExists(int trainNumber) throws IllegalArgumentException {
    if (!doesTrainNumberExist(trainNumber)) {
      throw new IllegalArgumentException("A train departure with this train number does not exist"
              + " please type in another train number");
    }
  }

  /**
   * Asserts that a train number does not exists in departures.
   *
   * @param trainNumber the train number that is checked to not exist
   * @throws IllegalArgumentException if a train number does exist
   */
  private void assertTrainNumberDoesNotExist(int trainNumber)
          throws IllegalArgumentException {
    if (doesTrainNumberExist(trainNumber)) {
      throw new IllegalArgumentException("A train departure with this train number already exists,"
              + " please type in a another train number");
    }
  }

  /**
   * Asserts that the specified new time of day is later than the current time of day.
   *
   * @param newTimeOfDay the time to be compared against the current time of day.
   * @throws IllegalArgumentException if the new time of day is before the current time of day.
   */
  private void assertTimeOfDayIsAfterCurrentTimeOfDay(LocalTime newTimeOfDay)
          throws IllegalArgumentException {
    if (newTimeOfDay.isBefore(timeOfDay)) {
      throw new IllegalArgumentException("New time " + newTimeOfDay
              + " must be after current time of day " + timeOfDay);
    }
  }

  /**
   * Asserts the uniqueness of a departure schedule in departures
   * based on its time, line, and track.
   *
   * @param departureTime a LocalTime object adjusted departure time accounting for any delays
   * @param line          a String object of a train departure line.
   * @param track         an integer of the track the train is supposed to arrive at,
   *                      -1 means that the train is unassigned
   */
  public void assertUniqueDepartureScheduling(LocalTime departureTime, String line, int track) {
    assertLineNotOccupied(departureTime, line);
    assertTrackNotOccupied(departureTime, track);
  }

  /**
   * Asserts that a track is not occupied by another train departure at the same time,
   * unless the track is unassigned (-1). After the assertion is made, the track is set.
   *
   * @param trainNumber the train number of the train departure to be updated.
   * @param track       the track the train is supposed to arrive at,
   *                    -1 means that the train is unassigned
   * @throws IllegalArgumentException if the track is occupied by another train departure
   *                                  at the same time.
   */
  public void assertAndSetTrack(int trainNumber, int track) throws IllegalArgumentException {
    TrainDeparture selected = getTrainDepartureByTrainNumber(trainNumber);
    assertTrackNotOccupied(selected.getFinalDepartureTime(), track);
    selected.setTrack(track);
  }

  /**
   * Asserts that that the new time will not collide with any other train that has
   * the same line nad the same track. After the assertion is made, the delay is set.
   *
   * @param trainNumber the train number of the train departure to be updated.
   * @param delay       the delay to be added to the departure time.
   */
  public void assertAndSetDelay(int trainNumber, LocalTime delay) {
    TrainDeparture selected = getTrainDepartureByTrainNumber(trainNumber);

    LocalTime finalDepartureTime = selected
            .getDepartureTime().plusHours(delay.getHour()).plusMinutes(delay.getMinute());
    assertUniqueDepartureScheduling(finalDepartureTime, selected.getLine(), selected.getTrack());

    selected.setDelay(delay);
  }

  /**
   * Asserts that departure time is not before time of day.
   *
   * @param departureTime a LocalTime object of a trains departure time.
   * @throws IllegalArgumentException if departure time is before time of day.
   */
  private void assertDepartureTimeIsNotBeforeTimeOfDay(LocalTime departureTime)
          throws IllegalArgumentException {
    if (departureTime.isBefore(timeOfDay)) {
      throw new IllegalArgumentException("Departure time must not be after time of day");
    }
  }

  /**
   * Asserts that departures is not empty.
   *
   * @throws IllegalArgumentException if departures is empty.
   */
  public void assertDeparturesNotEmpty() throws IllegalArgumentException {
    if (departures.isEmpty()) {
      throw new IllegalArgumentException("Option is not available because "
              + "no train departures exist. Please add a train departure to use this option.");
    }
  }

  /**
   * Prints out a list of train departures.
   *
   * @return a String of train departures seperated by a newline character.
   */
  @Override
  public String toString() {
    return departures.values()
            .stream()
            .map(TrainDeparture::toString)
            .collect(Collectors.joining("\n"));
  }

}