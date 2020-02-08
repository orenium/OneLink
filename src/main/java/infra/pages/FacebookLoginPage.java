package infra.pages;

import io.appium.java_client.MobileDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FacebookLoginPage extends BasePage {

    @FindBy(id = "m_login_email")
    public WebElement fbInputEmail;

    @FindBy(id = "m_login_password")
    public WebElement fbInputPassword;

    @FindBy(css = "button[name='login']")
    public WebElement loginBtn;

    @FindBy(css = "div._2pii")
    public WebElement notNowBtn;

    private final String FB_URL = "http://m.facebook.com";

    public FacebookLoginPage(MobileDriver mobileDriver) {
        super(mobileDriver);
    }

    public FacebookMainPage loginToFaceBook(String username, String password) {
        FacebookMainPage faceBookMainPage = null;

        try {
            driver.get(FB_URL);
            if (fbInputEmail.isDisplayed()) {
                fbInputEmail.sendKeys(username);
                fbInputPassword.sendKeys(password);
                loginBtn.click();
            }
            wait.until(ExpectedConditions.visibilityOf(notNowBtn)).click();
            System.out.println("OneTap login was dismissed");
            Thread.sleep(5000);
            allowNotificationsPopup();
            faceBookMainPage = new FacebookMainPage(driver);

        } catch (Exception ex) {
            System.out.println("FacebookLoginPage.loginToFaceBook: " + ex.getMessage());
        }
        return faceBookMainPage;
    }


    public void dissmissOneTapLogin() {


    }

}
