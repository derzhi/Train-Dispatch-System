package edu.ntnu.stud;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrainDepartureGroupTest {

  @Nested
  @DisplayName("Positive tests for TrainDepartureGroup")
  public class PositiveTrainDepartureGroupTests {

    @Test
    public void addedTrainDepartureCanBeAccessed() {
      TrainDepartureManager tdg = new TrainDepartureManager();
      TrainDeparture td = new TrainDeparture(LocalTime.of(15, 30), LocalTime.of(0, 15), "Bergen", "L4", 3123, -1);
      tdg.addTrainDeparture(td);
      assertEquals(td, tdg.getTrainDepartureByTrainNumber(3123));
    }

    @Test
    public void getTrainDepartureByDestinationReturnsCorrectList() {
      TrainDepartureManager tdg = new TrainDepartureManager();
      TrainDeparture td1 = new TrainDeparture(LocalTime.of(15, 30), LocalTime.of(0, 15), "Bergen", "L4", 3123, -1);
      TrainDeparture td2 = new TrainDeparture(LocalTime.of(15, 30), LocalTime.of(0, 15), "Bergen", "L4", 3124, -1);
      TrainDeparture td3 = new TrainDeparture(LocalTime.of(15, 30), LocalTime.of(0, 15), "Oslo", "L4", 3125, -1);
      tdg.addTrainDeparture(td1);
      tdg.addTrainDeparture(td2);
      tdg.addTrainDeparture(td3);
      assertEquals(List.of(td1, td2), tdg.getTrainDepartureByDestination("Bergen"));
    }

    @Test
    public void getTrainDepartureByDestinationReturnsEmptyList() {
      TrainDepartureManager tdg = new TrainDepartureManager();
      TrainDeparture td1 = new TrainDeparture(LocalTime.of(15, 30), LocalTime.of(0, 15), "Bergen", "L4", 3123, -1);
      TrainDeparture td2 = new TrainDeparture(LocalTime.of(15, 30), LocalTime.of(0, 15), "Bergen", "L4", 3124, -1);
      TrainDeparture td3 = new TrainDeparture(LocalTime.of(15, 30), LocalTime.of(0, 15), "Oslo", "L4", 3125, -1);
      tdg.addTrainDeparture(td1);
      tdg.addTrainDeparture(td2);
      tdg.addTrainDeparture(td3);
      assertEquals(List.of(), tdg.getTrainDepartureByDestination("Trondheim"));
    }


  }

  @Nested
  @DisplayName("Negative tests for TrainDepartureGroup")
  public class NegativeTrainDepartureGroupTests {

  }
}