import java.sql.*;

public class Admin extends User {
    public Admin(int id, String name, String address, String phone, String email, String password) {
        super(id, name, "Admin", address, phone, email, password);
    }

    // Add Employee
    public void addEmployee(String name, String role, String address, String phone, String email, String password) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO users(name, role, address, phone, email, password) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, role);
            ps.setString(3, address);
            ps.setString(4, phone);
            ps.setString(5, email);
            ps.setString(6, password);
            ps.executeUpdate();
            System.out.println(" Employee added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View Employees with their Task Status
    public void viewEmployees() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT u.id, u.name, u.role, u.email, u.address, u.phone, " +
                         "t.description, t.status " +
                         "FROM users u LEFT JOIN tasks t ON u.id = t.employee_id " +
                         "WHERE u.role != 'Admin' " +
                         "ORDER BY u.id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            int lastEmpId = -1;
            while (rs.next()) {
                int empId = rs.getInt("id");

                if (empId != lastEmpId) {
                    // Print employee details once
                    System.out.println("\n👤 Employee ID: " + empId);
                    System.out.println("   Name    : " + rs.getString("name"));
                    System.out.println("   Role    : " + rs.getString("role"));
                    System.out.println("   Email   : " + rs.getString("email"));
                    System.out.println("   Address : " + rs.getString("address"));
                    System.out.println("   Phone   : " + rs.getString("phone"));
                    System.out.println("   Tasks   : ");
                    lastEmpId = empId;
                }

                String taskDesc = rs.getString("description");
                String taskStatus = rs.getString("status");

                if (taskDesc != null) {
                    System.out.println("      - " + taskDesc + " [" + (taskStatus != null ? taskStatus : "Pending") + "]");
                } else {
                    System.out.println("      - No tasks assigned.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Assign Task
    public void assignTask(int empId, String desc) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO tasks(employee_id, description, status) VALUES(?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, empId);
            ps.setString(2, desc);
            ps.setString(3, "Pending"); // default status
            ps.executeUpdate();
            System.out.println("Task Assigned to Employee ID " + empId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
