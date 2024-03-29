package edu.ntnu.stud;

import org.junit.jupiter.api.*;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RegistryTest {
  private Registry departures;

  @BeforeEach
  void setUp() {
    departures = new Registry();

    departures.addTrainDeparture(LocalTime.of(15, 30), LocalTime.of(0, 15),
            "Bergen", "L4", 1, -1);
    departures.addTrainDeparture(LocalTime.of(11, 20), LocalTime.of(0, 5),
            "Trondheim", "B3", 2, -1);
    departures.addTrainDeparture(LocalTime.of(11, 20), LocalTime.of(0, 5),
            "Trondheim", "L3", 3, 2);
    departures.addTrainDeparture(LocalTime.of(7, 20), LocalTime.of(0, 0),
            "Kragerø", "L4", 4, 2);
  }

  @AfterEach
  void tearDown() {
    departures = null;
  }

  @Nested
  @DisplayName("Positive tests for the TrainDepartureRegister class")
  public class PositiveRegistryTests {
    @Test
    void getTimeOfDayEqualsDefaultTimeOfDay() {
      assertEquals(departures.getTimeOfDay(),
              LocalTime.of(0, 0));
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
    void addTrainDeparturesByTimeGivesCorrectSize() {
      departures.addTrainDeparture(LocalTime.of(12, 0), LocalTime.of(0, 0),
              "Trondheim", "L4", 5, 5);
      assertEquals(departures.getDepartures().size(), 5);
    }

    @Test
    void removeTrainDeparturesByTimeBeforeGivesCorrectSize() {
      departures.setTimeOfDay(LocalTime.of(12, 0));
      assertEquals(departures.getDepartures().size(), 1);
    }

    @Test
    void setTimeOfDayGivesCorrectTime() {
      departures.setTimeOfDay(LocalTime.of(12, 0));
      assertEquals(departures.getTimeOfDay(), LocalTime.of(12, 0));
    }

    @Test
    void assertUniqueDepartureSchedulingDoesNotThrow() {
      assertDoesNotThrow(() -> departures
              .assertUniqueDepartureScheduling(LocalTime.of(15, 45), "L3", -1));
    }

    @Test
    void assertDeparturesNotEmptyDoesNotThrow() {
      assertDoesNotThrow(() -> departures.assertDeparturesNotEmpty());
    }
  }

  @Nested
  @DisplayName("Negative tests for the TrainDepartureRegister class")
  public class NegativeRegistryTests {

    @Test
    void getTrainDepartureByTrainNumberDoesThrow() {
      assertThrows(IllegalArgumentException.class, () -> departures.getTrainDepartureByTrainNumber(5));
    }

    @Test
    void addTrainDeparturesDoesThrow() {
      assertThrows(IllegalArgumentException.class, () -> departures
              .addTrainDeparture(LocalTime.of(15, 30), LocalTime.of(0, 15),
                      "Bergen", "L4", 1, -1));
    }

    @Test
    void addTrainDepartureDoesThrowWhenTimeIsBeforeTimeOfDay() {
      departures.setTimeOfDay(LocalTime.of(12, 0));
      assertThrows(IllegalArgumentException.class, () -> departures
              .addTrainDeparture(LocalTime.of(11, 30), LocalTime.of(0, 15),
                      "Bergen", "L33", 23, 44));
    }

    @Test
    void addTrainDeparturesDoesThrowWhenTrainNumberAlreadyExists() {
      assertThrows(IllegalArgumentException.class, () -> departures
              .addTrainDeparture(LocalTime.of(22, 0), LocalTime.of(0, 15),
                      "Hønefoss", "L33", 1, -7));
    }

    @Test
    void setTimeOfDayDoesThrow() {
      departures.setTimeOfDay(LocalTime.of(16, 0));
      assertThrows(IllegalArgumentException.class, () -> departures.setTimeOfDay(LocalTime.of(12, 0)));
    }

    @Test
    void assertUniqueDepartureSchedulingDoesThrow() {
      assertThrows(IllegalArgumentException.class, () -> departures
              .assertUniqueDepartureScheduling(LocalTime.of(11, 25), "L3", 2));
    }

    @Test
    void assertDeparturesNotEmptyDoesThrow() {
      Registry departures2 = new Registry();
      assertThrows(IllegalArgumentException.class, departures2::assertDeparturesNotEmpty);
    }

    @Test
    void assertAndSetTrackDoesThrow() {
      assertThrows(IllegalArgumentException.class, () -> departures
              .assertAndSetTrack(2, 2));
    }

    @Test
    void assertAndSetDelayDoesThrow() {
      assertThrows(IllegalArgumentException.class, () -> departures
              .assertAndSetDelay(4, LocalTime.of(4, 5)));
    }
  }
}