public class QuarterlyEMIChart {

    public static void main(String[] args) {
        double principal = 10000;
        double annualRate = 12; // 12% annual
        int tenureMonths = 24;

        double monthlyRate = annualRate / 12 / 100;
        int monthsPerQuarter = 3;
        int numberOfQuarters = tenureMonths / monthsPerQuarter;

        // Effective quarterly rate
        double quarterlyRate = Math.pow(1 + monthlyRate, monthsPerQuarter) - 1;

        // Calculate quarterly EMI
        double quarterlyEMI = (principal * quarterlyRate) / (1 - Math.pow(1 + quarterlyRate, -numberOfQuarters));

        double balance = principal;

        System.out.println("\n================== EMI Chart (Month-wise) ==================");
        System.out.printf("%-6s %-17s %-10s %-10s %-15s %-17s%n",
                "Month", "Opening Balance", "Interest", "EMI", "Principal Paid", "Closing Balance");
        System.out.println("-------------------------------------------------------------");

        for (int month = 1; month <= tenureMonths; month++) {
            double openingBalance = balance;
            double interest = balance * monthlyRate;

            // Add monthly interest to balance
            balance += interest;

            double emi = 0;
            double principalPaid = 0;

            // Pay EMI every 3rd month
            if (month % 3 == 0) {
                emi = quarterlyEMI;
                principalPaid = emi - interest - getPreviousTwoMonthsInterest(balance, monthlyRate);
                balance -= principalPaid;
            }

            System.out.printf("%-6d ₹%-16.2f ₹%-9.2f ₹%-9.2f ₹%-14.2f ₹%-16.2f%n",
                    month, openingBalance, interest, emi, principalPaid, balance);
        }
        System.out.println("=============================================================\n");
    }

    // Calculate previous 2 months' interest (since EMI is quarterly)
    private static double getPreviousTwoMonthsInterest(double currentBalance, double monthlyRate) {
        // Approximate reverse calculation (for simplicity)
        double tempBalance = currentBalance;
        double interest3 = tempBalance * monthlyRate;
        tempBalance -= interest3;

        double interest2 = tempBalance * monthlyRate;
        tempBalance -= interest2;

        return interest2 + interest3;
    }
}
