package ru.bspb.cucumber.test.glue;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Attachment;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.bspb.cucumber.test.ConfProp;
import ru.bspb.cucumber.test.Finals;
import ru.bspb.cucumber.test.LoginPage;
import ru.bspb.cucumber.test.MainPage;

import java.time.Duration;
import java.util.*;

public class MyStepdefs {
    public static ChromeDriver driver;
    public static MainPage mainPage;
    public static LoginPage loginPage;
    public static SoftAssertions softAssertions;
    public static List<String> linksSet;
    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", ConfProp.getProperty("chromedriver"));
        linksSet = new ArrayList<>();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
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


    @Тогда("пользователь  на странице входа в личный кабинет")
    @Severity(SeverityLevel.NORMAL)
    public void goToLoginPageTest() {
        List<String> tabsURL = driver.getWindowHandles().stream().map((tab) -> {
            driver.switchTo().window(tab);
            return driver.getCurrentUrl();
        }).toList();
        Assertions.assertThat(tabsURL).as("Переход на новую страницу по ссылке").contains(Finals.LOGIN_PAGE_URL);
    }

    @Когда("пользователь нажимает на ссылку перехода в интернет банк")
    public void goToLoginPageByLink() {
        mainPage.clickGoToLoginPageLink();
    }

    @Когда("пользователь наводит курсор на секцию {string}")
    public void hoverPopoverSection(String section_name) {
        switch (section_name) {
            case "Cards" -> mainPage.hoverCardPopOverTrigger();
            case "Credit" -> mainPage.hoverCreditPopOverTrigger();
            case "Ipotec" -> mainPage.hoverIpotecPopOverTrigger();
            case "Deposit" -> mainPage.hoverDepositPopOverTrigger();
            case "Transaction" -> mainPage.hoverTransactionPopOverTrigger();
            case "Other" -> mainPage.hoverOtherPopOverTrigger();
        }
    }
    @И("Пользователь перешел на соответсвующую страницу {string}")
    @Severity(SeverityLevel.CRITICAL)
    public void goToNewPagesByPopoverLinksTest(String section_name) {
        switch (section_name) {
            case "Cards" -> {
                Set<String> popoverLinks = new HashSet<>(getPopoverLinks());
                popoverLinks.forEach((link) -> {
                    driver.get(link);
                    Assertions.assertThat(Arrays.stream(Finals.CARDS_POPOVER_LINKS).toList().contains(driver.getCurrentUrl()))
                            .as("Проверка работоспособности ссылок на другие страницы в секции карты");
                });
            }
            case "Credit" -> {
                Set<String> popoverLinks = new HashSet<>(getPopoverLinks());
                popoverLinks.forEach((link) -> {
                    driver.get(link);
                    Assertions.assertThat(Arrays.stream(Finals.CREDIT_POPOVER_LINKS).toList().contains(driver.getCurrentUrl()))
                            .as("Проверка работоспособности ссылок на другие страницы в секции кредиты");
                });
            }
            case "Ipotec" -> {
                Set<String> popoverLinks = new HashSet<>(getPopoverLinks());
                popoverLinks.forEach((link) -> {
                    driver.get(link);
                    Assertions.assertThat(Arrays.stream(Finals.IPOTEC_POPOVER_LINKS).toList().contains(driver.getCurrentUrl()))
                            .as("Проверка работоспособности ссылок на другие страницы в секции ипотека");
                });
            }
            case "Deposit" -> {
                Set<String> popoverLinks = new HashSet<>(getPopoverLinks());
                popoverLinks.forEach((link) -> {
                    driver.get(link);
                    Assertions.assertThat(Arrays.stream(Finals.DEPOSIT_POPOVER_LINKS).toList().contains(driver.getCurrentUrl()))
                            .as("Проверка работоспособности ссылок на другие страницы в секции вклады");
                });
            }
            case "Transaction" -> {
                Set<String> popoverLinks = new HashSet<>(getPopoverLinks());
                popoverLinks.forEach((link) -> {
                    driver.get(link);
                    Assertions.assertThat(Arrays.stream(Finals.TRANSACTION_POPOVER_LINKS).toList().contains(driver.getCurrentUrl()))
                            .as("Проверка работоспособности ссылок на другие страницы в секции платежи и переводы");
                });
            }
            case "Other" -> {
                Set<String> popoverLinks = new HashSet<>(getPopoverLinks());
                popoverLinks.forEach((link) -> {
                    driver.get(link);
                    Assertions.assertThat(Arrays.stream(Finals.OTHER_POPOVER_LINKS).toList().contains(driver.getCurrentUrl()))
                            .as("Проверка работоспособности ссылок на другие страницы в секции еще");
                });
            }
        }
    }

    @Тогда("Пользователь наблюдает соответствующие данные {string}")
    @Severity(SeverityLevel.TRIVIAL)
    public void checkDataCorrect(String section_name) {
        switch (section_name) {
            case "Cards" -> {
                List<String> popoverHeaderText = getPopoverHeaderText();
                List<String> popoverLinksText = getPopoverLinksText();
                softAssertions.assertThat(popoverHeaderText)
                        .as("Проверка на соотвествие заголовка в popover'e в секции карты")
                        .contains(Finals.CARDS_POPOVER_HEADER_TEXT);
                softAssertions.assertThat(popoverLinksText)
                        .as("Проверка на соотвествие текса ссылок в popover'e в секции карты")
                        .contains(Finals.CARDS_POPOVER_LINKS_TEXT);
                softAssertions.assertAll();
            }
            case "Credit" -> {
                List<String> popoverHeaderText = getPopoverHeaderText();
                List<String> popoverLinksText = getPopoverLinksText();
                softAssertions.assertThat(popoverHeaderText)
                        .as("Проверка на соотвествие заголовка в popover'e в секции кредиты")
                        .contains(Finals.CREDIT_POPOVER_HEADER_TEXT);
                softAssertions.assertThat(popoverLinksText)
                        .as("Проверка на соотвествие текса ссылок в popover'e в секции кредиты")
                        .contains(Finals.CREDIT_POPOVER_LINKS_TEXT);
                softAssertions.assertAll();
            }
            case "Ipotec" -> {
                List<String> popoverHeaderText = getPopoverHeaderText();
                List<String> popoverLinksText = getPopoverLinksText();
                softAssertions.assertThat(popoverHeaderText)
                        .as("Проверка на соотвествие заголовка в popover'e в секции ипотека")
                        .contains(Finals.IPOTEC_POPOVER_HEADER_TEXT);
                softAssertions.assertThat(popoverLinksText)
                        .as("Проверка на соотвествие текса ссылок в popover'e в секции ипотека")
                        .contains(Finals.IPOTEC_POPOVER_LINKS_TEXT);
                softAssertions.assertAll();
            }
            case "Deposit" -> {
                List<String> popoverHeaderText = getPopoverHeaderText();
                List<String> popoverLinksText = getPopoverLinksText();
                softAssertions.assertThat(popoverHeaderText)
                        .as("Проверка на соотвествие заголовка в popover'e в секции вклады")
                        .contains(Finals.DEPOSIT_POPOVER_HEADER_TEXT);
                softAssertions.assertThat(popoverLinksText)
                        .as("Проверка на соотвествие текса ссылок в popover'e в секции вклады")
                        .contains(Finals.DEPOSIT_POPOVER_LINKS_TEXT);
                softAssertions.assertAll();
            }
            case "Transaction" -> {
                List<String> popoverHeaderText = getPopoverHeaderText();
                List<String> popoverLinksText = getPopoverLinksText();
                softAssertions.assertThat(popoverHeaderText)
                        .as("Проверка на соотвествие заголовка в popover'e в секции платежи и переводы")
                        .contains(Finals.TRANSACTION_POPOVER_HEADER_TEXT);
                softAssertions.assertThat(popoverLinksText)
                        .as("Проверка на соотвествие текса ссылок в popover'e в секции платежи и переводы")
                        .contains(Finals.TRANSACTION_POPOVER_LINKS_TEXT);
                softAssertions.assertAll();
            }
            case "Other" -> {
                List<String> popoverHeaderText = getPopoverHeaderText();
                List<String> popoverLinksText = getPopoverLinksText();
                softAssertions.assertThat(popoverHeaderText)
                        .as("Проверка на соотвествие заголовка в popover'e в секции еще")
                        .contains(Finals.OTHER_POPOVER_HEADER_TEXT);
                softAssertions.assertThat(popoverLinksText)
                        .as("Проверка на соотвествие текса ссылок в popover'e в секции еще")
                        .contains(Finals.OTHER_POPOVER_LINKS_TEXT);
                softAssertions.assertAll();
            }
        }
    }
    @After
    public void tearDown(){
        driver.quit();
    }

    @Когда("пользователь нажимает на кнопку {string}")
    public void userClickButton(String button_name) {
        switch (button_name){
            case "LinkWithBank" ->  mainPage.clickLinkWithBankBtn();
            case "Bankomats" ->  mainPage.clickBankomatBtn();
            case "BankLogo" -> mainPage.clickHomePageLink();
        }
    }

    @Тогда("оказывается на {string} {string}")
    @Severity(SeverityLevel.CRITICAL)
    public void checkCurrentPage(String expected_page, String button_name) {
        Assertions.assertThat(driver.getCurrentUrl()).as("Проверка работы кнопки: " + button_name).isEqualTo(expected_page);
    }


    @Тогда("введенные данные совпадают с наблюдаемыми {string} {string}" )
    @Severity(SeverityLevel.BLOCKER)
    public void checkEnteredData(String login, String password) {
        softAssertions.assertThat(loginPage.getUsername())
                .as("Проверка корректности ввода данных в поле логин")
                .isEqualTo(login);
        softAssertions.assertThat(loginPage.getPassword())
                .as("Проверка корректности ввода данных в поле пароль")
                .isEqualTo(password);
        softAssertions.assertAll();
    }
    @Когда("пользователь вводит данные в поля {string} {string}")
    public void  enterDataToLoginField(String login, String password) {
        loginPage.setUsernameInput(login);
        loginPage.setPasswordInput(password);
    }

    @Дано("пользователь находится на странице входа в личный кабинет")
    public void goToLoginPage() {
        driver.get(Finals.LOGIN_PAGE_URL);
    }

    @Когда("пользователь нажимает на кнопку смены видимости")
    public void clickPasswordVisibilityBtn() {
        loginPage.clickPasswordVisibleDiv();
    }

    @Тогда("пароль становится видимым")
    @Severity(SeverityLevel.TRIVIAL)
    public void checkPasswordVisibilityTrue() {
        Assertions.assertThat(loginPage.getPasswordInputType())
                .as("Проверка видимости пароля с невидимого на видимый")
                .isEqualTo("text");
    }

    @Тогда("пароль становится невидимым")
    @Severity(SeverityLevel.TRIVIAL)
    public void checkPasswordVisibilityFalse() {
        Assertions.assertThat(loginPage.getPasswordInputType())
                .as("Проверка видимости пароля с видимого на невидимый")
                .isEqualTo("password");
    }

    @И("пользователь нажимает на конпку входа в личный кабинет")
    public void clickLoginBtn() {
        loginPage.clickLoginBtn();
    }

    @Тогда("появляется сообщение о некорректности введенных данных")
    @Severity(SeverityLevel.NORMAL)
    public void checkErrorMessage() {
        Assertions.assertThat(driver.findElement(By.cssSelector("#alerts-container > div.alert.alert-error")).isDisplayed())
                .as("Проверка на некорректно введеннные данные")
                .isTrue();
    }

    @Когда("пользователь нажимает на ссылку восстановить пароль")
    public void clickRestoreAccessBtn() {
        loginPage.clickRestoreAccessBtn();
    }

    @Тогда("появляется попап с информацией о восстановление пароля")
    @Severity(SeverityLevel.CRITICAL)
    public void checkPopUpVisibilityTrue() {
        Assertions.assertThat(loginPage.getRestoreAccessDisplayAttr())
                .as("Проверка на появление ПопАпа при нажатие Восстановить доступ ")
                .contains("block");
    }

    @Когда("пользовать нажимает кнопку закрытия")
    public void clickCloseRestoreAccessBtn() {
        loginPage.clickCloseRestoreAccessBtn();
    }

    @Тогда("попап закрывается")
    @Severity(SeverityLevel.BLOCKER)
    public void CheckPopUpVisibilityFalse() {
        Assertions.assertThat(loginPage.getRestoreAccessDisplayAttr())
                .as("Проверка на закрытие ПопАпа при нажатие соответстующей кнопки")
                .contains("none");
    }

}
