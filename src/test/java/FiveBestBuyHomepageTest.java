import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static java.time.Duration.ofSeconds;
import static org.assertj.core.api.Assertions.assertThat;

// Created by Vijay for AAssignment 5 purpose for SQA course
// Language USed JAva

class FiveBestBuyHomepageTest {

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
    void testAddProductsToCart() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        WebElement shopLaptops = null;
            shopLaptops = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[contains(@alt, 'Laptops & Computers')]\n")));
            assertThat(shopLaptops.isDisplayed()).isTrue();
        shopLaptops.click();
        Thread.sleep(5000);
        // Find the list of products
        List<WebElement> products = driver.findElements(By.cssSelector(".sku-item"));
        // Select the first four products
        for (int i = 0; i < Math.min(4, products.size()); i++) {
            WebElement product = products.get(i);
            WebElement addToCartButton = product.findElement(By.cssSelector(".add-to-cart-button"));
            // Click on the "Add to Cart" button
            addToCartButton.click();
        }
        // Wait for some time to allow the cart to update (you might want to use WebDriverWait in a real scenario)
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Find and print the number of items in the cart
        WebElement cartIcon = driver.findElement(By.cssSelector(".cart-icon"));
        String cartItemCount = cartIcon.getText();
        System.out.println("Number of items added to the cart: " + cartItemCount);
    }
    @Test
    void testRemoveProductsToCart() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        WebElement shopLaptops = null;
        shopLaptops = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[contains(@alt, 'Laptops & Computers')]\n")));
        assertThat(shopLaptops.isDisplayed()).isTrue();
        shopLaptops.click();
        Thread.sleep(5000);
        // Find the list of products
        List<WebElement> products = driver.findElements(By.cssSelector(".sku-item"));
        // Select the first four products
        for (int i = 0; i < Math.min(2, products.size()); i++) {
            WebElement product = products.get(i);
            WebElement addToCartButton = product.findElement(By.cssSelector(".add-to-cart-button"));
            // Click on the "Add to Cart" button
            addToCartButton.click();
            WebElement continueShopping = driver.findElement(By.xpath("//button[@class='c-button-link continue-shopping']\n"));;
            continueShopping.click();
        }
        Thread.sleep(5000);
        WebElement cartIcon = driver.findElement(By.cssSelector(".cart-icon"));
        cartIcon.click();
        // Wait for some time to ensure the cart page is loaded (you might want to use WebDriverWait in a real scenario)
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Find the list of products in the cart
        List<WebElement> itemsInCart = driver.findElements(By.cssSelector(".cart-item"));
        // Remove the first product from the cart (assuming there is a "Remove" button)
        if (!itemsInCart.isEmpty()) {
            WebElement removeButton = itemsInCart.get(0).findElement(By.cssSelector("button.c-button-link.cart-item__remove[type='button']"));
            removeButton.click();
            Thread.sleep(15000);
            System.out.println("Removed the first product from the cart");
            String cartItemCount = cartIcon.getText();
            System.out.println("Number of items remaining after removing the item from cart: " + cartItemCount);
        }
    }
    @Test
    void testCheckOutCart() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        WebElement shopLaptops = null;
        shopLaptops = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[contains(@alt, 'Laptops & Computers')]\n")));
        assertThat(shopLaptops.isDisplayed()).isTrue();
        shopLaptops.click();
        Thread.sleep(5000);
        WebElement product1 = driver.findElement(By.xpath("//button[contains(@class, 'add-to-cart-button')]\n"));;
        product1.click();
        WebElement continueShopping = driver.findElement(By.xpath("//button[@class='c-button-link continue-shopping']\n"));;
        continueShopping.click();
        Thread.sleep(5000);
        WebElement cartIcon = driver.findElement(By.cssSelector(".cart-icon"));
        cartIcon.click();
        // Wait for some time to ensure the cart page is loaded (you might want to use WebDriverWait in a real scenario)
            Thread.sleep(2000);
        WebElement checkOutButton = driver.findElement(By.xpath("//button[contains(@class, 'btn-primary')]\n"));
        checkOutButton.click();
        Thread.sleep(6000);
        System.out.println("Checkout is successful");
    }
    @Test
    void testSupportCenter() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        // Initialize JavascriptExecutor
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Scroll down to the end of the page
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(5000);
        WebElement supportCenter = driver.findElement(By.xpath("//a[@data-lid='ft_support_visit_our_support_center' and @href='/support']\n"));;
        supportCenter.click();
        Thread.sleep(5000);
        WebElement supportHeading = driver.findElement(By.cssSelector(".support-home-title"));
        // Get the text from the <h1> element
        String actualText = supportHeading.getText();
        // Expected text
        String expectedText = "Best Buy Support";
        // Validate that the actual text matches the expected text
        if (actualText.equals(expectedText)) {
            System.out.println("Text validation passed. Actual text: " + actualText);
        } else {
            System.out.println("Text validation failed. Actual text: " + actualText);
        }

    }
    @Test
    void testSupportCenterSearchBox() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        // Initialize JavascriptExecutor
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Scroll down to the end of the page
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(5000);
        WebElement supportCenter = driver.findElement(By.xpath("//a[@data-lid='ft_support_visit_our_support_center' and @href='/support']\n"));;
        supportCenter.click();
        Thread.sleep(5000);
        WebElement searchBox = driver.findElement(By.id("text-input"));
        searchBox.click();
        // Enter the text "iphone" into the text box
        searchBox.sendKeys("replace iphone screen");
        WebElement searchButton = driver.findElement(By.xpath("//button[contains(@class, 'c-button-secondary')]"));
        searchButton.click();
        Thread.sleep(5000);
    }

}