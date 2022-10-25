package manager;

import task.*;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {
    CustomLinkedList<Task> customLinkedList = new CustomLinkedList<>();

    @Override
    public void remove(int id) {
        customLinkedList.removeNode(customLinkedList.hashList.get(id));
        customLinkedList.hashList.remove(id);
    }

    @Override
    public void add(Task task) {
        if (customLinkedList.hashList.containsKey(task.getId())) {
            customLinkedList.removeNode(customLinkedList.hashList.get(task.getId()));
           customLinkedList.hashList.remove(task.getId());
        }
        customLinkedList.linkLast(task);
        customLinkedList.hashList.put(task.getId(), customLinkedList.tail);
    }

    @Override
    public List<Task> getHistory() {
        return customLinkedList.getTasks(customLinkedList);
    }

    private static class CustomLinkedList<Task> {
        Node<Task> head;
        Node<Task> tail;
        int size;
        Map<Integer, Node<Task>> hashList;

        public CustomLinkedList() {
            size = 0;
            hashList = new HashMap<>();
        }

        public void linkLast(Task task) {
            final Node<Task> oldTail = tail;
            final Node<Task> newNode = new Node<>(oldTail, task, null);
            tail = newNode;
            if (head == null) {
               head = newNode;
            } else {
                oldTail.setNext(newNode);
            }
            size++;
        }

        private List<Task> getTasks(CustomLinkedList<Task> list) {
            List<Task> aList = new ArrayList<>();
            Node<Task> node = list.head.getNext();
            aList.add(list.head.getTask());

            while (node != null) {
               aList.add(node.getTask());
               node = node.getNext();
            }
             return aList;
        }

        private void removeNode(Node<Task> node) {

            if (node != null) {
                if (node.getPrev() != null && node.getNext() != null) {
                    node.getPrev().setNext(node.getNext());
                    node.getNext().setPrev(node.getPrev());
                } else if (node.getPrev() != null && node.getNext() == null) {
                    node.getPrev().setNext(null);
                    tail = node.getPrev();
                } else if (node.getPrev() == null && node.getNext() != null) {
                    head = node.getNext();
                }

                node.setPrev(null);
                node.setNext(null);
            }
        }
    }
}
