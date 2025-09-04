import java.sql.*;
import java.util.*;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Employee Task Management System ---");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Enter choice: ");
            String choice = sc.nextLine();

            if (choice.equals("1")) {
                login();
            } else if (choice.equals("2")) {
                System.out.println("Exiting...");
                break;
            }
        }
    }

    private static void login() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("Email: ");
            String email = sc.nextLine();
            System.out.print("Password: ");
            String password = sc.nextLine();

            String sql = "SELECT * FROM users WHERE email=? AND password=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("role"),
                    rs.getString("address"),
                    rs.getString("phone"),
                    rs.getString("email"),
                    rs.getString("password")
                );

                if (user.getRole().equalsIgnoreCase("Admin")) {
                    adminMenu(user);
                } else {
                    employeeMenu(user);
                }
            } else {
                System.out.println("❌ Invalid credentials!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------- ADMIN MENU ----------------
    private static void adminMenu(User admin) throws Exception {
        try (Connection conn = DBConnection.getConnection()) {
            while (true) {
                System.out.println("\n--- Admin Menu ---");
                System.out.println("1. Add Employee");
                System.out.println("2. View Employees");
                System.out.println("3. Assign Task");
                System.out.println("4. Add Quiz Question");
                System.out.println("5. Logout");
                System.out.print("Enter choice: ");
                String choice = sc.nextLine();

                if (choice.equals("1")) {
                    System.out.print("Name: "); String name = sc.nextLine();
                    System.out.print("Role: "); String role = sc.nextLine();
                    System.out.print("Address: "); String address = sc.nextLine();
                    System.out.print("Phone: "); String phone = sc.nextLine();
                    System.out.print("Email: "); String email = sc.nextLine();
                    System.out.print("Password: "); String password = sc.nextLine();

                    String sql = "INSERT INTO users(name, role, address, phone, email, password) VALUES(?,?,?,?,?,?)";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, name); ps.setString(2, role); ps.setString(3, address);
                    ps.setString(4, phone); ps.setString(5, email); ps.setString(6, password);
                    ps.executeUpdate();
                    System.out.println("✅ Employee Added!");

                } else if (choice.equals("2")) {
                    Statement st = conn.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM users WHERE role!='Admin'");
                    while (rs.next()) {
                        System.out.println(rs.getInt("id") + " | " + rs.getString("name") + " | " + rs.getString("role"));
                    }

                } else if (choice.equals("3")) {
                    System.out.print("Enter Employee ID: ");
                    int empId = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter Task Description: ");
                    String desc = sc.nextLine();

                    String sql = "INSERT INTO tasks(employee_id, description) VALUES(?,?)";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setInt(1, empId); ps.setString(2, desc);
                    ps.executeUpdate();
                    System.out.println("✅ Task Assigned!");

                } else if (choice.equals("4")) {
                    System.out.print("Role: "); String role = sc.nextLine();
                    System.out.print("Question: "); String q = sc.nextLine();
                    System.out.print("Option1: "); String o1 = sc.nextLine();
                    System.out.print("Option2: "); String o2 = sc.nextLine();
                    System.out.print("Option3: "); String o3 = sc.nextLine();
                    System.out.print("Correct Answer: "); String ca = sc.nextLine();

                    String sql = "INSERT INTO quiz_questions(role,question,option1,option2,option3,correct_answer) VALUES(?,?,?,?,?,?)";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, role); ps.setString(2, q); ps.setString(3, o1);
                    ps.setString(4, o2); ps.setString(5, o3); ps.setString(6, ca);
                    ps.executeUpdate();
                    System.out.println("✅ Quiz Question Added!");

                } else if (choice.equals("5")) {
                    break;
                }
            }
        }
    }

    // ---------------- EMPLOYEE MENU ----------------
    private static void employeeMenu(User emp) throws Exception {
        try (Connection conn = DBConnection.getConnection()) {
            while (true) {
                System.out.println("\n--- Employee Menu ---");
                System.out.println("1. View Task");
                System.out.println("2. Take Quiz");
                System.out.println("3. Logout");
                System.out.print("Enter choice: ");
                String choice = sc.nextLine();

                if (choice.equals("1")) {
                    String sql = "SELECT * FROM tasks WHERE employee_id=?";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setInt(1, emp.getId());
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        System.out.println(rs.getInt("id") + " | " + rs.getString("description") +
                                " | Completed: " + rs.getBoolean("completed") +
                                " | Score: " + rs.getInt("score"));
                    }

                } else if (choice.equals("2")) {
                    Quiz quiz = new Quiz(emp.getRole());
                    int score = quiz.startQuiz();

                    String update = "UPDATE tasks SET completed=?, score=? WHERE employee_id=?";
                    PreparedStatement ps2 = conn.prepareStatement(update);
                    ps2.setBoolean(1, score >= 2);
                    ps2.setInt(2, score);
                    ps2.setInt(3, emp.getId());
                    ps2.executeUpdate();

                    System.out.println("✅ Quiz Finished! Your score: " + score);

                } else if (choice.equals("3")) {
                    break;
                }
            }
        }
    }
}
