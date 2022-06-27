package manager;

import task.*;
import java.util.List;

public interface HistoryManager {

    //добавить задачу в историю просмотров
    void add(Task task);

    //получить историю просмотров задач
    List<Task> getHistory();
}
