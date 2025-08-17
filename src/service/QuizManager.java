package service;

import model.Question;
import model.Result;

import java.util.*;
import java.time.LocalDateTime;
import util.FileHandler;

public class QuizManager {
    private List<Question> questions;
    private List<Result> results;
    private Scanner scanner;

    private final String questionsFile = "data/questions.txt";
    private final String resultsFile = "data/results.txt"; 


    public QuizManager() {
        this.scanner = new Scanner(System.in);
        this.questions = util.FileHandler.loadQuestions(questionsFile);
        this.results = util.FileHandler.loadResults(resultsFile);
    }

    // Add a new question
    public void addQuestion(Question q) {
        questions.add(q);
        FileHandler.saveQuestion(questionsFile, q);
    }

    // Start a quiz session
    public void startQuiz(String level, String category) {
        List<Question> filtered = new ArrayList<>();
        for (Question q : questions) {
            if (q.getLevel().equalsIgnoreCase(level) && q.getCategory().equalsIgnoreCase(category)) {
                filtered.add(q);
            }
        }

        if (filtered.isEmpty()) {
            System.out.println("No questions available for this level/category.");
            return;
        }

        Collections.shuffle(filtered); //shuffle questions for randomness

        if (filtered.size() > 5) {
        filtered = filtered.subList(0, 5);
        }

        int totalQuestions = filtered.size();

        int score = 0;
        for (Question q : filtered) {
            System.out.println("\n" + q.getText());

        String userAnswer = "";
        if (!q.getOptions().isEmpty()) {
            // Display numbered options
            for (int i = 0; i < q.getOptions().size(); i++) {
                System.out.println((i + 1) + ". " + q.getOptions().get(i));
            }

            // Let user pick option number
            while (true) {
                System.out.print("Enter the option number: ");
                String input = scanner.nextLine();

                try {
                    int choice = Integer.parseInt(input);
                    if (choice >= 1 && choice <= q.getOptions().size()) {
                        userAnswer = q.getOptions().get(choice - 1);
                        break;
                    } else {
                        System.out.println("Invalid number. Try again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                }
            }
        } else {
            // Open-ended question
            System.out.print("Your answer: ");
            userAnswer = scanner.nextLine();
        }

        // if (userAnswer.equalsIgnoreCase(q.getCorrectAnswer())) {
        //     score++;
        // }

        // Show result for this question
        if (userAnswer.equalsIgnoreCase(q.getCorrectAnswer())) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Incorrect. ❌ The correct answer is: " + q.getCorrectAnswer());
        }
    }

        Result result = new Result(LocalDateTime.now(), score, totalQuestions, level, category);
        results.add(result);
        FileHandler.saveResult(resultsFile, result);
        System.out.println("\nQuiz complete! " + result);
        
    }

    public void showResults() {
        if (results.isEmpty()) {
            System.out.println("No quiz history available.");
        } else {
            for (Result r : results) {
                System.out.println(r);
            }
        }
    }

    // Export questions by level/category
    public void exportQuestionsToPDFByFilter(String filename, String level, String category, int numberToExport) {
        List<Question> filtered = new ArrayList<>();
        for (Question q : questions) {
            boolean levelMatch = level.isEmpty() || q.getLevel().equalsIgnoreCase(level);
            boolean categoryMatch = category.isEmpty() || q.getCategory().equalsIgnoreCase(category);
            if (levelMatch && categoryMatch) filtered.add(q);
        }

        if (filtered.isEmpty()) {
            System.out.println("No questions found for the given filter.");
            return;
        }

        System.out.println("Found " + filtered.size() + " questions for the filter.");

        Collections.shuffle(filtered);
        if (numberToExport < filtered.size()) filtered = filtered.subList(0, numberToExport);

        FileHandler.exportQuestionsToPDF(filename, filtered);
    }

    public List<Question> getQuestions() {
        return questions;
    }

}
