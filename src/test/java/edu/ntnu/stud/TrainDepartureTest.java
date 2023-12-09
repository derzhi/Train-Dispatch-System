package edu.ntnu.stud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TrainDepartureTest {
  private TrainDeparture td;

  @Nested
  @DisplayName("Positive tests for the TrainDeparture class")
  public class PositiveTrainDepartureTests {

    @BeforeEach
    public void setUp() {
      td = new TrainDeparture(LocalTime.of(15, 30), LocalTime.of(0, 15), "Bergen", "L4", 3123, -1);
    }

    @Test
    public void getDepartureTimeShouldGetCorrectLocalTime() {
      assertEquals(td.getDepartureTime(), LocalTime.of(15, 30));
    }

    @Test
    public void getDelayShouldGetCorrectLocalTime() {
      assertEquals(td.getDelay(), LocalTime.of(0, 15));
    }

    @Test
    public void getDestinationShouldGetCorrectString() {
      assertEquals(td.getDestination(), "Bergen");
    }

    @Test
    public void getLineShouldGetCorrectString() {
      assertEquals(td.getLine(), "L4");
    }

    @Test
    public void getTrainNumberShouldGetCorrectInteger() {
      assertEquals(td.getTrainNumber(), 3123);
    }

    @Test
    public void getTrackNumberShouldGetCorrectInteger() {
      assertEquals(td.getTrack(), -1);
    }

  }

  @Nested
  @DisplayName("Negative tests for the TrainDeparture class")
  public class NegativeTrainDepartureTests {

    @Test
    public void destinationThrowsOnBlankString() {
      try {
        new TrainDeparture(LocalTime.of(15, 30), LocalTime.of(0, 15), "", "L4", 3123, 5);
        fail("Test failed");
      } catch (IllegalArgumentException e) {
        assertEquals("The string for the parameter was a blank string, please retry.", e.getMessage());
      }
    }

    @Test
    public void lineThrowsOnBlankString() {
      try {
        new TrainDeparture(LocalTime.of(15, 30), LocalTime.of(0, 15), "Bergen", "", 3123, 5);
        fail("Test failed");
      } catch (IllegalArgumentException e) {
        assertEquals("The string for the parameter was a blank string, please retry.", e.getMessage());
      }
    }

    @Test
    public void trainNumberThrowsOnNegativeInteger() {
      try {
        new TrainDeparture(LocalTime.of(15, 30), LocalTime.of(0, 15), "Bergen", "A4", -3123, 5);
        fail("Test failed");
      } catch (IllegalArgumentException e) {
        assertEquals("The integer for the parameter was negative, please retry.", e.getMessage());
      }
    }

    @Test
    public void trainNumberThrowsOnZeroInteger() {
      try {
        new TrainDeparture(LocalTime.of(15, 30), LocalTime.of(0, 15), "Bergen", "A4", 0, 5);
        fail("Test failed");
      } catch (IllegalArgumentException e) {
        assertEquals("The integer for the parameter was negative, please retry.", e.getMessage());
      }
    }

    @Test
    public void trackThrowsOnNegativeIntegerThatIsNotMinusOne() {
      try {
        new TrainDeparture(LocalTime.of(15, 30), LocalTime.of(0, 15), "Bergen", "A4", 3253, -2);
        fail("Test failed");
      } catch (IllegalArgumentException e) {
        assertEquals("The integer for the parameter was not over 0 or -1, please retry.", e.getMessage());
      }
    }

    @Test
    public void trackThrowsOnZeroInteger() {
      try {
        new TrainDeparture(LocalTime.of(15, 30), LocalTime.of(0, 15), "Bergen", "A4", 3253, 0);
        fail("Test failed");
      } catch (IllegalArgumentException e) {
        assertEquals("The integer for the parameter was not over 0 or -1, please retry.", e.getMessage());
      }
    }

    @Test
    public void setTrackThrowsOnNegativeIntegerThatIsNotMinusOne() {
      try {
        TrainDeparture td = new TrainDeparture(LocalTime.of(15, 30), LocalTime.of(0, 15), "Bergen", "A4", 3253, 0);
        td.setTrack(-2);
        fail("Test failed");
      } catch (IllegalArgumentException e) {
        assertEquals("The integer for the parameter was not over 0 or -1, please retry.", e.getMessage());
      }
    }

    @Test
    public void setTrackThrowsZeroInteger() {
      try {
        TrainDeparture td = new TrainDeparture(LocalTime.of(15, 30), LocalTime.of(0, 15), "Bergen", "A4", 3253, 0);
        td.setTrack(0);
        fail("Test failed");
      } catch (IllegalArgumentException e) {
        assertEquals("The integer for the parameter was not over 0 or -1, please retry.", e.getMessage());
      }
    }
  }
}