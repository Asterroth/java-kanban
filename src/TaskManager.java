//package yandex.practicum.kanban;

import java.util.HashMap;

public class TaskManager {
    private static int taskID;
    private static int subTaskID;
    private static int epicTaskID;
    protected HashMap<Integer, Task> taskList;
    protected HashMap<Integer[], SubTask> subTaskList;
    protected HashMap<Integer, EpicTask> epicTaskList;

    public void addNewTask (Task task) {
        if (task != null) {
            taskID++;
            taskList.put(taskID, task);
        }
    }

    public void updateTask (int ID, Task task) {
        if (taskList.containsKey(ID) && task != null) {
            taskList.put(ID, task);
        } else {
            System.out.println("No such ID task!");
        }
    }

    public String getTask (int ID) {
        if (taskList.containsKey(ID)) {
            return String.valueOf(taskList.get(ID));
        } else {
            return "No such ID task!";
        }
    }

    public void removeTask (int ID) {
        if (taskList.containsKey(ID)) {
            taskList.remove(ID);
        } else {
            System.out.println("No such ID task!");
        }
    }

    public void clearAllTasks () {
        taskList.clear();
    }

    //public void addNewSubTask (SubTask subTask) {
    //    if (subTask != null) {
    //        taskID++;
    //        subTaskList.put(taskID, subTask);
    //    }
    //}

    public void addNewEpicTask (EpicTask epicTask) {
        if (epicTask != null) {
            taskID++;
            taskList.put(taskID, epicTask);
        }
    }

    @Override
    public String toString() {
        String result = "taskList={";
        for (Integer ID : taskList.keySet()) {
            result = result + "\nTask ID = " + ID + ", " + taskList.get(ID).toString();
        }
        return result + "\n}";
    }
}
