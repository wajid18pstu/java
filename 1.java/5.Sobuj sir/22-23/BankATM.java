import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
Write a Java program to create a class called "Bank" with a collection of accounts and methods
to add and remove accounts, and to deposit and withdraw money. Also define a class called
"Account" to maintain account details of a particular customer. Add some constraint like banlance
will not zero or negative. Also  Creating an Account class which has deposit () and withdraw ()
methods. You should also making the program as ATM menu interface.  Beside Account
information as private instance variables.
*/

// Maintains the details of one customer's account
class Account {
    // Account information as private instance variables
    private final int accountNumber;
    private final String holderName;
    private double balance;

    // Constraint: the balance can never be zero or negative
    public Account(int accountNumber, String holderName, double initialBalance) {
        if (initialBalance <= 0) {
            throw new IllegalArgumentException("Initial balance must be greater than zero.");
        }
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = initialBalance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
        }
        // Constraint: after withdrawing, the balance must still be above zero
        if (balance - amount <= 0) {
            throw new IllegalArgumentException(
                    "Withdrawal denied: the balance cannot become zero or negative.");
        }
        balance -= amount;
    }

    @Override
    public String toString() {
        return String.format("Account No: %d | Name: %s | Balance: %.2f",
                accountNumber, holderName, balance);
    }
}

// Holds a collection of accounts and works on them
class Bank {
    private final List<Account> accounts = new ArrayList<>();

    public Account findAccount(int accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    public void addAccount(Account account) {
        if (findAccount(account.getAccountNumber()) != null) {
            throw new IllegalArgumentException(
                    "An account with number " + account.getAccountNumber() + " already exists.");
        }
        accounts.add(account);
    }

    public void removeAccount(int accountNumber) {
        Account account = findAccount(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account " + accountNumber + " was not found.");
        }
        accounts.remove(account);
    }

    public void deposit(int accountNumber, double amount) {
        getExistingAccount(accountNumber).deposit(amount);
    }

    public void withdraw(int accountNumber, double amount) {
        getExistingAccount(accountNumber).withdraw(amount);
    }

    public double getBalance(int accountNumber) {
        return getExistingAccount(accountNumber).getBalance();
    }

    private Account getExistingAccount(int accountNumber) {
        Account account = findAccount(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account " + accountNumber + " was not found.");
        }
        return account;
    }
}

// ATM menu interface
public class BankATM {
    private static final Scanner sc = new Scanner(System.in);

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid amount.");
            }
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("========== ATM MENU ==========");
        System.out.println("1. Create (add) account");
        System.out.println("2. Remove account");
        System.out.println("3. Deposit money");
        System.out.println("4. Withdraw money");
        System.out.println("5. Check balance");
        System.out.println("6. Exit");
        System.out.println("==============================");
    }

    public static void main(String[] args) {
        Bank bank = new Bank();
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            try {
                switch (choice) {
                    case 1: {
                        int number = readInt("Enter account number: ");
                        System.out.print("Enter account holder name: ");
                        String name = sc.nextLine().trim();
                        double initial = readDouble("Enter initial balance: ");
                        bank.addAccount(new Account(number, name, initial));
                        System.out.println("Account created successfully.");
                        break;
                    }
                    case 2: {
                        int number = readInt("Enter account number to remove: ");
                        bank.removeAccount(number);
                        System.out.println("Account removed successfully.");
                        break;
                    }
                    case 3: {
                        int number = readInt("Enter account number: ");
                        double amount = readDouble("Enter amount to deposit: ");
                        bank.deposit(number, amount);
                        System.out.printf("Deposit successful. New balance: %.2f%n",
                                bank.getBalance(number));
                        break;
                    }
                    case 4: {
                        int number = readInt("Enter account number: ");
                        double amount = readDouble("Enter amount to withdraw: ");
                        bank.withdraw(number, amount);
                        System.out.printf("Withdrawal successful. New balance: %.2f%n",
                                bank.getBalance(number));
                        break;
                    }
                    case 5: {
                        int number = readInt("Enter account number: ");
                        System.out.printf("Current balance: %.2f%n", bank.getBalance(number));
                        break;
                    }
                    case 6:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please select 1-6.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
