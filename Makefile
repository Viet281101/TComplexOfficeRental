# Variables
MVN=mvn
TOMCAT_HOME=/opt/tomcat9
WAR_FILE=target/TComplexOfficeRental.war
DEPLOY_DIR=$(TOMCAT_HOME)/webapps
DB_SETUP_SCRIPT=db/setup.sql

# Targets
.PHONY: all clean build deploy run db-setup

all: clean build deploy

clean:
	$(MVN) clean

build:
	$(MVN) package

deploy:
	cp $(WAR_FILE) $(DEPLOY_DIR)

run:
	$(TOMCAT_HOME)/bin/catalina.sh run

db-setup:
	mysql -u root -p < $(DB_SETUP_SCRIPT)

stop:
	$(TOMCAT_HOME)/bin/catalina.sh stop
