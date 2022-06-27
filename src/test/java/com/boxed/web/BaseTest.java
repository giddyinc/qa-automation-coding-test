package com.boxed.web;

import helpers.ProxyHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;

public class BaseTest {
    public static WebDriver driver;

    protected WebDriver getDriver() { return driver;}

    private ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("w3c", false);

        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        Proxy proxy = ProxyHelper.getProxy();
        System.out.println("proxy " + proxy.toJson());
        desiredCapabilities.setCapability(CapabilityType.PROXY, proxy);

        chromeOptions.merge(desiredCapabilities);
        return chromeOptions;
    }

    @BeforeSuite(alwaysRun = true)
    public void setDriver() {
        ProxyHelper.startBrowserMobProxyServer();
        Reporter.log("in @BeforeSuite...", true);
        WebDriverManager.chromedriver().setup();
        ChromeDriverService chromeDriverService = new ChromeDriverService.Builder()
                .usingAnyFreePort()
                .withVerbose(true)
                .withLogFile(new File("chrome-debug.log"))
                .build();

        driver = new ChromeDriver(chromeDriverService, getChromeOptions());
        driver.manage().window().maximize();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDriver() {
        Reporter.log("in @AfterSuite...", true);
        getDriver().quit();
        ProxyHelper.stopServer();
    }
}
