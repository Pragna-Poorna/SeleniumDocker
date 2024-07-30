package com.poorna.tests;

import com.google.common.util.concurrent.Uninterruptibles;
import com.poorna.tests.listeners.TestListener;
import com.poorna.tests.utils.Config;
import com.poorna.tests.utils.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

@Listeners({TestListener.class})
public abstract class AbstractTest {
    private static final Logger log = LoggerFactory.getLogger(AbstractTest.class);
    protected WebDriver driver;

    @BeforeSuite
    public void setupConfig(){
        Config.initialize();
    }
    @BeforeTest
    public void setDriver(ITestContext context) throws MalformedURLException {
        driver = Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED))?getRemoteDriver()
                :getLocalDriver();
        context.setAttribute(Constants.DRIVER,driver);
    }

    public WebDriver getRemoteDriver() throws MalformedURLException {
        Capabilities capabilities = new ChromeOptions();
        if(Config.get(Constants.BROWSER).equalsIgnoreCase(Constants.FIREFOX)){
            capabilities = new FirefoxOptions();
        }
        String urlFormat = Config.get(Constants.GRID_URL_FORMAT);
        String hubHost = Config.get(Constants.GRID_HUB_HOST);
        String url = String.format(urlFormat,hubHost);
        log.info("grid url : {}",url);
        return new RemoteWebDriver(new URL(url),capabilities);
    }

    private WebDriver getLocalDriver(){
        WebDriverManager.chromedriver().clearDriverCache().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    @AfterTest
    public void quitDriver(){
        driver.quit();
    }
}
