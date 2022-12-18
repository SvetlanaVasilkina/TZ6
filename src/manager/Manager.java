package manager;

import java.io.File;

public class Manager {
    public static TaskManager getDefault(File file) {
        return new FileBackedTasksManager(file);
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }

}
