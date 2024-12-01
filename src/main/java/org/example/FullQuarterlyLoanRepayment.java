package org.example;

import java.util.Scanner;

public class FullQuarterlyLoanRepayment {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input details
        System.out.print("Enter loan amount (Principal): ");
        double loanAmount = scanner.nextDouble();
        
        System.out.print("Enter annual interest rate (in %): ");
        double annualInterestRate = scanner.nextDouble();
        
        System.out.print("Enter loan tenure (in months): ");
        int tenureMonths = scanner.nextInt();

        // Calculate quarterly EMI
        double quarterlyInterestRate = annualInterestRate / 4 / 100;
        int tenureQuarters = tenureMonths / 3; // Convert months to quarters
        double emi = (loanAmount * quarterlyInterestRate * Math.pow(1 + quarterlyInterestRate, tenureQuarters))
                     / (Math.pow(1 + quarterlyInterestRate, tenureQuarters) - 1);

        System.out.printf("\nQuarterly EMI for the loan is: %.2f%n", emi);

        // Print loan chart header
        System.out.println("\nLoan Repayment Chart:");
        System.out.printf("%-10s%-20s%-20s%-20s%-20s%n", "Quarter", "Outstanding Balance", "Principal Paid", "Interest Paid", "EMI");
        System.out.println("===========================================================================================");

        // Generate chart for the full tenure
        double balance = loanAmount;

        for (int quarter = 1; quarter <= tenureQuarters; quarter++) {
            double interest = balance * quarterlyInterestRate;
            double principal = emi - interest;
            balance -= principal;

            // Print quarterly details
            System.out.printf("%-10d%-20.2f%-20.2f%-20.2f%-20.2f%n", quarter, balance + principal, principal, interest, emi);
        }

        scanner.close();
    }
}
