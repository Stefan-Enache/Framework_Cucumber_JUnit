package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[text()='My Account']")
    WebElement myAccount;

    @FindBy(linkText = "Register")
    WebElement register;

    @FindBy(linkText = "Login")
    WebElement btnLogin;


    public void clickMyAccount() {
        myAccount.click();
    }

    public void clickRegister() {
        register.click();
    }

    public void clickLogin() {
        btnLogin.click();
    }

}
