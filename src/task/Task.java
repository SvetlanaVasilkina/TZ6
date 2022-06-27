package task;

import java.util.Objects;

public class Task {

    private final String name;
    private final String description;
    private TaskStatus status;
    private int Id;

    public Task(String name, String description, TaskStatus status) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.Id = 0;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Id == task.Id && name.equals(task.name) &&
                description.equals(task.description) && status.equals(task.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, status, Id);
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", taskId=" + Id +
                '}' + '\n';
    }
}

