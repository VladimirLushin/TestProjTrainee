package ru.bspb.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//https://i.bspb.ru/auth/login
public class LoginPage {
    public WebDriver driver;
    @FindBy(name = "username")
    public WebElement usernameInput;

    @FindBy(name = "password")
    public WebElement passwordInput;

    @FindBy(css = "#login-button")
    public WebElement loginbutton;

    @FindBy(css = "#login-form > div:nth-child(7) > div")
    public WebElement passwordVisibleDiv;

    @FindBy(css = "#additional-actions > a")
    public WebElement restoreAccessBtn;

    @FindBy(css = "#security-rules-dialog-popup > div > div > div.modal-header > a")
    public WebElement closeRestoreAccessBtn;

    @FindBy(css = "#security-rules-dialog-popup")
    public WebElement restoreAccessPopUp;


    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void setPasswordInput(String password) {
        passwordInput.sendKeys(password);
    }

    public void setUsernameInput(String login) {
       usernameInput.sendKeys(login);
    }
    public String getUsername(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        return usernameInput.getAttribute("value");
    }
    public String getPassword(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        return passwordInput.getAttribute("value");
    }

    public void clickLoginBtn(){
        loginbutton.click();
    }
    public void clickPasswordVisibleDiv(){
        passwordVisibleDiv.click();
    }
    public void clickRestoreAccessBtn(){
        restoreAccessBtn.click();
    }
    public void clickCloseRestoreAccessBtn(){
        closeRestoreAccessBtn.click();
    }
    public String getPasswordInputType(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        return passwordInput.getAttribute("type");
    }
    public String getRestoreAccessDisplayAttr(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#security-rules-dialog-popup")));
        return restoreAccessPopUp.getAttribute("style");
    }
}