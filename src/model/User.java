package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private List<Result> history;   // Past quiz attempts

    public User(String username) {
        this.username = username;
        this.history = new ArrayList<>();
    }

    public String getUsername() { 
        return username; 
    }

    public List<Result> getHistory() {
         return history; 
    }

    public void addResult(Result result) {
        history.add(result);
    }

    @Override
    public String toString() {
        return "User: " + username + " | Attempts: " + history.size();
    }
}

