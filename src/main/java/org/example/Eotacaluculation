import java.util.Scanner;

public class EndOfTermLoanChart {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input loan details
        System.out.print("Enter loan amount (Principal): ");
        double loanAmount = scanner.nextDouble();

        System.out.print("Enter annual interest rate (in %): ");
        double annualInterestRate = scanner.nextDouble();

        System.out.print("Enter loan tenure (in months): ");
        int tenureMonths = scanner.nextInt();

        // Calculate EMI
        double monthlyInterestRate = annualInterestRate / 12 / 100;
        double emi = (loanAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, tenureMonths)) /
                     (Math.pow(1 + monthlyInterestRate, tenureMonths) - 1);

        System.out.printf("\nFixed EMI: %.2f%n", emi);

        // Initialize variables for the repayment chart
        double totalInterestPaid = 0;
        double outstandingBalance = loanAmount;

        System.out.printf("%-10s%-20s%-20s%-20s%-20s%n", "Month", "Outstanding Balance", "Principal Paid", "Interest Paid", "EMI");
        System.out.println("===========================================================================================");

        for (int month = 1; month <= tenureMonths; month++) {
            double interest = outstandingBalance * monthlyInterestRate;
            double principal = emi - interest;
            outstandingBalance -= principal;

            totalInterestPaid += interest;

            // Print monthly details
            System.out.printf("%-10d%-20.2f%-20.2f%-20.2f%-20.2f%n", month, outstandingBalance + principal, principal, interest, emi);
        }

        // Print loan summary
        double totalRepayment = loanAmount + totalInterestPaid;
        System.out.println("\nLoan Summary:");
        System.out.printf("Total Interest Paid: %.2f%n", totalInterestPaid);
        System.out.printf("Total Repayment Amount: %.2f%n", totalRepayment);

        scanner.close();
    }
}
