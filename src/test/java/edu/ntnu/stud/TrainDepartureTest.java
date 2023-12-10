package edu.ntnu.stud;

import org.junit.jupiter.api.*;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TrainDepartureTest {
  private TrainDeparture trainDeparture;

  @Nested
  @DisplayName("Positive tests for the TrainDeparture class")
  public class PositiveTrainDepartureTests {

    @BeforeEach
    void setUp() {
      trainDeparture = new TrainDeparture(LocalTime.of(15, 30), LocalTime.of(0, 15), "Bergen", "L4", 3123, -1);
    }

    @AfterEach
    void tearDown() {
      trainDeparture = null;
    }

    @Test
    void getDepartureTimeShouldGetCorrectLocalTime() {
      assertEquals(trainDeparture.getDepartureTime(), LocalTime.of(15, 30));
    }

    @Test
    void getDelayShouldGetCorrectLocalTime() {
      assertEquals(trainDeparture.getDelay(), LocalTime.of(0, 15));
    }

    @Test
    void getDestinationShouldGetCorrectString() {
      assertEquals(trainDeparture.getDestination(), "Bergen");
    }

    @Test
    void getLineShouldGetCorrectString() {
      assertEquals(trainDeparture.getLine(), "L4");
    }

    @Test
    void getTrainNumberShouldGetCorrectInteger() {
      assertEquals(trainDeparture.getTrainNumber(), 3123);
    }

    @Test
    void getTrackNumberShouldGetCorrectInteger() {
      assertEquals(trainDeparture.getTrack(), -1);
    }

    @Test
    void getFinalDepartureTimeShouldGetCorrectTime() {
      assertEquals(trainDeparture.getFinalDepartureTime(), LocalTime.of(15, 45));
    }

    @Test
    void getFinalDepartureTimeShouldGetCorrectTimeWithoutDelay() {
      trainDeparture.setDelay(LocalTime.of(0, 0));
      assertEquals(trainDeparture.getFinalDepartureTime(), LocalTime.of(15, 30));
    }

    @Test
    void setDelayShouldGetCorrectDelay() {
      trainDeparture.setDelay(LocalTime.of(1, 1));
      assertEquals(trainDeparture.getDelay(), LocalTime.of(1, 1));
    }

    @Test
    void setTrackShouldGetCorrectTrack() {
      trainDeparture.setTrack(21);
      assertEquals(trainDeparture.getTrack(), 21);
    }

  }

  @Nested
  @DisplayName("Negative tests for the TrainDeparture class")
  public class NegativeTrainDepartureTests {

    @Test
    void destinationThrowsOnBlankString() {
      try {
        new TrainDeparture(LocalTime.of(15, 30), LocalTime.of(0, 15), "", "L4", 3123, 5);
        fail("Test failed");
      } catch (IllegalArgumentException e) {
        assertEquals("The the parameter was a blank string, please retry.", e.getMessage());
      }
    }

    @Test
    void lineThrowsOnBlankString() {
      try {
        new TrainDeparture(LocalTime.of(15, 30), LocalTime.of(0, 15), "Bergen", "", 3123, 5);
        fail("Test failed");
      } catch (IllegalArgumentException e) {
        assertEquals("The the parameter was a blank string, please retry.", e.getMessage());
      }
    }

    @Test
    void trainNumberThrowsOnNegativeInteger() {
      try {
        new TrainDeparture(LocalTime.of(15, 30), LocalTime.of(0, 15), "Bergen", "A4", -3123, 5);
        fail("Test failed");
      } catch (IllegalArgumentException e) {
        assertEquals("The integer for the parameter was negative, please retry.", e.getMessage());
      }
    }

    @Test
    void trainNumberThrowsOnZeroInteger() {
      try {
        new TrainDeparture(LocalTime.of(15, 30), LocalTime.of(0, 15), "Bergen", "A4", 0, 5);
        fail("Test failed");
      } catch (IllegalArgumentException e) {
        assertEquals("The integer for the parameter was negative, please retry.", e.getMessage());
      }
    }

    @Test
    void trackThrowsOnNegativeIntegerThatIsNotMinusOne() {
      try {
        new TrainDeparture(LocalTime.of(15, 30), LocalTime.of(0, 15), "Bergen", "A4", 3253, -2);
        fail("Test failed");
      } catch (IllegalArgumentException e) {
        assertEquals("The integer for the parameter was not over 0 or -1, please retry.", e.getMessage());
      }
    }

    @Test
    void trackThrowsOnZeroInteger() {
      try {
        new TrainDeparture(LocalTime.of(15, 30), LocalTime.of(0, 15), "Bergen", "A4", 3253, 0);
        fail("Test failed");
      } catch (IllegalArgumentException e) {
        assertEquals("The integer for the parameter was not over 0 or -1, please retry.", e.getMessage());
      }
    }

    @Test
    void setTrackThrowsOnNegativeIntegerThatIsNotMinusOne() {
      try {
        TrainDeparture td = new TrainDeparture(LocalTime.of(15, 30), LocalTime.of(0, 15), "Bergen", "A4", 3253, 0);
        td.setTrack(-2);
        fail("Test failed");
      } catch (IllegalArgumentException e) {
        assertEquals("The integer for the parameter was not over 0 or -1, please retry.", e.getMessage());
      }
    }

    @Test
    void setTrackThrowsZeroInteger() {
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