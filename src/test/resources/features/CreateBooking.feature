@mesut
Feature: As a user, I can create a hotel reservation

  Scenario: user can create and delete a hotel reservation
    Given user creates a new reservation
    And the user provides the information required for the reservation
    When User creates hotel reservation
    Then the reservation has been created successfully
    And User cancels the created reservation




