package com.dharasudipta.crs.domain;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Reservation {

    private String id;
    private Car car;
    private LocalDate startDate;
    private LocalDate endDate;

    public Reservation(Car car, LocalDate startDate, LocalDate endDate) {
        this.id = java.util.UUID.randomUUID().toString(); // Generate a unique ID for the reservation
        this.car = car;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean overlapsWith(LocalDate proposedStartDate, LocalDate proposedEndDate) {
        // Check if the reservation overlaps with the proposed date range

        return Math.min(endDate.toEpochDay(), proposedEndDate.toEpochDay()) -
                Math.max(startDate.toEpochDay(), proposedStartDate.toEpochDay()) >= 0;
    }

}
