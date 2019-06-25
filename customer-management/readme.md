# Customer Management REST API

## Running Customer Management Application locally
Customer Management Application is a https://spring.io/guides/gs/spring-boot [Spring Boot] application built using https://spring.io/guides/gs/maven/ [Maven]. You can build a jar file and run it from the command line:


```
git clone https://github.com/njamesapidev/customer-management.git
cd customer-management
./mvnw package
java -jar target/*.jar
```

You can then access Customer Management Application API documentation here: http://localhost:8080/swagger-ui.html


## Database configuration

Customer Management Application uses an in-memory H2 database which gets populated at startup with data. 

## Working with Customer Management Application in an IDE

### prerequisites
The following are the prerequisites to be installed:
* Java 8 or newer.
* git command line tool (https://help.github.com/articles/set-up-git)
* Your prefered IDE 
  * Eclipse with the m2e plugin. Note: when m2e is available, there is an m2 icon in Help -> About dialog. If m2e is not there, just follow the install process here: http://www.eclipse.org/m2e/
  * [Spring Tools Suite](https://spring.io/tools) (STS)

### Steps:

1) On the command line
```
git clone https://github.com/njamesapidev/customer-management.git
```

2) Inside Eclipse or STS
```
File -> Import -> Maven -> Existing Maven project
```
Then either build using the command line `./mvnw generate-resources` or using the Eclipse launcher (right click on project and `Run As -> Maven install`) to run. Run the application main method by right clicking and choosing `Run As -> Spring Boot App`.

3) Navigate to Customer Management Application API documentation

Please open [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) in your browser.


## Application details?

|Spring Boot Configuration | Class or Java property files  |
|--------------------------|---|
|The Main Class | [CustomerManagementApplication](https://github.com/njamesapidev/customer-management/tree/master/src/main/java/com/santech/customermanagement/CustomerManagementApplication.java) |
|Properties Files | [application.properties](https://github.com/njamesapidev/customer-management/tree/master/src/main/resources) |

## Customer Management branches

The Customer Management master branch in the main [spring-projects](https://github.com/njamesapidev/customer-management)

# License

The Customer Management application is released under version 2.0 of the [Apache License](http://www.apache.org/licenses/LICENSE-2.0).
