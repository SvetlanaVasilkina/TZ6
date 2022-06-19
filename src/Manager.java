import task.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Manager {

    private int taskID = 0;
    HashMap<Integer, Epic> epics = new HashMap<>();
    HashMap<Integer, Task> tasks = new HashMap<>();
    HashMap<Integer, Subtask> subtasks = new HashMap<>();

    public int setTaskID() {
        return taskID += 1;
    }

    public ArrayList<String> getAllEpics() {
        ArrayList <String> taskNames = new ArrayList<>();

        for (Integer id: epics.keySet()) {
            taskNames.add(epics.get(id).taskName);
        }
        return taskNames;
    }

    public ArrayList<String> getAllTasks() {
        ArrayList <String> taskNames = new ArrayList<>();

        for (Integer id: tasks.keySet()) {
            taskNames.add(tasks.get(id).taskName);
        }
        return taskNames;
    }

    public ArrayList<String> getAllSubTasks() {
        ArrayList <String> taskNames = new ArrayList<>();

        for (Integer id: subtasks.keySet()) {
            taskNames.add(subtasks.get(id).taskName);
        }
        return taskNames;
    }

    //удалить все задачи
    public HashMap<Integer, Task> clearAllTasks() {
        tasks.clear();
        System.out.println("Задачи удалены");
        return tasks;
    }

    //удалить все эпики
    public HashMap<Integer, Epic> clearAllEpics() {
        epics.clear();
        System.out.println("Эпики удалены");
        return epics;
    }

    //удалить все подзадачи
    public HashMap<Integer, Subtask> clearAllSubTasks() {
        subtasks.clear();
        System.out.println("Подзадачи удалены");
        if (epics != null) {
            for (Epic epic: epics.values()) {
                changeEpicStatus(epic);
            }
        }
        return subtasks;
    }

    public Epic getEpicByID(int key) {
        return epics.get(key);
    }

    public Task getTaskByID(int key) {
        return tasks.get(key);
    }

    public Subtask getSubtaskByID(int key) {
        return subtasks.get(key);
    }

    public void removeEpicByID(int key) {
        epics.remove(key);
    }

    public void removeTaskByID(int key) {
        tasks.remove(key);
    }

    public void removeSubtaskByID(int key) {
        subtasks.remove(key);
    }

    //получить список подзадач эпика
    public ArrayList getEpicsSubtasks(Epic epic) {
        ArrayList taskNames = new ArrayList<>();
        for (Subtask subTask: epic.subtasks) {
            taskNames.add(subTask.taskName);
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
            if (subTask.status.equals("NEW")) {
                newList.add(subTask);
            } else if (subTask.status.equals("DONE")) {
                doneList.add(subTask);
            }
        }

        if (newList.size() == epic.subtasks.size()) {
            isNew = true;
        } else if (doneList.size() == epic.subtasks.size()) {
            isDone = true;
        }

        if (epic.subtasks == null || (isNew)) {
            epic.setStatus("NEW");
        } else if (isDone) {
            epic.setStatus("DONE");
        } else {
            epic.setStatus("IN_PROGRESS");
        }
    }

    //добавить задачу
    public void addTask(Task task) {
        taskID = setTaskID();
        task.setTaskID(taskID);
        tasks.put(taskID, task);
    }

    //добавить эпик
    public void addEpic(Epic epic) {
        taskID = setTaskID();
        epic.setTaskID(taskID);
        epics.put(taskID,epic);
        changeEpicStatus(epic);
    }

    //добавить подзадачу
    public void addSubTask(Subtask subTask, Epic epic) {
        taskID = setTaskID();
        subTask.setTaskID(taskID);
        subtasks.put(taskID, subTask);
        changeEpicStatus(epic);
    }

    //обновить задачу
    public void updateTask(Task task) {
        tasks.put(task.getTaskID(), task);
    }

    //обновить эпик
    public void updateEpic(Epic epic) {
        epics.put(epic.getTaskID(), epic);
        changeEpicStatus(epic);
    }

    //обновить подзадачу
    public void updateSubTask(Subtask subTask, Epic epic) {
        subtasks.put(subTask.getTaskID(), subTask);
        changeEpicStatus(epic);
    }
}
