package com.boxed.web;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class HomePageTest extends BaseTest {

    @Test(description = "go to Boxed homepage & verify page title", groups = "sample")
    public void testHomePageNavigation() {
        driver.get("https://www.boxed.com/");
        assertEquals(driver.getTitle(), "Wholesale | No Membership | Save Money, Time, & Gas | Boxed", "invalid page title");
    }
}
