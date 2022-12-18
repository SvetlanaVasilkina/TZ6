package manager;

import task.*;

import java.util.List;

public interface HistoryManager {

    void remove(int id); //удалить задачу из истории просмотров;

    //добавить задачу в историю просмотров
    void add(Task task);

    //получить историю просмотров задач
    List<Task> getHistory();

    boolean isEmpty();


}
