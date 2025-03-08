package stepDefinitions;

import java.util.HashMap;
import java.util.List;

import org.junit.Assert;

import factory.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataReader;

public class LoginSteps {
    HomePage homePage;
    LoginPage loginPage;
    MyAccountPage myAccountPage;
    List<HashMap<String, String>> dataMap; //Data driven

    @Given("the user navigates to Login page")
    public void user_navigate_to_login_page() {
        homePage = new HomePage(BaseClass.getDriver());
        homePage.clickMyAccount();
        homePage.clickLogin();
        BaseClass.getLogger().info("Clicked on My Account and on Login");
    }

    @When("user enters email as {string} and password as {string}")
    public void user_enters_email_as_and_password_as(String email, String pwd) {
        loginPage = new LoginPage(BaseClass.getDriver());
        loginPage.setEmail(email);
        loginPage.setPassword(pwd);
        BaseClass.getLogger().info("Entered email and password");
    }

    @When("the user clicks on the Login button")
    public void click_on_login_button() {
        loginPage.clickLogin();
        BaseClass.getLogger().info("Clicked on Login button");
    }


    @Then("the user should be redirected to MyAccount Page")
    public void user_navigates_to_my_account_page() {
        myAccountPage = new MyAccountPage(BaseClass.getDriver());
        boolean loginsStatus = myAccountPage.isMyAccountHeadingPresent();
        Assert.assertTrue(loginsStatus);
    }

    // Data Driven test
    @Then("the user should be redirected to MyAccount Page by passing email and password with excel row {string}")
    public void check_user_navigates_to_my_account_page_by_passing_email_and_password_with_excel_data(String rows) {
        dataMap = DataReader.data(System.getProperty("user.dir") + "\\testData\\Opencart_LoginData.xlsx", "Sheet1");

        int index = Integer.parseInt(rows) - 1;  // feature file counts from 1 but index counts from 0
        String email = dataMap.get(index).get("Username");
        String password = dataMap.get(index).get("Password");
        String expected = dataMap.get(index).get("Expected Result");

        loginPage = new LoginPage(BaseClass.getDriver());
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLogin();

        myAccountPage = new MyAccountPage(BaseClass.getDriver());
        try {
            boolean loginsStatus = myAccountPage.isMyAccountHeadingPresent();
            System.out.println("Logins status: " + loginsStatus);
            if (expected.equalsIgnoreCase("Valid")) {
                if (loginsStatus) {
                    myAccountPage.clickLogout();
                    Assert.assertTrue(true);
                } else {
                    Assert.fail();
                }
            }

            if (expected.equalsIgnoreCase("Invalid")) {
                if (loginsStatus) {
                    myAccountPage.clickLogout();
                    Assert.fail();
                } else {
                    Assert.assertTrue(true);
                }
            }
        } catch (Exception e) {
            Assert.fail();
        }
    }

}
