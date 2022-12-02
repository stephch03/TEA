# Timesheet Entry Application

## *Overview*

I know a small local business which still manually calculates the number of hours worked by each employee on paper.
It is inefficient and also prone to calculation errors. In this day and age, the process should be digitalized.

This **timesheet entry application** will allow company managers to keep track of the number of hours employees
have worked. Management can adjust employees' hours, sick days, and vacation time as appropriate.
Upon management's approval of the hours, the application will summarize the number of hours worked by each employee
so that this information can be forwarded to payroll.

## *User Stories*
- As a user, I want to be able to create a new employee and add it to an employee database.
- As a user, I want to be able to select an employee and view the number of hours they worked.
- As a user, I want to be able to edit the employee's hours.
- As a user, I want to be able to remove an employee from the database.
- As a user, I want to be able to print the timesheet.
- As a user, I want to be able to input the pay period.
- As a user, when I select the quit option from the application menu, I want to be reminded to save my employee
database to file and have the option to do so or not.
- As a user, when I start the application, I want to be given the option to load my employee database from file.

## *Future User Stories*
- As a user, I want to be able to login as an employee.
- As a user, I want to be able to login as a manager.
- As a user, I want to be able to approve of an employee's hours.
- As a user, I want to be able to indicate that I took a sick day.
- As a user, I want to be able to indicate that I took a vacation day.

## *Instructions for Grader*

- Note: you may need to click the maximize window button first to load all the buttons.
- You can add an employee by clicking the Add Employee button and typing in the name of the new Employee by FirstName
LastName.
- You can remove an employee by clicking the Remove Employee button and selecting the name of the employee.
- You can change the name of the employee by clicking the Change Employee Name button and selecting the name of the
employee and typing in the new name.

- My visual component is the teapot icon for my application which can be found in the top left corner of the window and as
the desktop icon. TEA stands for Timesheet Entry Application.
- You can save the state of my application by closing the window and selecting yes when prompted to save.
- You can reload the state of my application by selecting yes when prompted to load upon running the application.

## *Phase 4: Task 2*
Sample Event log:

Wed Nov 30 12:42:04 PST 2022 Steph Ho was added to the employee database.

Wed Nov 30 12:42:09 PST 2022 Candy Floss was added to the employee database.

Wed Nov 30 12:42:13 PST 2022 Candy Floss was removed from the employee database.

Wed Nov 30 12:42:19 PST 2022 Steph Ho was changed to Stephanie Ho.

Wed Nov 30 12:42:27 PST 2022 Stephanie Ho worked 6 hours on Day 1.

Wed Nov 30 12:42:32 PST 2022 Stephanie Ho worked 7 hours on Day 2.

Wed Nov 30 12:42:40 PST 2022 Stephanie Ho's Day 2 hours were changed from 7 to 6.

## *Phase 4: Task 3*

- In the console UI, TimesheetApp class, I have many
recurring REQUIRE clauses such as for the employee database to not be empty. I would want to refactor this into
an exception that I can throw and handle to decrease duplicated code and to improve coupling. 
- I'd like to refactor my printTimesheet() and changeDate() method from EmployeeDatabase into a new class since the two
methods are more specific to the timesheet. All the other methods in EmployeeDatabase are more specific to the database,
which contains information regarding all the employees currently in the database. This will increase the cohesion
of my classes.