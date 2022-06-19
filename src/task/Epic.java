package task;
import java.util.ArrayList;

public class Epic extends Task {

    public ArrayList<Subtask> subtasks;
    private String status;

    public Epic(String taskName, String taskDescription) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.status = " ";
        this.subtasks = new ArrayList<>();
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
