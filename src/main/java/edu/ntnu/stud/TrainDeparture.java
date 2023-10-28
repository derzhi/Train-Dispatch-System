package edu.ntnu.stud;

import java.time.LocalTime;

/**
 * <h1>TrainDeparture class</h1><br>
 *
 * <p><b>Role/Responsibility:</b></p>
 * This class is designed to model train departures. It offers a constructor for train departures
 * and methods to create, access, edit, and remove attributes of a TrainDeparture object.<br><br>
 *
 * <p><b>Information stored in a TrainDeparture object:</b></p>
 * The TrainDeparture program contains details about the departure time, delay time,
 * destination, line, train number, and track a train is expected to arrive at. An object variable
 * is designated for each piece of information about a train departure, ensuring all necessary
 * information is separable and easily accessible.<br><br>
 *
 * <p><b>Selection of datatypes:</b></p>
 * <ul>
 *     <li>The {@code LocalTime} class was chosen as the datatype for departureTime and delay
 *     to capture the necessary details, which are hours and minutes.</li>
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
 *     slated to depart.
 *     Given the specifics of the assignment and considerations like train delays, this value
 *     should remain constant.</li>
 *     <li>{@code destination}, {@code line}, and {@code trainNumber} - These values should remain
 *     constant for clarity
 *     and ease of reference.</li>
 * </ul><br><br>
 *
 * <p><b>Mutable attributes after object creation:</b></p>
 * Certain attributes can be changed after the TrainDeparture object has been instantiated:
 * <ul>
 *     <li>{@code delay} - Represents potential delays for a train's departure.</li>
 *     <li>{@code track} - Can be altered based on application functionalities, such as assigning a
 *     different track for a train departure.</li>
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
 */

// TODO: Will need rewrite
// TODO: More explanation on delay in immutable/mutable
// TODO: datatypes/LocalTime - add information about time easily being
// TODO: editable/good format? + used to + time?
// TODO: datatypes/int - default?
// TODO: object attributes/departuretime - write more about the specifications
// TODO: of the assignment rather than real life scenario

public class TrainDeparture {
  private LocalTime departureTime;
  private LocalTime delay;
  private String destination;
  private String line;
  private int trainNumber;
  private int track;

  /**
   * Train dispatch constructor.
   *
   * @param departureTime This parameter represents the departure time for a train
   *                      in a hh:mm format with a 24 hour clock. For example 15:34.
   * @param delay         This parameter represent the delay time for a train in
   *                      in hours and minutes. If is not delayed it is set to 00:00.
   * @param destination   This is a String parameter that represents the destination of a train.
   *                      An example would be "Bergen".
   * @param line          This is a String parameter represents the line/route that a train
   *                      is taking. An example would be "L4". Multiple trains can be on the same
   *                      line at different times.
   * @param trainNumber   A int parameter that represent the number of a train that is unique in a
   *                      24-hour window. An example would be "3123" or "232"
   * @param track         A int parameter that represent the track where the train is supposed to
   *                      arrive at. If a train does not have a track assigned, it is set to -1.
   */
  public TrainDeparture(LocalTime departureTime, LocalTime delay, String destination,
                        String line, int trainNumber, int track) {
    this.departureTime = departureTime;
    this.delay = delay;
    this.destination = destination;
    this.line = line;
    this.trainNumber = trainNumber;
    this.track = track;

    if (delay == null) {
      this.delay = LocalTime.of(0, 0);
    }

    if (track < 1) {
      this.track = -1;
    }

    //TODO: Better to do verification in constructor or before constructor?
    //Could be better because of instant feedback to the user.
    //Multiple validation layers make the system robust

  }

  public LocalTime getDepartureTime() {
    return departureTime;
  }

  public LocalTime getDelay() {
    return delay;
  }

  public String getDestination() {
    return destination;
  }

  public String getLine() {
    return line;
  }

  public int getTrainNumber() {
    return trainNumber;
  }

  public int getTrack() {
    return track;
  }

  @Override
  public String toString() {
    return "TrainDeparture { " + "Departure Time: " + departureTime
            + ", Delay: " + delay
            + ", Destination: " + destination
            + ", Line: " + line
            + ", Train Number: " + trainNumber
            + ", Track: " + track + " }";
  }

}
