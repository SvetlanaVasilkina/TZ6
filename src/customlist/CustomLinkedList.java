package customlist;

import java.util.ArrayList;
import java.util.List;

public class CustomLinkedList<Task> {
    private Node<Task> head;
    private Node<Task> tail;

    public void linkLast(Task task) {
        final Node<Task> oldTail = tail;
        final Node<Task> newNode = new Node<>(oldTail, task, null);
        tail = newNode;
        if (head == null) {
            head = newNode;
        } else {
            oldTail.setNext(newNode);
        }
    }

    public List<Task> getTasks() {
        List<Task> aList = new ArrayList<>();
        Node<Task> node = head.getNext();
        aList.add(head.getTask());

        while (node != null) {
            aList.add(node.getTask());
            node = node.getNext();
        }
        return aList;
    }

    public void removeNode(Node<Task> node) {

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

    public Node<Task> getTail() {
        return tail;
    }
}
