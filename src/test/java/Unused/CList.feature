@priceestimation
Feature: Priceestimation

  @sanity @regression
  Scenario Outline: User performs a search
    Given User opens classified-list page
    When User selects a filter results are updated
    #Then The price estimation is accurate
  Examples:
  | makeId      | month | year | model                       | fuel   | power     | equipmentline | mileage |
  | Alfa Romeo  | 03    | 2011 | Giulietta Limousine 5 Türen | Benzin | 170 (125) | Turismo       | 30000   |





  #Scenario: a few cukes
    #Given I have 42 cukes in my belly
    #When I wait 1 hour
    #Then my belly should growl
