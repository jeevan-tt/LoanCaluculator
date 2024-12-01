package org.example;

public class LoanRepaymentSchedule {

    public static void main(String[] args) {
        // Loan details
        double loanAmount = 20000; // Principal
        double annualInterestRate = 15; // Annual interest rate in percentage
        int tenureMonths = 24; // Loan tenure in months

        // EMI calculation
        double monthlyInterestRate = annualInterestRate / 12 / 100;
        double emi = (loanAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, tenureMonths))
                / (Math.pow(1 + monthlyInterestRate, tenureMonths) - 1);

        // Print header
        System.out.printf("%-15s%-20s%-20s%-20s%-20s%n", "Inst. No.", "Outstanding Principal", "Principal", "Interest", "EMI");
        System.out.println("=================================================================================");

        double balance = loanAmount;

        for (int i = 1; i <= tenureMonths; i++) {
            double interest = balance * monthlyInterestRate;
            double principal = emi - interest;
            balance -= principal;

            // Print monthly details
            System.out.printf("%-15d%-20.2f%-20.2f%-20.2f%-20.2f%n", i, balance + principal, principal, interest, emi);
        }
    }
}
