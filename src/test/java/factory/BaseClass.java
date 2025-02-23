package factory;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseClass {

    static WebDriver driver;
    static Properties properties;
    static Logger logger;

    public static WebDriver initialSetup() throws IOException {
        if (getProperties().getProperty("execution_env").equalsIgnoreCase("local")) {
            switch (getProperties().getProperty("browser").toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                default:
                    System.out.println("No matching browser");
                    driver = null;
            }
        } else if (getProperties().getProperty("execution_env").equalsIgnoreCase("remote")) {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            switch (getProperties().getProperty("os").toLowerCase()) {
                case "windows":
                    desiredCapabilities.setPlatform(Platform.WIN10);
                    break;
                case "mac":
                    desiredCapabilities.setPlatform(Platform.MAC);
                    break;
                case "linux":
                    desiredCapabilities.setPlatform(Platform.LINUX);
                    break;
                default:
                    System.out.println("Incorrect OS specified in XML");
            }

            switch (getProperties().getProperty("browser").toLowerCase()) {
                case "chrome":
                    desiredCapabilities.setBrowserName("chrome");
                    break;
                case "edge":
                    desiredCapabilities.setBrowserName("MicrosoftEdge");
                    break;
                case "firefox":
                    desiredCapabilities.setBrowserName("firefox");
                    break;
                default:
                    System.out.println("Incorrect browser name specified in XML");
                    driver = null;
            }
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), desiredCapabilities);
        } else {
            System.out.println("Incorrect execution environment specified in properties file");
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static Properties getProperties() throws IOException {
        FileReader propertiesFile = new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
        properties = new Properties();
        properties.load(propertiesFile);
        return properties;
    }

    public static Logger getLogger() {
        logger = LogManager.getLogger(); // Log4j
        return logger;
    }

    public static String randomString(int nrOfCharacters) {
        return RandomStringUtils.randomAlphabetic(nrOfCharacters);
    }

    public static String randomNumber(int nrOfNumbers) {
        return RandomStringUtils.randomNumeric(nrOfNumbers);
    }

    public static String randomAlphaNumeric(int nrOfCharacters, int nrOfNumbers) {
        return RandomStringUtils.randomAlphabetic(nrOfCharacters) + "$G" + RandomStringUtils.randomNumeric(nrOfNumbers);
    }

}