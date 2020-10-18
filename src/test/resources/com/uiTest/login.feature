@login
Feature: Login Page
  In order to login successfully
  User inserts valid credentials

  Background:
    Given I am on the Login page

  Scenario Outline: Positive Login Test
    When I enter user credentials:
      | username     | password        |
      | <user_email> | <user_password> |
    And I click on Login button
    Then I see logout button

    Examples:
      | user_email                 | user_password    |
      | phone.boock.test@gmail.com | phone.boock.test |
      | PHONE.BOOCK.TEST@GMAIL.COM | phone.boock.test |
      | test111@gmail.com          | 12345678         |


  Scenario Outline: Negative Login Test, invalid email address
    When I set invalid user email: <user_email>
    Then I observe error message: Email must be a valid email address.
    And The login button is disabled

    Examples:
      | user_email                                |
      | phone.boock.test@gmail.comcomcomcomcomcom |
      | phone.boock.test@gmail.c                  |
      | phone.boock.test@gmail.                   |
      | phone.boock.test@gmail                    |
      | phone.boock.test@                         |
      | phone.boock.test                          |

  Scenario: Negative Login Test, empty email address
    When I click on email input filed
    Then I observe error message because i don't set value: Email is required.

  Scenario Outline: Negative Login Test, invalid password
    When I set invalid too long password <password>
    Then I observe password error message: <message>

    Examples:
      | password              | message                                        |
      | 012345678901234567890 | Password must be no longer than 20 characters. |
      | 0123456               | Password must be at least 8 characters.        |
