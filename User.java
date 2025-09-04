public class User {
    private int id;
    private String name;
    private String role;
    private String address;
    private String phone;
    private String email;
    private String password;

    public User(int id, String name, String role, String address, String phone, String email, String password) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getRole() { return role; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setRole(String role) { this.role = role; }
    public void setAddress(String address) { this.address = address; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
}
