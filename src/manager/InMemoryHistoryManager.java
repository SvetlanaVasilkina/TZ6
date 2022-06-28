package manager;

import task.Task;
import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private final List<Task> tasksHistoryView = new ArrayList<>();

    @Override
    public void add(Task task) {
        int maxHistorySize = 10; // max размер истории просмотров

        if (tasksHistoryView.size() < maxHistorySize) {
            tasksHistoryView.add(task);
        } else {
            tasksHistoryView.remove(0);
            tasksHistoryView.add(task);
        }
    }

    @Override
    public List<Task> getHistory() {
        return tasksHistoryView;
    }
}
