package yandex.practicum.kanban;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager{
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
        history.remove(id);
    }

    @Override
    public List<Task> getHistory() {
        return history;
    }
}
