import java.util.ArrayList;
import java.util.Scanner;

public class GradeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Double> grades = new ArrayList<>();
        
        System.out.println("=== Student Grade Tracker ===");
        System.out.println("Enter student grades one by one. Type '-1' when you are finished.");
        
        
        while (true) {
            System.out.print("Enter grade: ");
            if (scanner.hasNextDouble()) {
                double grade = scanner.nextDouble();
                
                if (grade == -1) {
                    break;
                }
                
                if (grade < 0 || grade > 100) {
                    System.out.println("Invalid entry. Please enter a grade between 0 and 100.");
                } else {
                    grades.add(grade);
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            }
        }
        
        if (grades.isEmpty()) {
            System.out.println("\nNo grades were entered. Exiting program.");
        } else {
            displaySummaryReport(grades);
        }
        
        scanner.close();
    }
    
    private static void displaySummaryReport(ArrayList<Double> grades) {
        double total = 0;
        double highest = grades.get(0);
        double lowest = grades.get(0);
        
        for (double grade : grades) {
            total += grade;
            if (grade > highest) highest = grade;
            if (grade < lowest) lowest = grade;
        }
        
        double average = total / grades.size();
        
        System.out.println("\n============================= ");
        System.out.println("       SUMMARY REPORT        ");
        System.out.println("============================= ");
        System.out.printf("Total Students Tracked : %d\n", grades.size());
        System.out.printf("Average Score          : %.2f\n", average);
        System.out.printf("Highest Score          : %.2f\n", highest);
        System.out.printf("Lowest Score           : %.2f\n", lowest);
        System.out.println("============================= ");
    }
}
