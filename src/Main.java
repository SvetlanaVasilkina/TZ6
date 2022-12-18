import manager.FileBackedTasksManager;
import manager.Manager;
import manager.TaskManager;
import task.Epic;
import task.Subtask;
import task.Task;
import task.TaskStatus;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Task task1 = new Task("Задача 1", "Описание задачи1", TaskStatus.NEW);
        Task task2 = new Task("Задача 2", "Описание задачи2", TaskStatus.IN_PROGRESS);
        Epic epic1 = new Epic("Эпик 1", "Описание эпика 1");
        Subtask subtask1 = new Subtask("Подзадача 1", "Описание подзадачи 1", TaskStatus.IN_PROGRESS, 3);
        File file = new File("resources\\file.csv");
        TaskManager taskManager1 = Manager.getDefault(file);
        Task task3 = new Task("Задача 3", "Описание задачи 3", TaskStatus.NEW);

        //добавить задачи
        taskManager1.addTask(task1);
        taskManager1.addTask(task2);
        taskManager1.addEpic(epic1);
        taskManager1.addSubTask(subtask1);

        //просмотр и сохранение в историю
        taskManager1.getTaskById(task1.getId());
        taskManager1.getTaskById(task2.getId());
        taskManager1.getSubtaskById(subtask1.getId());

        //считать задачи из файла
        TaskManager taskManager2 = FileBackedTasksManager.loadFromFile(file);

        //просмотр истории нового менеджера
        System.out.println(taskManager2.getHistory());

        //сравнение менеджеров
        System.out.println(taskManager2.equals(taskManager1));

        //добавить задачу в новый менеджер (проверка номера id)
        taskManager2.addTask(task3);

        System.out.println(taskManager2.getAllTasks());
        System.out.println(taskManager2.getAllEpics());
        System.out.println(taskManager2.getAllSubTasks());
    }
}
