package manager;

import task.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {
    private final HistoryManager historyManager;
    private int id = 0;
    private final HashMap<Integer, Epic> epics = new HashMap<>();
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();

    public InMemoryTaskManager(HistoryManager historyManager) {
        this.historyManager = historyManager;
    }

    //Сгенерировать Id
    private int generateId() {
        return id += 1;
    }

    //получить историю просмотров задач
    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

    //Получить список эпиков
    @Override
    public List<Epic> getAllEpics() {
        List<Epic> epicsList = new ArrayList<>();
        for (Integer id: epics.keySet()) {
            epicsList.add(epics.get(id));
        }
        return epicsList;
    }

    //Получить список задач
    @Override
    public List<Task> getAllTasks() {
        List <Task> tasksList = new ArrayList<>();

        for (Integer id: tasks.keySet()) {
            tasksList.add(tasks.get(id));
        }
        return tasksList;
    }

    //Получить список подзадач
    @Override
    public List<Subtask> getAllSubTasks() {
        List <Subtask> subtasksList = new ArrayList<>();

        for (Integer id: subtasks.keySet()) {
            subtasksList.add(subtasks.get(id));
        }
        return subtasksList;
    }

    //удалить все задачи
    @Override
    public void clearAllTasks() {
        tasks.clear();
        System.out.println("Задачи удалены");
    }

    //удалить все эпики
    @Override
    public void clearAllEpics() {
        epics.clear();
        System.out.println("Эпики удалены");
    }

    //удалить все подзадачи
    @Override
    public void clearAllSubTasks() {
        subtasks.clear();
        System.out.println("Подзадачи удалены");
        if (epics != null) {
            for (Epic epic: epics.values()) {
                changeEpicStatus(epic);
            }
        }
    }

    //Получить эпик по id
    @Override
    public Epic getEpicById(int id) {
        historyManager.add(epics.get(id));
        return epics.get(id);
    }

    //Получить задачу по id
    @Override
    public Task getTaskById(int id) {
        historyManager.add(tasks.get(id));
        return tasks.get(id);
    }

    //Получить подзадачу по id
    @Override
    public Subtask getSubtaskById(int id) {
        historyManager.add(subtasks.get(id));
        return subtasks.get(id);
    }

    //Удалить эпик по id
    @Override
    public void removeEpicById(int id) {
        for (Subtask subtask: epics.get(id).subtasks) {
            historyManager.remove(subtask.getId());
        }
        epics.remove(id);
        historyManager.remove(id);
    }

    //Удалить задачу по id
    @Override
    public void removeTaskById(int id) {
        tasks.remove(id);
        historyManager.remove(id);
    }

    //Удалить подзадачу по id
    @Override
    public void removeSubtaskById(int id) {
        subtasks.remove(id);
        historyManager.remove(id);
    }

    //получить список подзадач эпика
    @Override
    public List<Subtask> getEpicsSubtasks(Epic epic) {
        List<Subtask> epicsSubtasks = new ArrayList<>();
        for (Subtask subTask: epic.subtasks) {
            epicsSubtasks.add(subTask);
        }
        return epicsSubtasks;
    }

    //изменить статуса эпика
    private void changeEpicStatus(Epic epic) {
        List<Subtask> newList = new ArrayList<>();
        List<Subtask> doneList = new ArrayList<>();
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
    @Override
    public void addTask(Task task) {
        id = generateId();
        task.setId(id);
        tasks.put(id, task);
    }

    //добавить эпик
    @Override
    public void addEpic(Epic epic) {
        id = generateId();
        epic.setId(id);
        epics.put(id,epic);
        changeEpicStatus(epic);
    }

    //добавить подзадачу
    @Override
    public void addSubTask(Subtask subTask) {
        id = generateId();
        subTask.setId(id);
        subtasks.put(id, subTask);
        changeEpicStatus(subTask.getEpic());
    }

    //обновить задачу
    @Override
    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    //обновить эпик
    @Override
    public void updateEpic(Epic epic) {
        epics.put(epic.getId(), epic);
        changeEpicStatus(epic);
    }

    //обновить подзадачу
    @Override
    public void updateSubTask(Subtask subTask) {
        subtasks.put(subTask.getId(), subTask);
        changeEpicStatus(subTask.getEpic());
    }

}
