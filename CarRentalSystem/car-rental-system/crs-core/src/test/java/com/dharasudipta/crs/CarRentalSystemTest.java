package com.dharasudipta.crs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dharasudipta.crs.application.CarRentalService;
import com.dharasudipta.crs.domain.CRSException;
import com.dharasudipta.crs.domain.Car;
import com.dharasudipta.crs.domain.CarType;
import com.dharasudipta.crs.domain.Reservation;
import com.dharasudipta.crs.port.in.ReservationApi;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
class CarRentalSystemTest {

    ReservationApi reservationApi;

    List<Car> availableCars;

    @BeforeEach
    void setUp() {
        availableCars = new ArrayList<>();

        availableCars.add(new Car("1", CarType.SEDAN));
        availableCars.add(new Car("2", CarType.SUV));
        availableCars.add(new Car("3", CarType.VAN));

        availableCars.add(new Car("12", CarType.SEDAN));
        availableCars.add(new Car("22", CarType.SUV));
        availableCars.add(new Car("32", CarType.VAN));

        availableCars.add(new Car("13", CarType.SEDAN));
        availableCars.add(new Car("23", CarType.SUV));
        availableCars.add(new Car("33", CarType.VAN));

        availableCars.add(new Car("14", CarType.SEDAN));
        availableCars.add(new Car("24", CarType.SUV));
        availableCars.add(new Car("34", CarType.VAN));

        reservationApi = new CarRentalService(availableCars);
    }

    @Test
    @Order(1)
    void testCarRentalService_ReserveONESedanForFOURDays() {
        log.info("testCarRentalService_ReserveONESedanForFOURDays::Testing car rental service for sedan");
        String startDateStr = "20-08-2025";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate startDate = LocalDate.parse(startDateStr, formatter);
        Reservation reservationDetails = null;
        try {
            reservationDetails = reservationApi.reserveCar(startDate, startDate.plusDays(4), CarType.SEDAN);
        } catch (CRSException ex) {
            log.error("Error occurred while reserving car: {}", ex.getMessage());
        }
        assertNotNull(reservationDetails);
        log.info("Reservation successful: id: {} startDate:{} endDate:{} for Car id: {} type: {}",
                reservationDetails.getId(), reservationDetails.getStartDate(), reservationDetails.getEndDate(),
                reservationDetails.getCar().getId(), reservationDetails.getCar().getType());
        assertNotNull(reservationDetails.getId());
        assertEquals(startDate, reservationDetails.getStartDate());
        assertEquals(startDate.plusDays(4), reservationDetails.getEndDate());
        log.info("testCarRentalService_ReserveONESedanForFOURDays::Testing car rental service for sedan completed");
    }

    @Test
    @Order(2)
    void testCarRentalService_ReserveFOURSUVsForFIVEDifferentDays() {
        log.info("testCarRentalService_ReserveFOURSUVsForFIVEDifferentDays::Testing car rental service for SUV");
        String startDateStr1 = "20-08-2025";
        String startDateStr2 = "21-09-2025";
        String startDateStr3 = "22-10-2025";
        String startDateStr4 = "23-12-2025";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate startDate1 = LocalDate.parse(startDateStr1, formatter);
        LocalDate startDate2 = LocalDate.parse(startDateStr2, formatter);
        LocalDate startDate3 = LocalDate.parse(startDateStr3, formatter);
        LocalDate startDate4 = LocalDate.parse(startDateStr4, formatter);
        Reservation reservationDetail1 = null;
        Reservation reservationDetail2 = null;
        Reservation reservationDetail3 = null;
        Reservation reservationDetail4 = null;
        try {
            reservationDetail1 = reservationApi.reserveCar(startDate1, startDate1.plusDays(5), CarType.SUV);
            reservationDetail2 = reservationApi.reserveCar(startDate2, startDate2.plusDays(5), CarType.SUV);
            reservationDetail3 = reservationApi.reserveCar(startDate3, startDate3.plusDays(5), CarType.SUV);
            reservationDetail4 = reservationApi.reserveCar(startDate4, startDate4.plusDays(5), CarType.SUV);

        } catch (CRSException ex) {
            log.error("Error occurred while reserving car: {}", ex.getMessage());
        }
        assertNotNull(reservationDetail1);
        assertNotNull(reservationDetail2);
        assertNotNull(reservationDetail3);
        assertNotNull(reservationDetail4);
        log.info("Reservation successful: id: {} startDate:{} endDate:{} for Car id: {} type: {}",
                reservationDetail1.getId(), reservationDetail1.getStartDate(), reservationDetail1.getEndDate(),
                reservationDetail1.getCar().getId(), reservationDetail1.getCar().getType());
        log.info("Reservation successful: id: {} startDate:{} endDate:{} for Car id: {} type: {}",
                reservationDetail2.getId(), reservationDetail2.getStartDate(), reservationDetail2.getEndDate(),
                reservationDetail2.getCar().getId(), reservationDetail2.getCar().getType());
        log.info("Reservation successful: id: {} startDate:{} endDate:{} for Car id: {} type: {}",
                reservationDetail3.getId(), reservationDetail3.getStartDate(), reservationDetail3.getEndDate(),
                reservationDetail3.getCar().getId(), reservationDetail3.getCar().getType());
        log.info("Reservation successful: id: {} startDate:{} endDate:{} for Car id: {} type: {}",
                reservationDetail4.getId(), reservationDetail4.getStartDate(), reservationDetail4.getEndDate(),
                reservationDetail4.getCar().getId(), reservationDetail4.getCar().getType());

        assertNotNull(reservationDetail1.getId());
        assertNotNull(reservationDetail2.getId());
        assertNotNull(reservationDetail3.getId());
        assertNotNull(reservationDetail4.getId());

        assertEquals(startDate1, reservationDetail1.getStartDate());
        assertEquals(startDate2, reservationDetail2.getStartDate());
        assertEquals(startDate3, reservationDetail3.getStartDate());
        assertEquals(startDate4, reservationDetail4.getStartDate());

        assertEquals(startDate1.plusDays(5), reservationDetail1.getEndDate());
        assertEquals(startDate2.plusDays(5), reservationDetail2.getEndDate());
        assertEquals(startDate3.plusDays(5), reservationDetail3.getEndDate());
        assertEquals(startDate4.plusDays(5), reservationDetail4.getEndDate());

        log.info(
                "testCarRentalService_ReserveFOURSUVsForFIVEDifferentDays::Testing car rental service for SUV completed");

    }

    @Test
    @Order(3)
    void testCarRentalService_ReserveFOURSUVsForFIVESAMEDays() {
        log.info("testCarRentalService_ReserveFOURSUVsForFIVESAMEDays::Testing car rental service for SUV");
        String startDateStr1 = "20-08-2025";
        String startDateStr2 = startDateStr1;
        String startDateStr3 = startDateStr1;
        String startDateStr4 = startDateStr1;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate startDate1 = LocalDate.parse(startDateStr1, formatter);
        LocalDate startDate2 = LocalDate.parse(startDateStr2, formatter);
        LocalDate startDate3 = LocalDate.parse(startDateStr3, formatter);
        LocalDate startDate4 = LocalDate.parse(startDateStr4, formatter);
        Reservation reservationDetail1 = null;
        Reservation reservationDetail2 = null;
        Reservation reservationDetail3 = null;
        Reservation reservationDetail4 = null;
        try {
            reservationDetail1 = reservationApi.reserveCar(startDate1, startDate1.plusDays(5), CarType.SUV);
            reservationDetail2 = reservationApi.reserveCar(startDate2, startDate2.plusDays(5), CarType.SUV);
            reservationDetail3 = reservationApi.reserveCar(startDate3, startDate3.plusDays(5), CarType.SUV);
            reservationDetail4 = reservationApi.reserveCar(startDate4, startDate4.plusDays(5), CarType.SUV);

        } catch (CRSException ex) {
            log.error("Error occurred while reserving car: {}", ex.getMessage());
        }
        assertNotNull(reservationDetail1);
        assertNotNull(reservationDetail2);
        assertNotNull(reservationDetail3);
        assertNotNull(reservationDetail4);
        log.info("Reservation successful: id: {} startDate:{} endDate:{} for Car id: {} type: {}",
                reservationDetail1.getId(), reservationDetail1.getStartDate(), reservationDetail1.getEndDate(),
                reservationDetail1.getCar().getId(), reservationDetail1.getCar().getType());
        log.info("Reservation successful: id: {} startDate:{} endDate:{} for Car id: {} type: {}",
                reservationDetail2.getId(), reservationDetail2.getStartDate(), reservationDetail2.getEndDate(),
                reservationDetail2.getCar().getId(), reservationDetail2.getCar().getType());
        log.info("Reservation successful: id: {} startDate:{} endDate:{} for Car id: {} type: {}",
                reservationDetail3.getId(), reservationDetail3.getStartDate(), reservationDetail3.getEndDate(),
                reservationDetail3.getCar().getId(), reservationDetail3.getCar().getType());
        log.info("Reservation successful: id: {} startDate:{} endDate:{} for Car id: {} type: {}",
                reservationDetail4.getId(), reservationDetail4.getStartDate(), reservationDetail4.getEndDate(),
                reservationDetail4.getCar().getId(), reservationDetail4.getCar().getType());

        assertNotNull(reservationDetail1.getId());
        assertNotNull(reservationDetail2.getId());
        assertNotNull(reservationDetail3.getId());
        assertNotNull(reservationDetail4.getId());

        assertEquals(startDate1, reservationDetail1.getStartDate());
        assertEquals(startDate2, reservationDetail2.getStartDate());
        assertEquals(startDate3, reservationDetail3.getStartDate());
        assertEquals(startDate4, reservationDetail4.getStartDate());

        assertEquals(startDate1.plusDays(5), reservationDetail1.getEndDate());
        assertEquals(startDate2.plusDays(5), reservationDetail2.getEndDate());
        assertEquals(startDate3.plusDays(5), reservationDetail3.getEndDate());
        assertEquals(startDate4.plusDays(5), reservationDetail4.getEndDate());

        log.info("testCarRentalService_ReserveFOURSUVsForFIVESAMEDays::Testing car rental service for SUV completed");

    }

    @Test
    @Order(4)
    void testCarRentalService_ReserveFIVESUVsForFIVESAMEDays() {
        log.info("testCarRentalService_ReserveFIVESUVsForFIVESAMEDays::Testing car rental service for SUV");
        String startDateStr1 = "20-08-2025";
        String startDateStr2 = startDateStr1;
        String startDateStr3 = startDateStr1;
        String startDateStr4 = startDateStr1;
        String startDateStr5 = startDateStr1;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate startDate1 = LocalDate.parse(startDateStr1, formatter);
        LocalDate startDate2 = LocalDate.parse(startDateStr2, formatter);
        LocalDate startDate3 = LocalDate.parse(startDateStr3, formatter);
        LocalDate startDate4 = LocalDate.parse(startDateStr4, formatter);
        LocalDate startDate5 = LocalDate.parse(startDateStr5, formatter);
        Reservation reservationDetail1 = null;
        Reservation reservationDetail2 = null;
        Reservation reservationDetail3 = null;
        Reservation reservationDetail4 = null;
        try {
            reservationDetail1 = reservationApi.reserveCar(startDate1, startDate1.plusDays(5), CarType.SUV);
            reservationDetail2 = reservationApi.reserveCar(startDate2, startDate2.plusDays(5), CarType.SUV);
            reservationDetail3 = reservationApi.reserveCar(startDate3, startDate3.plusDays(5), CarType.SUV);
            reservationDetail4 = reservationApi.reserveCar(startDate4, startDate4.plusDays(5), CarType.SUV);

        } catch (CRSException ex) {
            log.error("Error occurred while reserving car: {}", ex.getMessage());
        }
        assertNotNull(reservationDetail1);
        assertNotNull(reservationDetail2);
        assertNotNull(reservationDetail3);
        assertNotNull(reservationDetail4);
        log.info("Reservation successful: id: {} startDate:{} endDate:{} for Car id: {} type: {}",
                reservationDetail1.getId(), reservationDetail1.getStartDate(), reservationDetail1.getEndDate(),
                reservationDetail1.getCar().getId(), reservationDetail1.getCar().getType());
        log.info("Reservation successful: id: {} startDate:{} endDate:{} for Car id: {} type: {}",
                reservationDetail2.getId(), reservationDetail2.getStartDate(), reservationDetail2.getEndDate(),
                reservationDetail2.getCar().getId(), reservationDetail2.getCar().getType());
        log.info("Reservation successful: id: {} startDate:{} endDate:{} for Car id: {} type: {}",
                reservationDetail3.getId(), reservationDetail3.getStartDate(), reservationDetail3.getEndDate(),
                reservationDetail3.getCar().getId(), reservationDetail3.getCar().getType());
        log.info("Reservation successful: id: {} startDate:{} endDate:{} for Car id: {} type: {}",
                reservationDetail4.getId(), reservationDetail4.getStartDate(), reservationDetail4.getEndDate(),
                reservationDetail4.getCar().getId(), reservationDetail4.getCar().getType());

        assertNotNull(reservationDetail1.getId());
        assertNotNull(reservationDetail2.getId());
        assertNotNull(reservationDetail3.getId());
        assertNotNull(reservationDetail4.getId());

        assertEquals(startDate1, reservationDetail1.getStartDate());
        assertEquals(startDate2, reservationDetail2.getStartDate());
        assertEquals(startDate3, reservationDetail3.getStartDate());
        assertEquals(startDate4, reservationDetail4.getStartDate());

        assertEquals(startDate1.plusDays(5), reservationDetail1.getEndDate());
        assertEquals(startDate2.plusDays(5), reservationDetail2.getEndDate());
        assertEquals(startDate3.plusDays(5), reservationDetail3.getEndDate());
        assertEquals(startDate4.plusDays(5), reservationDetail4.getEndDate());

        CRSException crsException = assertThrows(CRSException.class, () -> {
            reservationApi.reserveCar(startDate5, startDate5.plusDays(5), CarType.SUV);
        }, () -> "Expected CRSException to be thrown");

        log.error("CRSException message: {}", crsException.getMessage());

        log.info("testCarRentalService_ReserveFIVESUVsForFIVESAMEDays::Testing car rental service for SUV completed");

    }

    @Test
    @Order(5)
    void testCarRentalService_ReserveVANForSeveralDays() {
        log.info("testCarRentalService_ReserveVANForSeveralDays::Testing car rental service for VAN");
        String startDateStr1 = "20-08-2025";
        String startDateStr2 = "21-09-2025";
        String startDateStr3 = "22-10-2025";
        String startDateStr4 = "23-12-2025";
        String startDateStr5 = "24-01-2026";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate startDate1 = LocalDate.parse(startDateStr1, formatter);
        LocalDate startDate2 = LocalDate.parse(startDateStr2, formatter);
        LocalDate startDate3 = LocalDate.parse(startDateStr3, formatter);
        LocalDate startDate4 = LocalDate.parse(startDateStr4, formatter);
        LocalDate startDate5 = LocalDate.parse(startDateStr5, formatter);
        Reservation reservationDetail1 = null;
        Reservation reservationDetail2 = null;
        Reservation reservationDetail3 = null;
        Reservation reservationDetail4 = null;
        Reservation reservationDetail5 = null;
        try {
            reservationDetail1 = reservationApi.reserveCar(startDate1, startDate1.plusDays(12), CarType.VAN);
            reservationDetail2 = reservationApi.reserveCar(startDate2, startDate2.plusDays(32), CarType.VAN);
            reservationDetail3 = reservationApi.reserveCar(startDate3, startDate3.plusDays(33), CarType.VAN);
            reservationDetail4 = reservationApi.reserveCar(startDate4, startDate4.plusDays(50), CarType.VAN);
            reservationDetail5 = reservationApi.reserveCar(startDate5, startDate5.plusDays(100), CarType.VAN);

        } catch (CRSException ex) {
            log.error("Error occurred while reserving car: {}", ex.getMessage());
        }
        assertNotNull(reservationDetail1);
        assertNotNull(reservationDetail2);
        assertNotNull(reservationDetail3);
        assertNotNull(reservationDetail4);
        assertNotNull(reservationDetail5);
        log.info("Reservation successful: id: {} startDate:{} endDate:{} for Car id: {} type: {}",
                reservationDetail1.getId(), reservationDetail1.getStartDate(), reservationDetail1.getEndDate(),
                reservationDetail1.getCar().getId(), reservationDetail1.getCar().getType());
        log.info("Reservation successful: id: {} startDate:{} endDate:{} for Car id: {} type: {}",
                reservationDetail2.getId(), reservationDetail2.getStartDate(), reservationDetail2.getEndDate(),
                reservationDetail2.getCar().getId(), reservationDetail2.getCar().getType());
        log.info("Reservation successful: id: {} startDate:{} endDate:{} for Car id: {} type: {}",
                reservationDetail3.getId(), reservationDetail3.getStartDate(), reservationDetail3.getEndDate(),
                reservationDetail3.getCar().getId(), reservationDetail3.getCar().getType());
        log.info("Reservation successful: id: {} startDate:{} endDate:{} for Car id: {} type: {}",
                reservationDetail4.getId(), reservationDetail4.getStartDate(), reservationDetail4.getEndDate(),
                reservationDetail4.getCar().getId(), reservationDetail4.getCar().getType());
        log.info("Reservation successful: id: {} startDate:{} endDate:{} for Car id: {} type: {}",
                reservationDetail5.getId(), reservationDetail5.getStartDate(), reservationDetail5.getEndDate(),
                reservationDetail5.getCar().getId(), reservationDetail5.getCar().getType());

        assertNotNull(reservationDetail1.getId());
        assertNotNull(reservationDetail2.getId());
        assertNotNull(reservationDetail3.getId());
        assertNotNull(reservationDetail4.getId());
        assertNotNull(reservationDetail5.getId());

        assertEquals(startDate1, reservationDetail1.getStartDate());
        assertEquals(startDate2, reservationDetail2.getStartDate());
        assertEquals(startDate3, reservationDetail3.getStartDate());
        assertEquals(startDate4, reservationDetail4.getStartDate());
        assertEquals(startDate5, reservationDetail5.getStartDate());

        assertEquals(startDate1.plusDays(12), reservationDetail1.getEndDate());
        assertEquals(startDate2.plusDays(32), reservationDetail2.getEndDate());
        assertEquals(startDate3.plusDays(33), reservationDetail3.getEndDate());
        assertEquals(startDate4.plusDays(50), reservationDetail4.getEndDate());
        assertEquals(startDate5.plusDays(100), reservationDetail5.getEndDate());

        log.info("testCarRentalService_ReserveVANForSeveralDays::Testing car rental service for VAN completed");

    }

    @Test
    @Order(6)
    void testCarRentalService_getAllReservations() {
        log.info("testCarRentalService_getAllReservations::Testing car rental service");
        String startDateStr1 = "20-08-2025";
        String startDateStr2 = "21-09-2025";
        String startDateStr3 = "22-10-2025";
        String startDateStr4 = "23-12-2025";
        String startDateStr5 = "24-01-2026";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate startDate1 = LocalDate.parse(startDateStr1, formatter);
        LocalDate startDate2 = LocalDate.parse(startDateStr2, formatter);
        LocalDate startDate3 = LocalDate.parse(startDateStr3, formatter);
        LocalDate startDate4 = LocalDate.parse(startDateStr4, formatter);
        LocalDate startDate5 = LocalDate.parse(startDateStr5, formatter);
        try {
            reservationApi.reserveCar(startDate1, startDate1.plusDays(42), CarType.SUV);
            reservationApi.reserveCar(startDate2, startDate2.plusDays(32), CarType.SEDAN);
            reservationApi.reserveCar(startDate3, startDate3.plusDays(33), CarType.VAN);
            reservationApi.reserveCar(startDate4, startDate4.plusDays(50), CarType.SUV);
            reservationApi.reserveCar(startDate5, startDate5.plusDays(100), CarType.SEDAN);

        } catch (CRSException ex) {
            log.error("Error occurred while reserving car: {}", ex.getMessage());
        }
        List<Reservation> reservations = null;
        try {
            reservations = reservationApi.getAllReservations();

        } catch (CRSException e) {
            log.error("Error occurred while fetching reservations: {}", e.getMessage());
        }
        assertNotNull(reservations);
        assertEquals(5, reservations.size());
        log.info("testCarRentalService_getAllReservations::Testing car rental service completed");
    }

    @Test
    @Order(7)
    void testCarRentalService_cancelONEReservation() {
        log.info("testCarRentalService_cancelONEReservation::Testing car rental service");
        String startDateStr1 = "20-08-2025";
        String startDateStr2 = "21-09-2025";
        String startDateStr3 = "22-10-2025";
        String startDateStr4 = "23-12-2025";
        String startDateStr5 = "24-01-2026";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate startDate1 = LocalDate.parse(startDateStr1, formatter);
        LocalDate startDate2 = LocalDate.parse(startDateStr2, formatter);
        LocalDate startDate3 = LocalDate.parse(startDateStr3, formatter);
        LocalDate startDate4 = LocalDate.parse(startDateStr4, formatter);
        LocalDate startDate5 = LocalDate.parse(startDateStr5, formatter);
        Reservation vanReservation = null;
        try {
            reservationApi.reserveCar(startDate1, startDate1.plusDays(42), CarType.SUV);
            reservationApi.reserveCar(startDate2, startDate2.plusDays(32), CarType.SEDAN);
            vanReservation = reservationApi.reserveCar(startDate3, startDate3.plusDays(33), CarType.VAN);
            reservationApi.reserveCar(startDate4, startDate4.plusDays(50), CarType.SUV);
            reservationApi.reserveCar(startDate5, startDate5.plusDays(100), CarType.SEDAN);

        } catch (CRSException ex) {
            log.error("Error occurred while reserving car: {}", ex.getMessage());
        }
        List<Reservation> reservations = null;
        try {
            reservations = reservationApi.getAllReservations();

        } catch (CRSException e) {
            log.error("Error occurred while fetching reservations: {}", e.getMessage());
        }
        assertNotNull(reservations);
        assertEquals(5, reservations.size());

        reservationApi.cancelReservation(vanReservation.getId());

        assertNotNull(reservations);
        try {
            assertEquals(4, reservationApi.getAllReservations().size());
        } catch (CRSException e) {
            log.error("Error occurred while fetching reservations after cancellation: {}", e.getMessage());
        }

        log.info("testCarRentalService_cancelONEReservation::Testing car rental service completed");
    }

    @AfterEach
    void tearDown() {
        // This should be for cleaning up resources or resetting states after each test
        // But I am using it to check overall reservation status.
        log.info("Overall reservation status after each test method:");
        assertNotNull(availableCars);
        availableCars.forEach(car -> {
            log.info("Car id: {} type: {}",
                    car.getId(), car.getType());
            if (CollectionUtils.isNotEmpty(car.getReservations())) {
                car.getReservations().forEach(reservation -> {
                    log.info("Reservation id: {} startDate: {} endDate: {}",
                            reservation.getId(), reservation.getStartDate(), reservation.getEndDate());
                });
            } else {
                log.info("No reservations for this car.");
            }
        });
    }

}
