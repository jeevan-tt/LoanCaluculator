import java.text.DecimalFormat;

public class QuarterlyToMonthlyEMI {

    public static void main(String[] args) {
        double principal = 10000;
        double annualRate = 12; // 12% annual interest
        int tenureMonths = 24;

        double monthlyRate = annualRate / 12 / 100;
        int monthsPerQuarter = 3;
        int numberOfQuarters = tenureMonths / monthsPerQuarter;

        // Calculate quarterly compounded rate
        double quarterlyRate = Math.pow(1 + monthlyRate, monthsPerQuarter) - 1;

        // Calculate quarterly EMI
        double quarterlyEMI = (principal * quarterlyRate) / 
                              (1 - Math.pow(1 + quarterlyRate, -numberOfQuarters));

        System.out.println("Quarterly EMI: " + format(quarterlyEMI));
        System.out.println("\nMonth | Opening Principal | Interest | Principal Paid | EMI | Closing Balance");

        double balance = principal;

        for (int month = 1; month <= tenureMonths; month++) {
            double openingBalance = balance;
            double interest = balance * monthlyRate;
            balance += interest;

            double emi = 0;
            double principalPaid = 0;

            if (month % 3 == 0) {
                // Recalculate 3-month interest
                double totalInterest = 0;
                double tempBalance = openingBalance;

                for (int i = 0; i < 3; i++) {
                    double tempInterest = tempBalance * monthlyRate;
                    totalInterest += tempInterest;
                    tempBalance += tempInterest;
                }

                emi = quarterlyEMI;
                principalPaid = emi - totalInterest;
                balance -= principalPaid;
            }

            System.out.printf("%5d | %18s | %8s | %14s | %5s | %15s%n",
                    month,
                    format(openingBalance),
                    format(interest),
                    format(principalPaid),
                    format(emi),
                    format(balance));
        }
    }

    private static String format(double value) {
        return new DecimalFormat("0.00").format(value);
    }
}
