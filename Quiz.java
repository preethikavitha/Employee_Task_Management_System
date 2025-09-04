import java.sql.*;
import java.util.Scanner;

public class Quiz {
    private String role;

    public Quiz(String role) {
        this.role = role;
    }

    public int startQuiz() {
        int score = 0;
        Scanner sc = new Scanner(System.in);

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM quiz_questions WHERE role = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, role);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("\nQ: " + rs.getString("question"));
                System.out.println("1. " + rs.getString("option1"));
                System.out.println("2. " + rs.getString("option2"));
                System.out.println("3. " + rs.getString("option3"));
                System.out.print("Your answer: ");
                String ans = sc.nextLine();

                String correct = rs.getString("correct_answer");
                if (ans.equalsIgnoreCase(correct) ||
                    (ans.equals("1") && correct.equals(rs.getString("option1"))) ||
                    (ans.equals("2") && correct.equals(rs.getString("option2"))) ||
                    (ans.equals("3") && correct.equals(rs.getString("option3")))) {
                    score++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Your total score: " + score);
        return score;
    }
}
