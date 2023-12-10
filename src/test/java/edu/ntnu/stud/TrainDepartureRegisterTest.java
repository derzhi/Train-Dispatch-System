package edu.ntnu.stud;

import org.junit.jupiter.api.*;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class TrainDepartureRegisterTest {
  private TrainDepartureRegister departures;

  @BeforeEach
  void setUp() {
    departures = new TrainDepartureRegister();

    TrainDeparture trainDeparture1 = new TrainDeparture(LocalTime.of(15, 30), LocalTime.of(0, 15), "Bergen", "L4", 1, -1);
    TrainDeparture trainDeparture2 = new TrainDeparture(LocalTime.of(12, 30), LocalTime.of(0, 0), "Trondheim", "B3", 2, -1);
    TrainDeparture trainDeparture3 = new TrainDeparture(LocalTime.of(11, 20), LocalTime.of(0, 5), "Trondheim", "L3", 3, 2);
    TrainDeparture trainDeparture4 = new TrainDeparture(LocalTime.of(7, 21), LocalTime.of(0, 0), "Kragerø", "L4", 4, 3);

    departures.addTrainDeparture(trainDeparture1);
    departures.addTrainDeparture(trainDeparture2);
    departures.addTrainDeparture(trainDeparture3);
    departures.addTrainDeparture(trainDeparture4);
  }

  @AfterEach
  void tearDown() {
    departures = null;
  }

  @Nested
  @DisplayName("Positive tests for the TrainDepartureRegister class")
  public class PositiveTrainDepartureRegisterTests {
    @Test
    void getTimeOfDayEqualsDefaultTimeOfDay() {
      assertEquals(departures.getTimeOfDay(), LocalTime.of(0, 0));
    }

    @Test
    void getDeparturesEqualsRightSize() {
      assertEquals(departures.getDepartures().size(), 4);
    }

    @Test
    void getDeparturesByDestinationEqualsRightSize() {
      assertEquals(departures.getDeparturesByDestination("Trondheim").size(), 2);
    }

    @Test
    void getTrainDepartureByTrainNumberGiveCorrectTrainDeparture() {
      TrainDeparture departure = departures.getTrainDepartureByTrainNumber(1);

      assertEquals(departure.getDepartureTime(), LocalTime.of(15, 30));
      assertEquals(departure.getDelay(), LocalTime.of(0, 15));
      assertEquals(departure.getDestination(), "Bergen");
      assertEquals(departure.getLine(), "L4");
      assertEquals(departure.getTrainNumber(), 1);
      assertEquals(departure.getTrack(), -1);
    }

    @Test
    void addTrainDeparturesGivesCorrectSize() {
      assertEquals(departures.getDepartures().size(), 4);
    }

    @Test
    void removeTrainDeparturesByTimeBeforeGivesCorrectSize() {
      departures.setTimeOfDay(LocalTime.of(12, 0));
      assertEquals(departures.getDepartures().size(), 2);
    }

    @Test
    void setTimeOfDayGivesCorrectTime() {
      departures.setTimeOfDay(LocalTime.of(12, 0));
      assertEquals(departures.getTimeOfDay(), LocalTime.of(12, 0));
    }

    @Test
    void assertUniqueDepartureSchedulingDoesNotThrow() {
      assertDoesNotThrow(() -> departures.assertUniqueDepartureScheduling(LocalTime.of(15, 45), "L3", -1));
    }

    @Test
    void assertDeparturesNotEmptyDoesNotThrow() {
      assertDoesNotThrow(() -> departures.assertDeparturesNotEmpty());
    }

    @Test
    void assertDepartureTimeIsNotBeforeTimeOfDayDoesNotThrow() {
      assertDoesNotThrow(() -> departures.assertDepartureTimeIsNotBeforeTimeOfDay(LocalTime.of(15, 45)));
    }
  }

  @Nested
  @DisplayName("Negative tests for the TrainDepartureRegister class")
  public class NegativeTrainDepartureRegisterTests {

    @Test
    void getTrainDepartureByTrainNumberDoesThrow() {
      assertThrows(IllegalArgumentException.class, () -> departures.getTrainDepartureByTrainNumber(5));
    }

    @Test
    void addTrainDeparturesDoesThrow() {
      TrainDeparture trainDeparture5 = new TrainDeparture(LocalTime.of(15, 30), LocalTime.of(0, 15), "Bergen", "L4", 1, -1);
      assertThrows(IllegalArgumentException.class, () -> departures.addTrainDeparture(trainDeparture5));
    }

    @Test
    void setTimeOfDayDoesThrow() {
      departures.setTimeOfDay(LocalTime.of(16, 0));
      assertThrows(IllegalArgumentException.class, () -> departures.setTimeOfDay(LocalTime.of(12, 0)));
    }

    @Test
    void assertUniqueDepartureSchedulingDoesThrow() {
      assertThrows(IllegalArgumentException.class, () -> departures.assertUniqueDepartureScheduling(LocalTime.of(11, 25), "L3", 2));
    }

    @Test
    void assertDeparturesNotEmptyDoesThrow() {
      TrainDepartureRegister departures2 = new TrainDepartureRegister();
      assertThrows(IllegalArgumentException.class, departures2::assertDeparturesNotEmpty);
    }

    @Test
    void assertDepartureTimeIsNotBeforeTimeOfDayDoesThrow() {
      departures.setTimeOfDay(LocalTime.of(12, 0));
      assertThrows(IllegalArgumentException.class, () -> departures.assertDepartureTimeIsNotBeforeTimeOfDay(LocalTime.of(11, 0)));
    }

  }
}