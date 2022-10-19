package ru.bspb.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// https://www.bspb.ru/retail/ibank
public class MainPage {
    public WebDriver driver;

    @FindBy(css = ".css-e11y5u")
    public WebElement goToLoginPageBtn;

    @FindBy(css = "#app-wrapper > div.css-1ujpw5q > div.css-fjazzb > div > div > a > div")
    public WebElement goToLoginPageLink;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void clickGoToLoginPageBtn(){
        goToLoginPageBtn.click();
    }
    public void clickGoToLoginPageLink(){
        goToLoginPageLink.click();
    }
}