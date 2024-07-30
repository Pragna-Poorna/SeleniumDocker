package com.poorna.tests;

import com.poorna.project.pages.*;
import com.poorna.tests.model.ReservationTestData;
import com.poorna.tests.utils.Config;
import com.poorna.tests.utils.Constants;
import com.poorna.tests.utils.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ReservationTest extends AbstractTest {

    private ReservationTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void setParameters(String testDataPath){
        testData = JsonUtil.getTestData(testDataPath, ReservationTestData.class);
    }

    @Test
    public void userRegistrationTest(){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo(Config.get(Constants.FLIGHT_RESERVATION_URL));
        Assert.assertTrue(registrationPage.isLoaded());

        registrationPage.enterUserDetails(testData.firstName(), testData.lastName());
        registrationPage.enterUserCredentials(testData.email(), testData.password());
        registrationPage.enterAddress(testData.street(), testData.city(), testData.zip());
        registrationPage.register();
    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTest(){
        RegistrationConfirmPage registrationConfirmationPage = new RegistrationConfirmPage(driver);
        Assert.assertTrue(registrationConfirmationPage.isLoaded());
        Assert.assertEquals(registrationConfirmationPage.getFirstName(), testData.firstName());
        registrationConfirmationPage.goToFlightsSearch();
    }

    @Test(dependsOnMethods = "registrationConfirmationTest")
    public void flightsSearchTest(){
        SearchPage flightsSearchPage = new SearchPage(driver);
        Assert.assertTrue(flightsSearchPage.isLoaded());
        flightsSearchPage.selectPassengers(testData.passengersCount());
        flightsSearchPage.searchFlights();
    }

    @Test(dependsOnMethods = "flightsSearchTest")
    public void flightsSelectionTest(){
        SelectionPage flightsSelectionPage = new SelectionPage(driver);
        Assert.assertTrue(flightsSelectionPage.isLoaded());
        flightsSelectionPage.selectFlights();
        flightsSelectionPage.confirmFlights();
    }

    @Test(dependsOnMethods = "flightsSelectionTest")
    public void flightReservationConfirmationTest(){
        ConfirmationPage flightConfirmationPage = new ConfirmationPage(driver);
        Assert.assertTrue(flightConfirmationPage.isLoaded());
        Assert.assertEquals(flightConfirmationPage.getPrice(), testData.expectedPrice());
    }
}
