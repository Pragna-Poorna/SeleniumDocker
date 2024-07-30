package com.poorna.project.pages;

import com.poorna.project.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SelectionPage extends AbstractPage {
    public SelectionPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isLoaded() {
        wait.until(ExpectedConditions.visibilityOf(confirmFlightsButton));
        return confirmFlightsButton.isDisplayed();
    }

    @FindBy(name = "departure-flight")
    private List<WebElement> departureFlightsOptions;

    @FindBy(name = "arrival-flight")
    private List<WebElement> arrivalFlightsOptions;

    @FindBy(id = "confirm-flights")
    private WebElement confirmFlightsButton;

    public void selectFlights(){
        int random = ThreadLocalRandom.current().nextInt(0, departureFlightsOptions.size());
        departureFlightsOptions.get(random).click();
        arrivalFlightsOptions.get(random).click();
    }

    public void confirmFlights(){
        moveAndClick(confirmFlightsButton);
    }

}
