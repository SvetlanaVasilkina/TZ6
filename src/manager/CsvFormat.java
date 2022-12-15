package manager;

import task.Epic;
import task.Subtask;
import task.Task;
import task.TasksType;

public class CsvFormat {

    //id,type,name,status,description,epic
    //1,TASK,Task1,NEW,Description task1,
    //2,EPIC,Epic2,DONE,Description epic2,
    //3,SUBTASK,Sub Task2,DONE,Description sub task3,2
    //
    //2,3

    public static String toString(Task task) {
        return (task.getId() + ", " + TasksType.TASK + ", " + task.getName()
                + ", " + task.getStatus() + ", " + task.getDescription() + ", " + "\n");
    }

    public static String toString(Epic epic) {
        return (epic.getId() + ", " + TasksType.EPIC + ", " + epic.getName()
                + ", " + epic.getStatus() + ", " + epic.getDescription() + ", " + "\n");
    }

    public static String toString(Subtask subtask) {
        return (subtask.getId() + ", " + TasksType.SUBTASK + ", " + subtask.getName()
                + ", " + subtask.getStatus() + ", " + subtask.getDescription() + ", "
                + subtask.getEpic().getId() + "\n");
    }

   /* public static Task fromString(String value) {
        String[] values = value.split(", ");
        final int id = Integer.parseInt(values[0]);
        final TasksType type = TasksType.valueOf(values[1]);
        final String name = values[2];
        final TaskStatus status = TaskStatus.valueOf(values[3]);
        final String description = values[4];
        switch (type) {
            case TASK:
                Task task = new Task(name,description,status);
                task.setId(id);
                return task;
            case
            case EPIC:
                Epic epic = new Epic(name,description,status);
        }

    }*/

    public static String historyToString(HistoryManager manager) {
        String line = "";
        for (Task task: manager.getHistory()) {
            line += task.getId() + ", ";
        }
        return line;

    }

}
