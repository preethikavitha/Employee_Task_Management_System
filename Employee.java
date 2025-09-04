import java.sql.*;

public class Employee extends User {
    public Employee(int id, String name, String role, String address, String phone, String email, String password) {
        super(id, name, role, address, phone, email, password);
    }

    // View tasks assigned
    public void viewTask() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM tasks WHERE employee_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Task: " + rs.getString("description") +
                        " | Status: " + (rs.getBoolean("completed") ? "Completed" : "Pending") +
                        " | Score: " + rs.getInt("score"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Take Quiz
    public void takeQuiz() {
        Quiz q = new Quiz(getRole());
        int score = q.startQuiz();

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE tasks SET completed=?, score=? WHERE employee_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBoolean(1, score >= 2);
            ps.setInt(2, score);
            ps.setInt(3, getId());
            ps.executeUpdate();

            if (score >= 2) {
                System.out.println("✅ Task Completed & Updated in DB!");
            } else {
                System.out.println("❌ Task still pending. Score too low.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
