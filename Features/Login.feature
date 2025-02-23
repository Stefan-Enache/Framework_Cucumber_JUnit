Feature: Login with Valid Credentials

  @sanity @regression
  Scenario: Successful Login with Valid Credentials
    Given the user navigates to Login page
    When user enters email as "valid_email_1@gmail.com" and password as "test@123"
    And the user clicks on the Login button
    Then the user should be redirected to MyAccount Page

  @regression
  Scenario Outline: Login Data Driven
    Given the user navigates to Login page
    When user enters email as "<email>" and password as "<password>"
    And the user clicks on the Login button
    Then the user should be redirected to MyAccount Page

    Examples:
      | email                   | password |
      | invalid_email@gmail.com | test123  |
      | valid_email_1@gmail.com | test@123 |
