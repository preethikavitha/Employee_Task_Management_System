public class Task {
    private int id;
    private int employeeId;
    private String description;
    private boolean completed;
    private int score;

    public Task(int id, int employeeId, String description, boolean completed, int score) {
        this.id = id;
        this.employeeId = employeeId;
        this.description = description;
        this.completed = completed;
        this.score = score;
    }

    public int getId() { return id; }
    public int getEmployeeId() { return employeeId; }
    public String getDescription() { return description; }
    public boolean isCompleted() { return completed; }
    public int getScore() { return score; }
}
