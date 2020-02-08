import infra.pages.AppsFlyerPage;
import infra.pages.FacebookMainPage;
import infra.pages.FacebookLoginPage;
import infra.pages.GooglePlayAppPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SomeTest extends BaseTest {

    private final String FB_USERNAME = "uemuptkcvb_1579771708@tfbnw.net";
    private final String FB_PASSWORD = "Aa123456!";
    private final String POST_CONTENT = "https://afonelink.onelink.me/X9ni/d1dee455";
    private String lastPostOnFeed;
    private AppsFlyerPage appsFlyerPage;

    @Test
    public void loginToFacebook() {

        System.out.println("This is the test: loginToFacebook");
        FacebookLoginPage loginPage = new FacebookLoginPage(mobileDriver);
        FacebookMainPage mainPage = loginPage.loginToFaceBook(FB_USERNAME, FB_PASSWORD);
        mainPage.postOnFaceBook(POST_CONTENT);
        GooglePlayAppPage googlePlayAppPage = mainPage.openLastPostURL();
        boolean appInstalled = googlePlayAppPage.installApp();
        if (appInstalled){
           appsFlyerPage = googlePlayAppPage.openApp();
        }

        Assert.assertEquals(appsFlyerPage.getPageMainContent(), "Welcome to the Second Activity!");


    }


}
