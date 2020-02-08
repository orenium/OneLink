package infra.pages;

import io.appium.java_client.MobileDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FacebookPostPage extends BasePage {

    @FindBy(css = "textarea.composerInput.mentions-input")
    public WebElement postArea;

    @FindBy(css = "button._54k8._52jg._56bs._26vk._56b_._56bw._56bv")
    public WebElement postBtn;

    public FacebookPostPage(MobileDriver driver) {
        super(driver);
    }

    public void post(String postContent) {

        try {
            System.out.println("Posting on FB: " + postContent);
            wait.until(ExpectedConditions.elementToBeClickable(postArea)).sendKeys(postContent);
            driver.hideKeyboard();
            postBtn.click();
            Thread.sleep(5000);  // For the post to be fully posted
            System.out.println("Posted successfully!");
        } catch (Exception ex) {
            System.out.println("FacebookPostPage.post: " + ex.getMessage());
        }
    }
}
