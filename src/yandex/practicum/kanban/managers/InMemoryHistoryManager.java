package yandex.practicum.kanban.managers;

import yandex.practicum.kanban.model.HistoryManager;
import yandex.practicum.kanban.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    final HashMap<Integer, Node> recordedTasks = new HashMap<>();

    private Node head;
    private Node tail;

    public void linkLast(Task element) {
        final Node oldTail = tail;
        final Node newNode = new Node(oldTail, element, null);
        tail = newNode;
        recordedTasks.put(element.getId(), newNode);
        if (oldTail == null) {
            head = newNode;
        } else {
            oldTail.next = newNode;
        }
    }

    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();
        Node currentNode = head;
        while (currentNode != null) {
            tasks.add(currentNode.data);
            currentNode = currentNode.next;
        }
        return tasks;
    }

    public void removeNode(Node node) {
        if (node != null) {
            final Node next = node.next;
            final Node prev = node.prev;
            node.data = null;

            if (head == node && tail == node) {
                head = null;
                tail = null;
            } else if (head == node) {
                head = next;
                head.prev = null;
            } else if (tail == node) {
                tail = prev;
                tail.next = null;
            } else {
                prev.next = next;
                next.prev = prev;
            }
        }
    }

    @Override //5/
    public void addRecord(Task task) {
        if (task != null) {
            removeRecord(task.getId());
            linkLast(task);
        }
    }

    @Override
    public void removeRecord(int id) {
        removeNode(recordedTasks.get(id));
    }


    @Override
    public List<Task> getHistory() {
        return getTasks();
    }
}

class Node {
    public Task data;
    public Node next;
    public Node prev;

    public Node(Node prev, Task data, Node next) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }
}
