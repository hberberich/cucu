#!/usr/bin/env bash
#mvn test -Dcucumber.options="--tags @acceptance" -DBROWSER=Safari
#mvn test -Dcucumber.options="--tags @acceptance" -DBROWSER=Chrome
#mvn test -Dcucumber.options="--tags @acceptance" -DBROWSER=Firefox
mvn test -Dcucumber.options="--tags @acceptance" -DBROWSER=PhantomJS

#mvn test -Dcucumber.options="--tags @acceptance" -DBROWSER=Firefox surefire-report:report