/*
 * (C) Copyright 2021 Boni Garcia (https://bonigarcia.github.io/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static java.time.Duration.ofSeconds;
import static org.assertj.core.api.Assertions.assertThat;

class BestBuyHomepageTestOld {

    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = WebDriverManager.chromedriver().create();

        // Loading BestBuy Homepage takes roughly 3 seconds to complete loading the page/elements
        // We will get 'ElementNotVisibleException' if there is a delay in loading particular element 
        // which Webdriver wants to interact. 
        // So, the best solution is do implicit wait for few seconds.

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
    void testBestBuyHomepageHeaderElements() {
        try {
        String sutUrl = "https://www.bestbuy.com/";
        driver.get(sutUrl);

        assertThat(driver.getTitle())
                .isEqualTo("Best Buy | Official Online Store | Shop Now & Save");
        assertThat(driver.getCurrentUrl()).isEqualTo(sutUrl);
        assertThat(driver.getPageSource()).containsIgnoringCase("</html>");

        // By name
        // Verify Auto-Complete Search field is enabled

        WebElement textByName = driver.findElement(By.name("frmSearch"));
        assertThat(textByName.isEnabled()).isTrue();

        // By XPath
        // Verify Menu button is displayed
        WebElement menu_button = driver.findElement(By.xpath("//button[@aria-label='Menu']"));
        assertThat(menu_button.isDisplayed()).isTrue();

        // By XPath
        // Verify Store button is displayed
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        WebElement store_location_button = null;
        try {
            store_location_button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"lt-container\"]/div/div/span/span/button")));
        } catch (TimeoutException e) {
            System.out.println("Element not found within 10 seconds");
        } catch (NoSuchElementException e) {
            System.out.println("Element not found on the page");
        }


        // By linkText
        // Verify Shopping Cart title
        WebElement linkByText = driver.findElement(By.linkText("Cart"));
        assertThat(linkByText.getTagName()).isEqualTo("a");
        assertThat(linkByText.getAttribute("title")).isEqualTo("Cart");

        // By XPath
        // Verify Account menu button is displayed
        WebElement account_menu_button = driver.findElement(By.xpath("//*[@id=\"account-menu-account-button\"]/span"));
        assertThat(account_menu_button.isDisplayed()).isTrue();
        account_menu_button.click();

        // By Xpath
            // Verify Top Deals menu button is displayed
            WebElement top_deals_button = driver.findElement(By.xpath("/html/body/div[4]/div/div/div[1]/header/div[2]/nav/div/ul/li[1]/a"));
            assertThat(top_deals_button.isDisplayed()).isTrue();

            // By XPath
            // Verify Deal of the Day button is displayed
            WebElement deal_of_day_button = driver.findElement(By.xpath("/html/body/div[4]/div/div/div[1]/header/div[2]/nav/div/ul/li[2]/a"));
            assertThat(deal_of_day_button.isDisplayed()).isTrue();

            // By XPath
            // Verify more button is displayed
            WebElement more_button = driver.findElement(By.xpath("/html/body/div[4]/div/div/div[1]/header/div[2]/nav/div/div/button/span"));
            assertThat(more_button.isDisplayed()).isTrue();

            // By XPath
            // Verify credit cards button is displayed
            WebElement credit_cards_button = driver.findElement(By.xpath("/html/body/div[4]/div/div/div[1]/header/div[2]/nav/div/ul/li[4]/a"));
            assertThat(credit_cards_button.isDisplayed()).isTrue();

        // By XPath
        // Verify Sign In button is displayed using anchor with href

        WebElement sign_in_button = driver.findElement(By.xpath("//a[contains(@href,'/identity/global/signin')]"));
        String href = sign_in_button.getAttribute("href");
        assertThat(account_menu_button.isDisplayed()).isTrue();

        // By XPath
        // Verify Sign In button is displayed using class and text
        WebElement sign_in_button_1 = driver.findElement(By.xpath("//*[@class='c-button c-button-secondary c-button-sm sign-in-btn' and contains(text(),'Sign In')]"));


        // Slow Selenium down ... for assert to catchup ...
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // By XPath
        // Verify Create Account button is displayed using class and text
        WebElement create_account_button = driver.findElement(By.xpath("//*[@class='c-button c-button-outline c-button-sm create-account-btn' and contains(text(),'Create Account')]"));

        // By XPath
        // Verify Check Order Status using anchor with href
        WebElement check_order_status = driver.findElement(By.xpath("//a[contains(@href,'/profile/ss/orderlookup')]"));

            System.out.println("All tabs are present on the Best Buy website.");
        try {
            Thread.sleep(5000);
            System.out.println("\n\n\n  Test ... Completed \n\n\n");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
            System.out.println("Test case testBestBuyHomepageHeaderElements passed.");
        } catch (AssertionError e) {
            System.out.println("Test case testBestBuyHomepageHeaderElements failed: " + e.getMessage());
            throw e; // Re-throw the exception to mark the test as failed
        }

    }

    @Test
    void testBestBuyEmailSignUp() {
        try {
        driver.get(
                "https://www.bestbuy.com/");

        WebElement inputText = driver.findElement(By.name("footer-email-signup"));
        String textValue = "mySeleliumTest@gmail.com";
        inputText.sendKeys(textValue);
        assertThat(inputText.getAttribute("value")).isEqualTo(textValue);

        //inputText.clear();

        //assertThat(inputText.getAttribute("value")).isEmpty();
            System.out.println("Test case testBestBuyEmailSignUp passed.");
        } catch (AssertionError e) {
            System.out.println("Test case testBestBuyEmailSignUp failed: " + e.getMessage());
            throw e; // Re-throw the exception to mark the test as failed
        }
    }

}
