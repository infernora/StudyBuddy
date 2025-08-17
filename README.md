# Study Quiz Manager - StudyBuddy

This is a console-based Java application that allows users to create study questions, take quizzes, track scores, and export questions to PDF. I demonstrated Java fundamentals including OOP, file handling, collections, streams, and external library integration (OpenPDF).

---
## Motivation

The goal was to build a project using my knowledge from MOOC's "Programming in Java-I" course
---

## Idea

**Study Quiz Manager** enables users to:

1. Add questions with options (multiple choice) or open-ended answers.  
2. Take quizzes filtered by **level** (Easy, Medium, Hard) and **category** (Math, Science, Geography, etc.).  
3. See correct answers immediately after answering each question.  
4. Limit quizzes to **5 questions per round**.  
5. Track past results with scores and metadata.  
6. Export selected questions to a **PDF** file.

---
## Features

- **Add Questions**: Multiple-choice or open-ended.  
- **Take Quiz**: Randomized questions with max 5 per round.  
- **Immediate Feedback**: Shows if the answer is correct and the correct answer.  
- **Score Tracking**: Stores results for review.  
- **Export to PDF**: Export questions by category or level.  
- **Persistence**: Questions and results are stored in text files (`questions.txt` and `results.txt`).  

---


## Technologies

- **Java** – Core language  
- **OpenPDF** – Export questions to PDF  
- **File I/O** – Save/load questions and results  
- **Collections & Streams** – Manage questions and results  
- **Scanner** – Console input  

---

## Setup and Running

1. Clone the repository or download the project.  
2. Ensure the folder structure is intact.  
3. Download **OpenPDF jar** and add it to your project classpath.  
4. Open in **VS Code** or any Java IDE.

## What I Learned

- **Java OOP:** Classes, objects, constructors, encapsulation, and modular design.  
- **Collections & Data Handling:** Lists, filtering, shuffling, and subsetting.  
- **File I/O:** Reading/writing text files for questions and results.  
- **User Interaction:** Console menus, input validation, and interactive quizzes.  
- **External Libraries:** Generating formatted PDFs with OpenPDF.  
- **Problem-Solving:** Limiting quiz questions, scoring logic, and immediate feedback.  
- **Project Structure:** Separating concerns into `model`, `service`, and `util` for maintainability.  
- **Debugging & Testing:** Fixing logical and runtime errors.  


