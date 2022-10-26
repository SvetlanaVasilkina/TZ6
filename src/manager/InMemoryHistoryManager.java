package manager;

import customLinkedList.Node;
import task.*;
import customLinkedList.*;
import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {
    private final CustomLinkedList<Task> customLinkedList = new CustomLinkedList<>();
    private final Map<Integer, Node<Task>> hashList = new HashMap<>();

    @Override
    public void remove(int id) {
        customLinkedList.removeNode(hashList.get(id));
        hashList.remove(id);
    }

    @Override
    public void add(Task task) {
        if (hashList.containsKey(task.getId())) {
            customLinkedList.removeNode(hashList.get(task.getId()));
            hashList.remove(task.getId());
        }
        customLinkedList.linkLast(task);
        hashList.put(task.getId(), customLinkedList.getTail());
    }

    @Override
    public List<Task> getHistory() {
        return customLinkedList.getTasks(customLinkedList);
    }


}
