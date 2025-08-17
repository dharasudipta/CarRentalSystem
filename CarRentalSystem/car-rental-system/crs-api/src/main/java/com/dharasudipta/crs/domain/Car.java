package com.dharasudipta.crs.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Car {
    private String id;
    private CarType type;
    private List<Reservation> reservations;

    public Car(String id, CarType type) {
        this.id = id;
        this.type = type;
        this.reservations = new ArrayList<>();
    }

    public boolean isAvailable(LocalDate startDate, LocalDate endDate) {
        // Check if the car is available for the given date range
        return reservations.stream()
                .noneMatch(reservation -> reservation.overlapsWith(startDate, endDate));
    }

    public Reservation reserve(LocalDate startDate, LocalDate endDate) {
        // Create a new reservation for the car
        Reservation reservation = new Reservation(this, startDate, endDate);
        reservations.add(reservation);
        return reservation;
    }

}
