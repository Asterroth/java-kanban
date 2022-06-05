package yandex.practicum.kanban;

import java.util.HashMap;

public class TaskManager {
    protected static int taskId;
    protected HashMap<Integer, Task> taskList;
    protected HashMap<Integer, EpicTask> epicList;

    // Adding of new task
    public void addNewTask (Task task) {
        if (task != null) {
            taskId++;
            task.taskId = taskId;
            taskList.put(task.taskId, task);
        }
    }

    // Adding of new epic task
    public void addNewEpic (EpicTask epicTask) {
        if (epicTask != null) {
            taskId++;
            epicTask.taskId = taskId;
            epicList.put(epicTask.taskId, epicTask);
        }
    }

    // Adding of new subtask
    public void addNewSub (Integer epicId, SubTask subTask) {
        if (subTask != null) {
            taskId++;
            subTask.epicOwnerId = epicId;
            subTask.taskId = taskId;
            epicList.get(epicId).subTasks.put(subTask.taskId, subTask);
            epicStatus(epicId);
        }
    }

    // Setting of status for epic task
    public void epicStatus (Integer epicId) {
        boolean statusNew = false;
        boolean statusDone = false;
        int newCounter = 0;
        int doneCounter = 0;
        for (Integer subId : epicList.get(epicId).subTasks.keySet()) {
            if (epicList.get(epicId).subTasks.get(subId).status.equals(StatusList.NEW)) {
                newCounter++;
            } else {
                newCounter--;
            }
        }
        if (newCounter == epicList.get(epicId).subTasks.size()) {
            statusNew = true;
        }
        for (Integer subId : epicList.get(epicId).subTasks.keySet()) {
            if (epicList.get(epicId).subTasks.get(subId).status.equals(StatusList.DONE)) {
                doneCounter++;
            } else {
                doneCounter--;
            }
        }
        if (doneCounter == epicList.get(epicId).subTasks.size()) {
            statusDone = true;
        }
        if (statusNew) {
            epicList.get(epicId).status = StatusList.NEW;
        } else if (statusDone) {
            epicList.get(epicId).status = StatusList.DONE;
        } else {
            epicList.get(epicId).status = StatusList.IN_PROGRESS;
        }
    }

    // Getting of tasks list
    public String getTaskList () {
        String result = "";
        for (Integer id : taskList.keySet()) {
            result += "\nTask #" + id
                    + ": "
                    + taskList.get(id).toString();
        }
        return result;
    }

    // Getting of epic tasks list
    public String getEpicList () {
        String result = "";
        for (Integer id : epicList.keySet()) {
            result += "\nEpic task #" + id
                    + ": "
                    + epicList.get(id).toString();
        }
        return result;
    }

    // Getting of subtasks list
    public String getSubList () {
        String result = "";
        for (Integer epicId : epicList.keySet()) {
            for (Integer subId : epicList.get(epicId).subTasks.keySet()) {
                result += "\nSubtask #" + subId
                        + ": "
                        + epicList.get(epicId).subTasks.get(subId).toString();
            }
        }
        return result;
    }

    // Updating of selected task
    public void updateTask (int id, Task task) {
        if (taskList.containsKey(id) && task != null) {
            taskList.put(task.taskId = id, task);
        } else {
            System.out.println("No such task id!");
        }
    }

    // Updating of selected epic task
    public void updateEpic (int id, EpicTask epicTask) {
        if (epicList.containsKey(id) && epicTask != null) {
            epicList.put(epicTask.taskId = id, epicTask);
        } else {
            System.out.println("No such epic task id!");
        }
    }

    // Updating of selected subtask
    public void updateSub (int epicId, int id, SubTask subTask) {
        if (epicList.get(epicId).subTasks.containsKey(id) && subTask != null) {
            subTask.epicOwnerId = epicId;
            epicList.get(epicId).subTasks.put(subTask.taskId = id, subTask);
            epicStatus(epicId);
        }
    }

    // Getting of selected task
    public String getTask (int id) {
        if (taskList.containsKey(id)) {
            return "Task #" + id + ", " + taskList.get(id).toString();
        } else {
            return "No such task id!";
        }
    }

    // Getting of selected epic task
    public String getEpic (int id) {
        if (epicList.containsKey(id)) {
            return "Epic task #" + id + ", " + epicList.get(id).toString();
        } else {
            return "No such epic task id!";
        }
    }

    // Getting of selected epic task with subtasks list
    public String getEpicWithSubs (int id) {
        String result = "";
        if (epicList.containsKey(id)) {
            result +="Epic task #" + id + ", " + epicList.get(id).toString();
            for (Integer subId : epicList.get(id).subTasks.keySet()) {
                result += "\n\tSubtask #" + subId + ", " + epicList.get(id).subTasks.get(subId).toString();
            }
            return result;
        } else {
            return "No such epic task id!";
        }
    }

    // Getting of selected subtask
    public String getSub (int id) {
        for (Integer epicId : epicList.keySet()) {
            if (epicList.get(epicId).subTasks.containsKey(id)) {
                return "Subtask #" + id + ", " + epicList.get(epicId).subTasks.get(id).toString();
            }
        }
        return "No such subtask id!";
    }

    // Removing of selected task
    public void removeTask (int id) {
        if (taskList.containsKey(id)) {
            taskList.remove(id);
        } else {
            System.out.println("No such task id!");
        }
    }

    // Removing of selected epic task
    public void removeEpic (int id) {
        if (epicList.containsKey(id)) {
            epicList.remove(id);
        } else {
            System.out.println("No such epic task id!");
        }
    }

    // Removing of selected subtask
    public void removeSub (int id) {
        boolean wasRemoved = false;
        for (Integer epicId : epicList.keySet()) {
            if (epicList.get(epicId).subTasks.containsKey(id)) {
                epicList.get(epicId).subTasks.remove(id);
                epicStatus(epicId);
                wasRemoved = true;
            }
        }
        if (!wasRemoved) {
            System.out.println("No such epic task id!");
        }
    }

    // Removing of all tasks
    public void clearTasks () { taskList.clear(); }

    // Removing of all epic tasks
    public void clearEpic () { epicList.clear(); }

    // Removing of all subtasks
    public void clearSub () {
        for (Integer epicId : epicList.keySet()) {
            epicList.get(epicId).subTasks.clear();
        }
    }

    @Override
    public String toString() {
        String result = "{\n";
        for (Integer taskId : taskList.keySet()) {
            result = result + "Task #" + taskId + ": " + taskList.get(taskId).toString() + "\n";
        }
        for (Integer epicId : epicList.keySet()) {
            result = result + "\nEpic #" + epicId + ": " + epicList.get(epicId).toString();
            for (Integer subId : epicList.get(epicId).subTasks.keySet()) {
                result += "\n\tSubtask #" + subId + ": " + epicList.get(epicId).subTasks.get(subId).toString();
            }
        }
        return result + "\n}";
    }
}
