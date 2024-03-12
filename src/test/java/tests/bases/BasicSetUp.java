package tests.bases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import static mavendemo.drivers.DriverManager.createDriver;
import static mavendemo.drivers.DriverManager.quitDriver;

import mavendemo.enums.Browsers;;
public class BasicSetUp {
    @Parameters ("browser")
    @BeforeClass (alwaysRun = true)
    public void setupTest (final String browser) {
        createDriver(Browsers.valueOf (browser.toUpperCase ()));
    }

    @AfterClass (alwaysRun = true)
    public void tearDown () {
        quitDriver ();
    }
}
