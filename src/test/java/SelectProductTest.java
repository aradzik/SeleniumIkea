import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SelectProductTest {

    private static ChromeDriver driver;

    @Before
    public void beforeTest() {
        driver = new ChromeDriver();
    }

    @Test
    public void testSearch() throws InterruptedException {

        String url = "https://www.ikea.com/pl/pl/";
        driver.get(url);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();

        TimeUnit.SECONDS.sleep(2);
        WebElement rodoButton = driver.findElement(By.id("ikeaTermsConsentModalClose"));
        if (rodoButton.isDisplayed()) rodoButton.click();

        WebElement products = driver.findElement(By.cssSelector("button.main-bygga-menu__button.hnf-trailing-icon"));
        products.click();

        //Sprawdzenie czy otworzyła się zakładka z produktami
        String txt = driver.findElement(By.cssSelector("span.main-bygga-menu__dropdown__heading")).getText();
        String expectedTxt = "Produkty";
        Assertions.assertEquals(expectedTxt, txt, "Products page failed. " +
                "Expected: " + expectedTxt + " Actual: " + txt);

        WebElement sofa = driver.findElement(By.cssSelector("button.main-bygga-menu__level1__item-button"));
        if (sofa.getText().equals("Sofy i fotele")) sofa.click();

        //Sprawdzenie czy otworzyła się zakładka z podproduktami
        boolean isDisplayed = driver.findElement(By.cssSelector("ul.main-bygga-menu__sub.main-bygga-menu__level2")).isDisplayed();
        Assert.assertTrue(isDisplayed);

        WebElement leatherSofa = driver.findElement(By.xpath("/html/body/header/div[3]/div/nav[1]/div/ul[1]/li[1]/div/div[1]/ul/li[1]/ul/li[4]/a"));
        leatherSofa.click();
        TimeUnit.SECONDS.sleep(2);

        //Sprawdzenie czy otworzyła się podstrona z sofami skórzanymi
        txt = driver.findElement(By.cssSelector("h1.range-page-title__title")).getText();
        expectedTxt = "Sofy skórzane/ze sztucznej skóry";
        Assertions.assertEquals(expectedTxt, txt, "Leather sofa page failed. " +
                "Expected: " + expectedTxt + " Actual: " + txt);

        WebElement selectSofa = driver.findElement(By.xpath("/html/body/main/div[4]/div[2]/div/div[2]/div[1]/div[1]/div[2]/div/div[2]/div/div/a/div/div/div"));
        selectSofa.click();
        TimeUnit.SECONDS.sleep(2);

        //Sprawdzenie czy otworzyła się podstrona z konkretną sofą
        txt = driver.findElement(By.cssSelector("span.product-pip__name")).getText();
        expectedTxt = "GRÖNLID";
        Assertions.assertEquals(expectedTxt, txt, "Leather sofa page failed. " +
                "Expected: " + expectedTxt + " Actual: " + txt);
        WebElement addToCard = driver.findElement(By.cssSelector("button.range-btn.range-btn--transactional.range-leading-icon.fill.js-purchase-add-to-cart"));
        addToCard.click();

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
