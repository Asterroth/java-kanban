package yandex.practicum.kanban.managers;

import yandex.practicum.kanban.model.HistoryManager;
import yandex.practicum.kanban.model.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private List<Task> history = new ArrayList<>();

    @Override
    public void addRecord(Task task) {
        history.add(task);
        int maxHistoryLength = 10;
        if (history.size() > maxHistoryLength) {
            history.remove(0);
        }
    }

    @Override
    public void removeRecord(int id) {
        for (Task task : history) {
            if (task.getId() == id) {
                history.remove(task);
                return;
            }
        }

    }

    @Override
    public List<Task> getHistory() {
        return history;
    }
}
