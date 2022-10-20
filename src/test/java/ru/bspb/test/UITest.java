package ru.bspb.test;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.DefaultAssertionErrorCollector;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.data.Index;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidCookieDomainException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UITest {
    static final String MAIN_PAGE_URL = "https://www.bspb.ru/retail/ibank";
    static final String LOGIN_PAGE_URL = "https://i.bspb.ru/auth?response_type=code&client_id=1&redirect_uri=https%3A%2F%2Fi.bspb.ru%2Flogin%2Fsuccess&prefetch_uri=https%3A%2F%2Fi.bspb.ru%2Flogin%2Fprefetch&force_new_session=true";
    public static WebDriver driver;
    public static MainPage mainPage;
    public static LoginPage loginPage;
    public static SoftAssertions softAssertions;
    @BeforeClass
    public static void setup(){
        System.setProperty("webdriver.chrome.driver", ConfProp.getProperty("chromedriver"));
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        softAssertions = new SoftAssertions();
    }
    @Test
    public void goToLoginPageTestByBtn(){
        driver.get(MAIN_PAGE_URL);
        mainPage.clickGoToLoginPageBtn();
        Assertions.assertThat(driver.getCurrentUrl())
                .isEqualTo(LOGIN_PAGE_URL).
                as("Проверка на переход но страницу логина");
    }
    @Test
    public void goToLoginPageTestByLink(){
        driver.get(MAIN_PAGE_URL);
        mainPage.clickGoToLoginPageLink();
        List<String> tabsURL = driver.getWindowHandles().stream().map((tab) -> {
            driver.switchTo().window(tab);
            return driver.getCurrentUrl();
        }).toList();
        Assertions.assertThat(tabsURL).contains(LOGIN_PAGE_URL).as("Переход на новую страницу по ссылке");
    }
    @Test
    public void correctInputTest(){
        driver.get(LOGIN_PAGE_URL);
        loginPage.setUsernameInput(ConfProp.getProperty("login"));
        softAssertions
                .assertThat(loginPage.getUsername())
                .isEqualTo(ConfProp.getProperty("login"))
                .as("Проверка корректности ввода данных в поле логин");
        loginPage.setPasswordInput(ConfProp.getProperty("password"));
        softAssertions
                .assertThat(loginPage.getPassword())
                .isEqualTo(ConfProp.getProperty("password"))
                .as("Проверка корректности ввода данных в поле пароль");
        softAssertions.assertAll();
    }
    @Test
    public void passwordVisibleTest(){
        driver.get(LOGIN_PAGE_URL);
        loginPage.clickPasswordVisibleDiv();
        softAssertions
                .assertThat(loginPage.getPasswordInputType())
                .isEqualTo("text")
                .as("Проверка видимости пароля с невидимого на видимый");
        loginPage.clickPasswordVisibleDiv();
        softAssertions
                .assertThat(loginPage.getPasswordInputType())
                .isEqualTo("password")
                .as("Проверка видимости пароля с видимого на невидимый");
        softAssertions.assertAll();
    }
    @Test
    public void loginTest(){
        driver.get(LOGIN_PAGE_URL);
        loginPage.setUsernameInput(ConfProp.getProperty("login"));
        loginPage.setPasswordInput(ConfProp.getProperty("password"));
        loginPage.clickLoginBtn();
        Assertions
                .assertThat(driver.findElement(By.cssSelector("#alerts-container > div.alert.alert-error")).isDisplayed())
                .isTrue();
    }
    @Test
    public void restoreAccessPopUpTest(){
        driver.get(LOGIN_PAGE_URL);
        loginPage.clickRestoreAccessBtn();
        softAssertions
                .assertThat(loginPage.getRestoreAccessDisplayAttr())
                .contains("block")
                .as("Проверка на появление ПопАпа при нажатие Восстановить доступ ");
        loginPage.clickCloseRestoreAccessBtn();
        softAssertions
                .assertThat(loginPage.getRestoreAccessDisplayAttr())
                .contains("none")
                .as("Проверка на закрытие ПопАпа при нажатие соответстующей кнопки");
        softAssertions.assertAll();
    }
    @AfterClass
    public static void tearDown() {
        driver.quit(); }

}
