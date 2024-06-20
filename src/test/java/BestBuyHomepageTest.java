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
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static java.time.Duration.ofSeconds;
import static org.assertj.core.api.Assertions.assertThat;

// Created by Vijay for AAssignment purpose for SQA cource
// Language USed JAva

class BestBuyHomepageTest {

    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = WebDriverManager.chromedriver().create();

        // Loading BestBuy Homepage takes roughly 3 seconds to complete loading the page/elements
        // We will get 'ElementNotVisibleException' if there is a delay in loading particular element 
        // which Webdriver wants to interact. 
        // So, the best solution is do implicit wait for few seconds.

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5).toMillis(), TimeUnit.MILLISECONDS);
        String sutUrl = "https://www.bestbuy.com/";
        driver.get(sutUrl);
        driver.manage().window().maximize();
        assertThat(driver.getTitle())
                .isEqualTo("Best Buy | Official Online Store | Shop Now & Save");
        assertThat(driver.getCurrentUrl()).isEqualTo(sutUrl);
        assertThat(driver.getPageSource()).containsIgnoringCase("</html>");

    }

    @AfterEach
    void teardown() throws InterruptedException {
        // FIXME: pause for manual browser inspection
        driver.quit();
        Thread.sleep(Duration.ofSeconds(5).toMillis());

    }

    @Test
    void testBestBuyHomepageHeaderElements() {
//        String sutUrl = "https://www.bestbuy.com/";
//        driver.get(sutUrl);
//
//        assertThat(driver.getTitle())
//                .isEqualTo("Best Buy | Official Online Store | Shop Now & Save");
//        assertThat(driver.getCurrentUrl()).isEqualTo(sutUrl);
//        assertThat(driver.getPageSource()).containsIgnoringCase("</html>");
//
//        // By name
//        // Verify Auto-Complete Search field is enabled
//
//        WebElement textByName = driver.findElement(By.name("frmSearch"));
//        assertThat(textByName.isEnabled()).isTrue();

        // By XPath
        // Verify Menu button is displayed
        WebElement menu_button = driver.findElement(By.xpath("//button[@aria-label='Menu']"));
        assertThat(menu_button.isDisplayed()).isTrue();

        // By XPath
        // Verify Store button is displayed
        WebElement store_location_button = driver.findElement(By.xpath("//*[@id=\"lt-container\"]/div/div/span/span/button"));
        assertThat(store_location_button.isDisplayed()).isTrue();


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

        // By XPath
        // Verify Sign In button is displayed using anchor with href

        WebElement sign_in_button = driver.findElement(By.xpath("//a[contains(@href,'/identity/global/signin')]"));
        String href = sign_in_button.getAttribute("href");
        assertThat(account_menu_button.isDisplayed()).isTrue();
        System.out.println("innerText : " + sign_in_button.getAttribute("innerText"));
        System.out.println("outerText : " + sign_in_button.getAttribute("outerText"));

        String tagName = sign_in_button.getTagName();
        System.out.println("TagName : " + tagName);

        String innerHTML = sign_in_button.getAttribute("innerHTML");
        System.out.println("innerHTML : " + innerHTML);

        String text = sign_in_button.getText();
        System.out.println("Sign In text : " + text);

        // By XPath
        // Verify Sign In button is displayed using class and text
        WebElement sign_in_button_1 = driver.findElement(By.xpath("//*[@class='c-button c-button-secondary c-button-sm sign-in-btn' and contains(text(),'Sign In')]"));
        System.out.println("sign_in_button_1 TagName: " + sign_in_button_1.getTagName());
        System.out.println("sign_in_button_1 Text: " + sign_in_button_1.getText());
        assertThat(sign_in_button_1.getText()).isEqualTo("Sign In");

        // Slow Selenium down ... for assert to catchup ... 
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // By XPath
        // Verify Create Account button is displayed using class and text
        WebElement create_account_button = driver.findElement(By.xpath("//*[@class='c-button c-button-outline c-button-sm create-account-btn' and contains(text(),'Create Account')]"));
        System.out.println("create_account_button TagName: " + create_account_button.getTagName());
        System.out.println("create_account_button Text: " + create_account_button.getText());
        assertThat(create_account_button.getText()).isEqualTo("Create Account");


        // By XPath
        // Verify Check Order Status using anchor with href
        WebElement check_order_status = driver.findElement(By.xpath("//a[contains(@href,'/profile/ss/orderlookup')]"));
        assertThat(check_order_status.isDisplayed()).isTrue();
        System.out.println("Check Order Status text : " + check_order_status.getText());

        try {
            Thread.sleep(5000);
            System.out.println("\n\n\n  Test ... Completed  - Remove this when you are done unit-testing your code \n\n\n");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
//
//    @Test
//    void testBestBuyEmailSignUp() {
////        driver.get(
////                "https://www.bestbuy.com/");
//
//        WebElement inputText = driver.findElement(By.name("footer-email-signup"));
//        String textValue = "mySeleliumTest@gmail.com";
//        inputText.sendKeys(textValue);
//        assertThat(inputText.getAttribute("value")).isEqualTo(textValue);
//
//
//        //inputText.clear();
//
//        //assertThat(inputText.getAttribute("value")).isEmpty();
//    }

    @Test
    void testMenuHamburgerButtonFunctionality() {
        try {
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
        } catch (AssertionError e) {
            System.out.println("Test case testBestBuyMenuItemElements  failed: " + e.getMessage());
            throw e; // Re-throw the exception to mark the test as failed
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testDealsMenu() {
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
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        menu_hamburger__button.click();
        WebElement Deals_menu_hamburger__button = null;
        try {
            Deals_menu_hamburger__button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class=\"c-button-unstyled top-four v-fw-medium\"][text()=\"Deals\"]")));
            assertThat(Deals_menu_hamburger__button.isDisplayed()).isTrue();
        } catch (TimeoutException e) {
            System.out.println("Element Deals_menu_hamburger__button not found within 10 seconds");
        } catch (NoSuchElementException e) {
            System.out.println("Element Deals_menu_hamburger__button not found on the page");
        }
        Deals_menu_hamburger__button.click();
        System.out.println("Deals_menu_hamburger__button button is present and able to clik on the button in Best Buy website.");
    }

    @Test
    void testSupportMenu() {
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
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        menu_hamburger__button.click();
        WebElement Support_menu_hamburger__button = null;
        try {
            Support_menu_hamburger__button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='c-button-unstyled top-four v-fw-medium' and @type='button' and @data-id='node-62' and @data-t='header-navigation-button']")));
            assertThat(Support_menu_hamburger__button.isDisplayed()).isTrue();
        } catch (TimeoutException e) {
            System.out.println("Element Support_menu_hamburger__button not found within 10 seconds");
        } catch (NoSuchElementException e) {
            System.out.println("Element Support_menu_hamburger__button not found on the page");
        }
        Support_menu_hamburger__button.click();
        System.out.println("Support_menu_hamburger__button button is present and able to clik on the button in Best Buy website.");
    }

    @Test
    void testBrandsMenu() {
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
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        menu_hamburger__button.click();
        WebElement Brands_menu_hamburger__button = null;
        try {
            Brands_menu_hamburger__button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='c-button-unstyled top-four v-fw-medium' and @type='button' and @data-id='node-82' and @data-t='header-navigation-button']\n")));
            assertThat(Brands_menu_hamburger__button.isDisplayed()).isTrue();
        } catch (TimeoutException e) {
            System.out.println("Element Brands_menu_hamburger__button not found within 10 seconds");
        } catch (NoSuchElementException e) {
            System.out.println("Element Brands_menu_hamburger__button not found on the page");
        }
        Brands_menu_hamburger__button.click();
        System.out.println("Brands_menu_hamburger__button button is present and able to clik on the button in Best Buy website.");
    }

    @Test
    void testAppliancesMenu() throws InterruptedException {
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
        WebElement Appliances_menu_hamburger__button = null;
        try {
            Appliances_menu_hamburger__button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@class='liDropdownList ']/button[@class='c-button-unstyled hamburger-menu-flyout-list-item ' and @data-lid='ubr_app' and @data-t='header-navigation-button']\n")));
            assertThat(Appliances_menu_hamburger__button.isDisplayed()).isTrue();
        } catch (TimeoutException e) {
            System.out.println("Element Appliances_menu_hamburger__button not found within 10 seconds");
        } catch (NoSuchElementException e) {
            System.out.println("Element Appliances_menu_hamburger__button not found on the page");
        }
        Appliances_menu_hamburger__button.click();
        System.out.println("Appliances_menu_hamburger__button button is present and able to clik on the button in Best Buy website.");
    }

    @Test
    void testTvAndTheatreMenu() {
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
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        menu_hamburger__button.click();
        WebElement Tv_menu_hamburger__button = null;
        try {
            Tv_menu_hamburger__button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(@class, 'liDropdownList')]/button[contains(@class, 'c-button-unstyled') and @data-id='node-276' and @data-lid='ubr_tv' and @data-t='header-navigation-button']\n")));
            assertThat(Tv_menu_hamburger__button.isDisplayed()).isTrue();
        } catch (TimeoutException e) {
            System.out.println("Element Tv_menu_hamburger__button not found within 10 seconds");
        } catch (NoSuchElementException e) {
            System.out.println("Element Tv_menu_hamburger__button not found on the page");
        }
        Tv_menu_hamburger__button.click();
        System.out.println("Tv_menu_hamburger__button is present and able to clik on the button in Best Buy website.");
    }

    @Test
    void testComputersAndTablesMenu() {
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
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        menu_hamburger__button.click();
        WebElement Computers_menu_hamburger_button = null;
        try {
            Computers_menu_hamburger_button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@class='liDropdownList ']/button[@class='c-button-unstyled hamburger-menu-flyout-list-item ' and @data-id='node-401' and @data-lid='ubr_cp' and @data-t='header-navigation-button']\n")));
            assertThat(Computers_menu_hamburger_button.isDisplayed()).isTrue();
        } catch (TimeoutException e) {
            System.out.println("Computers_menu_hamburger__button Tv_menu_hamburger__button not found within 10 seconds");
        } catch (NoSuchElementException e) {
            System.out.println("Computers_menu_hamburger__button Tv_menu_hamburger__button not found on the page");
        }
        Computers_menu_hamburger_button.click();
        System.out.println("Computers_menu_hamburger__button is present and able to clik on the button in Best Buy website.");
    }

    @Test
    void testCellPhonesMenu() {
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
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        menu_hamburger__button.click();
        WebElement Cell_menu_hamburger_button = null;
        try {
            Cell_menu_hamburger_button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'hamburger-menu-flyout-list-item') and @data-id='node-475' and @data-t='header-navigation-button']\n")));
            assertThat(Cell_menu_hamburger_button.isDisplayed()).isTrue();
        } catch (TimeoutException e) {
            System.out.println("Cell_menu_hamburger_button Tv_menu_hamburger__button not found within 10 seconds");
        } catch (NoSuchElementException e) {
            System.out.println("Cell_menu_hamburger_button Tv_menu_hamburger__button not found on the page");
        }
        Cell_menu_hamburger_button.click();
        System.out.println("Cell_menu_hamburger_button is present and able to clik on the button in Best Buy website.");
    }

    @Test
    void testAudioMenu() {
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
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        menu_hamburger__button.click();
        WebElement Audio_menu_hamburger_button = null;
        try {
            Audio_menu_hamburger_button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'hamburger-menu-flyout-list-item') and @data-id='node-526' and @data-t='header-navigation-button']\n")));
            assertThat(Audio_menu_hamburger_button.isDisplayed()).isTrue();
        } catch (TimeoutException e) {
            System.out.println("Audio_menu_hamburger_button Tv_menu_hamburger__button not found within 10 seconds");
        } catch (NoSuchElementException e) {
            System.out.println("Audio_menu_hamburger_button Tv_menu_hamburger__button not found on the page");
        }
        Audio_menu_hamburger_button.click();
        System.out.println("Audio_menu_hamburger_button is present and able to clik on the button in Best Buy website.");
    }

    @Test
    void testVideoGamesMenu() {
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
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        menu_hamburger__button.click();
        WebElement Video_Games_menu_hamburger_button = null;
        try {
            Video_Games_menu_hamburger_button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'hamburger-menu-flyout-list-item') and @data-id='node-599' and @data-t='header-navigation-button']\n")));
            assertThat(Video_Games_menu_hamburger_button.isDisplayed()).isTrue();
        } catch (TimeoutException e) {
            System.out.println("Video_Games_menu_hamburger_button Tv_menu_hamburger__button not found within 10 seconds");
        } catch (NoSuchElementException e) {
            System.out.println("Video_Games_menu_hamburger_button Tv_menu_hamburger__button not found on the page");
        }
        Video_Games_menu_hamburger_button.click();
        System.out.println("Video_Games_menu_hamburger_button is present and able to clik on the button in Best Buy website.");
    }

    @Test
    void testCamerasMenu() {
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
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        menu_hamburger__button.click();
        WebElement Cameras_menu_hamburger_button = null;
        try {
            Cameras_menu_hamburger_button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'hamburger-menu-flyout-list-item') and @data-id='node-665' and @data-t='header-navigation-button']\n")));
            assertThat(Cameras_menu_hamburger_button.isDisplayed()).isTrue();
        } catch (TimeoutException e) {
            System.out.println("Cameras_menu_hamburger_button Tv_menu_hamburger__button not found within 10 seconds");
        } catch (NoSuchElementException e) {
            System.out.println("Cameras_menu_hamburger_button Tv_menu_hamburger__button not found on the page");
        }
        Cameras_menu_hamburger_button.click();
        System.out.println("Cameras_menu_hamburger_button is present and able to clik on the button in Best Buy website.");
    }

    @Test
    void testHomeFurnitureMenu() {
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
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        menu_hamburger__button.click();
        WebElement HomeFurniture_menu_hamburger_button = null;
        try {
            HomeFurniture_menu_hamburger_button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'hamburger-menu-flyout-list-item') and @data-id='node-734' and @data-t='header-navigation-button']\n")));
            assertThat(HomeFurniture_menu_hamburger_button.isDisplayed()).isTrue();
        } catch (TimeoutException e) {
            System.out.println("HomeFurniture_menu_hamburger_button Tv_menu_hamburger__button not found within 10 seconds");
        } catch (NoSuchElementException e) {
            System.out.println("HomeFurniture_menu_hamburger_button Tv_menu_hamburger__button not found on the page");
        }
        HomeFurniture_menu_hamburger_button.click();
        System.out.println("HomeFurniture_menu_hamburger_button is present and able to clik on the button in Best Buy website.");
    }

    @Test
    void testSmartHomeFurnitureMenu() {
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
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        menu_hamburger__button.click();
        WebElement SmartHomeFurniture_menu_hamburger_button = null;
        try {
            SmartHomeFurniture_menu_hamburger_button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'hamburger-menu-flyout-list-item') and @data-id='node-781' and @data-t='header-navigation-button']\n")));
            assertThat(SmartHomeFurniture_menu_hamburger_button.isDisplayed()).isTrue();
        } catch (TimeoutException e) {
            System.out.println("SmartHomeFurniture_menu_hamburger_button Tv_menu_hamburger__button not found within 10 seconds");
        } catch (NoSuchElementException e) {
            System.out.println("SmartHomeFurniture_menu_hamburger_button Tv_menu_hamburger__button not found on the page");
        }
        SmartHomeFurniture_menu_hamburger_button.click();
        System.out.println("SmartHomeFurniture_menu_hamburger_button is present and able to clik on the button in Best Buy website.");
    }

    @Test
    void testCarElectronicsMenu() {
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
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        menu_hamburger__button.click();
        WebElement CarElectronics_menu_hamburger_button = null;
        try {
            CarElectronics_menu_hamburger_button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'hamburger-menu-flyout-list-item') and @data-id='node-820' and @data-t='header-navigation-button']\n")));
            assertThat(CarElectronics_menu_hamburger_button.isDisplayed()).isTrue();
        } catch (TimeoutException e) {
            System.out.println("CarElectronics_menu_hamburger_button Tv_menu_hamburger__button not found within 10 seconds");
        } catch (NoSuchElementException e) {
            System.out.println("CarElectronics_menu_hamburger_button Tv_menu_hamburger__button not found on the page");
        }
        CarElectronics_menu_hamburger_button.click();
        System.out.println("CarElectronics_menu_hamburger_button is present and able to clik on the button in Best Buy website.");
    }

    @Test
    void testMoviesMenu() {
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
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        menu_hamburger__button.click();
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
        WebElement Movies_menu_hamburger_button = null;
        try {
            Movies_menu_hamburger_button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'hamburger-menu-flyout-list-item') and @data-id='node-862' and @data-t='header-navigation-button']\n")));
            assertThat(Movies_menu_hamburger_button.isDisplayed()).isTrue();
        } catch (TimeoutException e) {
            System.out.println("Movies_menu_hamburger_button Tv_menu_hamburger__button not found within 10 seconds");
        } catch (NoSuchElementException e) {
            System.out.println("Movies_menu_hamburger_button Tv_menu_hamburger__button not found on the page");
        }
        Movies_menu_hamburger_button.click();
        System.out.println("Movies_menu_hamburger_button is present and able to clik on the button in Best Buy website.");
    }

    @Test
    void testWearableMenu() {
        String sutUrl = "https://www.bestbuy.com/";
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
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        menu_hamburger__button.click();
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
        WebElement Wearable_menu_hamburger_button = null;
        try {
            Wearable_menu_hamburger_button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'hamburger-menu-flyout-list-item') and @data-id='node-915' and @data-t='header-navigation-button']\n")));
            assertThat(Wearable_menu_hamburger_button.isDisplayed()).isTrue();
        } catch (TimeoutException e) {
            System.out.println("Wearable_menu_hamburger_button Tv_menu_hamburger__button not found within 10 seconds");
        } catch (NoSuchElementException e) {
            System.out.println("Wearable_menu_hamburger_button Tv_menu_hamburger__button not found on the page");
        }
        Wearable_menu_hamburger_button.click();
        System.out.println("Wearable_menu_hamburger_button is present and able to clik on the button in Best Buy website.");
    }

    @Test
    void testHealthMenu() {
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
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        menu_hamburger__button.click();
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

        WebElement Health_menu_hamburger_button = null;
        try {
            Health_menu_hamburger_button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'hamburger-menu-flyout-list-item') and @data-id='node-1008' and @data-t='header-navigation-button']\n")));
            assertThat(Health_menu_hamburger_button.isDisplayed()).isTrue();
        } catch (TimeoutException e) {
            System.out.println("Health_menu_hamburger_button Tv_menu_hamburger__button not found within 10 seconds");
        } catch (NoSuchElementException e) {
            System.out.println("Health_menu_hamburger_button Tv_menu_hamburger__button not found on the page");
        }
        Health_menu_hamburger_button.click();
        System.out.println("Health_menu_hamburger_button is present and able to clik on the button in Best Buy website.");
    }

    @Test
    void testOutdoorMenu() {
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
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        menu_hamburger__button.click();
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

        WebElement Outdoor_menu_hamburger_button = null;
        try {
            Outdoor_menu_hamburger_button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'hamburger-menu-flyout-list-item') and @data-id='node-1060' and @data-t='header-navigation-button']\n")));
            assertThat(Outdoor_menu_hamburger_button.isDisplayed()).isTrue();
        } catch (TimeoutException e) {
            System.out.println("Outdoor_menu_hamburger_button Tv_menu_hamburger__button not found within 10 seconds");
        } catch (NoSuchElementException e) {
            System.out.println("Outdoor_menu_hamburger_button Tv_menu_hamburger__button not found on the page");
        }
        Outdoor_menu_hamburger_button.click();
        System.out.println("Outdoor_menu_hamburger_button is present and able to clik on the button in Best Buy website.");
    }

    @Test
    void testToysMenu() {
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
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        menu_hamburger__button.click();
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
        WebElement Toys_menu_hamburger_button = null;
        try {
            Toys_menu_hamburger_button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'hamburger-menu-flyout-list-item') and @data-id='node-1126' and @data-t='header-navigation-button']\n")));
            assertThat(Toys_menu_hamburger_button.isDisplayed()).isTrue();
        } catch (TimeoutException e) {
            System.out.println("Toys_menu_hamburger_button Tv_menu_hamburger__button not found within 10 seconds");
        } catch (NoSuchElementException e) {
            System.out.println("Toys_menu_hamburger_button Tv_menu_hamburger__button not found on the page");
        }
        Toys_menu_hamburger_button.click();
        System.out.println("Toys_menu_hamburger_button is present and able to clik on the button in Best Buy website.");
    }

    @Test
    void testElectricTransportationMenu() {
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
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        menu_hamburger__button.click();
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
        WebElement ElectricTransportation_menu_hamburger_button = null;
        try {
            ElectricTransportation_menu_hamburger_button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'hamburger-menu-flyout-list-item') and @data-id='node-1163' and @data-t='header-navigation-button']\n")));
            assertThat(ElectricTransportation_menu_hamburger_button.isDisplayed()).isTrue();
        } catch (TimeoutException e) {
            System.out.println("ElectricTransportation_menu_hamburger_button Tv_menu_hamburger__button not found within 10 seconds");
        } catch (NoSuchElementException e) {
            System.out.println("ElectricTransportation_menu_hamburger_button Tv_menu_hamburger__button not found on the page");
        }
        ElectricTransportation_menu_hamburger_button.click();
        System.out.println("ElectricTransportation_menu_hamburger_button is present and able to clik on the button in Best Buy website.");
    }

    @Test
    void testNewsFeedMenu() {
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
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        menu_hamburger__button.click();
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
        WebElement NewsFeed_menu_hamburger_button = null;
        try {
            NewsFeed_menu_hamburger_button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'hamburger-menu-flyout-list-item') and @data-id='node-1194' and @data-t='header-navigation-button']\n")));
            assertThat(NewsFeed_menu_hamburger_button.isDisplayed()).isTrue();
        } catch (TimeoutException e) {
            System.out.println("NewsFeed_menu_hamburger_button Tv_menu_hamburger__button not found within 10 seconds");
        } catch (NoSuchElementException e) {
            System.out.println("NewsFeed_menu_hamburger_button Tv_menu_hamburger__button not found on the page");
        }
        NewsFeed_menu_hamburger_button.click();
        System.out.println("NewsFeed_menu_hamburger_button is present and able to clik on the button in Best Buy website.");
    }


}


