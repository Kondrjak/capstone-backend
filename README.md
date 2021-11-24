# Run on local machine
* install docker and docker-compose
* run "docker-compose up" in root directory - your database should now be ready to used by your build
* install maven
* run mvn build, mvn test

# Done:
* Spring initializr with deps: Rest Repositories, Spring Data JPA, H2 Database, Lombok
* Health Check, get localhost:8080/health returns "Hello there! I'm running."
* heroku login
* git init
* renamed master to main
* heroku create dev-capstone-backend
* add: system.properties "java.runtime.version=17"
* git push heroku main
* Health Check, get https://dev-capstone-backend.herokuapp.com/health returns "Hello there! I'm running"
* share on github.com/romsenkabomsen/dev-capstone-backend
* heroku addons:create heroku-postgresql
* heroku pg:psql
* create codepoint group data class and restful api repo
* create workflow for continuous development

# Todo:
* get postgresql to work on local machine in docker and connect to postgresql (exposed from docker) to local build
