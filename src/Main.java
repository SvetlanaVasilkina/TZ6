import task.Epic;
import task.Subtask;
import task.Task;

public class Main {

    public static void main(String[] args) {
        Manager manager = new Manager();
        System.out.println("Поехали!");
        Epic epic1 = new Epic("переезд", "в другой город");
        Subtask subtask1 = new Subtask("собрать вещи", "запоковать все в коробки",
                "NEW", epic1);
        Subtask subtask2 = new Subtask("взять кота", "взять переноску для кота",
                "NEW", epic1);
        Epic epic2 = new Epic("найти новую работу", "искать работу в интернете");
        Subtask subtask3 = new Subtask("подготовить резюме", "обновить стаж работы",
                "NEW", epic2);
        Task task1 = new Task("купить продукты", "сходить в магазин", "NEW");
        Task task2 = new Task("согласовать отпуск на работе", "написать заявление", "NEW");

        //Добавить задачи
        manager.addEpic(epic1);
        manager.addEpic(epic2);
        manager.addSubTask(subtask1, epic1);
        manager.addSubTask(subtask2, epic1);
        manager.addSubTask(subtask3, epic2);
        manager.addTask(task1);
        manager.addTask(task2);

        //Изменение статуса подзадачи и эпика
        subtask1.status = "DONE";
        manager.updateSubTask(subtask1, epic1);
        System.out.println(subtask1.status);
        System.out.println(epic1.getStatus());

        //Печать списка задач
        System.out.println("Список эпиков: " + manager.getAllEpics());
        System.out.println("Список задач: " + manager.getAllTasks());
        System.out.println("Список подзадач: " + manager.getAllSubTasks());

        //Печать списка подзадач эпика
        System.out.println("Эпик: " + epic2.taskName + ". Cписок подзадач: " + manager.getEpicsSubtasks(epic2));

        //Удалить задачу и эпик
        int key = task1.getTaskID();
        manager.removeTaskByID(key);
        manager.removeEpicByID(1);

        //Проверить новый список задач и эпиков
        System.out.println("Список эпиков: " + manager.getAllEpics());
        System.out.println("Список задач: " + manager.getAllTasks());
    }
}
