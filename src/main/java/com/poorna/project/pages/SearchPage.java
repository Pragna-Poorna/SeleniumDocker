package com.poorna.project.pages;

import com.poorna.project.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class SearchPage extends AbstractPage {
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isLoaded() {
        wait.until(ExpectedConditions.visibilityOf(passengerSelect));
        return passengerSelect.isDisplayed();
    }
    @FindBy(id = "passengers")
    private WebElement passengerSelect;

    @FindBy(id = "search-flights")
    private WebElement searchFlightsButton;

    public void selectPassengers(String noOfPassengers){
        Select passengers = new Select(passengerSelect);
        passengers.selectByValue(noOfPassengers);
    }

    public void searchFlights(){
        moveAndClick(searchFlightsButton);
    }
}
