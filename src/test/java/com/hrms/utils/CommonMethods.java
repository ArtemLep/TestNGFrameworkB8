package com.hrms.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CommonMethods {
    public static WebDriver driver;

    /**
     * this method will open a browser, set up configuration and navigate to the URL
     */
    @BeforeMethod(alwaysRun = true)
    public static void setUp() {

        ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPATH);
        switch (ConfigsReader.getPropertyValue("browser").toLowerCase()) {
            case "chrome":

                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Ivalid browser");
        }
        driver.get(ConfigsReader.getPropertyValue("url"));
        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
    }

    /**
     * this method will close any open browser
     */
    @AfterMethod(alwaysRun = true)
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /*This method will clear a textbox and send text to it*/
    public static void sendText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);

    }

    /**
      this method will return an object of Explicit wait
    /**
     *
     * @return
     */
    public static WebDriverWait getWait() {
        WebDriverWait wait = new WebDriverWait(driver, Constants.EXPLICIT_WAIT);
        return wait;
    }

    /*Theis methods wil wait until given element becomes clickable*/
    /**
     *
     * @param element
     */
    public static void waitForClickability(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));

    }

    /*Theis methods wil wait until and then click */
    /**
     *
     * @param element
     */
    public static void click(WebElement element) {
        waitForClickability(element);
        element.click();
    }

    public static JavascriptExecutor getJSExecutor() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js;
    }

    public static void JSclick(WebElement element) {
        getJSExecutor().executeScript("argumetns[0].click();", element);

    }

    /**
     *
     * @param fileName
     */
    public static void takeScreenshot(String fileName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(sourceFile, new File(Constants.SCRENSHOT_FILEPATH + fileName + getTimeStamp("yyyy-MM-dd-HH-mm-ss")+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param pattern
     * @return
     */
    public static String getTimeStamp(String pattern) {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

       return (sdf.format(date));

    }
}

