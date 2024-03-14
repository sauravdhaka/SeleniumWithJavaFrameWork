package mavendemo.drivers;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import mavendemo.enums.Browsers;

public final class DriverManager {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
    private static final Logger LOG = LogManager.getLogger("DriverManager.class");
    private static final String NO_SANDBOX = "--no-sandbox";
    private static final String DISABLE_DEV_SHM = "--disable-dev-shm-usage";
    private static final String CUSTOM_WINDOW_SIZE = "--window-size=1050,600";
    private static final String HEADLESS = "--headless";

    public static void createDriver(final Browsers browser) {
        switch (browser) {
            case FIREFOX -> setupFirefoxDriver();
            case EDGE -> setupEdgeDriver();
            case OPERA -> setupOperaDriver();
            default -> setupChromeDriver();
        }
        setupBrowserTimeouts();
    }

    public static WebDriver getDriver() {
        return DriverManager.DRIVER.get();
    }

    public static void quitDriver() {
        if (null != DRIVER.get()) {
            LOG.info("Closing the driver...");
            getDriver().quit();
            DRIVER.remove();
        }
    }

    


    private static void setDriver(final WebDriver driver) {
        DriverManager.DRIVER.set(driver);
    }

    private static void setupBrowserTimeouts() {
        LOG.info("Setting Browser Timeouts....");
        getDriver().manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(20));
        getDriver().manage()
                .timeouts()
                .pageLoadTimeout(Duration.ofSeconds(20));
        getDriver().manage()
                .timeouts()
                .scriptTimeout(Duration.ofSeconds(20));
    }

    private static void setupChromeDriver() {
        LOG.info("Setting up Chrome Driver...");
        final var isHeadless = Boolean.parseBoolean(Objects.requireNonNullElse(System.getProperty("headless"), "true"));
        final var chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("safebrowsing.enabled", "true");
        chromePrefs.put("download.prompt_for_download", "false");
        chromePrefs.put("download.default_directory",
                String.valueOf(Paths.get(System.getProperty("user.home"), "Downloads")));
        final var options = new ChromeOptions();
        options.addArguments(NO_SANDBOX);
        options.addArguments(DISABLE_DEV_SHM);
        options.addArguments(CUSTOM_WINDOW_SIZE);
        if (isHeadless) {
            options.addArguments(HEADLESS);
        }
        options.addArguments("--safebrowsing-disable-download-protection");
        options.setExperimentalOption("prefs", chromePrefs);

        setDriver(WebDriverManager.chromedriver()
                .capabilities(options)
                .create());
        LOG.info("Chrome Driver created successfully!");
    }

    private static void setupEdgeDriver() {
        LOG.info("Setting up Edge Driver....");
        setDriver(WebDriverManager.edgedriver()
                .create());
        LOG.info("Edge Driver created successfully!");
    }

    private static void setupFirefoxDriver() {
        LOG.info("Setting up Firefox Driver....");
        final var options = new FirefoxOptions();
        options.addArguments(NO_SANDBOX);
        options.addArguments(DISABLE_DEV_SHM);
        options.addArguments(CUSTOM_WINDOW_SIZE);
        options.addArguments(HEADLESS);
        setDriver(WebDriverManager.firefoxdriver()
                .capabilities(options)
                .create());
        LOG.info("Firefox Driver created successfully!");
    }

    private static void setupOperaDriver() {
        LOG.info("Setting up Opera Driver....");
        setDriver(WebDriverManager.operadriver()
                .create());
        LOG.info("Opera Driver created successfully!");

    }

    private DriverManager() {
    }
}
