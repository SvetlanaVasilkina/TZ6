import task.Epic;
import task.Subtask;
import task.Task;
import task.TaskStatus;

public class Main {

    public static void main(String[] args) {
        Manager manager = new Manager();
        System.out.println("Поехали!");
        Epic epic1 = new Epic("переезд", "в другой город", null);
        Subtask subtask1 = new Subtask("собрать вещи", "запоковать все в коробки",
                TaskStatus.NEW, epic1);
        Subtask subtask2 = new Subtask("взять кота", "взять переноску для кота",
                TaskStatus.NEW, epic1);
        Epic epic2 = new Epic("найти новую работу", "искать работу в интернете", null);
        Subtask subtask3 = new Subtask("подготовить резюме", "обновить стаж работы",
                TaskStatus.NEW, epic2);
        Task task1 = new Task("купить продукты", "сходить в магазин", TaskStatus.NEW);
        Task task2 = new Task("согласовать отпуск на работе", "написать заявление", TaskStatus.NEW);

        //Добавить задачи
        manager.addEpic(epic1);
        manager.addEpic(epic2);
        manager.addSubTask(subtask1);
        manager.addSubTask(subtask2);
        manager.addSubTask(subtask3);
        manager.addTask(task1);
        manager.addTask(task2);

        //Изменение статуса подзадачи и эпика
        subtask1.setStatus(TaskStatus.DONE);
        manager.updateSubTask(subtask1);
        System.out.println(subtask1.getStatus());
        System.out.println(epic1.getStatus());

        //Печать списка задач
        System.out.println("Список эпиков: " + manager.getAllEpics());
        System.out.println("Список задач: " + manager.getAllTasks());
        System.out.println("Список подзадач: " + manager.getAllSubTasks());

        //Печать списка подзадач эпика
        System.out.println("Эпик: " + epic2.getName() + ". Cписок подзадач: " + manager.getEpicsSubtasks(epic2));

        //Удалить задачу и эпик
        int key = task1.getId();
        manager.removeTaskById(key);
        manager.removeEpicById(1);

        manager.updateEpic(epic2);
        //Проверить новый список задач и эпиков
        System.out.println("Список эпиков: " + manager.getAllEpics());
        System.out.println("Список задач: " + manager.getAllTasks());
    }
}
