package util;

import model.Question;
import model.Result;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.*;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.util.List;

public class FileHandler {
    
    // Load questions from file
    public static List<Question> loadQuestions(String filename) {
        List<Question> questions = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            for (String line : lines) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    String text = parts[0];
                    List<String> options = parts[1].isEmpty() ? new ArrayList<>() : Arrays.asList(parts[1].split(","));
                    String correct = parts[2];
                    String level = parts[3];
                    String category = parts[4];
                    questions.add(new Question(text, options, correct, level, category));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading questions: " + e.getMessage());
        }
        return questions;
    }

    // Save question to file (append mode)
    public static void saveQuestion(String filename, Question q) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            String options = String.join(",", q.getOptions());
            writer.write(q.getText() + "|" + options + "|" + q.getCorrectAnswer() + "|" + q.getLevel() + "|" + q.getCategory());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving question: " + e.getMessage());
        }
    }

    // Load results from file
    public static List<Result> loadResults(String filename) {
        List<Result> results = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            for (String line : lines) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    LocalDateTime date = LocalDateTime.parse(parts[0]);
                    int score = Integer.parseInt(parts[1]);
                    int total = Integer.parseInt(parts[2]);
                    String level = parts[3];
                    String category = parts[4];
                    results.add(new Result(date, score, total, level, category));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading results: " + e.getMessage());
        }
        return results;
    }

    // Save result to file (append mode)
    public static void saveResult(String filename, Result r) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(r.getDate() + "|" + r.getScore() + "|" + r.getTotalQuestions() + "|" + r.getLevel() + "|" + r.getCategory());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving result: " + e.getMessage());
        }
    }

    public static void exportQuestionsToPDF(String filename, List<Question> questions) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filename));
            document.open();

            Font questionFont = new Font(Font.HELVETICA, 14, Font.BOLD);
            Font optionFont = new Font(Font.HELVETICA, 12);
            
            int qNum = 1;
            for (Question q : questions) {
                // Question text
                Paragraph questionPara = new Paragraph(qNum + ". " + q.getText(), questionFont);
                questionPara.setSpacingAfter(5);
                document.add(questionPara);

                // Options
                if (!q.getOptions().isEmpty()) {
                    char optLetter = 'A';
                    for (String opt : q.getOptions()) {
                        Paragraph optPara = new Paragraph("   " + optLetter + ". " + opt, optionFont);
                        document.add(optPara);
                        optLetter++;
                    }
                }

                // Answer, Level, Category
                Paragraph metaPara = new Paragraph("Answer: " + q.getCorrectAnswer() +
                        " | Level: " + q.getLevel() + " | Category: " + q.getCategory(), optionFont);
                metaPara.setSpacingAfter(15);
                document.add(metaPara);

                qNum++;
            }

            System.out.println("Questions exported to PDF: " + filename);
        } catch (Exception e) {
            System.out.println("Error exporting PDF: " + e.getMessage());
        } finally {
            document.close();
        }
    }
}
