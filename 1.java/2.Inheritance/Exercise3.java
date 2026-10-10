/*
3. Create a class named 'Member' having the following members:
Data members
1 - Name
2 - Age
3 - Phone number
4 - Address
5 – Salary
It also has a method named 'printSalary' which prints the salary of the members.
Two classes 'Employee' and 'Manager' inherits the 'Member' class. The 'Employee' and
'Manager' classes have data members 'specialization' and 'department' respectively. Now,
assign name, age, phone number, address and salary to an employee and a manager by
making an object of both of these classes and print the same.
*/
class Member {
    protected String name;
    protected int age;
    protected String phoneNumber;
    protected String address;
    protected double salary;

    public Member(String name, int age, String phoneNumber, String address, double salary) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.salary = salary;
    }

    public void printSalary() {
        System.out.println("Salary: " + salary);
    }

    public void printDetails() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Phone number: " + phoneNumber);
        System.out.println("Address: " + address);
        printSalary();
    }
}

class Employee extends Member {
    private String specialization;

    public Employee(String name, int age, String phoneNumber, String address,
                    double salary, String specialization) {
        super(name, age, phoneNumber, address, salary);
        this.specialization = specialization;
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Specialization: " + specialization);
    }
}

class Manager extends Member {
    private String department;

    public Manager(String name, int age, String phoneNumber, String address,
                   double salary, String department) {
        super(name, age, phoneNumber, address, salary);
        this.department = department;
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Department: " + department);
    }
}

public class Exercise3 {
    public static void main(String[] args) {
        Employee employee = new Employee("Rahim Uddin", 28, "01711000000",
                "Khulna", 35000, "Software Development");
        Manager manager = new Manager("Karim Ahmed", 40, "01811000000",
                "Dhaka", 80000, "Engineering");

        System.out.println("----- Employee -----");
        employee.printDetails();

        System.out.println();
        System.out.println("----- Manager -----");
        manager.printDetails();
    }
}
