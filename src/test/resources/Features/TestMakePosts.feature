#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@smoke
Feature: Verify different GET & POST operations

  Scenario: Verify one author of the post
    Given The endpoint is up and running for "https://jsonplaceholder.typicode.com/"
    When User performs a post request for adding a new post
    Then User performs a get post to validate the new post
    When User performs a post request for adding a new comment
    Then User performs a get comment to validate the new comment
    And User performs a get post request for id "10"
    Then User sees the  response with pairs
      | userId | id | title                       | body                                                                                                                        |
      |     10 | 99 | laboriosam dolor voluptates | doloremque ex facilis sit sint culpa\nsoluta assumenda eligendi non ut eius\nsequi ducimus vel quasi\nveritatis est dolores |
    And User gets listofallusers and validate for id "9" name "Glenna Reichert" username "Delphine" email "email" Chaim_McDermott@dana.io
    
