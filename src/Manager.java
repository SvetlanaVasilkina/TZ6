import task.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Manager {

    private int Id = 0;
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Subtask> subtasks = new HashMap<>();

    private int generateId() {
        return Id += 1;
    }

    public ArrayList<Epic> getAllEpics() {
        ArrayList <Epic> epicsList = new ArrayList<>();
        for (Integer id: epics.keySet()) {
            epicsList.add(epics.get(id));
        }
        return epicsList;
    }

    public ArrayList<Task> getAllTasks() {
        ArrayList <Task> tasksList = new ArrayList<>();

        for (Integer id: tasks.keySet()) {
            tasksList.add(tasks.get(id));
        }
        return tasksList;
    }

    public ArrayList<Subtask> getAllSubTasks() {
        ArrayList <Subtask> subtasksList = new ArrayList<>();

        for (Integer id: subtasks.keySet()) {
            subtasksList.add(subtasks.get(id));
        }
        return subtasksList;
    }

    //удалить все задачи
    public void clearAllTasks() {
        tasks.clear();
        System.out.println("Задачи удалены");
    }

    //удалить все эпики
    public void clearAllEpics() {
        epics.clear();
        System.out.println("Эпики удалены");
    }

    //удалить все подзадачи
    public void clearAllSubTasks() {
        subtasks.clear();
        System.out.println("Подзадачи удалены");
        if (epics != null) {
            for (Epic epic: epics.values()) {
                changeEpicStatus(epic);
            }
        }
    }

    public Epic getEpicById(int id) {
        return epics.get(id);
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public Subtask getSubtaskById(int id) {
        return subtasks.get(id);
    }

    public void removeEpicById(int id) {
        epics.remove(id);
    }

    public void removeTaskById(int id) {
        tasks.remove(id);
    }

    public void removeSubtaskById(int id) {
        subtasks.remove(id);
    }

    //получить список подзадач эпика
    public ArrayList<String> getEpicsSubtasks(Epic epic) {
        ArrayList<String> taskNames = new ArrayList<>();
        for (Subtask subTask: epic.subtasks) {
            taskNames.add(subTask.getName());
        }
        return taskNames;
    }

    //изменить статуса эпика
    private void changeEpicStatus(Epic epic) {
        ArrayList<Subtask> newList = new ArrayList<>();
        ArrayList<Subtask> doneList = new ArrayList<>();
        boolean isNew = false;
        boolean isDone = false;
        for (Subtask subTask: epic.subtasks) {
            if (subTask.getStatus() == TaskStatus.NEW) {
                newList.add(subTask);
            } else if (subTask.getStatus() == TaskStatus.DONE) {
                doneList.add(subTask);
            }
        }

        if (newList.size() == epic.subtasks.size()) {
            isNew = true;
        } else if (doneList.size() == epic.subtasks.size()) {
            isDone = true;
        }

        if (epic.subtasks == null || (isNew)) {
            epic.setStatus(TaskStatus.NEW);
        } else if (isDone) {
            epic.setStatus(TaskStatus.DONE);
        } else {
            epic.setStatus(TaskStatus.IN_PROGRESS);
        }
    }

    //добавить задачу
    public void addTask(Task task) {
        Id = generateId();
        task.setId(Id);
        tasks.put(Id, task);
    }

    //добавить эпик
    public void addEpic(Epic epic) {
        Id = generateId();
        epic.setId(Id);
        epics.put(Id,epic);
        changeEpicStatus(epic);
    }

    //добавить подзадачу
    public void addSubTask(Subtask subTask) {
        Id = generateId();
        subTask.setId(Id);
        subtasks.put(Id, subTask);
        changeEpicStatus(subTask.getEpic());
    }

    //обновить задачу
    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    //обновить эпик
    public void updateEpic(Epic epic) {
        epics.put(epic.getId(), epic);
        changeEpicStatus(epic);
    }

    //обновить подзадачу
    public void updateSubTask(Subtask subTask) {
        subtasks.put(subTask.getId(), subTask);
        changeEpicStatus(subTask.getEpic());
    }
}
