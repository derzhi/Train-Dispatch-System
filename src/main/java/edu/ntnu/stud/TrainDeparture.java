package edu.ntnu.stud;

import java.time.LocalTime;

/**
 * <h1>TrainDeparture class</h1><br>
 *
 * <p><b>Role/Responsibility:</b></p>
 * This class is designed to model train departures. It offers a constructor to create
 * train departures and methods to access and set attributes of a TrainDeparture object.
 * This constructor verifies the input of the parameters entered, and throws exceptions
 * of illegal data.<br><br>
 *
 * <p><b>Information stored in a TrainDeparture object:</b></p>
 * The train Departure application contains information about the departure time, delay time,
 * destination, line, train number, and the track a train is expected to arrive at. An object
 * variable is designated for each piece of information about a train departure, ensuring all
 * necessary information is separable and easily accessible.<br><br>
 *
 * <p><b>Selection of datatypes:</b></p>
 * <ul>
 *     <li>The {@code LocalTime} class was chosen as the datatype for departureTime and delay
 *     to capture the necessary details, which are hours and minutes in addition to having methods
 *     for validation</li>
 *     <li>The destination and line attributes are represented using the {@code String} class,
 *     accommodating the need for a longer text containing both characters and numbers, especially
 *     for the line attribute.</li>
 *     <li>The train and track number required positive integers, potentially ranging from two
 *     to four digits. The primitive datatype {@code int} was selected, given its adequate range
 *     and the absence of memory constraints to be concerned about in this context.</li>
 * </ul><br><br>
 *
 * <p><b>Immutable attributes after object creation:</b></p>
 * The following attributes are considered immutable after a TrainDeparture object is created:
 * <ul>
 *     <li>{@code departureTime} - Set during object creation and reflects when a train is
 *     stated to depart. The train should depart either at the time set for departure or it gets
 *     a delay. Therefore the departureTime should be immutable, and the delay will take care of
 *     any delay information.</li>
 *     <li>{@code destination}, {@code line}, {@code trainNumber}  - These values should remain
 *     constant for clarity and ease of reference. There are also no functionality requirement to
 *     edit this information for a train departure</li>
 * </ul><br><br>
 *
 * <p><b>Mutable attributes after object creation:</b></p>
 * Certain attributes can be changed after the TrainDeparture object has been instantiated:
 * <ul>
 *     <li>{@code delay} - Can be altered based on application functionalities
 *     to set the delay.</li>
 *     <li>{@code track} - Can be altered based on application functionalities
 *     to set the track.</li>
 * </ul><br><br>
 *
 * <p><b>Handling invalid data:</b></p>
 * This class takes steps to validate data upon the creation of a TrainDeparture object:
 * <ul>
 *     <li>{@code departureTime} - Expected in the format hh:mm, where hours (hh) range
 *     from 00 to 24 and minutes (mm) from 00 to 59.</li>
 *     <li>{@code delay} - Abides by the same format rules as departureTime. A default
 *     value of 00:00 is assigned if not explicitly set.</li>
 *     <li>{@code destination} - A non-empty String with more than one character, exclusively
 *     consisting of letters.</li>
 *     <li>{@code line} - Though not rigidly defined in the assignment, it appears to consist
 *     of a character followed by a digit and cannot be empty.</li>
 *     <li>{@code trainNumber} - Must be a positive integer of a certain range.</li>
 *     <li>{@code track} - A positive integer, but values beyond certain thresholds or of
 *     incorrect datatype will trigger exceptions.</li>
 * </ul><br><br>
 *
 * @author Adrian Aleksander Buczek
 * @version 0.1
 * @since 0.1
 */
public class TrainDeparture {
  private final LocalTime departureTime;
  private LocalTime delay;
  private final String destination;
  private final String line;
  private final int trainNumber;
  private int track;

  /**
   * Validates a string parameter to ensure it is not blank.
   *
   * @param parameter the string parameter to validate
   * @throws IllegalArgumentException if the string is blank
   */
  private void validateStringParameter(String parameter) throws IllegalArgumentException {
    if (parameter.isBlank()) {
      throw new IllegalArgumentException("The string for the parameter was a blank string, "
              + "please retry.");
    }
  }

  /**
   * Validates an integer parameter to ensure it is not negative.
   *
   * @param parameter the integer parameter to validate
   * @throws IllegalArgumentException if the integer is negative
   */
  private void validatePositiveIntegerParameter(int parameter) throws IllegalArgumentException {
    if (parameter < 1) {
      throw new IllegalArgumentException("The integer for the parameter was negative, "
              + "please retry.");
    }
  }

  /**
   * Validates an integer parameter to ensure it bigger than zero or equal to negative one.
   *
   * @param parameter the integer parameter to validate
   * @throws IllegalArgumentException if the integer is not bigger than zero
   *                                  or equal to negative one.
   */
  private void validatePositiveIntegerOrNegativeOne(int parameter) throws IllegalArgumentException {
    if (parameter < 1 && parameter != -1) {
      throw new IllegalArgumentException("The integer for the parameter was not over 0 or -1, "
              + "please retry.");
    }
  }

  /**
   * Constructs a TrainDeparture object by a assigning the parameters below as
   * attributes of the Item. Does this only after verifying that all the parameters are valid.
   *
   * @param departureTime a LocalTime parameter representing the departure time of a train
   *                      in a hh:mm format.
   * @param delay         a LocalTime parameter represent the delay time after the departure time.
   *                      If is not delayed it is set to 00:00.
   * @param destination   a String parameter representing the destination of a train.
   * @param line          a String parameter representing the line of a train.
   * @param trainNumber   a positive integer representing a unique train number.
   * @param track         A int parameter that represent the track where the train is supposed to
   *                      arrive at. If a train does not have a track assigned, it is set to -1.
   *
   * @throws IllegalArgumentException if any of the String parameters are blank, trainNumber is not
   *                                  a positive integer or if the track parameter is not a positive
   *                                  integer or -1.
   */

  //TODO: can destination only contain letters and not numbers?
  //TODO: LocalTime has methods for manipulation?
  public TrainDeparture(LocalTime departureTime, LocalTime delay, String destination,
                        String line, int trainNumber, int track) throws IllegalArgumentException {
    validateStringParameter(destination);
    validateStringParameter(line);
    validatePositiveIntegerParameter(trainNumber);
    validatePositiveIntegerOrNegativeOne(track);

    this.departureTime = departureTime;
    this.delay = delay;
    this.destination = destination;
    this.line = line;
    this.trainNumber = trainNumber;
    this.track = track;

    //TODO: Better to do verification in constructor or before constructor?
    //Could be better because of instant feedback to the user.
    //Multiple validation layers make the system robust
  }

  /**
   * Returns the LocalTime object representing the departure time of a train.
   *
   * @return a LocalTime object in a 24-hour format (hh:mm)
   */
  public LocalTime getDepartureTime() {
    return departureTime;
  }

  /**
   * Returns the LocalTime object representing the delay of a train.
   *
   * @return a LocalTime object in a 24-hour format (hh:mm)
   */
  public LocalTime getDelay() {
    return delay;
  }

  /**
   * Returns the String representing the destination of a train.
   *
   * @return a String with the name of the destination.
   */
  public String getDestination() {
    return destination;
  }

  /**
   * Returns a String representing the line (route) of a train.
   *
   * @return a String with the combination of integers and letters.
   */
  public String getLine() {
    return line;
  }

  /**
   * Returns the integer value representing a unique train number given to each train departure.
   *
   * @return an integer of the train number.
   */
  public int getTrainNumber() {
    return trainNumber;
  }

  /**
   * Returns the integer value representing the track the train is supposed to arrive at.
   *
   * @return an integer of the track number, -1 representing that the train is unassigned.
   */
  public int getTrack() {
    return track;
  }

  /**
   * Returns the departure time plus the delay.
   *
   * @return a LocalTime object with departure time plus hours of delay and minutes of delay.
   */
  public LocalTime getDestinationTimePlusDelay() {
    return departureTime.plusHours(delay.getHour()).plusMinutes(delay.getMinute());
  }

  /**
   * Sets an positive integer value or -1 as the track attribute of an
   * existing TrainDeparture object.
   *
   * @param track is a positive integer value or -1.
   * @throws IllegalArgumentException if the input parameter is not a positive integer or -1.
   */
  public void setTrack(int track) {
    validatePositiveIntegerOrNegativeOne(track);
    this.track = track;
  }

  /**
   * Sets a LocalTime object value as the delay attribute of an existing TrainDeparture object.
   *
   * @param delay is a LocalTime object for the delay of a train departure.
   */
  public void setDelay(LocalTime delay) {
    this.delay = delay;
  }

  /**
   * Prints a String that represents a train departure all its attribute information.
   * Where delay is not printed if delay is 00:00, and where the track is not printed
   * if it is -1
   *
   * @return a String that represents the train departure information.
   */
  @Override
  public String toString() {
    String delay = getDelay().toString();
    String track = String.valueOf(getTrack());

    if (getDelay().equals(LocalTime.MIN)) {
      delay = "";
    }

    if (getTrack() == -1) {
      track = "";
    }

    return String.format("%-15s | %-5s | %-13s | %-12s | %-7s | %-5s",
            getDepartureTime(),
            getLine(),
            getTrainNumber(),
            getDestination(),
            delay,
            track
    );
  }

}
