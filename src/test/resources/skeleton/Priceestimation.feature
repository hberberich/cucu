@priceestimation
Feature: Priceestimation

  @sanity @regression
  Scenario Outline: User wants a price estimation for his car
    Given User opens price estimation tool
    When User enters his car data "<makeId>" "<month>" "<year>" "<model>" "<fuel>" "<power>" "<equipmentline>" "<mileage>" and submits
    Then The price estimation is accurate
  Examples:
  | makeId      | month | year | model                       | fuel   | power     | equipmentline | mileage |
  | Alfa Romeo  | 03    | 2011 | Giulietta Limousine 5 Türen | Benzin | 170 (125) | Turismo       | 30000   |
  #| Alfa Romeo  | 03    | 2011 | Giulietta Limousine 5 Türen | Benzin | 170 (125) | Turismo       | 50000   |




  #Scenario: a few cukes
    #Given I have 42 cukes in my belly
    #When I wait 1 hour
    #Then my belly should growl
