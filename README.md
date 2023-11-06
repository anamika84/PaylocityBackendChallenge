# PaylocityBackendChallenge
Requirements

Able to view employees and their dependents
  Solution: 
  I have created 2 Controller EmployeeController and DependentController which will deal with all employees and dependntsa relation ship.
    "addDependentToEmployee" api will allow use to add dependent for an employee.
    "removeDependentFromEmployee" api will allow us to remove any dependent from an employee

  * both controllers implementing interface and override method with the thought of accomodate the additional requirement in future.

To Calculate the total salary after deducting amount as per given rules I have created SalaryController which will deal with al salary related tasks.
  "getEmployeeSalary" will allow us to get salary details of an employee by passing an employee id.
  "addSalaryToEmployee" will calculate salary by dividing it to 26 paycheck and deduct the amount as per the following rules and save the record in DB

  To save the data in Databse I have create 3 tables 
