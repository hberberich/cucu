@priceestimation
Feature: Priceestimation

#  @sanity @regression
#  Scenario Outline: User wants a price estimation for his car using light modelXXX
#    Given User opens price estimation tool
#    When User enters his car data "<makeId>" "<month>" "<year>" "<model>" "<fuel>" "<power>" "<equipmentline>" "<mileage>" and submits
#    Then The price estimation is accurate
#  Examples:
#  | makeId      | month | year | model                       | fuel   | power     | equipmentline | mileage |
#  | Alfa Romeo  | 03    | 2011 | Giulietta Limousine 5 Türen | Benzin | 170 (125) | Turismo       | 30000   |
#  | Alfa Romeo  | 03    | 2011 | Giulietta Limousine 5 Türen | Benzin | 170 (125) | Turismo       | 50000   |



  @acceptance
  Scenario Outline: User wants a price estimation for his car using light model
    Given User opens price estimation tool on "<url>"
    When User enters his car data "<makeId>" "<month>" "<year>" "<model>" "<fuel>" "<power>" "<equipmentline>" "<mileage>" and submits
    Then The price estimation is accurate
    Examples:
      | url                                            | makeId      | month | year | model                       | fuel    | power     | equipmentline                    | mileage |
      | https://<env>.autoscout24.de/fahrzeugbewertung | Alfa Romeo  | 03    | 2011 | Giulietta Limousine 5 Türen | Benzin  | 170 (125) | 1.4 TB 16V Multiair              | 30000   |
      | https://<env>.autoscout24.be/evaluationvoiture | Alfa Romeo  | 03    | 2011 | Giulietta Berline 5 Deuren  | Benzine | 170 (125) | 1.4i Multi Air Progression Start | 30000   |

  @acceptance
  Scenario Outline: User changes language on be site
    Given User opens price estimation tool on "<url>"
    When User changes language to "<lang>"
    Then Page is shown with new "<lang>"
    When User changes language to "<lang2>"
    Then Page is shown with new "<lang2>"
    Examples:
      | url                                        | lang | lang2 |
      | http://<env>.autoscout24.be/prijsschatting | FR   | NL    |


  @acceptance
  Scenario Outline: Display Standard Equipment on detail page
    Given User opens price estimation tool on "<url>"
    When User enters default "<cardata>"
    And User clicks link Standardausstattung einblenden
    Then Standard Equipment is shown
    Examples:
      | url                                           | cardata             |
      #| http://localhost:9000/fahrzeugbewertung       | CarDataDE.LocalHost |
      | http://<env>.autoscout24.de/fahrzeugbewertung | CarDataDE.DevProd   |

#  Scenario Outline: Loop Colors and equipment
#    Given User opens price estimation tool on "<url>"
#    When User enters default "<cardata>"
#    And Selects additional equipment
#    Examples:
#      | url                                     | cardata  |
#      | http://localhost:9000/fahrzeugbewertung | CarDataDE |
