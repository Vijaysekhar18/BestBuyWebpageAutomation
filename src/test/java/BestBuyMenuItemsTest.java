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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static java.time.Duration.ofSeconds;
import static org.assertj.core.api.Assertions.assertThat;

class BestBuyMenuItemsTest {

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
        driver.quit();
        //Thread.sleep(ofSeconds(5).toMillis());
        Thread.sleep(ofSeconds(5).toMillisPart());

    }

    @Test
    void testBestBuyMenuItemElements() {
        try {
        String sutUrl = "https://www.bestbuy.com/";
        driver.get(sutUrl);
        driver.manage().window().maximize();

        assertThat(driver.getTitle())
                .isEqualTo("Best Buy | Official Online Store | Shop Now & Save");
        assertThat(driver.getCurrentUrl()).isEqualTo(sutUrl);
        assertThat(driver.getPageSource()).containsIgnoringCase("</html>");

        // By XPath
        // Verify Menu Button button is displayed and able to click the button
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        WebElement menu_hamburger__button = null;
        //WebElement menu_hamburger__button = driver.findElement(By.xpath("//button[@class=\"c-button-unstyled hamburger-menu-button\"]"));;

        try {
             menu_hamburger__button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class=\"c-button-unstyled hamburger-menu-button\"]")));
            assertThat(menu_hamburger__button.isDisplayed()).isTrue();
        } catch (TimeoutException e) {
            System.out.println("Element menu not found within 10 seconds");
        } catch (NoSuchElementException e) {
            System.out.println("Element menu not found on the page");
        }
            Thread.sleep(5000);
            menu_hamburger__button.click();
            System.out.println("Menu button is present and able to clik on the button in Best Buy website.");
           // By XPath
            // Verify Deals Button under Menu button is displayed and able to click the button
            WebElement Deals_menu_hamburger__button = null;
            try {
                Deals_menu_hamburger__button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class=\"c-button-unstyled top-four v-fw-medium\"][text()=\"Deals\"]")));
                assertThat(menu_hamburger__button.isDisplayed()).isTrue();
            } catch (TimeoutException e) {
                System.out.println("Element Deals_menu_hamburger__button not found within 10 seconds");
            } catch (NoSuchElementException e) {
                System.out.println("Element Deals_menu_hamburger__button not found on the page");
            }
            Deals_menu_hamburger__button.click();
            System.out.println("Deals_menu_hamburger__button button is present and able to clik on the button in Best Buy website.");

            // By XPath
            // Verify Deals Button under Menu button is displayed and able to click the button
            WebElement ClearanceDeals_Deals_menu_hamburger__button = null;
            try {
                ClearanceDeals_Deals_menu_hamburger__button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class=\"hamburger-menu-flyout-list-item  \"][contains(text(),\"Clearance Deals\")]")));
                assertThat(ClearanceDeals_Deals_menu_hamburger__button.isDisplayed()).isTrue();
            } catch (TimeoutException e) {
                System.out.println("Element ClearanceDeals_Deals_menu_hamburger__button not found within 10 seconds");
            } catch (NoSuchElementException e) {
                System.out.println("Element ClearanceDeals_Deals_menu_hamburger__button not found on the page");
            }
            ClearanceDeals_Deals_menu_hamburger__button.click();
            System.out.println("Deals_menu_hamburger__button button is present and able to clik on the button in Best Buy website.");

            try {
            Thread.sleep(5000);
            System.out.println("\n\n\n  Test ... Completed \n\n\n");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

            // By XPath
            // Verify that User is able to click on sort by drop down and view the results based on discount
            WebElement Sortby_dropdown = null;
            try {
                Sortby_dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id=\"sort-by-select\"]")));
                assertThat(Sortby_dropdown.isDisplayed()).isTrue();
            } catch (TimeoutException e) {
                System.out.println("Element Sortby_dropdown not found within 10 seconds");
            } catch (NoSuchElementException e) {
                System.out.println("Element Sortby_dropdown not found on the page");
            }
            Sortby_dropdown.click();
            // Create a Select object from the WebElement
            Select dropdown = new Select(Sortby_dropdown);
            // Select a value by visible text
            dropdown.selectByVisibleText("Best Discount");
            System.out.println("Sortby_dropdown button is present and able to clik on the button in Best Buy website.");
            try {
                Thread.sleep(5000);
                System.out.println("\n\n\n  Test ... Completed \n\n\n");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            // By XPath
            // Verify Clearance Deals Text is displayed
            WebElement ClearanceDeals_text = driver.findElement(By.xpath("//button[@aria-label='Menu']"));
            // Check if the element is visible
            boolean isElementVisible = ClearanceDeals_text.isDisplayed();
            // Print the result
            if (isElementVisible) {
                System.out.println("Clearance Deals page is visible and sorted according to discount is visible.");
            } else {
                System.out.println("Clearance page is not visible.");
            }
            System.out.println("Test case testBestBuyMenuItemElements  passed.");
        } catch (AssertionError e) {
            System.out.println("Test case testBestBuyMenuItemElements  failed: " + e.getMessage());
            throw e; // Re-throw the exception to mark the test as failed
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


}
