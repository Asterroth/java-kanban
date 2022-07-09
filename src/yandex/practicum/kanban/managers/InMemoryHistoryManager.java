package yandex.practicum.kanban.managers;

import yandex.practicum.kanban.model.HistoryManager;
import yandex.practicum.kanban.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private List<Task> history = new ArrayList<>();
    private HashMap<Integer, Node> recordedTasks = new HashMap<>();

    public Node head;
    public Node tail;

    public void linkLast(Task element) {  //5/ реализация двусвязного списка задач с методом linkLast
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

    public List<Task> getTasks() { //5/ реализация двусвязного списка задач с методом getTasks
        List<Task> tasks = new ArrayList<>();
        Node currentNode = head;
        while (!(currentNode == null)) {
            tasks.add(currentNode.data);
            currentNode = currentNode.next;
        }
        return tasks;
    }

    public void removeNode(Node node) {  //5/ метод void remove(int id) для удаления задачи из просмотра (принимает объект Node — узел связного списка и вырезает его)
        if (!(node == null)) {
            final Task data = node.data;
            final Node next = node.next;
            final Node prev = node.prev;
            node.data = null;

            if (head == node && tail == node) {
                head = null;
                tail = null;
            } else if (head == node && !(tail == node)) {
                head = next;
                head.prev = null;
            } else if (!(head == node) && tail == node) {
                tail = prev;
                tail.next = null;
            } else {
                prev.next = next;
                next.prev = prev;
            }

            node = null;

        }

    }

    @Override //5/
    public void addRecord(Task task) {
        if (!(task == null)) {
            removeRecord(task.getId());
            linkLast(task);
        }
    }

    @Override  //5/
    public void removeRecord(int id) {
        removeNode(recordedTasks.get(id));
    }


    @Override
    public List<Task> getHistory() {
        return getTasks();  //5/
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
