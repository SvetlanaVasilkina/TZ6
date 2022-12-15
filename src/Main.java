import manager.FileBackedTasksManager;
import manager.HistoryManager;
import manager.Manager;
import manager.TaskManager;
import task.Epic;
import task.Subtask;
import task.Task;
import task.TaskStatus;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        HistoryManager historyManager = Manager.getDefaultHistory();
       TaskManager inMemoryTaskManager = Manager.getDefault(historyManager);
        Task task12 = new Task("Задача 1", "Описание задачи1", TaskStatus.NEW);
        Task task13 = new Task("Задача 2", "Описание задачи2", TaskStatus.IN_PROGRESS);
        //Path path = Files.createFile(Paths.get("C:\\Users\\М Видео\\file.csv"));
         File file = new File(String.valueOf("C:\\Users\\М Видео\\file.csv"));
        FileBackedTasksManager fileBackedTasksManager = new FileBackedTasksManager(file);

        fileBackedTasksManager.addTask(task12);
        fileBackedTasksManager.addTask(task13);
        fileBackedTasksManager.getTaskById(task13.getId());
        //inMemoryTaskManager.getTaskById(task12.getId());

        System.out.println("Поехали!");
        /*Epic epic1 = new Epic("переезд", "в другой город");
        Subtask subtask1 = new Subtask("собрать вещи", "запоковать все в коробки",
                TaskStatus.NEW, epic1);
        Subtask subtask2 = new Subtask("взять кота", "взять переноску для кота",
                TaskStatus.NEW, epic1);
        Epic epic2 = new Epic("найти новую работу", "искать работу в интернете");
        Subtask subtask3 = new Subtask("вспомнить про хомячка", "искать его везде",
                TaskStatus.NEW, epic1);
        Task task1 = new Task("купить продукты", "сходить в магазин", TaskStatus.NEW);
        Task task2 = new Task("согласовать отпуск на работе", "написать заявление", TaskStatus.NEW);

        //Добавить задачи
        inMemoryTaskManager.addEpic(epic1);
        inMemoryTaskManager.addEpic(epic2);
        inMemoryTaskManager.addSubTask(subtask1);
        inMemoryTaskManager.addSubTask(subtask2);
        inMemoryTaskManager.addSubTask(subtask3);
        inMemoryTaskManager.addTask(task1);
        inMemoryTaskManager.addTask(task2);

        //Изменение статуса подзадачи и эпика
        subtask1.setStatus(TaskStatus.DONE);
        inMemoryTaskManager.updateSubTask(subtask1);
        System.out.println(subtask1.getStatus());
        System.out.println(epic1.getStatus());

        //Печать списка задач
        System.out.println("Список эпиков: " + inMemoryTaskManager.getAllEpics());
        System.out.println("Список задач: " + inMemoryTaskManager.getAllTasks());
        System.out.println("Список подзадач: " + inMemoryTaskManager.getAllSubTasks());

        //Печать списка подзадач эпика
        System.out.println("Эпик: " + epic1.getName() + ". Cписок подзадач: " + inMemoryTaskManager.getEpicsSubtasks(epic1));

        inMemoryTaskManager.updateEpic(epic2);

        //Проверить новый список задач и эпиков
        System.out.println("Список эпиков: " + inMemoryTaskManager.getAllEpics());
        System.out.println("Список задач: " + inMemoryTaskManager.getAllTasks());

        //Просмотреть задачу, подзадачу или эпик
        inMemoryTaskManager.getEpicById(1);
        inMemoryTaskManager.getEpicById(2);
        inMemoryTaskManager.getSubtaskById(3);
        inMemoryTaskManager.getSubtaskById(5);

        //первый вызов истории просмотров, должны быть задачи 1,2,3,5
        System.out.println("История просмотров: " + inMemoryTaskManager.getHistory());

        inMemoryTaskManager.getEpicById(2);
        inMemoryTaskManager.getEpicById(2);
        inMemoryTaskManager.getSubtaskById(4);
        inMemoryTaskManager.getEpicById(2);
        inMemoryTaskManager.getEpicById(2);
        inMemoryTaskManager.getEpicById(2);
        inMemoryTaskManager.getEpicById(2);
        inMemoryTaskManager.getTaskById(6);
        inMemoryTaskManager.getTaskById(7);

        //второй вызов истории просмотров, должны быть задачи 1,3,5,4,2,6,7
        System.out.println("История просмотров: " + inMemoryTaskManager.getHistory());

        // удалить задачу (task id - 6)
        inMemoryTaskManager.removeTaskById(task1.getId());

        // удалить эпик с тремя подзадачами
        inMemoryTaskManager.removeEpicById(epic1.getId());

        //третий вызов истории просмотров, должны быть задачи 2,7
        System.out.println("История просмотров: " + inMemoryTaskManager.getHistory());*/
    }
}
