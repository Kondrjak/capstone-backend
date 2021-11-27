# Setup on your local machine
* install maven
* run "mvn test" for testing
* run "mvn build -DargLine="-JDBC_DATABASE_URL=jdbc:h2:mem:test -AUTH_JWT_SECRET=someTestSecret"
* when using other frameworks make sure the environment variables JDBC_DATABASE_URL and AUTH_JWT_SECRET are set as above

# Todo:
* integrate spring security and o-auth
* make application.properties database.dialect environment dependent

# Done:
* Spring initializr with deps: Rest Repositories, Spring Data JPA, H2 Database, Lombok
* Health Check, get localhost:8080/health returns "Hello there! I'm running."
* add: system.properties "java.runtime.version=17"
* pushed and deployed to heroku
* Health Check, get https://dev-capstone-backend.herokuapp.com/health returns "Hello there! I'm running"
* share on github.com/romsenkabomsen/capstone-backend
* added heroku postgres to heroku-deployment
* create codepoint group data class and restful api repo
* create workflow for continuous development
* use h2 db for local testing

