package edu.ntnu.stud;

import java.time.LocalTime;

/**
 * Train Dispatch.
 *
 * <p>
 * This class represents a Train dispatch class
 * </p>
 *
 * @author Adrian Aleksander Buczek
 * @version 1.0
 */
public class TrainDispatch {
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
  public TrainDispatch(LocalTime departureTime, LocalTime delay, String destination,
                       String line, int trainNumber, int track) {
    this.departureTime = departureTime;
    this.delay = delay;
    this.destination = destination;
    this.line = line;
    this.trainNumber = trainNumber;
    this.track = track;
  }


}
