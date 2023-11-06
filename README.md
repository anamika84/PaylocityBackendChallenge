# PaylocityBackendChallenge
Requirements

Question: Able to view employees and their dependents

    I have created 2 Controller EmployeeController and DependentController which will deal with all employees and dependntsa relation ship.
    "addDependentToEmployee" api will allow use to add dependent for an employee.
    "removeDependentFromEmployee" api will allow us to remove any dependent from an employee


Question: To Calculate the total salary after deducting amount as per given rules:

    I have created SalaryController which will deal with al salary related tasks.
    "getEmployeeSalary" will allow us to get salary details of an employee by passing an employee id.
    "addSalaryToEmployee" will calculate salary by dividing it to 26 paycheck and deduct the amount as per the following rules and save the record in DB

DataBase 

    I have used relational DataBase to make a relation b/w employye , dependent and salary data.
    an employee can have many dependents.
    an employee can have multiple salaries.

    spring boot jpa dependency will aloow cfreating tbale on the fly while starting application.
    
    
