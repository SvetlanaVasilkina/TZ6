package task;

public class Subtask extends Task {

    public Subtask(String taskName, String taskDescription, String status, Epic epic) {
        super(taskName, taskDescription, status);
        epic.subtasks.add(this);
    }
}
