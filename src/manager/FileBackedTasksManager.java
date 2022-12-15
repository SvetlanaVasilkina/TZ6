package manager;

import task.Epic;
import task.Subtask;
import task.Task;
import task.TaskStatus;

import java.io.*;
import java.util.List;

public class FileBackedTasksManager extends InMemoryTaskManager {
    private final File file;
    public FileBackedTasksManager(File file) {
        super();
        this.file = file;
    }

    protected void save() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write("id,type,name,status,description,epic \n");

            for (Task task: getTasks().values()) {
                bufferedWriter.write(CsvFormat.toString(task));
            }
            for (Epic epic: getEpics().values()) {
                bufferedWriter.write(CsvFormat.toString(epic));
            }
            for (Subtask subtask: getSubtasks().values()) {
                bufferedWriter.write(CsvFormat.toString(subtask));
            }
            bufferedWriter.write("\n");

            if (historyManager != null) {
                bufferedWriter.write(CsvFormat.historyToString(super.historyManager));
            }


            bufferedWriter.close();

        } catch(IOException exception) {
            exception.getMessage();
        }
    }

    public static FileBackedTasksManager loadFromFile(File file) {
        FileBackedTasksManager fileBackedTasksManager = new FileBackedTasksManager(file);
        return fileBackedTasksManager;
    }

    @Override
    public List<Task> getHistory() {
        final List<Task> history = super.getHistory();
        save();
        return history;
    }

    @Override
    public Epic getEpicById(int id) {
        final Epic epic = super.getEpicById(id);
        save();
        return epic;
    }

    @Override
    public Task getTaskById(int id) {
        Task task = super.getTaskById(id);
        save();
        return task;
    }

    @Override
    public Subtask getSubtaskById(int id) {
        final Subtask subtask = super.getSubtaskById(id);
        save();
        return subtask;
    }

    @Override
    public void addTask(Task task) {
        super.addTask(task);
        save();

    }

    @Override
    public void addEpic(Epic epic) {
        super.addEpic(epic);
        save();
    }

    @Override
    public void addSubTask(Subtask subTask) {
        super.addSubTask(subTask);
        save();
    }

    @Override
    public void updateTask(Task task) {
        super.updateTask(task);
        save();
    }

    @Override
    public void updateEpic(Epic epic) {
        super.updateEpic(epic);
        save();
    }

    @Override
    public void updateSubTask(Subtask subTask) {
        super.updateSubTask(subTask);
        save();
    }
}
