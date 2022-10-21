package ru.bspb.cucumber.test.glue;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.bspb.cucumber.test.ConfProp;
import ru.bspb.cucumber.test.Finals;
import ru.bspb.cucumber.test.LoginPage;
import ru.bspb.cucumber.test.MainPage;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyStepdefs {
    public static WebDriver driver;
    public static MainPage mainPage;
    public static LoginPage loginPage;
    public static SoftAssertions softAssertions;
    public static List<String> linksSet;
    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", ConfProp.getProperty("chromedriver"));
        linksSet = new ArrayList<>();
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        softAssertions = new SoftAssertions();
    }
    public static List<String>  getPopoverHeaderText(){
        return driver.findElements(By.cssSelector(".css-6pxx36"))
                .stream()
                .map(WebElement::getText).toList();
    }
    public static List<String> getPopoverLinksText(){
        return driver.findElements(By.cssSelector(".css-16fpbj"))
                .stream()
                .map(WebElement::getText).toList();
    }
    public static List<String> getPopoverLinks(){
        return driver.findElements(By.cssSelector(".css-16fpbj"))
                .stream()
                .map((link) -> link.getAttribute("href")).toList();
    }

    @Дано("пользователь находится на домашней странице банка")
    public void goToMainPage() {
        driver.get(Finals.MAIN_PAGE_URL);
    }

    @Когда("пользователь нажимает на кнопку перехода в интернет банк")
    public void clickGoToLoginPageBtn() {
        mainPage.clickGoToLoginPageBtn();
    }

    @Тогда("пользователь находится на странице входа в личный кабинет")
    public void goToLoginPageTest() {
        List<String> tabsURL = driver.getWindowHandles().stream().map((tab) -> {
            driver.switchTo().window(tab);
            return driver.getCurrentUrl();
        }).toList();
        Assertions.assertThat(tabsURL).contains(Finals.LOGIN_PAGE_URL).as("Переход на новую страницу по ссылке");
    }

    @Когда("пользователь нажимает на ссылку перехода в интернет банк")
    public void goToLoginPageByLink() {
        mainPage.clickGoToLoginPageLink();
    }
    @After
    public void tearDown(){
        driver.quit();
    }

    @Когда("пользователь наводит курсор на секцию {string}")
    public void hoverPopoverSection(String section_name) {
        switch (section_name){
            case "Cards":{
                mainPage.hoverCardPopOverTrigger();
                break;
            }
            case "Credit":{
                mainPage.hoverCreditPopOverTrigger();
                break;
            }
            case "Ipotec":{
                mainPage.hoverIpotecPopOverTrigger();
                break;
            }
            case "Deposit":{
                mainPage.hoverDepositPopOverTrigger();
                break;
            }
            case "Transaction":{
                mainPage.hoverTransactionPopOverTrigger();
                break;
            }
            case "Other":{
                mainPage.hoverOtherPopOverTrigger();
                break;
            }
        }
    }
    @И("нажимает на каждую ссылку")
    public void goToNewPagesByPopoverLinksTest() {
        Set<String> popoverLinks = new HashSet<>(getPopoverLinks());
        popoverLinks.forEach((link)->{
            driver.get(link);
            linksSet.add(driver.getCurrentUrl());
        });
    }

    @И("проверяет корректность отображаемых данных")
    public void checkDataCorrect() {
    }

    @Тогда("пользователь находится на соотвествующей странице")
    public void checkPopoverLinks() {

    }
}
