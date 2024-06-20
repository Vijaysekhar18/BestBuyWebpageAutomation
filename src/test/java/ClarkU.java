import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static java.time.Duration.ofSeconds;
import static org.assertj.core.api.Assertions.assertThat;
public class ClarkU {
    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = WebDriverManager.chromedriver().create();
        // Code is created by Gopireddy Vijay Sekhar Reddy
        // Code id created as part of Class Discussion 8
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5).toMillis(), TimeUnit.MILLISECONDS);
        driver.get(
                "https://www.clarku.edu/academics/graduate/programs/masters/masters-in-computer-science/");
        driver.manage().window().maximize();
    }
    @AfterEach
    void teardown() throws InterruptedException {
        // FIXME: pause for manual browser inspection
        driver.quit();
        //Thread.sleep(ofSeconds(5).toMillis());
        Thread.sleep(ofSeconds(5).toMillisPart());
    }
    @Test
    void testAdmissionLinkFunctionality  () {
        try {
            // Code to locate the admissions link element
            WebElement admissionsLink = driver.findElement(By.xpath("//span[text()='Admissions']"));
            // Click on the admissions link
            admissionsLink.click();
            // Wait for the page to load
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
            // Locate the headerContent element
            WebElement headerContent = driver.findElement(By.tagName("h1"));
            // Get the text from the headerContent element
            String actualText = headerContent.getText();
            // Specify the expected text
            String expectedText = "Undergraduate Admissions";
            if (actualText.equals(expectedText)) {
                System.out.println("Text verification in Undergraduate Admissions page passed!");
            } else {
                System.out.println("Text verification in Undergraduate Admissions page failed. Actual text: " + actualText);
            }
            System.out.println("Test case testAdmissionLinkFunctionality passed.");
        } catch (AssertionError e) {
            System.out.println("Test case testAdmissionLinkFunctionality failed: " + e.getMessage());
            throw e; // Re-throw the exception to mark the test as failed.
        }
    }
    @Test
    void testQuickLinksFunctionality() {
        try {
            // Code to locate the admissions link element
            WebElement quickLink = driver.findElement(By.xpath("//button[@id='quicklinks__button']"));
            // Click on the quick link
            quickLink.click();
            WebElement clarkU = driver.findElement(By.xpath("//a[@href='https://you.clarku.edu']"));
            clarkU.click();
            // Wait for the page to load
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
            // Locate the <img> element
            WebElement imgElement = driver.findElement(By.xpath("//img[@src='/uPortal/media/skins/respondr/clark/img/clark-logo.png']"));
            // Check if the <img> element is displayed
            if (imgElement.isDisplayed()) {
                System.out.println("Clark You Image is displayed.");
            } else {
                System.out.println("Clark You Image is not displayed.");
            }
            System.out.println("Test case testQuickLinksFunctionality passed.");
        } catch (AssertionError e) {
            System.out.println("Test case testQuickLinksFunctionality failed: " + e.getMessage());
            throw e; // Re-throw the exception to mark the test as failed.
        }
    }
    @Test
    void testVisitLinkFunctionality() {
        try {
            // Code to locate the admissions link element
            WebElement visitLink = driver.findElement(By.xpath("//a[@href='https://www.clarku.edu/undergraduate-admissions/campus-visits/']"));
            // Click on the Visit link
            visitLink.click();
            // Wait for the page to load
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
            // Locate the header text element
            WebElement headerText = driver.findElement(By.tagName("h1"));
            // Get the text from the header element
            String actualText = headerText.getText();
            // Specify the expected text
            String expectedText = "Campus Visits";
            // Perform the text verification
            if (actualText.equals(expectedText)) {
                System.out.println("Text verification in Campus Visits page is passed!");
            } else {
                System.out.println("Text verification in Campus Visits page is failed. Actual text: " + actualText);
            }
            System.out.println("Test case testVisitLinkFunctionality passed.");
        } catch (AssertionError e) {
            System.out.println("Test case testVisitLinkFunctionality failed: " + e.getMessage());
            throw e; // Re-throw the exception to mark the test as failed.
        }
    }
    @Test
    void testApplyButton() {
        try {
            // Code to locate the Visit Button element
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement applyButton = new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@aria-label='Apply']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", applyButton);
            // Click on Apply Button
            applyButton.click();
            System.out.println("applyButton is displayed on the website and able to click.");
            // Locate the header text element
            WebElement headerTextElement = driver.findElement(By.tagName("h1"));
            // Get the text from the header text element
            String actualText = headerTextElement.getText();
            // Specify the expected text
            String expectedText = "How to Apply: Graduate Admissions Requirements";
            // code to validate the text validation
            if (actualText.equals(expectedText)) {
                System.out.println("Text validation in How to Apply page passed!");
            } else {
                System.out.println("Text validation in How to Apply page failed. Actual text: " + actualText);
            }
            System.out.println("Test case testApplyButton passed.");
        } catch (AssertionError e) {
            System.out.println("Test case testApplyButton failed: " + e.getMessage());
            throw e; // Re-throw the exception to mark the test as failed.
        }
    }
    @Test
    void testMailingListFunctionality() {
        try {
            // Locating the text input field by its CSS selector
            WebElement firstNameInput = driver.findElement(By.cssSelector("input[placeholder='First Name']"));
            // Enter a value into the text input field
            firstNameInput.sendKeys("Vijay");
            // Locating the text input field by its CSS selector
            WebElement lastNameInput = driver.findElement(By.cssSelector("input[placeholder='Last Name']"));
            // Enter a value into the text input field
            lastNameInput.sendKeys("Sekhar");
            // Locating the submit button by its CSS selector
            WebElement submitButton = driver.findElement(By.cssSelector("button.default.form_button_submit"));
            // Scroll into view and click the submit button
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
            submitButton.click();
            System.out.println("Test case testMailingListFunctionality passed.");
        } catch (TimeoutException e) {
            System.out.println("Timed out waiting for alert to be present. Test case testMailingListFunctionality failed.");
            throw e;
        } catch (Exception e) {
            System.out.println("Test case testMailingListFunctionality failed: " + e.getMessage());
            throw e;
        }
    }
    @Test
    void testScholorshipFunctinality() {
        try {
            // Code to locate the Scholorship section element
            WebElement scholorship = driver.findElement(By.xpath("//a[@href='https://www.clarku.edu/graduate-education/admissions/tuition-and-scholarships/#18299' and contains(@aria-label, 'Tuition and Scholarships')]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scholorship);
            // Click on scholorship section
            scholorship.click();
            System.out.println("scholorship section is displayed on the website and able to click.");
            // Locate the header text element
            WebElement headerTextElement = driver.findElement(By.tagName("h1"));
            // Get the text from the header text element
            String actualText = headerTextElement.getText();
            // Specify the expected text
            String expectedText = "Tuition and Scholarships";
            // code to validate the text validation
            if (actualText.equals(expectedText)) {
                System.out.println("Text validation in Tuition and Scholarships page passed!");
            } else {
                System.out.println("Text validation in Tuition and Scholarships failed. Actual text: " + actualText);
            }
            System.out.println("Test case testScholorshipFunctinality passed.");
        } catch (AssertionError e) {
            System.out.println("Test case testScholorshipFunctinality failed: " + e.getMessage());
            throw e; // Re-throw the exception to mark the test as failed.
        }
    }
    @Test
    void testSocialMediaLinkInstagram() {
        try {
            // Get the current window handle (assuming only one window is open initially)
            String mainWindowHandle = driver.getWindowHandle();
            // Initialize JavascriptExecutor
            JavascriptExecutor js = (JavascriptExecutor) driver;
            // Scroll down to the end of the page
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            // Wait for a few seconds (adjust this as needed)
            Thread.sleep(2000);
            // Find and click the "Follow Us" link
            WebElement instagramLink = driver.findElement(By.linkText("See more of us on Instagram"));
            instagramLink.click();
            // Switch to the new tab or window
            for (String handle : driver.getWindowHandles()) {
                if (!handle.equals(mainWindowHandle)) {
                    driver.switchTo().window(handle);
                    break;
                }
            }
            // Wait for some time for the new page to load (adjust this as needed)
            Thread.sleep(3000);
            // Get the URL of the new page
            String expectedUrl = "https://www.instagram.com/clarkuniversity/";
            String actualUrl = driver.getCurrentUrl();
            System.out.println("Actual URL is: " + actualUrl);
            // Add an assertion to verify the URL after clicking the link
            assert actualUrl.equals(expectedUrl) : "Follow Us link did not navigate to the expected Instagram page";
            System.out.println("Test case testSocialMediaLinkInstagram passed.");
            // Close the new tab or window
            driver.close();
            // Switch back to the main window
            driver.switchTo().window(mainWindowHandle);
        } catch (AssertionError e) {
            System.out.println("Test case testSocialMediaLinkInstagram failed: " + e.getMessage());
            throw e; // Re-throw the exception to mark the test as failed
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void testApplyGradButton() {
        try {
            // Initialize JavascriptExecutor
            JavascriptExecutor js = (JavascriptExecutor) driver;
            // Scroll down to the end of the page
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            // Wait for a few seconds (adjust this as needed)
            Thread.sleep(2000);
            // Find and click the "Follow Us" link
            WebElement gradLink =  driver.findElement(By.cssSelector("a[aria-label='Apply Grad footer link']"));
            gradLink.click();
            // Wait for some time for the new page to load (adjust this as needed)
            Thread.sleep(3000);
            // Get the URL of the new page
            String expectedUrl = "https://www.clarku.edu/graduate-education/admissions/";
            String actualUrl = driver.getCurrentUrl();
            System.out.println("Actual URL is: " + actualUrl);
            // Add an assertion to verify the URL after clicking the link
            assert actualUrl.equals(expectedUrl) : "Grad Link did not navigate to the expected Graduation page";
            System.out.println("Test case testApplyGradButton passed.");
            // Close the new tab or window
            driver.close();
        } catch (AssertionError e) {
            System.out.println("Test case testApplyGradButton failed: " + e.getMessage());
            throw e; // Re-throw the exception to mark the test as failed
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void testContactUs() {
        try {
            // Initialize JavascriptExecutor
            JavascriptExecutor js = (JavascriptExecutor) driver;
            // Scroll down to the end of the page
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            // Wait for a few seconds (adjust this as needed)
            Thread.sleep(2000);
            // Find and click the "Follow Us" link
            WebElement contactUsLink =  driver.findElement(By.cssSelector("a[aria-label='Contact us footer link']"));
            contactUsLink.click();
            // Wait for some time for the new page to load (adjust this as needed)
            Thread.sleep(3000);
            // Get the URL of the new page
            String expectedUrl = "https://www.clarku.edu/contactus/";
            String actualUrl = driver.getCurrentUrl();
            System.out.println("Actual URL is: " + actualUrl);
            // Add an assertion to verify the URL after clicking the link
            assert actualUrl.equals(expectedUrl) : "Contact Us button did not navigate to the expected Contact US page";
            System.out.println("Test case testContactUs passed.");
            // Close the new tab or window
            driver.close();
        } catch (AssertionError e) {
            System.out.println("Test case testContactUs failed: " + e.getMessage());
            throw e; // Re-throw the exception to mark the test as failed
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
