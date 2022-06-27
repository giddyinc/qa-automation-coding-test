package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.boxed.web.BaseTest;

public class BasePage {
    protected static WebDriver driver = BaseTest.driver;

    protected WebDriver getDriver() { return driver; }

    protected WebElement elementBy(By locator) {
        return getDriver().findElement(locator);
    }

    protected void clearAndType(By locator, String input) {
        WebElement element = elementBy(locator);
        element.clear();
        element.sendKeys(input);
    }

    protected void sendKey(By locator, String input) {
        elementBy(locator).sendKeys(input);
    }
}
