package task;
import java.util.ArrayList;

public class Epic extends Task {

    public ArrayList<Subtask> subtasks;

    public Epic(String name, String description) {
        super(name, description, null);
        this.subtasks = new ArrayList<>();
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
