import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public abstract class BaseTest {

    public static AppiumDriver<WebElement> mobileDriver = null;

    private DesiredCapabilities capabilities;

    @BeforeTest
    public void runBeforeEachTest() {
        swtNewAndroidDriver();
    }


    private void swtNewAndroidDriver() {

        capabilities = new DesiredCapabilities();

        try {
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);

            // Emulator
//            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
//            capabilities.setCapability(AndroidMobileCapabilityType.AVD, "Pixel_2");
            // OR
            //Real device
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "3b5de430");

            capabilities.setCapability(AndroidMobileCapabilityType.APPLICATION_NAME, " ");
            capabilities.setCapability(AndroidMobileCapabilityType.BROWSER_NAME, "Chrome");
            capabilities.setCapability("chromedriverExecutable", "/Users/obroshi/Documents/OneLink/src/main/resources/chromedriver");
            capabilities.setCapability(MobileCapabilityType.NO_RESET, false);

            mobileDriver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), capabilities);

        } catch (Exception ex) {
            System.out.println("BaseTest.swtNewAndroidDriver: " + ex.getMessage());
        }
    }

    @AfterTest
    public void runAfterEachTest() {

        if (mobileDriver != null) {
            mobileDriver.quit();
        }
    }

}
