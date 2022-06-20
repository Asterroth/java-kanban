package yandex.practicum.kanban.model;

import yandex.practicum.kanban.model.Epic;
import yandex.practicum.kanban.model.Subtask;
import yandex.practicum.kanban.model.Task;

import java.util.List;
import java.util.Map;

public interface TaskManager {

    void addTask(Task task);

    void addEpic(Epic epic);

    void addSubtask(Subtask subTask);

    Task getTask(int id);

    Epic getEpic(int id);

    Subtask getSubTask(int id);

    List<Integer> getSubTaskList(int epicId);

    Map<Integer, Task> getTasksMap();

    Map<Integer, Epic> getEpicsMap();

    Map<Integer, Subtask> getSubTasksMap();

    void clearTask();

    void clearEpic();

    void clearSubTask();

    void updateTask(Task task);

    void updateEpic(Epic epic);

    void updateSubTask(Subtask subTask);

    void updateStatusEpic(int id);

    void removeTask(int id);

    void removeEpic(int id);

    void removeSubTask(int id);

    List<Task> getHistory();
}