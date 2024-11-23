### **README.md** for QuizApp 🎉📝

# **QuizApp - Java Swing-based Quiz Application** 🧠💻

Welcome to **QuizApp**! This is a simple and interactive **Java Swing** application that allows users to take a quiz, stores their score in a database, and provides instant feedback after each question. 🚀✨

---

## **Features** 🌟

✅ **User-Friendly Interface**: Built using **Java Swing**, the app provides an intuitive and engaging experience.  
✅ **Dynamic Questions**: Loads questions dynamically one at a time.  
✅ **Instant Feedback**: Displays whether the selected answer is correct or incorrect.  
✅ **Score Tracking**: Tracks the user's score in real-time.  
✅ **Database Integration**: Saves the user's roll number and score into a **MySQL database**.  
✅ **Error Handling**: Alerts users for invalid inputs and other issues.

---

## **How to Run the Application** 🚀

### Prerequisites 🛠️
1. **Java Development Kit (JDK)** 17 or later.  
2. **MySQL Database** installed on your system.  
3. **MySQL Connector JAR** added to your project's classpath.  

   > You can download it [here](https://dev.mysql.com/downloads/connector/j/).

---

### Database Setup 🗄️
1. Open your MySQL command-line client or GUI (e.g., MySQL Workbench).  
2. Create a database named `quiz`:  
   ```sql
   CREATE DATABASE quiz;
   ```
3. Create a table `qz` to store roll numbers and scores:  
   ```sql
   CREATE TABLE qz (
       roll VARCHAR(20) PRIMARY KEY, -- Roll number
       score INT NOT NULL            -- Quiz score
   );
   ```

---

### Steps to Run 🏃‍♂️
1. **Compile the Program**:  
   Open a terminal/command prompt and navigate to the folder containing the `QuizApp.java` file.  
   ```bash
   javac QuizApp.java
   ```

2. **Run the Program**:  
   Execute the following command to start the application:  
   ```bash
   java QuizApp
   ```

3. **Take the Quiz**:  
   - Enter your **roll number** when prompted.  
   - Answer the questions one by one by selecting the correct option.  
   - Your score will be displayed at the end and saved in the database! 🎉  

---

## **Code Structure** 📂

```plaintext
QuizApp.java  // Main application file
```

### Key Classes and Methods 📚
1. **`Question` Class**: Represents each quiz question with text, options, and the correct answer.  
   - `checkAnswer(int answer)`: Checks if the user's selected answer is correct.  

2. **`QuizApp` Class**: Main class containing the GUI and logic.  
   - **GUI Components**:
     - `JLabel`: Displays questions and score.
     - `JRadioButton`: For selecting options.
     - `JButton`: To navigate to the next question.
   - **Methods**:
     - `loadQuestion()`: Loads the current question into the UI.
     - `saveScoreToDatabase()`: Saves the user's score to the database.
     - `showScore()`: Displays the final score.

---

## **Dependencies** 🛠️
- **MySQL Database**  
- **MySQL Connector JAR**  

---

## **Screenshots** 📸

### **Welcome Screen** 🖥️
![Welcome Screen](https://via.placeholder.com/500x300?text=Welcome+Screen)  

### **Question Screen** ❓
![Question Screen](https://via.placeholder.com/500x300?text=Question+Screen)  

### **Final Score** 🎯
![Final Score](https://via.placeholder.com/500x300?text=Final+Score)  

---

## **Customization** 🎨
- **Add More Questions**:  
  Add more questions by editing the `questions` array in the `QuizApp` constructor.

  ```java
  this.questions = new Question[]{
      new Question("Your Question?", new String[]{"Option1", "Option2", "Option3", "Option4"}, correctOptionIndex),
      // Add more questions here
  };
  ```

- **Update Database Credentials**:  
  Update your MySQL credentials in the `saveScoreToDatabase()` method:
  ```java
  String url = "jdbc:mysql://localhost:3306/quiz";
  String user = "root";
  String password = ""; // Add your MySQL password here
  ```

---

## **Technologies Used** 💻
- **Java Swing**: For the graphical user interface.  
- **JDBC**: For database connectivity.  
- **MySQL**: To store user scores.  

---

## **Troubleshooting** 🛠️

### Common Issues 😵
1. **Database Connection Error**:  
   - Ensure the database is running and credentials are correct.  
   - Verify that the MySQL Connector JAR is added to the classpath.

2. **Empty Roll Number**:  
   If you don’t enter a roll number, the program will terminate.

3. **Java Version Compatibility**:  
   Ensure you are using **JDK 17** or higher.

---

## **Contributing** 🤝
Feel free to fork this project and add more features like:
- Timer for each question. ⏲️  
- Enhanced UI with colors and themes. 🎨  
- Leaderboard to display top scores. 🏆  

---

## **License** 📜
This project is licensed under the **MIT License**. You’re free to use and modify it as per the terms of the license.

---

## **Acknowledgements** 🙌
- Thanks to **Java Swing** for the GUI components.  
- Inspired by the desire to learn and build engaging quiz applications!  

---

### **Enjoy the QuizApp! Happy Quizzing!** 🎉🧠
