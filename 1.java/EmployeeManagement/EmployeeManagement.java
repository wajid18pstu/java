/*
Create a Java program to implement an `Employee` management system demonstrating the major characteristics of
Object-Oriented Programming. Create an abstract class `Person` containing an abstract method `calculateSalary()`, and
create an `Employee` class that extends `Person`. The `Employee` class should contain private variables `employeeId`,
`name`, `department`, `basicSalary`, and `bonus`, along with appropriate getter and setter methods and methods
`calculateSalary()` and `displayInfo()`. Create at least three employee objects and demonstrate different operations.
Explain how encapsulation and data hiding are achieved using private variables and public methods. Add a default
constructor, parameterized constructor, and copy constructor to `Employee`. Use the `this` keyword appropriately and
create objects using all three constructors. Overload at least one method, such as `calculateSalary()` or `setSalary()`,
using different parameter lists. Explain the usefulness of constructor overloading and method overloading. Implement
`calculateSalary()` in `Employee` and override the appropriate method from `Person` to demonstrate inheritance,
abstraction, and method overriding. Use a parent-class `Person` reference to refer to an `Employee` object and
demonstrate runtime polymorphism. Demonstrate the use of the `super` keyword where appropriate. Add a static variable
`totalEmployees` to count the total number of Employee objects created and create a static method
`showTotalEmployees()` to display the count. Validate employee information so that `basicSalary` and `bonus` cannot be
negative. Create a custom `InvalidSalaryException` and throw it when an invalid salary or bonus is entered. Handle the
exception using `try-catch` and demonstrate both valid and invalid salary values in the `main()` method. Finally, briefly
explain how the program demonstrates class and objects, encapsulation, data hiding, abstraction, inheritance,
polymorphism, constructor and method overloading, method overriding, `this`, `super`, static members, validation, and
exception handling.
*/

// Custom checked exception, thrown when a salary or bonus is negative
class InvalidSalaryException extends Exception {
    public InvalidSalaryException(String message) {
        super(message);
    }
}

// Abstraction: Person declares WHAT must be done (calculateSalary) but not HOW
abstract class Person {

    public Person() {
        // Called through super() every time an Employee is created
    }

    public abstract double calculateSalary();

    public void displayInfo() {
        System.out.println("Person type: " + getClass().getSimpleName());
    }
}

// Inheritance: Employee IS-A Person
class Employee extends Person {

    // Encapsulation / data hiding: fields are private, so they can only be
    // read or changed through the public getters and setters below
    private int employeeId;
    private String name;
    private String department;
    private double basicSalary;
    private double bonus;

    // Static member: shared by the whole class, not by individual objects
    private static int totalEmployees = 0;

    // ---------- Constructors (constructor overloading) ----------

    // 1. Default constructor
    public Employee() {
        super();
        this.employeeId = 0;
        this.name = "Unknown";
        this.department = "Not assigned";
        this.basicSalary = 0;
        this.bonus = 0;
        totalEmployees++;
    }

    // 2. Parameterized constructor (validates salary and bonus)
    public Employee(int employeeId, String name, String department,
                    double basicSalary, double bonus) throws InvalidSalaryException {
        super();
        validateAmount(basicSalary, "Basic salary");
        validateAmount(bonus, "Bonus");
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.basicSalary = basicSalary;
        this.bonus = bonus;
        totalEmployees++;   // counted only if validation passed
    }

    // 3. Copy constructor: builds a new Employee from an existing one
    public Employee(Employee other) {
        super();
        this.employeeId = other.employeeId;
        this.name = other.name;
        this.department = other.department;
        this.basicSalary = other.basicSalary;
        this.bonus = other.bonus;
        totalEmployees++;
    }

    // ---------- Validation ----------
    private static void validateAmount(double amount, String label) throws InvalidSalaryException {
        if (amount < 0) {
            throw new InvalidSalaryException(label + " cannot be negative: " + amount);
        }
    }

    // ---------- Getters and setters ----------
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) throws InvalidSalaryException {
        validateAmount(basicSalary, "Basic salary");
        this.basicSalary = basicSalary;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) throws InvalidSalaryException {
        validateAmount(bonus, "Bonus");
        this.bonus = bonus;
    }

    // ---------- Method overloading: setSalary ----------
    public void setSalary(double basicSalary) throws InvalidSalaryException {
        setBasicSalary(basicSalary);               // updates only the basic salary
    }

    public void setSalary(double basicSalary, double bonus) throws InvalidSalaryException {
        validateAmount(basicSalary, "Basic salary");   // validate both first so that
        validateAmount(bonus, "Bonus");                // nothing changes if one is invalid
        this.basicSalary = basicSalary;
        this.bonus = bonus;
    }

    // ---------- Method overriding: implements Person's abstract method ----------
    @Override
    public double calculateSalary() {
        return basicSalary + bonus;
    }

    // ---------- Method overloading: calculateSalary with a tax percentage ----------
    public double calculateSalary(double taxPercent) {
        if (taxPercent < 0 || taxPercent > 100) {
            throw new IllegalArgumentException("Tax percent must be between 0 and 100");
        }
        double gross = calculateSalary();
        return gross - (gross * taxPercent / 100);
    }

    // Overrides Person.displayInfo() and reuses it with super
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Employee ID : " + employeeId);
        System.out.println("Name        : " + name);
        System.out.println("Department  : " + department);
        System.out.printf("Basic Salary: %.2f%n", basicSalary);
        System.out.printf("Bonus       : %.2f%n", bonus);
        System.out.printf("Total Salary: %.2f%n", calculateSalary());
    }

    // ---------- Static method ----------
    public static void showTotalEmployees() {
        System.out.println("Total employees created: " + totalEmployees);
    }
}

public class EmployeeManagement {
    public static void main(String[] args) {

        // ---- Object 1: default constructor, then data set through setters ----
        System.out.println("===== Employee 1 (default constructor + setters) =====");
        Employee emp1 = new Employee();
        emp1.setEmployeeId(101);
        emp1.setName("Rahim Uddin");
        emp1.setDepartment("IT");
        try {
            emp1.setSalary(40000, 5000);        // overloaded setSalary(basic, bonus)
        } catch (InvalidSalaryException e) {
            System.out.println("Error: " + e.getMessage());
        }
        emp1.displayInfo();

        // ---- Object 2: parameterized constructor (valid values) ----
        System.out.println("\n===== Employee 2 (parameterized constructor) =====");
        Employee emp2 = null;
        try {
            emp2 = new Employee(102, "Karim Ahmed", "Finance", 55000, 8000);
        } catch (InvalidSalaryException e) {
            System.out.println("Error: " + e.getMessage());
        }
        emp2.displayInfo();

        // ---- Object 3: copy constructor ----
        System.out.println("\n===== Employee 3 (copy constructor of Employee 2) =====");
        Employee emp3 = new Employee(emp2);
        emp3.setEmployeeId(103);                // changing the copy does not affect emp2
        emp3.setName("Nusrat Jahan");
        emp3.displayInfo();

        // ---- Overloaded calculateSalary ----
        System.out.println("\n===== Method overloading =====");
        System.out.printf("Employee 2 gross salary      : %.2f%n", emp2.calculateSalary());
        System.out.printf("Employee 2 after 10%% tax     : %.2f%n", emp2.calculateSalary(10));

        // ---- Runtime polymorphism: parent reference, child object ----
        System.out.println("\n===== Runtime polymorphism =====");
        Person person = emp2;                   // Person reference -> Employee object
        System.out.printf("Salary via Person reference  : %.2f%n", person.calculateSalary());
        person.displayInfo();                   // Employee's version runs at runtime

        // ---- Validation: invalid values ----
        System.out.println("\n===== Validation with InvalidSalaryException =====");

        try {
            Employee bad = new Employee(104, "Invalid Person", "HR", -30000, 2000);
            bad.displayInfo();
        } catch (InvalidSalaryException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        try {
            emp1.setBonus(-500);
        } catch (InvalidSalaryException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        try {
            emp1.setSalary(-1000);
        } catch (InvalidSalaryException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        // ---- Valid update after the failed attempts ----
        try {
            emp1.setSalary(45000);
            System.out.printf("Valid update done. Employee 1 new total salary: %.2f%n",
                    emp1.calculateSalary());
        } catch (InvalidSalaryException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        // ---- Static method ----
        System.out.println();
        Employee.showTotalEmployees();          // 3 (the invalid object was never created)
    }
}

/*
===================== Explanations =====================

Encapsulation and data hiding:
  employeeId, name, department, basicSalary and bonus are private, so code outside Employee cannot touch
  them directly. They are reached only through public getters and setters, and the salary setters validate
  the value before storing it. The data is hidden and always kept in a valid state.

Usefulness of constructor overloading:
  One class can be created in several ways: with defaults (default constructor), with full data
  (parameterized constructor) or as a duplicate of another object (copy constructor).

Usefulness of method overloading:
  The same method name works with different parameters. calculateSalary() gives the gross salary while
  calculateSalary(taxPercent) gives the salary after tax. setSalary(basic) and setSalary(basic, bonus)
  update one or both values. This keeps the API simple and readable.

How the program demonstrates each concept:
  - Class and objects: Employee is a class; emp1, emp2, emp3 are objects created from it.
  - Encapsulation / data hiding: private fields with public getters/setters.
  - Abstraction: Person is abstract and declares calculateSalary() without an implementation.
  - Inheritance: Employee extends Person and reuses its members.
  - Polymorphism: a Person reference holds an Employee object, and the Employee version of
    calculateSalary() and displayInfo() runs at runtime.
  - Constructor and method overloading: three Employee constructors; calculateSalary() and setSalary()
    each have more than one version.
  - Method overriding: Employee overrides calculateSalary() and displayInfo() from Person.
  - this: separates fields from parameters (this.name = name) and refers to the current object.
  - super: super() calls the Person constructor; super.displayInfo() reuses the parent's output.
  - Static members: totalEmployees and showTotalEmployees() belong to the class and count all objects.
  - Validation: negative basicSalary or bonus is rejected before it is stored.
  - Exception handling: invalid values throw InvalidSalaryException, which main() handles with try-catch.
*/
