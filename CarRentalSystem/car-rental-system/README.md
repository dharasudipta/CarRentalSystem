# CarRental System - Basic

By Sudipta Dhara

## Technology Used

* Maven(Wrapper)(for Build)
* Java(As Programming Language)

## Design Pattern Used

* Hexagonal/Port & Adapter

## How to build

* Check out and execute (inside directory CarRentalSystem/CarRentalSystem
/car-rental-system)
  * mvn clean install

## How to run the Test

* Either the mvn clean install will run it.
* or run the test class "CarRentalSystem/car-rental-system/crs-core/src/test/java/com/dharasudipta/crs/CarRentalSystemTest.java"

## Assumptions and Limitations

1. Reservation can book only one car within given date range.
2. No validation for past date checkes
3. Secure coding practice - Not ran
4. Random car for a provided car type from the inventory will be allocated - No wato choose a specific make and model.
5. One Car reservation at a time, not a bulk reservation possible
6. Rotation is not possible for car health
7. Extending reservation on same car is not possible - a different car might be allocated for next booking.
8. No upper limit on maximum number of days , a car can be booked for ages.
9. System do not handle TimeStamp.
