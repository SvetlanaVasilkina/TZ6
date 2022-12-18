package manager;

import task.*;
import customlist.*;
import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {


    protected final CustomLinkedList<Task> customLinkedList = new CustomLinkedList<>();
    protected final Map<Integer, Node<Task>> hashList = new HashMap<>();

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
        return customLinkedList.getTasks();
    }

    @Override
    public boolean isEmpty() {
        return customLinkedList.isEmpty();
    }


}
