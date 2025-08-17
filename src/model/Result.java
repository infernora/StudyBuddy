package model;

import java.time.LocalDateTime;

public class Result {
    private LocalDateTime date;   
    private int score;            
    private int totalQuestions;   
    private String level;         
    private String category;      

    public Result(LocalDateTime date, int score, int totalQuestions, String level, String category) {
        this.date = date;
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.level = level;
        this.category = category;
    }

    public LocalDateTime getDate() { 
        return date; 
    }

    public int getScore() { 
        return score; 
    }

    public int getTotalQuestions() { 
        return totalQuestions; 
    }

    public String getLevel() { 
        return level; 
    }

    public String getCategory() { 
        return category; 
    }

    @Override
    public String toString() {
        return date + " → Score: " + score + "/" + totalQuestions + " (" + level + ", " + category + ")";
    }
}
