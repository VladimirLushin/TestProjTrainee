package ru.bspb.test;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class UITest {
    static final String MAIN_PAGE_URL = "https://www.bspb.ru/retail/ibank";
    static String loginPageURL = "https://i.bspb.ru/auth?response_type=code&client_id=1&redirect_uri=https%3A%2F%2Fi.bspb.ru%2Flogin%2Fsuccess&prefetch_uri=https%3A%2F%2Fi.bspb.ru%2Flogin%2Fprefetch&force_new_session=true";
    public static WebDriver driver;
    public static MainPage mainPage;
    public static LoginPage loginPage;
    @BeforeClass
    public static void setup(){
        System.setProperty("webdriver.chrome.driver", ConfProp.getProperty("chromedriver"));
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @Test
    public void goToLoginPageTestByBtn(){
        driver.get(MAIN_PAGE_URL);
        mainPage.clickGoToLoginPageBtn();
        Assert.assertEquals(loginPageURL, driver.getCurrentUrl());
    }
    /*@Test
    public void goToLoginPageTestByLink(){
        driver.get(mainPageURL);
        mainPage.clickGoToLoginPageLink();
        Assert.assertEquals(loginPageURL, driver.getCurrentUrl());
    }*/
    @Test
    public void correctInputTest(){
        driver.get(loginPageURL);
        loginPage.setUsernameInput(ConfProp.getProperty("login"));
        Assert.assertEquals(ConfProp.getProperty("login"), loginPage.getUsername());
        loginPage.setPasswordInput(ConfProp.getProperty("password"));
        Assert.assertEquals(ConfProp.getProperty("password"),loginPage.getPassword());
    }
    @Test
    public void passwordVisibleTest(){
        driver.get(loginPageURL);
        loginPage.clickPasswordVisibleDiv();
        Assert.assertEquals("text", loginPage.getPasswordInputType());
        loginPage.clickPasswordVisibleDiv();
        Assert.assertEquals("pasword",loginPage.getPasswordInputType());
    }
    @Test
    public void loginTest(){
        driver.get(loginPageURL);
        loginPage.setUsernameInput(ConfProp.getProperty("login"));
        loginPage.setPasswordInput(ConfProp.getProperty("password"));
        loginPage.clickLoginBtn();
        Assert.assertTrue(driver.findElement(By.cssSelector("#alerts-container > div.alert.alert-error")).isDisplayed());
    }
    @Test
    public void restoreAccessPopUpTest(){
        driver.get(loginPageURL);
        loginPage.clickRestoreAccessBtn();
        Assert.assertTrue(loginPage.getRestoreAccessDisplayAttr().contains("block"));
    }
    @AfterClass
    public static void tearDown() {
        driver.quit(); }

}
