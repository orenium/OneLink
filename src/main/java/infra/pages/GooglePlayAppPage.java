package infra.pages;

import io.appium.java_client.MobileDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class GooglePlayAppPage extends BasePage {

    private List<WebElement> buttonsOnPage;

    public GooglePlayAppPage(MobileDriver driver) {
        super(driver);
        buttonsOnPage = new ArrayList<>();
    }

    public boolean installApp() {
        boolean IsInstalled = false;
        try {
            Thread.sleep(5000);
            switchContext("NATIVE_APP");
            buttonsOnPage = driver.findElements(By.className("android.widget.Button"));
            for (WebElement element : buttonsOnPage) {
                if (element.getText().equals("Install")) {
                    element.click();
                    System.out.println("Installing app...");
                    IsInstalled = true;
                    Thread.sleep(5000);
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println("GooglePlayAppPage.installApp: " + ex.getMessage());
        }
        return IsInstalled;
    }

    public AppsFlyerPage openApp() {

        try {
            buttonsOnPage = driver.findElements(By.className("android.widget.Button"));
            for (WebElement element : buttonsOnPage) {
                if (element.getText().equals("Open")) {
                    element.click();
                    System.out.println("Opening app...");
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println("GooglePlayAppPage.openApp: " + ex.getMessage());
        }
        return new AppsFlyerPage(driver);
    }
}
