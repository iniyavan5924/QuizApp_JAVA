import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

// Main Quiz Application class with GUI
public class QuizApp extends JFrame {
    private Question[] questions;
    private int currentQuestionIndex;
    private int score;
    private String rollNumber;

    // Swing components
    private JLabel questionLabel;
    private JRadioButton[] options;
    private ButtonGroup optionsGroup;
    private JButton nextButton;
    private JLabel scoreLabel;

    // Constructor to initialize the quiz with predefined questions
    public QuizApp() {
        // Initialize questions
        this.questions = new Question[]{
            new Question("What is the capital of France?",
                         new String[]{"Berlin", "Madrid", "Paris", "Rome"}, 3),
            new Question("Which planet is known as the Red Planet?",
                         new String[]{"Earth", "Mars", "Jupiter", "Saturn"}, 2),
            new Question("What is the largest ocean on Earth?",
                         new String[]{"Atlantic", "Indian", "Southern", "Pacific"}, 4),
            new Question("Who wrote the play 'Romeo and Juliet'?",
                         new String[]{"Charles Dickens", "William Shakespeare", "Jane Austen", "Mark Twain"}, 2)
        };

        this.currentQuestionIndex = 0;
        this.score = 0;

        // Set up the GUI
        setTitle("Quiz Application");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Roll number input at the start
        rollNumber = JOptionPane.showInputDialog(this, "Enter your Roll Number:");
        if (rollNumber == null || rollNumber.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Roll Number is required to start the quiz.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        // Question label
        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(questionLabel, BorderLayout.NORTH);

        // Options
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1));
        options = new JRadioButton[4];
        optionsGroup = new ButtonGroup();
        for (int i = 0; i < options.length; i++) {
            options[i] = new JRadioButton();
            optionsGroup.add(options[i]);
            optionsPanel.add(options[i]);
        }
        add(optionsPanel, BorderLayout.CENTER);

        // Next button
        nextButton = new JButton("Next");
        nextButton.addActionListener(new NextButtonListener());
        add(nextButton, BorderLayout.SOUTH);

        // Load the first question
        loadQuestion();

        // Show the GUI
        setVisible(true);
    }

    // Method to load the current question
    private void loadQuestion() {
        if (currentQuestionIndex < questions.length) {
            Question q = questions[currentQuestionIndex];
            questionLabel.setText("Q" + (currentQuestionIndex + 1) + ": " + q.getQuestionText());

            String[] opts = q.getOptions();
            for (int i = 0; i < opts.length; i++) {
                options[i].setText(opts[i]);
                options[i].setSelected(false);
            }
        } else {
            // Quiz is over
            showScore();
        }
    }

    // Method to display the final score and save it to the database
    private void showScore() {
        JOptionPane.showMessageDialog(this, "Quiz Over! You scored " + score + "/" + questions.length + ".", "Quiz Over", JOptionPane.INFORMATION_MESSAGE);
        saveScoreToDatabase();
        System.exit(0);
    }

    // Method to save the roll number and score to the database
    private void saveScoreToDatabase() {
        String url = "jdbc:mysql://localhost:3306/quiz"; // Replace with your database URL
        String user = "root";                            // Replace with your database username
        String password = "";                            // Replace with your database password

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO qz (roll, score) VALUES (?, ?) " +
                           "ON DUPLICATE KEY UPDATE score = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, rollNumber);
            preparedStatement.setInt(2, score);
            preparedStatement.setInt(3, score);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Your score has been successfully saved to the database!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error saving score to the database: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Listener for the Next button
    private class NextButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Check if an option is selected
            int selectedOption = -1;
            for (int i = 0; i < options.length; i++) {
                if (options[i].isSelected()) {
                    selectedOption = i + 1; // Options are 1-indexed
                    break;
                }
            }

            if (selectedOption == -1) {
                JOptionPane.showMessageDialog(QuizApp.this, "Please select an option before proceeding.", "No Option Selected", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Check the answer
            if (questions[currentQuestionIndex].checkAnswer(selectedOption)) {
                score++;
            }

            // Load the next question
            currentQuestionIndex++;
            loadQuestion();
        }
    }

    // Main method to start the quiz application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QuizApp());
    }
}

// Class to represent each quiz question
class Question {
    private String questionText;
    private String[] options;
    private int correctOption;

    public Question(String questionText, String[] options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public boolean checkAnswer(int answer) {
        return answer == correctOption;
    }
}
