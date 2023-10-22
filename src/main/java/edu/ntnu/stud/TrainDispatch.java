package edu.ntnu.stud;

import java.time.LocalTime;
public class TrainDispatch {
    private LocalTime depatureTime;
    private LocalTime delay;
    private String destination;
    private String line;
    private int trainNumber;
    private int track;

    public TrainDispatch(LocalTime depatureTime, LocalTime delay, String destination, String line, int trainNumber, int track) {
        this.depatureTime = depatureTime;
        this.delay = delay;
        this.destination = destination;
        this.line = line;
        this.trainNumber = trainNumber;
        this.track = track;
    }


}
