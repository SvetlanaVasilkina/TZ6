package manager;

import task.Epic;
import task.Subtask;
import task.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileBackedTasksManager extends InMemoryTaskManager {
    private final File file;

    public FileBackedTasksManager(File file) {
        super();
        this.file = file;
    }

    //сохранить в файл
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
            if (!historyManager.isEmpty()) {
                bufferedWriter.write(CsvFormat.historyToString(historyManager));
            }

            bufferedWriter.close();

        } catch(IOException e) {
            try {
                throw new ManagerSaveException();
            } catch (ManagerSaveException ex) {
                ex.printStackTrace();
            }
        }
    }

    //считать из файла
    public static FileBackedTasksManager loadFromFile(File file) {
        try {
            ArrayList<String> values = new ArrayList<>();
            FileBackedTasksManager tasksManager = new FileBackedTasksManager(file);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();

            while(bufferedReader.ready()) {
                line = bufferedReader.readLine();
                values.add(line);
            }

            bufferedReader.close();

            int index = 0;
            for (String v: values) {
                if (v.equals("")) {
                    index = values.indexOf(v) + 1;
                    break;
                } else {
                    Task task = CsvFormat.fromString(v);
                    switch (task.getType()) {
                        case TASK:
                            tasksManager.loadTask(task);
                            break;
                        case EPIC:
                            Epic epic = (Epic) task;
                            tasksManager.loadEpic(epic);
                            break;
                        case SUBTASK:
                            Subtask subtask = (Subtask) task;
                            tasksManager.loadSubtask(subtask);
                            break;
                    }
                }
            }
            List<Integer> idFromHistory = CsvFormat.historyFromString(values.get(index));
            for (Integer id: idFromHistory) {
                tasksManager.loadHistory(id);
            }

            return tasksManager;
        } catch (IOException e) {
            return null;
        }
    }

    //добавить задачу из файла в структуру tasksManager
    private void loadTask(Task task) {
        if (task.getId() > super.id) {
            super.id = task.getId();
        }
        tasks.put(task.getId(), task);

    }

    //добавить эпик из файла в структуру tasksManager
    private void loadEpic(Epic epic) {
        if (epic.getId() > super.id) {
            super.id = epic.getId();
        }
        epics.put(epic.getId(), epic);
        changeEpicStatus(epic);
    }

    //добавить подзадачу из файла в структуру tasksManager
    private void loadSubtask(Subtask subtask) {
        if (subtask.getId() > super.id) {
            super.id = subtask.getId();
        }
        subtasks.put(subtask.getId(), subtask);
        Epic epic = epics.get(subtask.getEpicId());
        epic.getSubtasks().add(subtask);
        changeEpicStatus(epic);
    }

    //восстановить historyManager
    protected void loadHistory(int id) {
        if(tasks.containsKey(id)) {
            super.historyManager.add(tasks.get(id));
        } else if (epics.containsKey(id)) {
            super.historyManager.add(epics.get(id));
        } else if (subtasks.containsKey(id)) {
            super.historyManager.add(subtasks.get(id));
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileBackedTasksManager that = (FileBackedTasksManager) o;
        return Objects.equals(file, that.file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(file);
    }
}
