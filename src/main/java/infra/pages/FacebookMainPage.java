package infra.pages;

import io.appium.java_client.MobileDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class FacebookMainPage extends BasePage {

    @FindBy(id = "u_0_t")
    public WebElement userProfileImg;

    @FindBy(css = "div._4g34._6ber._78cq._7cdk._5i2i._52we")
    public WebElement newPostArea;

    @FindAll({
            @FindBy(css = "article._55wo._5rgr._5gh8.async_like._1tl-")})
    public List<WebElement> posts;

    String postContent = "https://afonelink.onelink.me/X9ni/d1dee455";

    public FacebookMainPage(MobileDriver driver) {
        super(driver);
        System.out.println("FB main page is loaded");
    }

    public void postOnFaceBook(String requestedPostContent) {
        FacebookPostPage facebookPostPage = null;

        try {
            postContent = requestedPostContent;
            wait.until(ExpectedConditions.elementToBeClickable(newPostArea)).click();
            facebookPostPage = new FacebookPostPage(driver);
            facebookPostPage.post(postContent);

        } catch (Exception ex) {
            System.out.println("FaceBookMainPage.post: " + ex.getMessage());
        }

    }


    public GooglePlayAppPage openLastPostURL() {
        String lastPost = "";
        List<WebElement> posts;
        int retry = 0;
        try {
            do {
                posts = driver.findElements(By.cssSelector("div.story_body_container span > p"));
                if (!posts.isEmpty()) {
                    lastPost = posts.get(0).getText();  // Latest post on feed
                    System.out.println("Last post: " +lastPost);
                    if (lastPost.equals(postContent)){
                        posts.get(0).click();
                        System.out.println("Opening post link");
                    } else {
                        System.out.println("last post and requested post are not the same!");
                    }
                } else {
                    System.out.println("Unable to find posts. Retrying #" + (retry + 1));
                }
                retry++;
            } while (posts.size() == 0 && retry < 3);
        } catch (Exception ex) {
            System.out.println("FacebookMainPage.openLastPostURL: " + ex.getMessage());
        }
        return new GooglePlayAppPage(driver);
    }

}
