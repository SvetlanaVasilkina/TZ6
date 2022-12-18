package manager;

import task.*;

import java.util.ArrayList;
import java.util.List;

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
                + subtask.getEpicId() + "\n");
    }

    //из строки в Task
    public static Task fromString(String value) {
        String[] values = value.split(", ");
        int id = Integer.parseInt(values[0]);
        TasksType type = TasksType.valueOf(values[1]);
        String name = values[2];
        TaskStatus status = TaskStatus.valueOf(values[3]);
        String description = values[4];

        switch (type) {
            case TASK:
                Task task = new Task(name,description,status);
                task.setId(id);
                task.setType(type);
                return task;
            case SUBTASK:
                int epicId = Integer.parseInt(values[5]);
                Task subtask =new Subtask(name, description, status, epicId);
                subtask.setId(id);
                subtask.setType(type);
                return subtask;
            case EPIC:
                Task epic = new Epic(name,description);
                epic.setId(id);
                epic.setType(type);
                return epic;
            default:
                return null;
        }
    }

    //из HistoryManager в строку
    public static String historyToString(HistoryManager manager) {
        String line = "";
        for (Task task: manager.getHistory()) {
            line += task.getId() + ", ";
        }
        line = line.substring(0, line.length() - 2);
        return line;
    }

    //из строки в id задач из истории просмотров
    public static List<Integer> historyFromString(String value) {
        String[] values = value.split(", ");
        List<Integer> idFromHistory = new ArrayList<>();

        for (String val: values) {
            idFromHistory.add(Integer.parseInt(val));
        }
        return idFromHistory;
    }
}
