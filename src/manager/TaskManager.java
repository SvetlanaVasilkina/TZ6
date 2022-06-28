package manager;

import task.*;
import java.util.List;

public interface TaskManager {

    //Получить список задач
    List<Task> getAllTasks();

    //Получить список эпиков
    List<Epic> getAllEpics();

    //Получить список подзадач
    List<Subtask> getAllSubTasks();

    //удалить все задачи
    void clearAllTasks();

    //удалить все эпики
    void clearAllEpics();

    //удалить все подзадачи
    void clearAllSubTasks();

    //Получить эпик по id
    Epic getEpicById(int id);

    //Получить задачу по id
    Task getTaskById(int id);

    //Получить подзадачу по id
    Subtask getSubtaskById(int id);

    //Удалить эпик по id
    void removeEpicById(int id);

    //Удалить задачу по id
    void removeTaskById(int id);

    //Удалить подзадачу по id
    void removeSubtaskById(int id);

    //получить список подзадач эпика
    List<Subtask> getEpicsSubtasks(Epic epic);

    //добавить задачу
    void addTask(Task task);

    //добавить эпик
    void addEpic(Epic epic);

    //добавить подзадачу
    void addSubTask(Subtask subTask);

    //обновить задачу
    void updateTask(Task task);

    //обновить эпик
    void updateEpic(Epic epic);

    //обновить подзадачу
    void updateSubTask(Subtask subTask);

    //получить историю просмотров задач
    List<Task> getHistory();

}