package org.example;

import java.util.Scanner;

public class HalfYearlyLoanRepayment {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input details
        System.out.print("Enter loan amount (Principal): ");
        double loanAmount = scanner.nextDouble();
        
        System.out.print("Enter annual interest rate (in %): ");
        double annualInterestRate = scanner.nextDouble();
        
        System.out.print("Enter loan tenure (in months): ");
        int tenureMonths = scanner.nextInt();

        // Calculate half-yearly EMI
        double halfYearlyInterestRate = annualInterestRate / 2 / 100;
        int tenureHalfYears = tenureMonths / 6;
        double emi = (loanAmount * halfYearlyInterestRate * Math.pow(1 + halfYearlyInterestRate, tenureHalfYears))
                     / (Math.pow(1 + halfYearlyInterestRate, tenureHalfYears) - 1);

        System.out.printf("\nHalf-Yearly EMI for the loan is: %.2f%n", emi);

        // Print loan chart header
        System.out.println("\nLoan Repayment Chart:");
        System.out.printf("%-10s%-20s%-20s%-20s%-20s%n", "Period", "Outstanding Balance", "Principal Paid", "Interest Paid", "EMI");
        System.out.println("===========================================================================================");

        // Generate chart for the full tenure
        double balance = loanAmount;

        for (int period = 1; period <= tenureHalfYears; period++) {
            double interest = balance * halfYearlyInterestRate;
            double principal = emi - interest;
            balance -= principal;

            // Print half-yearly details
            System.out.printf("%-10d%-20.2f%-20.2f%-20.2f%-20.2f%n", period, balance + principal, principal, interest, emi);
        }

        scanner.close();
    }
}
