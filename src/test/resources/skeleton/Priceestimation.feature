@priceestimation
Feature: Priceestimation

  @acceptance
  Scenario Outline: User wants a price estimation for his car using light model
    Given User opens price estimation tool on "<url>"
    When User enters his car data "<makeId>" "<month>" "<year>" "<model>" "<fuel>" "<power>" "<equipmentline>" "<mileage>" and submits
    Then The price estimation is accurate
    Examples:
      | url                                            | makeId      | month | year | model                       | fuel    | power     | equipmentline                    | mileage |
      | https://<env>.autoscout24.de/fahrzeugbewertung | Alfa Romeo  | 03    | 2011 | Giulietta Limousine 5 Türen | Benzin  | 170 (125) | 1.4 TB 16V Multiair              | 30000   |
      | https://<env>.autoscout24.be/evaluationvoiture | Alfa Romeo  | 03    | 2011 | Giulietta Berline 5 Deuren  | Benzine | 170 (125) | 1.4i Multi Air Progression Start | 30000   |
      #| http://localhost:9000/fahrzeugbewertung        | Alfa Romeo  | 03    | 2011 | Giulietta Limousine 5 Türen | Benzin  | 170 (125) | Grundaustattung                  | 30000   |

  @acceptance
  Scenario Outline: User wants a price estimation for his car using full model
    Given User opens price estimation tool on "<url>"
    When User enters default "<cardata>"
    And User selects all additional equipment
    Then The price estimation is accurate
    Examples:
      | url                                          | cardata               |
      | https://<env>.autoscout24.de/fahrzeugbewertung| CarDataDE.DevProd |
      #| http://192.168.99.100:8080/fahrzeugbewertung | CarDataDE.LocalDocker |

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

  @acceptance
  Scenario Outline: Error codes are not shown to client
    Given User opens price estimation tool on "<url>"
    Then The right error page is shown with error messages "<errText>" "<errButtonText>"
    Examples:
    # Creating 5xx Errors on Prod creates alerts !!!
      | url                                                    | errText                                                               | errButtonText      |
      | http://<env>.autoscout24.de/priceestimation/code/400  | Die gesuchte Seite existiert leider nicht mehr.                        | Zur Startseite     |
#      | http://<env>.autoscout24.de/priceestimation/code/403  | Die gesuchte Seite existiert leider nicht mehr.                        | Zur Startseite     |
#      | http://<env>.autoscout24.de/priceestimation/code/404  | Die gesuchte Seite existiert leider nicht mehr.                        | Zur Startseite     |
#      | http://<env>.autoscout24.de/priceestimation/code/500  | Die gesuchte Seite ist momentan leider nicht verfügbar.                | Zur Startseite     |
#      | http://<env>.autoscout24.de/priceestimation/code/501  | Die gesuchte Seite ist momentan leider nicht verfügbar.                | Zur Startseite     |
#      | http://<env>.autoscout24.de/priceestimation/code/502  | Die gesuchte Seite ist momentan leider nicht verfügbar.                | Zur Startseite     |
#      | http://<env>.autoscout24.de/priceestimation/code/503  | Die gesuchte Seite ist momentan leider nicht verfügbar.                | Zur Startseite     |
#      | http://<env>.autoscout24.de/priceestimation/code/504  | Die gesuchte Seite ist momentan leider nicht verfügbar.                | Zur Startseite     |
      | http://<env>.autoscout24.be/priceestimation/code/400  | De door u opgevraagde pagina bestaat helaas niet meer.                 | Naar de homepage   |
#      | http://<env>.autoscout24.be/priceestimation/code/403  | De door u opgevraagde pagina bestaat helaas niet meer.                 | Naar de homepage   |
#      | http://<env>.autoscout24.be/priceestimation/code/404  | De door u opgevraagde pagina bestaat helaas niet meer.                 | Naar de homepage   |
#      | http://<env>.autoscout24.be/priceestimation/code/500  | De door u opgevraagde pagina is op dit moment helaas niet beschikbaar. | Naar de homepage   |
#      | http://<env>.autoscout24.be/priceestimation/code/501  | De door u opgevraagde pagina is op dit moment helaas niet beschikbaar. | Naar de homepage   |
#      | http://<env>.autoscout24.be/priceestimation/code/502  | De door u opgevraagde pagina is op dit moment helaas niet beschikbaar. | Naar de homepage   |
#      | http://<env>.autoscout24.be/priceestimation/code/503  | De door u opgevraagde pagina is op dit moment helaas niet beschikbaar. | Naar de homepage   |
#      | http://<env>.autoscout24.be/priceestimation/code/504  | De door u opgevraagde pagina is op dit moment helaas niet beschikbaar. | Naar de homepage   |
      | http://<env>.autoscout24.com/priceestimation/code/400 | This page no longer exists.                                            | Go to our Homepage |
#      | http://<env>.autoscout24.com/priceestimation/code/403 | This page no longer exists.                                            | Go to our Homepage |
#      | http://<env>.autoscout24.com/priceestimation/code/404 | This page no longer exists.                                            | Go to our Homepage |
#      | http://<env>.autoscout24.com/priceestimation/code/500 | This page is currently unavailable.                                    | Go to our Homepage |
#      | http://<env>.autoscout24.com/priceestimation/code/501 | This page is currently unavailable.                                    | Go to our Homepage |
#      | http://<env>.autoscout24.com/priceestimation/code/502 | This page is currently unavailable.                                    | Go to our Homepage |
#      | http://<env>.autoscout24.com/priceestimation/code/503 | This page is currently unavailable.                                    | Go to our Homepage |
#      | http://<env>.autoscout24.com/priceestimation/code/504 | This page is currently unavailable.                                    | Go to our Homepage |
#      | http://<env>.autoscout24.bg/gibtsnicht.html           | За съжаление търсената от Вас страница вече не съществува.             | Към началната страница |
#      | http://<env>.autoscout24.cz/gibtsnicht.html           | Hledaná stránka již bohužel neexistuje.                                | Na úvodní stranu |
#      | http://<env>.autoscout24.at/gibtsnicht.html           | Die gesuchte Seite existiert leider nicht mehr.                        | Zur Startseite |
#      | http://<env>.autoscout24.es/gibtsnicht.html           | La página que busca ya no existe.                                      | Ir a la página de inicio |
#      | http://<env>.autoscout24.fr/gibtsnicht.html           | Désolé, la page que vous avez demandée n'existe plus.                  | Retour à la page d'accueil |
#      | http://<env>.autoscout24.lu/gibtsnicht.html           | Désolé, la page que vous avez demandée n'existe plus.                  | Retour à la page d'accueil |
#      | http://<env>.autoscout24.hr/gibtsnicht.html           | Stranica koju ste tražili nažalost više ne postoji.                    | Početna stranica |
#      | http://<env>.autoscout24.it/gibtsnicht.html           | La pagina cercata non esiste più.                                      | Alla pagina iniziale |
#      | http://<env>.autoscout24.nl/gibtsnicht.html           | De door u opgevraagde pagina bestaat helaas niet meer.                 | Naar de homepage |
#      | http://<env>.autoscout24.ro/gibtsnicht.html           | Din păcate, pagina căutată nu mai există.                              | Către pagina de start |
#      | http://<env>.autoscout24.ru/gibtsnicht.html           | К сожалению, запрошенная страница не существует.                       | На главную |
#      | http://<env>.autoscout24.se/gibtsnicht.html           | Den eftersökta hemsidan existerar tyvärr inte längre.                  | Till startsidan |
#      | http://<env>.autoscout24.com.tr/gibtsnicht.html       | Aranan sayfa maalesef artık mevcut değil.                              | Başlangıç sayfasına git |
#      | http://<env>.autoscout24.com.ua/gibtsnicht.html       | На жаль, сторінки, яку Ви шукаєте, більше не існує.                    | На головну |
##      | http://<env>.autoscout24.pl/gibtsnicht.html           | Szukana strona niestety już nie istnieje.                             | Do strony startowej |

###      | http://<env>.autoscout24.hu/gibtsnicht.html | A keresett oldal sajnos már nem létezik. | A kezdőlapra |


#  Scenario Outline: Loop Colors and equipment
#    Given User opens price estimation tool on "<url>"
#    When User enters default "<cardata>"
#    And Selects additional equipment
#    Examples:
#      | url                                     | cardata  |
#      | http://localhost:9000/fahrzeugbewertung | CarDataDE |
