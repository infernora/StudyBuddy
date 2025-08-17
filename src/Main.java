import service.QuizManager;
import model.Question;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        QuizManager manager = new QuizManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Study Quiz Manager ===");
            System.out.println("1. Add a question");
            System.out.println("2. Take a quiz");
            System.out.println("3. View past results");
            System.out.println("4. Export questions to PDF");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("Enter question text: ");
                    String text = scanner.nextLine().trim();

                    System.out.print("Enter options (comma-separated, leave empty for open-ended): ");
                    String optionsInput = scanner.nextLine().trim();
                    List<String> options = optionsInput.isEmpty() ? List.of()
                            :  Arrays.stream(optionsInput.split(","))
                            .map(String::trim)
                            .collect(Collectors.toList());

                    System.out.print("Enter correct answer: ");
                    String answer = scanner.nextLine().trim();

                    System.out.print("Enter level (Easy/Medium/Hard): ");
                    String level = scanner.nextLine().trim();

                    System.out.print("Enter category: ");
                    String category = scanner.nextLine().trim();

                    Question q = new Question(text, options, answer, level, category);
                    manager.addQuestion(q);
                    System.out.println("Question added successfully!");
                    break;

                case "2":
                    System.out.print("Enter quiz level: ");
                    String quizLevel = scanner.nextLine().trim();

                    System.out.print("Enter quiz category: ");
                    String quizCategory = scanner.nextLine().trim();

                    manager.startQuiz(quizLevel, quizCategory);
                    break;

                case "3":
                    manager.showResults();
                    break;

                case "4":

                    
                    System.out.println("\n=== Question Summary ===");
                    
                    Map<String, Map<String, Long>> summary = manager.getQuestions().stream()
                        .collect(Collectors.groupingBy(Question::getLevel,
                                Collectors.groupingBy(Question::getCategory, Collectors.counting())));

                    if (summary.isEmpty()) {
                        System.out.println("No questions available.");
                        break;
                    }

                    for (String lvl : summary.keySet()) {
                        System.out.println("Level: " + lvl);
                        Map<String, Long> catMap = summary.get(lvl);
                        for (String cat : catMap.keySet()) {
                            System.out.println("   Category: " + cat + " -> " + catMap.get(cat) + " questions");
                        }
                    }
                    
                    System.out.print("Enter level to filter (leave empty for all): ");
                    String filterLevel = scanner.nextLine().trim();

                    System.out.print("Enter category to filter (leave empty for all): ");
                    String filterCategory = scanner.nextLine().trim();

                    System.out.print("Enter number of questions to export: ");
                    int numberToExport = 0;
                    while (true) {
                        try {
                            numberToExport = Integer.parseInt(scanner.nextLine().trim());
                            if (numberToExport > 0) break;
                            System.out.print("Please enter a positive number: ");
                        } catch (NumberFormatException e) {
                            System.out.print("Invalid number. Try again: ");
                        }
                    }

                    System.out.print("Enter PDF filename (e.g., quiz.pdf): ");
                    String filename = scanner.nextLine().trim();

                    manager.exportQuestionsToPDFByFilter(filename, filterLevel, filterCategory, numberToExport);
                    break;

                case "5":
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
