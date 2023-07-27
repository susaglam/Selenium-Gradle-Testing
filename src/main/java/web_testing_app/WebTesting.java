package web_testing_app;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.nio.file.Path;
import java.nio.file.Paths;


public class WebTesting {
    private static WebDriver localDriver;


    public static WebDriver getDriver() {
        return localDriver;
    }


    public static void initSystemProperties() {
        // Collect path to drivers
        Path currentRelativePath = Paths.get("");

        String geckoPath = Paths.get(currentRelativePath.toAbsolutePath().toString(), "drivers/geckodriver.exe").toString();
        geckoPath = geckoPath.replace("\\", "/");
        System.out.println(geckoPath);

        String chromePath = Paths.get(currentRelativePath.toAbsolutePath().toString(), "drivers/chromedriver.exe").toString();
        chromePath = chromePath.replace("\\", "/");
        System.out.println(chromePath);

        // Firefox Driver
        /** Tested for Firefox Version 85.0.2    **/
        /** Tested for geckodriver Version 0.29.0 **/
        System.setProperty("webdriver.gecko.driver", geckoPath);

        // Chrome Driver
        /** Tested for Chrome Version 114       **/
        /** Tested for chromedriver Version 114 **/
        System.setProperty("webdriver.chrome.driver", chromePath);
    }


    public static WebDriver launchDriver(String siteUrl, String browser) {
        if (browser.equals("firefox")) {
            // Set options for Firefox
            FirefoxOptions options = new FirefoxOptions()
                    .addPreference("browser.startup.page", 1)
                    .addPreference("browser.startup.homepage", siteUrl)
                    .setAcceptInsecureCerts(true)
                    .setHeadless(false);

            // Browser is launched on creation of the driver
            quitDriver();
            localDriver = new FirefoxDriver(options);
        } else if (browser.equals("chrome")) {
            // Set options for Chrome
            ChromeOptions options = (ChromeOptions) new ChromeOptions()
                    .addArguments("--homepage \"" + siteUrl + "\"")
                    .setAcceptInsecureCerts(true)
                    .setHeadless(false);

            // Browser is launched on creation of the driver
            quitDriver();
            localDriver = new ChromeDriver(options);
        }

        return localDriver;
    }

    public static void quitDriver() {
        if (localDriver != null) {
            localDriver.quit();
            localDriver = null;
        }
    }
}
