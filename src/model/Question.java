package model;

import java.util.List;


public class Question {
    private String text;              
    private List<String> options;     
    private String correctAnswer;     
    private String level;            
    private String category;         

    public Question(String text, List<String> options, String correctAnswer, String level, String category) {
        this.text = text;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.level = level;
        this.category = category;
    }

    
    public String getText() { 
        return text; 
    }

    public List<String> getOptions() { 
        return options; 
    }

    public String getCorrectAnswer() { 
        return correctAnswer; 
    }

    public String getLevel() { 
        return level; 
    }

    public String getCategory() {
         return category; 
    }

    @Override
    public String toString() {
        return "Question: " + text + " [" + level + ", " + category + "]";
    }
}