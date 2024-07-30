package com.poorna.tests.model;

public record ReservationTestData(String firstName, String lastName,
                                  String email, String password,
                                  String street, String city,
                                  String zip, String passengersCount,
                                  String expectedPrice) {
}
