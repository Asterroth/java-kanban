package yandex.practicum.kanban;

import java.util.List;

public interface HistoryManager {

    void addRecord(Task task);

    void removeRecord(int id);

    List<Task> getHistory();
}