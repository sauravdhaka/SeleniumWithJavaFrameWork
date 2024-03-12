package mavendemo.utilities;

import org.openqa.selenium.WebElement;

public class Helper {
     public static void enterText (final WebElement element, final String text) {
        element.click ();
        element.clear ();
        element.sendKeys (text);
    }

    public static void pause (final long timeInMillis) throws InterruptedException {
        Thread.sleep (timeInMillis);
    }

    private Helper () {
        
    }
}
