import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FinancialQuarterSimplified {

    public static String getAllowedQuarters(Timestamp sanctionTimestamp) {
        LocalDate sanctionDate = sanctionTimestamp.toLocalDateTime().toLocalDate();
        LocalDate today = LocalDate.now();

        int sanctionFY = getFinancialYearStart(sanctionDate);
        int currentFY = getFinancialYearStart(today);

        if (currentFY > sanctionFY) {
            return "Q1,Q2,Q3,Q4";
        }

        int sanctionQ = getQuarter(sanctionDate);
        int currentQ = getQuarter(today);

        // List of all quarters
        List<String> allQuarters = List.of("Q1", "Q2", "Q3", "Q4");
        List<String> result = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            int qNum = i + 1;
            if (qNum > currentQ && qNum != sanctionQ) {
                result.add("Q" + qNum);
            }
        }

        return result.isEmpty() ? "No eligible quarters" : String.join(",", result);
    }

    private static int getFinancialYearStart(LocalDate date) {
        return date.getMonthValue() >= 4 ? date.getYear() : date.getYear() - 1;
    }

    private static int getQuarter(LocalDate date) {
        int m = date.getMonthValue();
        if (m >= 4 && m <= 6) return 1;
        else if (m >= 7 && m <= 9) return 2;
        else if (m >= 10 && m <= 12) return 3;
        else return 4; // Jan - Mar
    }

    // Example usage
    public static void main(String[] args) {
        Timestamp sanctionTs = Timestamp.valueOf("2025-05-15 00:00:00"); // May 15, 2025
        System.out.println(getAllowedQuarters(sanctionTs)); // Example output
    }
}
