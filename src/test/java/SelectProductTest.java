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
        WebElement rodoButton = driver.findElement(By.id("onetrust-accept-btn-handler"));
        if (rodoButton.isDisplayed()) rodoButton.click();

        //Select sofa tab
        WebElement products = driver.findElement(By.linkText("Produkty"));
        products.click();

        //Check that the sofa tab is open
        String txt = driver.findElement(By.className("hnf-menu__heading")).getText();
        String expectedTxt = "Produkty";
        Assertions.assertEquals(expectedTxt, txt, "Products page failed. " +
                "Expected: " + expectedTxt + " Actual: " + txt);

        //Select sofas and armchairs tab
        WebElement sofa = driver.findElement(By.linkText("Sofy i fotele"));
        if (sofa.getText().equals("Sofy i fotele")) sofa.click();
        TimeUnit.SECONDS.sleep(2);

        //Check that the sofas and armchairs tab is open
        boolean isDisplayed =driver.findElement(By.cssSelector("body > aside > div.hnf-menu__container > nav.hnf-menu__nav2.hnf-menu--active > ul > li:nth-child(4) > nav > ul")).isDisplayed();
        Assert.assertTrue(isDisplayed);

        //Select leather sofas tab
        WebElement leatherSofa = driver.findElement(By.xpath("/html/body/aside/div[2]/nav[2]/ul/li[4]/nav/ul/li[4]/a"));
        leatherSofa.click();
        TimeUnit.SECONDS.sleep(2);

        //Check that leather sofas tab is open
        txt = driver.findElement(By.className("plp-page-title__title")).getText();
        expectedTxt = "Sofy skórzane/ze sztucznej skóry";
        Assertions.assertEquals(expectedTxt, txt, "Leather sofa page failed. " +
                "Expected: " + expectedTxt + " Actual: " + txt);

        //Select two seat leather sofas
        WebElement selectSofa = driver.findElement(By.linkText("Sofy ze sztucznej skóry, 2-osobowe"));
        selectSofa.click();
        TimeUnit.SECONDS.sleep(2);

        //Check availability of GRÖNLID sofa
        txt = driver.findElement(By.className("range-revamp-header-section__title--small")).getText();
        expectedTxt = "GRÖNLID";
        Assertions.assertEquals(expectedTxt, txt, "Leather sofa page failed. " +
                "Expected: " + expectedTxt + " Actual: " + txt);

        //Add to card sofa
        WebElement addToCard = driver.findElement(By.cssSelector("body > div.plp-page-container > div > div > div.plp-main-container.plp-main-container > div > div > div.plp-product-list > div > div > div.plp-product-list__fragment > div > div > button"));
        addToCard.click();

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
