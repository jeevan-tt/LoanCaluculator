import java.util.Scanner;

public class YearlyRepaymentChart {

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
        double outstandingBalance = loanAmount;
        double totalInterestPaid = 0;
        double totalPrincipalPaid = 0;

        System.out.printf("\n%-10s%-20s%-20s%-20s%-20s%n", "Year", "Outstanding Balance", "Principal Paid", "Interest Paid", "Total Paid");
        System.out.println("=========================================================================================");

        for (int month = 1; month <= tenureMonths; month++) {
            double interest = outstandingBalance * monthlyInterestRate;
            double principal = emi - interest;
            outstandingBalance -= principal;

            totalInterestPaid += interest;
            totalPrincipalPaid += principal;

            // Print yearly summary
            if (month % 12 == 0 || month == tenureMonths) {
                int year = (month - 1) / 12 + 1;
                double yearlyTotalPaid = totalPrincipalPaid + totalInterestPaid;
                System.out.printf("%-10d%-20.2f%-20.2f%-20.2f%-20.2f%n", year, outstandingBalance, totalPrincipalPaid, totalInterestPaid, yearlyTotalPaid);

                // Reset yearly totals
                totalPrincipalPaid = 0;
                totalInterestPaid = 0;
            }
        }

        scanner.close();
    }
}
