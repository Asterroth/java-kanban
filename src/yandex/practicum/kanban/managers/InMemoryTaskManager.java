package yandex.practicum.kanban.managers;

import yandex.practicum.kanban.model.Status;
import yandex.practicum.kanban.model.Epic;
import yandex.practicum.kanban.model.Subtask;
import yandex.practicum.kanban.model.Task;
import yandex.practicum.kanban.model.TaskManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskManager implements TaskManager {
    private int id = 0;
    private final Map<Integer, Task> tasksMap;
    private final Map<Integer, Epic> epicsMap;
    private final Map<Integer, Subtask> subTasksMap;
    private final InMemoryHistoryManager history; ////

    public InMemoryTaskManager() {
        this.tasksMap = new HashMap<>();
        this.epicsMap = new HashMap<>();
        this.subTasksMap = new HashMap<>();
        this.history = new InMemoryHistoryManager();////
    }

    @Override
    public void addTask(Task task) {
        task.setId(id++);
        tasksMap.put(task.getId(), task);
    }

    @Override
    public void addEpic(Epic epic) {
        epic.setId(id++);
        epicsMap.put(epic.getId(), epic);
    }

    @Override
    public void addSubtask(Subtask subTask) {
        subTask.setId(id++);
        subTasksMap.put(subTask.getId(), subTask);
        getSubTaskList(subTask.getEpicId()).add(subTask.getId());
        updateStatusEpic(subTask.getEpicId());
    }

    @Override
    public Task getTask(int id) {
        if (tasksMap.getOrDefault(id, null) != null) {
            history.addRecord(tasksMap.get(id));
            return tasksMap.get(id);
        } else {
            return null;
        }
    }

    @Override
    public Epic getEpic(int id) {
        if (epicsMap.getOrDefault(id, null) != null) {
            history.addRecord(epicsMap.get(id));
            return epicsMap.get(id);
        } else {
            return null;
        }
    }

    @Override
    public Subtask getSubTask(int id) {
        if (subTasksMap.getOrDefault(id, null) != null) {
            history.addRecord(subTasksMap.get(id));
            return subTasksMap.get(id);
        } else {
            return null;
        }
    }

    @Override
    public Map<Integer, Task> getTasksMap() {
        return tasksMap; //////
    }

    @Override
    public Map<Integer, Epic> getEpicsMap() {
        return epicsMap; //////
    }

    @Override
    public Map<Integer, Subtask> getSubTasksMap() {
        return subTasksMap; ///////
    }

    @Override
    public List<Integer> getSubTaskList(int epicId) {
        if (epicsMap.getOrDefault(epicId, null) != null) {
            return epicsMap.getOrDefault(epicId, null).getSubtasksId();
        } else {
            return null;
        }
    }

    @Override
    public void clearTask() {
        tasksMap.clear();
    }

    @Override
    public void clearEpic() {
        epicsMap.clear();
        subTasksMap.clear();
    }

    @Override
    public void clearSubTask() {
        subTasksMap.clear();
        for (Integer id : epicsMap.keySet()) {
            epicsMap.get(id).setStatus(Status.NEW);
            epicsMap.get(id).getSubtasksId().clear();
        }
    }

    @Override
    public void updateTask(Task task) {
        if (tasksMap.containsKey(task.getId())) {
            tasksMap.put(task.getId(), task);
        }
    }

    @Override
    public void updateEpic(Epic epic) {
        if (epicsMap.containsKey(epic.getId())) {
            epicsMap.put(epic.getId(), epic);
        }
    }

    @Override
    public void updateSubTask(Subtask subTask) {
        if (subTasksMap.containsKey(subTask.getId())) {
            subTasksMap.put(subTask.getId(), subTask);
            updateStatusEpic(subTask.getEpicId());
        }
    }

    @Override
    public void updateStatusEpic(int id) {
        int newCounter = 0;
        int doneCounter = 0;
        if (getSubTaskList(id) != null) {
            for (int i = 0; i < getSubTaskList(id).size(); i++) {
                if (subTasksMap.getOrDefault(getSubTaskList(id).get(i), null) != null) {
                    if (subTasksMap.getOrDefault(getSubTaskList(id).get(i), null).getStatus() == Status.NEW) {
                        newCounter++;
                    } else {
                        newCounter--;
                    }
                    if (subTasksMap.get(getSubTaskList(id).get(i)).getStatus() == Status.DONE) {
                        doneCounter++;
                    } else {
                        doneCounter--;
                    }
                }
            }
            if (newCounter == getSubTaskList(id).size()) {
                epicsMap.get(id).setStatus(Status.NEW);
            } else if (doneCounter == getSubTaskList(id).size()) {
                epicsMap.get(id).setStatus(Status.DONE);
            } else {
                epicsMap.get(id).setStatus(Status.IN_PROGRESS);
            }

        }
    }

    @Override
    public void removeTask(int id) {
        if (tasksMap.getOrDefault(id, null) != null) {
            tasksMap.remove(id);
            history.removeRecord(id);
        }
    }

    @Override
    public void removeEpic(int id) {
        if (getSubTaskList(id) != null) {
            for (int i = 0; i < getSubTaskList(id).size(); i++) {
                subTasksMap.remove(getSubTaskList(id).get(i));
                history.removeRecord(getSubTaskList(id).get(i));
            }
            epicsMap.remove(id);
            history.removeRecord(id);
        }
    }

    @Override
    public void removeSubTask(int id) {
        if (subTasksMap.containsKey(id)) {
            Epic epic = getEpic(subTasksMap.get(id).getEpicId());
            epic.getSubtasksId().remove((Integer) id);
            updateStatusEpic(epic.getId());
            subTasksMap.remove(id);
            history.removeRecord(id);
        }
    }

    @Override
    public List<Task> getHistory() {
        return history.getHistory();
    }
}
