import infra.pages.AppsFlyerPage;
import infra.pages.FacebookLoginPage;
import infra.pages.FacebookMainPage;
import infra.pages.GooglePlayAppPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SomeTest extends BaseTest {

    private final String FB_USERNAME = "uemuptkcvb_1579771708@tfbnw.net";
    private final String FB_PASSWORD = "Aa123456!";
    private final String POST_CONTENT = "https://afonelink.onelink.me/X9ni/d1dee455";
    private AppsFlyerPage appsFlyerPage;
    private final String EXPECTED = "Welcome to the Second Activity!";

    @Test
    public void homeAssignmentTest() {

        System.out.println("Step 1: Login to Facebook");
        FacebookLoginPage loginPage = new FacebookLoginPage(mobileDriver);
        FacebookMainPage mainPage = loginPage.loginToFaceBook(FB_USERNAME, FB_PASSWORD);

        System.out.println("Step 2: Publish the given link on the feed");
        mainPage.postOnFaceBook(POST_CONTENT);

        System.out.println("Step 3: Click on the posted link from the feed");
        GooglePlayAppPage googlePlayAppPage = mainPage.openLastPostURL();

        System.out.println("Step 4 :Install 'AppsFlyer Applinks");
        boolean appInstalled = googlePlayAppPage.installApp();
        if (appInstalled) {
            System.out.println("Step 5 :Launch the AppsFlyer app ");
            appsFlyerPage = googlePlayAppPage.openApp();
        }

        System.out.println("Step 6 :Verify 2nd Activity");
        String actual = appsFlyerPage.getSecondActivityContent();
        if (actual.equals(EXPECTED)) {
            System.out.println("Test Passed");
        } else {
            System.out.println("Test failed");
        }
        Assert.assertEquals(actual, EXPECTED);

    }


}
