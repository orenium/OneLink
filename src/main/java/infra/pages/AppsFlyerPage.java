package infra.pages;

import io.appium.java_client.MobileDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AppsFlyerPage extends BasePage {

    @FindBy(id = "com.appsflyer.appsflyerapplinks:id/textView")
    public WebElement activityContent;

    public AppsFlyerPage(MobileDriver driver) {
        super(driver);
    }

    public String getSecondActivityContent() {
        List<WebElement> textViews;
        int rety = 3;
        try {
            do {
                Thread.sleep(3000);
                textViews = driver.findElements(By.className("android.widget.TextView"));
                rety--;
            } while (!textViews.get(2).getText().isEmpty() && rety > 0);
            return textViews.get(1).getText();
        } catch (Exception ex) {
            System.out.println("AppsFlyerPage.getPageMainContent: " + ex.getMessage());
        }
        return null;
    }

}
