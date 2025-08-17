package com.dharasudipta.crs.application;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.dharasudipta.crs.domain.CRSException;
import com.dharasudipta.crs.domain.Car;
import com.dharasudipta.crs.domain.CarType;
import com.dharasudipta.crs.domain.Reservation;
import com.dharasudipta.crs.port.in.ReservationApi;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CarRentalService implements ReservationApi {

    private List<Car> availableCars;

    // Intial inventory
    public CarRentalService(List<Car> availableCars) {
        this.availableCars = availableCars;
    }

    @Override
    public List<Reservation> getAllReservations() throws CRSException {
        // Implementation to retrieve all reservations
        if (CollectionUtils.isEmpty(availableCars)) {
            throw new CRSException("No cars available in the system.");
        }
        return this.availableCars.stream()
                .flatMap(car -> car.getReservations().stream())
                .collect(Collectors.toList());
    }

    @Override
    public Reservation reserveCar(LocalDate startDate, LocalDate endDate, CarType carType) throws CRSException {
        // Implementation to reserve a car
        List<Car> availableCarTypeList = availableCars.stream().filter(c -> c.getType().equals(carType)).toList();
        if (CollectionUtils.isEmpty(availableCarTypeList)) {
            throw new CRSException("No cars available for the requested type:" + carType);
        }
        availableCarTypeList = availableCarTypeList.stream()
                .filter(c -> c.isAvailable(startDate, endDate))
                .collect(Collectors.toList());

        if (CollectionUtils.isEmpty(availableCarTypeList)) {
            throw new CRSException("No " + carType + " cars available during requested period - startDate: " + startDate
                    + ", endDate: " + endDate);
        }

        return availableCarTypeList.get(0).reserve(startDate, endDate);
    }

    @Override
    public void cancelReservation(String reservationId) {
        // Implementation to cancel a reservation
        this.availableCars.forEach(car -> {
            List<Reservation> reservations = car.getReservations();
            if (CollectionUtils.isNotEmpty(reservations)) {
                reservations.removeIf(reservation -> reservation.getId().equals(reservationId));
                log.info("Reservation with ID: {} has been cancelled for car id: {} & car type: {}", reservationId,
                        car.getId(), car.getType());
            }
        });
    }

}
