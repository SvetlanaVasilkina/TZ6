package task;
import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {

    private List<Subtask> subtasks;

    public Epic(String name, String description) {
        super(name, description, null);
        this.subtasks = new ArrayList<>();
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    @Override
    public String toString() {
        return "Epic{"  +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status='" + getStatus() +
                ", taskId=" + getId() +
                '\'' + "subtasks=" + subtasks +
                '}' + '\n';
    }
}
