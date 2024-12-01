package org.example;

import java.util.Scanner;

public class LoanRepaymentCustomEMI {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input loan details
        System.out.print("Enter loan amount (Principal): ");
        double loanAmount = scanner.nextDouble();

        System.out.print("Enter annual interest rate (in %): ");
        double annualInterestRate = scanner.nextDouble();

        double monthlyInterestRate = annualInterestRate / 12 / 100;
        double balance = loanAmount;

        System.out.println("\nEnter EMI for each month (Enter 0 to exit):");
        System.out.printf("%-10s%-20s%-20s%-20s%-20s%n", "Month", "Outstanding Balance", "Principal Paid", "Interest Paid", "EMI");
        System.out.println("===========================================================================================");

        int month = 0;

        while (balance > 0) {
            month++;
            double interest = balance * monthlyInterestRate;

            // Prompt user for EMI input
            System.out.printf("Enter EMI for month %d: ", month);
            double emi = scanner.nextDouble();

            if (emi == 0) {
                System.out.println("Exiting early. Loan not fully repaid.");
                break;
            }

            // Validate EMI
            if (emi < interest) {
                System.out.printf("Warning: EMI (%.2f) is less than the interest (%.2f). Balance will increase.%n", emi, interest);
            }

            // Calculate principal paid
            double principal = emi - interest;

            // Adjust for final payment if EMI is more than balance
            if (emi >= balance + interest) {
                emi = balance + interest;
                principal = balance;
            }

            balance -= principal;

            // Print monthly details
            System.out.printf("%-10d%-20.2f%-20.2f%-20.2f%-20.2f%n", month, balance + principal, principal, interest, emi);

            if (balance <= 0) {
                System.out.println("\nLoan fully repaid!");
                break;
            }
        }

        scanner.close();
    }
}
