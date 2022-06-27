import manager.HistoryManager;
import manager.Manager;
import manager.TaskManager;
import task.Epic;
import task.Subtask;
import task.Task;
import task.TaskStatus;

public class Main {

    public static void main(String[] args) {
        HistoryManager historyManager = Manager.getDefaultHistory();
        TaskManager inMemoryTaskManager = Manager.getDefault(historyManager);
        System.out.println("Поехали!");
        Epic epic1 = new Epic("переезд", "в другой город");
        Subtask subtask1 = new Subtask("собрать вещи", "запоковать все в коробки",
                TaskStatus.NEW, epic1);
        Subtask subtask2 = new Subtask("взять кота", "взять переноску для кота",
                TaskStatus.NEW, epic1);
        Epic epic2 = new Epic("найти новую работу", "искать работу в интернете");
        Subtask subtask3 = new Subtask("подготовить резюме", "обновить стаж работы",
                TaskStatus.NEW, epic2);
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
        System.out.println("Эпик: " + epic2.getName() + ". Cписок подзадач: " + inMemoryTaskManager.getEpicsSubtasks(epic2));

        //Удалить задачу
        int key = task1.getId();
        inMemoryTaskManager.removeTaskById(key);

        inMemoryTaskManager.updateEpic(epic2);

        //Проверить новый список задач и эпиков
        System.out.println("Список эпиков: " + inMemoryTaskManager.getAllEpics());
        System.out.println("Список задач: " + inMemoryTaskManager.getAllTasks());

        //Просмотреть задачу, подзадачу или эпик
        inMemoryTaskManager.getEpicById(1);
        inMemoryTaskManager.getEpicById(2);
        inMemoryTaskManager.getSubtaskById(3);
        inMemoryTaskManager.getSubtaskById(4);
        inMemoryTaskManager.getSubtaskById(5);
        inMemoryTaskManager.getEpicById(1);
        inMemoryTaskManager.getEpicById(2);
        inMemoryTaskManager.getEpicById(2);
        inMemoryTaskManager.getEpicById(2);
        inMemoryTaskManager.getEpicById(2);
        inMemoryTaskManager.getEpicById(2);
        inMemoryTaskManager.getEpicById(2);

        System.out.println("История просмотров: " + historyManager.getHistory());

    }
}
