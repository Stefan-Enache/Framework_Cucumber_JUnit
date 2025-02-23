package stepDefinitions;

import java.util.Map;

import org.junit.Assert;

import factory.BaseClass;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;


public class RegistrationSteps {

    HomePage homePage;
    AccountRegistrationPage registrationPage;

    @Given("the user navigates to Register Account page")
    public void user_navigates_to_register_account_page() {
        homePage = new HomePage(BaseClass.getDriver());
        homePage.clickMyAccount();
        homePage.clickRegister();
        BaseClass.getLogger().info("Clicked on My Account and on Login");
    }

    @When("the user enters the following details into the fields")
    public void user_enters_the_details_into_below_fields(DataTable dataTable) {
        Map<String, String> dataMap = dataTable.asMap(String.class, String.class);
        registrationPage = new AccountRegistrationPage(BaseClass.getDriver());
        registrationPage.setFirstName(dataMap.get("firstName"));
        registrationPage.setLastName(dataMap.get("lastName"));
        registrationPage.setEmail(BaseClass.randomAlphaNumeric(7, 5) + "@gmail.com");
        registrationPage.setTelephone(dataMap.get("telephone"));
        registrationPage.setPassword(dataMap.get("password"));
        registrationPage.setConfirmPassword(dataMap.get("password"));
        BaseClass.getLogger().info("Filled input fields");
    }

    @When("the user selects Privacy Policy")
    public void user_selects_privacy_policy() {
        registrationPage.setPrivacyPolicy();
    }

    @When("the user clicks on Continue button")
    public void user_clicks_on_continue_button() {
        registrationPage.clickContinue();
        BaseClass.getLogger().info("Clicked on Continue button");
    }

    @Then("the user account should get created successfully")
    public void user_account_should_get_created_successfully() {
        Assert.assertEquals(registrationPage.getConfirmationMsg(), "Your Account Has Been Created!");
    }
}
