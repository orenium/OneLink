package infra.pages;

import io.appium.java_client.MobileDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AppsFlyerPage extends BasePage {

    @FindBy(id = "com.appsflyer.appsflyerapplinks:id/textView")
    public WebElement activityContent;

    public AppsFlyerPage(MobileDriver driver) {
        super(driver);
    }

    public String getPageMainContent() {
        try {
            Thread.sleep(4000);   // For the redirect to the 2sn Activity
            wait.until(ExpectedConditions.visibilityOf(activityContent));
            return activityContent.getText();
        } catch (Exception ex) {
            System.out.println("AppsFlyerPage.getPageMainContent: " + ex.getMessage());
        }
        return null;
    }

}
