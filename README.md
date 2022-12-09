# EMPLOYEE ASSIGNMENT
### PURPOSE
3-Tier Mapping in the tables. This project is all about stablishing relationship between 
Employee, Project and Project Entities.

### TECHNOLOGY USED
1) This project is developed on SpringBoot Framework

2) Maven plugin is used to run spring boot application, generate build information, solve dependencies etc.
3) Postman is used for testing various API's on this project like PUT,POST,GET,DElETE
4) MySql database is used as database to store our data 

#### Maven Dependencies 
     * spring-boot-jpa
     * spring-boot-web
     * spring-boot-mysql-connector
     * spring-boot-Junit(testing)
     * spring-boot-mockito(testing)
     * spring-boot-starter-security
     * spring-boot-thymeleaf

#### Entities
 
* Employee
* Project 
* Department
* Manager

### Mapping Between Entities
      1) one-to-many 
This  Mapping between department and employees entities because a single 
department can have multiple employees. and we have stablished relation ship between manager 
and employee one manager can control many employee
      
       2)  Many-to-one 
This Mapping between employee and department entities because multiple 
employee can have same department 
 
       3) Many-to-Many
This relationship between employee-project and project and employee 
because a single employee can have multiple project and a single project 
can be assigned to multiple employees.

      4) one-to-one
This relationship is between manager and department one department can only have one manger(head of department HOD)




By using this method I have provided so many kind api 
to GET, POST, PUT, DELETE in diffrent ways. 


