package manager;


public class ManagerSaveException extends Exception {
    public ManagerSaveException () {
        super();
        System.out.println("Ошибка сохранения файла");
    }
}
