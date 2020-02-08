package infra.pages;

import io.appium.java_client.MobileDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class GooglePlayAppPage extends BasePage {

    @FindBy(id = "com.android.vending:id/right_button")
    public WebElement installBtn;


    List<WebElement> buttonsOnPage;

    public GooglePlayAppPage(MobileDriver driver) {
        super(driver);
        buttonsOnPage = new ArrayList<>();
    }

    public boolean installApp() {
        boolean IsInstalled = false;
        try {
//            wait.until(ExpectedConditions.elementToBeClickable(installBtn)).click();
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
