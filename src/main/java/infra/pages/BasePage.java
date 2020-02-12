package infra.pages;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class BasePage {

    public MobileDriver driver;
    public WebDriverWait wait;
    public static TouchAction touchAction;

    public BasePage(MobileDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
        touchAction = new TouchAction(driver);
        PageFactory.initElements(driver, this);

    }

    public void allowNotificationsPopup() {

        try {
            switchContext("NATIVE_APP");
            driver.findElement(By.xpath(".//android.widget.Button[@text='Allow']")).click();
            System.out.println("Allow notifications button was clicked");
            Thread.sleep(2000);
            switchContext("CHROMIUM");
        } catch (Exception ex) {
            System.out.println("BasePage.allowNotificationsPopup: " + ex.getMessage());
        }

    }


    public void switchContext(String context) {

        try {
            Set contexts = driver.getContextHandles();
            for (Object s : contexts) {
                if (s.toString().contains(context)) {
                    driver.context(s.toString());
                    System.out.println("context was changed to " + context);
                }
            }
        } catch (Exception ex) {
            System.out.println("BasePage.switchContext: " + ex.getMessage());
        }
    }

}
