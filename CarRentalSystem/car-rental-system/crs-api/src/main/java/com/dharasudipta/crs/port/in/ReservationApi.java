package com.dharasudipta.crs.port.in;

import java.time.LocalDate;
import java.util.List;

import com.dharasudipta.crs.domain.CRSException;
import com.dharasudipta.crs.domain.CarType;
import com.dharasudipta.crs.domain.Reservation;


public interface ReservationApi {

    List<Reservation> getAllReservations() throws CRSException;

    Reservation reserveCar(LocalDate startDate, LocalDate endDate, CarType carType) throws CRSException;

    void cancelReservation(String reservationId) ;

}
