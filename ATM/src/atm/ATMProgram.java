package atm;

import java.util.Scanner;

public class ATMProgram {
    private double balance;

    public ATMProgram(double initialBalance) {
        this.balance = initialBalance;
    }

    public void checkBalance() {
        System.out.println("Current balance: $" + balance);
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance. Withdrawal failed.");
        } else {
            balance -= amount;
            System.out.println("Successfully withdrawn $" + amount);
            checkBalance();
        }
    }

    public void transfer(double amount, String bank) {
        double transferFee = 0.0;

        if (bank.equalsIgnoreCase("same")) {
            transferFee = 0.0; // No fee for same bank transfer
        } else if (bank.equalsIgnoreCase("other")) {
            transferFee = 0.001 * amount; // 0.1% fee for other bank transfer
        } else {
            System.out.println("Invalid bank selection.");
            return;
        }

        double totalAmount = amount + transferFee;
        if (totalAmount > balance) {
            System.out.println("Insufficient balance. Transfer failed.");
        } else {
            balance -= totalAmount;
            System.out.println("Successfully transferred $" + amount + " to " + bank + " bank account.");
            System.out.println("Transfer fee: $" + transferFee);
            checkBalance();
        }
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Successfully deposited $" + amount);
        checkBalance();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATMProgram myATM = new ATMProgram(65000.0); // Initial balance is $1000

        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Transfer Money");
            System.out.println("4. Deposit/Receive Money");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    myATM.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    myATM.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to transfer: ");
                    double transferAmount = scanner.nextDouble();
                    System.out.print("Transfer to (same/other) bank: ");
                    String bank = scanner.next();
                    myATM.transfer(transferAmount, bank);
                    break;
                case 4:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    myATM.deposit(depositAmount);
                    break;
                case 5:
                    System.out.println("Exiting. Thank you for using the ATM!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
