package task;

public class Task {

    public String taskName;
    public String taskDescription;
    public String status;
    private int taskID;

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public Task() {
    }

    public Task(String taskName, String taskDescription, String status) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.status = status;
        this.taskID = 0;
    }
}

