
import org.junit.Before;
import org.junit.Test;
import org.junit.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class FormTest {

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

        TimeUnit.SECONDS.sleep(5);
        WebElement rodoButton = driver.findElement(By.id("ikeaTermsConsentModalClose"));
        if (rodoButton.isDisplayed()) rodoButton.click();

        WebElement clickLogin = driver.findElement(By.xpath("/html/body/header/div[3]/div/nav[2]/ul/li[1]/a"));
        clickLogin.click();
        TimeUnit.SECONDS.sleep(2);

        //Sprawdzenie czy znajdujemy się na stronie logowania
        String txt = driver.findElement(By.xpath("/html/body/div/div/div[1]/div[2]/h1/span")).getText();
        String expectedTxt = "Zaloguj";
        Assertions.assertEquals(expectedTxt, txt, "Login page failed. " +
                        "Expected: " + expectedTxt + " Actual: " + txt);


        WebElement clickRegister = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[4]/a"));
        clickRegister.click();
        TimeUnit.SECONDS.sleep(2);

        //Sprawdzenie czy znajdujemy się na stronie rejestracji
        txt = driver.findElement(By.id("profile-page-headline")).getText();
        expectedTxt = "Utwórz profil IKEA Family";
        Assertions.assertEquals(expectedTxt, txt, "Register page failed. " +
                "Expected: " + expectedTxt + " Actual: " + txt);


        WebElement name = driver.findElement(By.id("family-signup-form-firstName"));
        WebElement surname = driver.findElement(By.id("family-signup-form-lastName"));
        WebElement birth = driver.findElement(By.id("family-signup-form-birthDate"));
        WebElement gender = driver.findElement(By.id("family-signup-form-genderCode-1"));
        WebElement street = driver.findElement(By.id("family-signup-form-address1"));
        WebElement zipCode = driver.findElement(By.id("family-signup-form-zipCode"));
        WebElement city = driver.findElement(By.id("family-signup-form-cityName"));
        WebElement shop = driver.findElement(By.id("family-signup-form-preferredStore"));
        WebElement email = driver.findElement(By.id("family-signup-form-username"));
        WebElement password = driver.findElement(By.id("family-signup-form-password"));
        WebElement privacyPolicy = driver.findElement(By.id("family-signup-form-acceptPrivacyPolicy"));
        //WebElement submit = driver.findElement(By.id("family-signup-form-submit"));

        //Sprawdzanie czy elementy wystepuja na stronie
        Assert.assertTrue(name.isDisplayed());
        Assert.assertTrue(surname.isDisplayed());
        Assert.assertTrue(birth.isDisplayed());
        Assert.assertTrue(street.isDisplayed());
        Assert.assertTrue(zipCode.isDisplayed());
        Assert.assertTrue(city.isDisplayed());
        Assert.assertTrue(shop.isDisplayed());
        Assert.assertTrue(email.isDisplayed());
        Assert.assertTrue(password.isDisplayed());

        name.sendKeys("Anna");
        surname.sendKeys("Nowak");
        birth.sendKeys("01.01.1996");
        gender.click();
        street.sendKeys("Pocztowa 1");
        zipCode.sendKeys("20000");
        city.sendKeys("Lublin");
        Select objSelect = new Select(shop);
        objSelect.selectByVisibleText("Lublin");
        email.sendKeys("an@example.com");
        password.sendKeys("Password1");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("var evt = document.createEvent('MouseEvents');" + "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);" + "arguments[0].dispatchEvent(evt);", privacyPolicy);

        /**
         * Click create account
         * */
//        Actions actions = new Actions(driver);
//        actions.moveToElement(submit).click().build().perform();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}