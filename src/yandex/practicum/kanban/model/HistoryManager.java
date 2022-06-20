package yandex.practicum.kanban.model;

import yandex.practicum.kanban.model.Task;

import java.util.List;

public interface HistoryManager {

    void addRecord(Task task);

    void removeRecord(int id);

    List<Task> getHistory();
}