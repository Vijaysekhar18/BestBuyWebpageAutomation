import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static java.time.Duration.ofSeconds;
import static org.assertj.core.api.Assertions.assertThat;

public class FollowUsLinkTest {
    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = WebDriverManager.chromedriver().create();

        // Code is created by Gopireddy Vijay Sekhar Reddy
        // Code id created as part of Class Discussion 8

        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5).toMillis(), TimeUnit.MILLISECONDS);

    }

    @AfterEach
    void teardown() throws InterruptedException {
        // FIXME: pause for manual browser inspection
        driver.quit();
        //Thread.sleep(ofSeconds(5).toMillis());
        Thread.sleep(ofSeconds(5).toMillisPart());


    }
    @Test
    void testFollowUsLink() {
        try {
            driver.get(
                    "https://www.clarku.edu/academics/graduate/programs/masters/masters-in-computer-science/");

            // Initialize JavascriptExecutor
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Scroll down to the end of the page
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

            // Wait for a few seconds (adjust this as needed)
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Find and click the "Follow Us" link
            WebElement followUsLink = driver.findElement(By.linkText("See more of us on LinkedIn"));
            //WebElement followUsLink = driver.findElement(By.xpath("/html/body/div[1]/footer/div/div[1]/div/div[3]/ul/li[6]/a/span[1]"));

            followUsLink.click();
            Thread.sleep(2000);
            // Add an assertion to verify the URL after clicking the link
            String expectedUrl = "https://www.clarku.edu/academics/graduate/programs/masters/masters-in-computer-science/";
            String actualUrl = driver.getCurrentUrl();
            assert actualUrl.equals(expectedUrl) : "Follow Us link did not navigate to the expected LinkedIn page";
            System.out.println("Test case testFollowUsLink passed.");
        } catch (AssertionError e) {
            System.out.println("Test case testFollowUsLink failed: " + e.getMessage());
            throw e; // Re-throw the exception to mark the test as failed
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
