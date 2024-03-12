package tests.pages;

import org.openqa.selenium.By;




import static mavendemo.drivers.DriverManager.getDriver;

public class OrderConfirmationPage {
    public String getOrderConfirmationMessage () {
        return getDriver ().findElement (By.cssSelector ("div > mat-card:nth-child(1) > div > div"))
            .getText ();
    }

    public String getOrderDeliveryMessage () {
        return getDriver ().findElement (By.cssSelector ("div.confirmation"))
            .getText ();
    }
    
    public String getThanksMessage () {
        return getDriver ().findElement (By.cssSelector ("h1.confirmation"))
            .getText ();
    }
}
