package edu.ntnu.stud;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class TrainDepartureTest {

  // Positive tests
  @Test
  public void testTrainDepartureCreation() {
    TrainDeparture td = new TrainDeparture(LocalTime.of(15, 30), LocalTime.of(0, 15), "Bergen", "L4", 3123, 5);

    assertNotNull(td);
    assertEquals(LocalTime.of(15, 30), td.getDepartureTime());
    assertEquals(LocalTime.of(0, 15), td.getDelay());
    assertEquals("Bergen", td.getDestination());
    assertEquals("L4", td.getLine());
    assertEquals(3123, td.getTrainNumber());
    assertEquals(5, td.getTrack());
  }

  @Test
  public void testToString() {
    TrainDeparture td = new TrainDeparture(LocalTime.of(15, 30), LocalTime.of(0, 15), "Bergen", "L4", 3123, 5);
    String expectedString = "TrainDeparture { Departure Time: 15:30, Delay: 00:15, Destination: Bergen, Line: L4, Train Number: 3123, Track: 5 }";
    assertEquals(expectedString, td.toString());
  }

  // Negative tests
  @Test
  public void testDefaultDelayTime() {
    TrainDeparture td = new TrainDeparture(LocalTime.of(15, 30), null, "Bergen", "L4", 3123, 5);
    assertEquals(LocalTime.of(0, 0), td.getDelay());
  }

  @Test
  public void testDefaultTrackAssignment() {
    TrainDeparture td = new TrainDeparture(LocalTime.of(15, 30), LocalTime.of(0, 15), "Bergen", "L4", 3123, -5);
    assertEquals(-1, td.getTrack());
  }

}