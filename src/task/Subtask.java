package task;

import java.util.Objects;

public class Subtask extends Task {

    private final Epic epic;

    public Subtask(String name, String description, TaskStatus status, Epic epic) {
        super(name, description, status);
        this.epic = epic;
        epic.subtasks.add(this);
    }

    public Epic getEpic() {
        return epic;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status=" + getStatus() +
                ", taskId=" + getId() +
                '}' + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Subtask subtask = (Subtask) o;
        return epic.equals(subtask.epic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), epic);
    }
}
