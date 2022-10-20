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
import java.util.*;
import java.util.stream.Collectors;

public class UITest {
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        softAssertions = new SoftAssertions();
    }
    @Test
    public void goToLoginPageTestByBtn(){
        driver.get(Finals.MAIN_PAGE_URL);
        mainPage.clickGoToLoginPageBtn();
        Assertions.assertThat(driver.getCurrentUrl())
                .isEqualTo(Finals.LOGIN_PAGE_URL).
                as("Проверка на переход но страницу логина");
    }
    @Test
    public void goToLoginPageTestByLink(){
        driver.get(Finals.MAIN_PAGE_URL);
        mainPage.clickGoToLoginPageLink();
        List<String> tabsURL = driver.getWindowHandles().stream().map((tab) -> {
            driver.switchTo().window(tab);
            return driver.getCurrentUrl();
        }).toList();
        Assertions.assertThat(tabsURL).contains(Finals.LOGIN_PAGE_URL).as("Переход на новую страницу по ссылке");
    }
    @Test
    public void correctInputTest(){
        driver.get(Finals.LOGIN_PAGE_URL);
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
        driver.get(Finals.LOGIN_PAGE_URL);
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
        driver.get(Finals.LOGIN_PAGE_URL);
        loginPage.setUsernameInput(ConfProp.getProperty("login"));
        loginPage.setPasswordInput(ConfProp.getProperty("password"));
        loginPage.clickLoginBtn();
        Assertions
                .assertThat(driver.findElement(By.cssSelector("#alerts-container > div.alert.alert-error")).isDisplayed())
                .isTrue();
    }
    @Test
    public void restoreAccessPopUpTest(){
        driver.get(Finals.LOGIN_PAGE_URL);
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
    @Test
    public void popoverСardTest(){
        driver.get(Finals.MAIN_PAGE_URL);
        mainPage.hoverCardPopOverTrigger();
        List<String> popoverHeaderText = getPopoverHeaderText();
        List<String> popoverLinksText = getPopoverLinksText();
        List<String> popoverLinks = getPopoverLinks();
        softAssertions.assertThat(popoverHeaderText)
                .contains(Finals.CARDS_POPOVER_HEADER_TEXT)
                .as("Проверка на соотвествие заголовка в popover'e в секции карты");
        softAssertions.assertThat(popoverLinksText)
                .contains(Finals.CARDS_POPOVER_LINKS_TEXT)
                .as("Проверка на соотвествие текса ссылок в popover'e в секции карты");
        softAssertions.assertThat(popoverLinks)
                .contains(Finals.CARDS_POPOVER_LINKS)
                .as("Проверка на соотвествие ссылок в popover'e в секции карты");
        softAssertions.assertAll();
    }
    @Test
    public void popoverCardLinksTest(){
        driver.get(Finals.MAIN_PAGE_URL);
        mainPage.hoverCardPopOverTrigger();
        Set<String> popoverLinks = new HashSet<>(getPopoverLinks());
        popoverLinks.forEach((link)->{
            driver.get(link);
            Assertions.assertThat(Arrays.stream(Finals.CARDS_POPOVER_LINKS).toList().contains(driver.getCurrentUrl()))
                    .as("Проверка работоспособности ссылок на другие страницы в секции карты");
        });
    }
    @Test
    public void popoverCreditTest(){
        driver.get(Finals.MAIN_PAGE_URL);
        mainPage.hoverCreditPopOverTrigger();
        List<String> popoverHeaderText = getPopoverHeaderText();
        List<String> popoverLinksText = getPopoverLinksText();
        List<String> popoverLinks = getPopoverLinks();
        softAssertions.assertThat(popoverHeaderText)
                .contains(Finals.CREDIT_POPOVER_HEADER_TEXT)
                .as("Проверка на соотвествие заголовка в popover'e в секции кредиты");
        softAssertions.assertThat(popoverLinksText)
                .contains(Finals.CREDIT_POPOVER_LINKS_TEXT)
                .as("Проверка на соотвествие текса ссылок в popover'e в секции кредиты");
        softAssertions.assertThat(popoverLinks)
                .contains(Finals.CREDIT_POPOVER_LINKS)
                .as("Проверка на соотвествие ссылок в popover'e в секции кредиты");
        softAssertions.assertAll();
    }
    @Test
    public void popoverCreditLinksTest(){
        driver.get(Finals.MAIN_PAGE_URL);
        mainPage.hoverCreditPopOverTrigger();
        Set<String> popoverLinks = new HashSet<>(getPopoverLinks());
        popoverLinks.forEach((link)->{
            driver.get(link);
            Assertions.assertThat(Arrays.stream(Finals.CREDIT_POPOVER_LINKS).toList().contains(driver.getCurrentUrl()))
                    .as("Проверка работоспособности ссылок на другие страницы в секции кредиты");
        });
    }
    @Test
    public void popoverIpotecTest(){
        driver.get(Finals.MAIN_PAGE_URL);
        mainPage.hoverIpotecPopOverTrigger();
        List<String> popoverHeaderText = getPopoverHeaderText();
        List<String> popoverLinksText = getPopoverLinksText();
        List<String> popoverLinks = getPopoverLinks();
        softAssertions.assertThat(popoverHeaderText)
                .contains(Finals.IPOTEC_POPOVER_HEADER_TEXT)
                .as("Проверка на соотвествие заголовка в popover'e в секции ипотека");
        softAssertions.assertThat(popoverLinksText)
                .contains(Finals.IPOTEC_POPOVER_LINKS_TEXT)
                .as("Проверка на соотвествие текса ссылок в popover'e в секции ипотека");
        softAssertions.assertThat(popoverLinks)
                .contains(Finals.IPOTEC_POPOVER_LINKS)
                .as("Проверка на соотвествие ссылок в popover'e в секции ипотека");
        softAssertions.assertAll();
    }
    @Test
    public void popoverIpotecLinksTest(){
        driver.get(Finals.MAIN_PAGE_URL);
        mainPage.hoverIpotecPopOverTrigger();
        Set<String> popoverLinks = new HashSet<>(getPopoverLinks());
        popoverLinks.forEach((link)->{
            driver.get(link);
            Assertions.assertThat(Arrays.stream(Finals.IPOTEC_POPOVER_LINKS).toList().contains(driver.getCurrentUrl()))
                    .as("Проверка работоспособности ссылок на другие страницы в секции ипотека");
        });
    }
    @Test
    public void popoverDepositTest(){
        driver.get(Finals.MAIN_PAGE_URL);
        mainPage.hoverDepositPopOverTrigger();
        List<String> popoverHeaderText = getPopoverHeaderText();
        List<String> popoverLinksText = getPopoverLinksText();
        List<String> popoverLinks = getPopoverLinks();
        softAssertions.assertThat(popoverHeaderText)
                .contains(Finals.DEPOSIT_POPOVER_HEADER_TEXT)
                .as("Проверка на соотвествие заголовка в popover'e в секции вклады");
        softAssertions.assertThat(popoverLinksText)
                .contains(Finals.DEPOSIT_POPOVER_LINKS_TEXT)
                .as("Проверка на соотвествие текса ссылок в popover'e в секции вклады");
        softAssertions.assertThat(popoverLinks)
                .contains(Finals.DEPOSIT_POPOVER_LINKS)
                .as("Проверка на соотвествие ссылок в popover'e в секции вклады");
        softAssertions.assertAll();
    }
    @Test
    public void popoverDepositLinksTest(){
        driver.get(Finals.MAIN_PAGE_URL);
        mainPage.hoverDepositPopOverTrigger();
        Set<String> popoverLinks = new HashSet<>(getPopoverLinks());
        popoverLinks.forEach((link)->{
            driver.get(link);
            Assertions.assertThat(Arrays.stream(Finals.DEPOSIT_POPOVER_LINKS).toList().contains(driver.getCurrentUrl()))
                    .as("Проверка работоспособности ссылок на другие страницы в секции вклады");
        });
    }
    @Test
    public void popoverTransactionTest(){
        driver.get(Finals.MAIN_PAGE_URL);
        mainPage.hoverTransactionPopOverTrigger();
        List<String> popoverHeaderText = getPopoverHeaderText();
        List<String> popoverLinksText = getPopoverLinksText();
        List<String> popoverLinks = getPopoverLinks();
        softAssertions.assertThat(popoverHeaderText)
                .contains(Finals.TRANSACTION_POPOVER_HEADER_TEXT)
                .as("Проверка на соотвествие заголовка в popover'e в секции платежи и переводы");
        softAssertions.assertThat(popoverLinksText)
                .contains(Finals.TRANSACTION_POPOVER_LINKS_TEXT)
                .as("Проверка на соотвествие текса ссылок в popover'e в секции платежи и переводы");
        softAssertions.assertThat(popoverLinks)
                .contains(Finals.TRANSACTION_POPOVER_LINKS)
                .as("Проверка на соотвествие ссылок в popover'e в секции платежи и переводы");
        softAssertions.assertAll();
    }
    @Test
    public void popoverTransactionLinksTest(){
        driver.get(Finals.MAIN_PAGE_URL);
        mainPage.hoverTransactionPopOverTrigger();
        Set<String> popoverLinks = new HashSet<>(getPopoverLinks());
        popoverLinks.forEach((link)->{
            driver.get(link);
            Assertions.assertThat(Arrays.stream(Finals.TRANSACTION_POPOVER_LINKS).toList().contains(driver.getCurrentUrl()))
                    .as("Проверка работоспособности ссылок на другие страницы в секции платежи и переводы");
        });
    }
    @Test
    public void popoverOtherTest(){
        driver.get(Finals.MAIN_PAGE_URL);
        mainPage.hoverOtherPopOverTrigger();
        List<String> popoverHeaderText = getPopoverHeaderText();
        List<String> popoverLinksText = getPopoverLinksText();
        List<String> popoverLinks = getPopoverLinks();
        softAssertions.assertThat(popoverHeaderText)
                .contains(Finals.OTHER_POPOVER_HEADER_TEXT)
                .as("Проверка на соотвествие заголовка в popover'e в секции еще");
        softAssertions.assertThat(popoverLinksText)
                .contains(Finals.OTHER_POPOVER_LINKS_TEXT)
                .as("Проверка на соотвествие текса ссылок в popover'e в секции еще");
        softAssertions.assertThat(popoverLinks)
                .contains(Finals.OTHER_POPOVER_LINKS)
                .as("Проверка на соотвествие ссылок в popover'e в секции еще");
        softAssertions.assertAll();
    }
    @Test
    public void popoverOtherLinksTest(){
        driver.get(Finals.MAIN_PAGE_URL);
        mainPage.hoverOtherPopOverTrigger();
        Set<String> popoverLinks = new HashSet<>(getPopoverLinks());
        popoverLinks.forEach((link)->{
            driver.get(link);
            Assertions.assertThat(Arrays.stream(Finals.OTHER_POPOVER_LINKS).toList().contains(driver.getCurrentUrl()))
                    .as("Проверка работоспособности ссылок на другие страницы в секции еще");
        });
    }
    @Test
    public void linkWithBankBtnTest(){
        driver.get(Finals.MAIN_PAGE_URL);
        mainPage.clickLinkWithBankBtn();
        Assertions.assertThat(driver.getCurrentUrl())
                .isEqualTo(Finals.LINK_WITH_BANK_PAGE_URL)
                .as("Проверка работы кнопки СВЯЗЬ С БАНКОМ");
    }
    @Test
    public void bankomatBtnTest(){
        driver.get(Finals.MAIN_PAGE_URL);
        mainPage.clickBankomatBtn();
        Assertions.assertThat(driver.getCurrentUrl())
                .isEqualTo(Finals.BANKOMAT_PAGE_URL)
                .as("Проверка работы кнопки БАНКОМАТЫ");
    }
    @Test
    public void homepageLinkTest(){
        driver.get(Finals.MAIN_PAGE_URL);
        mainPage.clickHomePageLink();
        Assertions.assertThat(driver.getCurrentUrl())
                .isEqualTo(Finals.HOMEPAGE_URL)
                .as("Проверка иконки банка на переход на домашнюю страницу");
    }
    @Test
    public void partsLinksTest(){
        List<String> partsLinks = driver
                .findElements(By.cssSelector("css-tchm6t"))
                .stream().map((link)->{
                    return link.getAttribute("href");
                }).toList();

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
                .map((link) ->{
                    return link.getAttribute("href");
                }).toList();
    }
    @AfterClass
    public static void tearDown() {
        driver.quit(); }

}
