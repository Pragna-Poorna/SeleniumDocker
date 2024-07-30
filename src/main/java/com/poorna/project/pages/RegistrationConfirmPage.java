package com.poorna.project.pages;

import com.poorna.project.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationConfirmPage extends AbstractPage {

    public RegistrationConfirmPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isLoaded() {
        this.wait.until(ExpectedConditions.visibilityOf(goToFlightsSearchButton));
        return goToFlightsSearchButton.isDisplayed();
    }

    @FindBy(id = "go-to-flights-search")
    private WebElement goToFlightsSearchButton;

    @FindBy(css = "#registration-confirmation-section p b")
    private WebElement firstNameElement;

    public String getFirstName(){
        return this.firstNameElement.getText();
    }

    public void goToFlightsSearch(){
        this.goToFlightsSearchButton.click();
    }


}
