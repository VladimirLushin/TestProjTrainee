package ru.bspb.test;

import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// https://www.bspb.ru/retail/ibank
public class MainPage {
    public WebDriver driver;
    public Actions builder;

    @FindBy(css = ".css-e11y5u")
    public WebElement goToLoginPageBtn;

    @FindBy(css = "#app-wrapper > div.css-1ujpw5q > div.css-fjazzb > div > div > a > div")
    public WebElement goToLoginPageLink;

    @FindBy(css = "#popover-trigger-4")
    public WebElement cardPopOverTrigger;

    @FindBy(css = "#popover-trigger-6")
    public WebElement creditPopOverTrigger;

    @FindBy(css = "#popover-trigger-8")
    public WebElement ipotecPopOverTrigger;

    @FindBy(css = "#popover-trigger-10")
    public WebElement depositPopOverTrigger;

    @FindBy(css = "#popover-trigger-12")
    public WebElement transactionPopOverTrigger;

    @FindBy(css = "#popover-trigger-14")
    public WebElement otherPopOverTrigger;

    @FindBy(css = ".css-k13pds")
    public WebElement linkWithBankBtn;

    @FindBy(css = ".css-1juz3op")
    public WebElement bankomatBtn;

    @FindBy(css = ".css-trt3i2")
    public WebElement homePageLink;




    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.builder = new Actions(this.driver);
    }

    public void clickGoToLoginPageBtn(){
        goToLoginPageBtn.click();
    }
    public void clickGoToLoginPageLink(){
        goToLoginPageLink.click();
    }
    public void hoverOtherPopOverTrigger(){
        builder.moveToElement(otherPopOverTrigger).perform();
    }
    public void hoverTransactionPopOverTrigger(){
        builder.moveToElement(transactionPopOverTrigger).perform();
    }
    public void hoverDepositPopOverTrigger(){
        builder.moveToElement(depositPopOverTrigger).perform();
    }
    public void hoverIpotecPopOverTrigger(){
        builder.moveToElement(ipotecPopOverTrigger).perform();
    }
    public void hoverCreditPopOverTrigger(){
        builder.moveToElement(creditPopOverTrigger).perform();
    }
    public void hoverCardPopOverTrigger(){
        builder.moveToElement(cardPopOverTrigger).perform();
    }
    public void clickLinkWithBankBtn(){
        linkWithBankBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#\\37 0 > div.chakra-stack.css-1mz8um9 > a:nth-child(1) > span")));
    }
    public void clickBankomatBtn(){
        bankomatBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#\\34  > div.css-0 > div.css-1ujpw5q > div > form > div.chakra-container.css-jf7n8r > div > div > div.chakra-select__wrapper.css-7wlecp > select")));
    }
    public void clickHomePageLink(){
        homePageLink.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#\\32")));
    }
}