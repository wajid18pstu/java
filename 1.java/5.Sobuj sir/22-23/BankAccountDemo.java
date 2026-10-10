/*
Write a Java program to create an abstract class BankAccount with abstract methods deposit() and
withdraw(). Create subclasses: SavingsAccount and CurrentAccount that extend the BankAccount
class and implement the respective methods to handle deposits and withdrawals for each account type.
*/
abstract class BankAccount {
    protected final String accountNumber;
    protected final String holderName;
    protected double balance;

    public BankAccount(String accountNumber, String holderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = initialBalance;
    }

    // Abstract methods: every account type must implement them in its own way
    public abstract void deposit(double amount);

    public abstract void withdraw(double amount);

    public double getBalance() {
        return balance;
    }

    public void displayInfo() {
        System.out.println(getClass().getSimpleName() + " | Account No: " + accountNumber
                + " | Holder: " + holderName
                + String.format(" | Balance: %.2f", balance));
    }
}

class SavingsAccount extends BankAccount {
    private static final double MINIMUM_BALANCE = 500;    // must always remain in the account

    public SavingsAccount(String accountNumber, String holderName, double initialBalance) {
        super(accountNumber, holderName, initialBalance);
        if (initialBalance < MINIMUM_BALANCE) {
            throw new IllegalArgumentException(
                    "A savings account needs an opening balance of at least " + MINIMUM_BALANCE);
        }
    }

    @Override
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }
        balance += amount;
        System.out.printf("Deposited %.2f into savings account. New balance: %.2f%n", amount, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
        }
        // A savings account cannot go below the minimum balance
        if (balance - amount < MINIMUM_BALANCE) {
            throw new IllegalArgumentException(String.format(
                    "Withdrawal denied: balance must stay at or above %.2f.", MINIMUM_BALANCE));
        }
        balance -= amount;
        System.out.printf("Withdrew %.2f from savings account. New balance: %.2f%n", amount, balance);
    }
}

class CurrentAccount extends BankAccount {
    private static final double OVERDRAFT_LIMIT = 10000;  // balance may go this far below zero

    public CurrentAccount(String accountNumber, String holderName, double initialBalance) {
        super(accountNumber, holderName, initialBalance);
    }

    @Override
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }
        balance += amount;
        System.out.printf("Deposited %.2f into current account. New balance: %.2f%n", amount, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
        }
        // A current account may be overdrawn, but only up to the overdraft limit
        if (balance - amount < -OVERDRAFT_LIMIT) {
            throw new IllegalArgumentException(String.format(
                    "Withdrawal denied: overdraft limit of %.2f exceeded.", OVERDRAFT_LIMIT));
        }
        balance -= amount;
        System.out.printf("Withdrew %.2f from current account. New balance: %.2f%n", amount, balance);
    }
}

public class BankAccountDemo {
    public static void main(String[] args) {
        // BankAccount references pointing to subclass objects (polymorphism)
        BankAccount savings = new SavingsAccount("SA-1001", "Rahim Uddin", 5000);
        BankAccount current = new CurrentAccount("CA-2001", "Karim Ahmed", 2000);

        System.out.println("----- Savings Account -----");
        savings.displayInfo();
        savings.deposit(1500);
        savings.withdraw(2000);
        try {
            savings.withdraw(4500);     // would drop below the minimum balance
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        savings.displayInfo();

        System.out.println();
        System.out.println("----- Current Account -----");
        current.displayInfo();
        current.deposit(1000);
        current.withdraw(8000);         // allowed: uses the overdraft
        System.out.printf("Balance after overdraft use: %.2f%n", current.getBalance());
        try {
            current.withdraw(6000);     // would exceed the overdraft limit
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        current.displayInfo();
    }
}
