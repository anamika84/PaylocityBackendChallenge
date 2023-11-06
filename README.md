# PaylocityBackendChallenge

Technology used :
   1. Spring Boot
   2. Java 11
   3. H2 Database
   4. JPA
   5. Postman

HOW TO RUN PROJECT :

  1. download the repo from github
  2. open in any IDE
  3. run the application by running Employee2Application.java . database tables will be create during runtime
  4. open brwser and write http://localhost:8080/h2-console which will open a login box.
  5. add JDBC URL: jdbc:h2:mem:test 
  6. and connect to database
  7. add employee

         ex:  curl --location 'http://localhost:8080/api/v1/employee' \
            --header 'Content-Type: application/json' \
            --data '{   
                "firstName" : "Anamika",
                "lastName": "gupta",
                "grossSalary" : 140000,
               "dateOfBirth": "1984-07-04"
                
            }'

8. add dependent

         curl --location 'http://localhost:8080/api/v1/dependents' \
         --header 'Content-Type: application/json' \
         --data '{
             "firstName" : "divya",
             "lastName": "singh",
             "relationship" : "DomesticPartner",
             "dateOfBirth": "2007-09-04"
             
         }'

9. add dependent to employee by giving employee id and dependent id as a path variable. it will show employee details with all dependent associated with employee.
   
         curl --location --request POST 'http://localhost:8080/api/v1/Employee/1/dependents/3/add' \  
         --data ''

10. add salary to amployee by passing employee id as a path variable. it will give gross salary , and total salary after deduction with all the details of an employee.

         curl --location --request POST 'http://localhost:8080/api/v1/employee/1/dependents/1/add' \
         --data ''
    


Requirements :

Question: Able to view employees and their dependents
Assumptions:

    to see employee and their dependents , an employee should have one to many relationship with dependent, as an employee can have multiple dependents.
    I have created 2 Controller EmployeeController and DependentController which will deal with all employees and dependntsa relation ship.
    steps:
    "addEmployee" to add employee record in table.
    "add dependent to add dependent record in table.
    "addDependentToEmployee" api will allow use to add dependent for an employee.
    
    **Note** both controller implements interface to accomodate furture additional requirments.

    Employee table can save basic personal attributes of employee plus gross salary.
    Dependent table will save basic attributes of dependent, relation type to an employee and a foregin key anme employee_id which will connect both tabes.
   

Question: To Calculate the total salary after deducting amount as per given rules:
Assumptions:

    1. created SalaryController which will deal with all salary related tasks.
    2. Salary table will save total salary after deducting amount based on rules and employee_id to make a relationship with the associated employee.
    3."addSalaryToEmployee" will calculate salary by dividing it to 26 paycheck and deduct the amount as per the rules and save the record in DB. 
    4. "getEmployeeSalary" will allow us to get salary details of an employee by passing an employee id.
    to view an employee salary run "addSalaryToEmployee" api which will give result as total salary which is the salary after deducting based on rules.
    **Note**  controller implements interface to accomodate furture additional requirments.


    2. Calculation : to calculate final amount from gross salary , I have enhance RelationShip enum by adding one more field "Amount". it makes easy to make amount final get the amount         in for loop by comparing the relation type.

   Exception:
      Added exception handle class if user trying to get some data which doesnt exisit.

DataBase 

    Used relational DataBase to make a relation b/w employye , dependent and salary data.
    1. an employee can have many dependents.
    2. an employee can have multiple salaries.
    *spring boot jpa dependency will aloow cfreating tbale on the fly while starting application.

Question: Employee cant have both spouse and domestic partner 

         "addDependentToEmployee" will validate dependent before adding and throw an exception if trying to add both.
    
    
