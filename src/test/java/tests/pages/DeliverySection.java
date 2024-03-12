package tests.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;



import static mavendemo.drivers.DriverManager.getDriver;

public class DeliverySection {
    
    public WebElement continueBtn () {
        return getDriver ().findElement (By.cssSelector (".btn.nextButton"));
    }
    
    public String getDeliveryAddress () {
        return getDriver ().findElement (By.cssSelector ("mat-card > div.addressCont > div:nth-child(3)"))
            .getText ();
    }

    public String getDeliveryAddressCountry () {
        return getDriver ().findElement (By.cssSelector ("mat-card > div.addressCont > div:nth-child(4)"))
            .getText ();
    }

    public String getDeliveryAddressName () {
        return getDriver ().findElement (By.cssSelector ("mat-card > div.addressCont > div:nth-child(2)"))
            .getText ();
    }

    public String getDeliveryAddressPhoneNumber () {
        return getDriver ().findElement (By.cssSelector ("mat-card > div.addressCont > div:nth-child(5)"))
            .getText ();
    }

    public WebElement oneDayDeliveryOption () {
        return getDriver ().findElement (By.cssSelector ("input.mat-radio-input"));
    }

    public void selectDeliveryOption () {
        final Actions actions = new Actions (getDriver ());
        actions.pause (Duration.ofSeconds (5))
            .click (oneDayDeliveryOption ())
            .build ()
            .perform ();
        continueBtn ().click ();
    }
}
