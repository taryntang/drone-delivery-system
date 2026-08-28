# cs6300-group-9

## Prerequisites
1. JDK 17
2. Maven
3. Postgresql


## Web page guide:
  - server.port = 9090
  - Please navigate to http://localhost:9090
### Admin login (recommend to log in as an admin first to check existing data)
  - Username: admin
  - password: admin
### Customer login
  - Register first


## Two ways to run
### Run with Docker 

1. Clean Docker containers & images.

2. Change the database url in deliveryservice/src/main/resources/application.properties to:  
`spring.datasource.url=jdbc:postgresql://db:5432/deliverydb`

3. Run `mvn clean` via command line.  
If command not found, try to install Maven using `brew install maven` (for Mac) or `choco install maven` (for Windows).  

4. Run `mvn clean install -DskipTests=true` via command line.  
If there is a folder called "Target" with .jar, then it is build successfully.

5. Run `docker-compose up` via command line.  
If it shows "Failed to bind tcp 0.0.0.0:5432 address already in use", try:  
- `sudo lsof -i tcp:5432`
- `sudo kill <pid number>`
or change the db ports to "5432" instead of "5432:5432" in docker-compose.yml

6. Delete the containers and images after you finish running.  

### Local test without Docker:  
  
1. Change the databse url in deliveryservice/src/main/resources/application.properties to:  
`spring.datasource.url=jdbc:postgresql://localhost:5432/deliverydb`
  
2. Install Postgres app or install via command line:  
- For Mac: run `brew install postgres`  
- For Windows: run `choco install postgres`  

3. If there are some packages/dependencies showing red letters with dependencies already exist:  
Clicking File -> Invalidate Caches and restarting the IDE



  
  
