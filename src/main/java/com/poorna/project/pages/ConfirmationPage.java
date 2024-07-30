package com.poorna.project.pages;

import com.poorna.project.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfirmationPage extends AbstractPage {
    public ConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isLoaded() {
        wait.until(ExpectedConditions.visibilityOf(flightConfirmationElement));
        return flightConfirmationElement.isDisplayed();
    }

    private static final Logger log = LoggerFactory.getLogger(ConfirmationPage.class);

    @FindBy(css = "#flights-confirmation-section .card-body .row:nth-child(1) .col:nth-child(2)")
    private WebElement flightConfirmationElement;

    @FindBy(css = "#flights-confirmation-section .card-body .row:nth-child(3) .col:nth-child(2)")
    private WebElement totalPriceElement;

    public String getPrice(){
        String confirmation = this.flightConfirmationElement.getText();
        String price = this.totalPriceElement.getText();
        log.info("Flight confirmation# : {}", confirmation);
        log.info("Total price : {}", price);
        return price;
    }
}
