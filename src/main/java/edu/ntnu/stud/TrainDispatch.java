package edu.ntnu.stud;

import java.time.LocalTime;

/**
 * Represents a train dispatch at a train station.
 *
 * <p>
 * <h3>Role/Responsibility</h3>
 * The role of the TrainDispatch class is to have a constructor for
 * the train dispatches and provide various methods for accessing,
 * creating, editing and removing attributes of a TrainDispatch object.
 * </p><br>
 * <p>
 * <h3>Information stored in a train dispatch object</h3>
 * The requirements for the Train Dispatch program was to have various information about the
 * following categories: departure time, delay time, destination, line, the train number, and the
 * track the train was supposed to arrive at. There were other specific requirement that
 * are in the document and some are also provided in the @param section below.
 * </p><br>
 * <p>
 * <h3>Selection of datatypes</h3>
 * I chose the LocalTime class as the datatype for departureTime and delay object variables
 * in order to record the only time that was needed, hours and minutes.
 * For the destination and line object variables I selected the String class because of the need
 * to have a longer text with multiple characters and numbers in the line object variable.
 * The train and track number needed to have whole positive numbers with an unspecified size. In the
 * document that was provided the train number was ranging from two digits to four digits, and from
 * my limited research the biggest train station in the world has 67 tracks
 * (Grand Central Terminal in New York). I chose the primitive datatype int because it provided
 * bigger than enough size and there are no memory constraints that I need to worry about.
 * </p><br>
 * <p>
 * <h3>What object variables should be immutable after the creation of a object?</h3>
 * Departure time:
 * The train either arrive or not arrive at the train station. The train can also arrive before, at
 * or after the designated time. The assigment does not specify the cancellation of a train arrival
 * and only specifies that a train can have a set delay. From this information I think that the
 * departure time should be set at the creation of a train dispatch object. My reasoning is that if
 * a train arrives before the departure time it will wait until the departure time to depart from
 * the train station. If comes any time later that the wait time for the departure it
 * will have a delay.
 * Delay:
 * The delay of the train can change depending on the situation of the train and should therefore be
 * a mutable object and should have set methods for the class.
 * //TODO: Destination:
 * //TODO: Line:
 * Train number:
 * The train number should be in my opinion unchangeable upon the creation of a train dispatch
 * object. This is in order to not give the option to change it so that it is easy to find back the
 * train dispatch number.
 * Track:
 * The assigment specifies a function of the application that gives the user an option to add a
 * track to a train departure. Therefore the train object variable should be mutable.
 * </p>
 *
 * @author Adrian Aleksander Buczek
 * @version 1.0
 */

//TODO: Add source for the biggest train station?
//TODO: Add that int is a default
//TODO: Add bullet points?
//TODO: Add points from the assigment in the immutable/immutable question
//TODO: More explanation on delay in immutable/mutable
//TODO: Should the documentation of the program be in the javadoc or in another file?

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
