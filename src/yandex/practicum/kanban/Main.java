package yandex.practicum.kanban;

import java.util.HashMap;

public class Main {
    static TaskManager taskManager = new TaskManager();

    public static void main(String[] args) {
        taskManager.taskList = new HashMap<>();
        taskManager.epicList = new HashMap<>();

// Fill tasks
        for (int i = 0; i < 3; i++) {
            addNewTask("Task #" + (taskManager.taskId + 1)
                    , "Description of Task #" + (taskManager.taskId + 1)
                    , StatusList.NEW);
        }
// Fill epic tasks
        for (int i = 0; i < 3; i++) {
            addNewEpic("Epic #" + (taskManager.taskId + 1)
                    , "Description of Epic #" + (taskManager.taskId + 1)
                    , StatusList.NEW);
        }
// Fill subtasks for Epic id = 4
        for (int i = 0; i < 2; i++) {
            addNewSub(4, "Subtask #" + (taskManager.taskId + 1)
                    , "Description of Subtask #" + (taskManager.taskId + 1)
                    , StatusList.NEW);
        }

// Fill subtasks for Epic id = 5
        for (int i = 0; i < 2; i++) {
            addNewSub(5, "Subtask #" + (taskManager.taskId + 1)
                    , "Description of Subtask #" + (taskManager.taskId + 1)
                    , StatusList.DONE);
        }

// Fill subtasks for Epic id = 6
        addNewSub(6, "Subtask #" + (taskManager.taskId + 1)
                , "Description of Subtask #" + (taskManager.taskId + 1)
                , StatusList.NEW);
        addNewSub(6, "Subtask #" + (taskManager.taskId + 1)
                , "Description of Subtask #" + (taskManager.taskId + 1)
                , StatusList.IN_PROGRESS);

        //System.out.println("\nInitial list of all created tasks:\n" + taskManager);
        System.out.println(taskList());
        System.out.println(epicList());
        System.out.println(subList());


// Update task #3
        updateTask(3, "NewTask #3", "Description of NewTask #3", StatusList.IN_PROGRESS);
// Update epic task #6
        updateEpic(6, "NewEpicTask #6", "Description of NewEpicTask #6");
// Update subtask #10 in epic task #5
        updateSub(5, 10, "NewSub #10", "Description of NewSub #10", StatusList.IN_PROGRESS);
        System.out.println("\nTotal list of tasks after updating of task #3, epic task #6 and subtask #10:\n" + taskManager);
// Printing of task #2, epic task #4 without and with subtasks, subtask #12
        System.out.println("\nTask #2 content:\n" + getTask(2));
        System.out.println("\nEpic task #4 content:\n" + getEpic(4));
        System.out.println("\nEpic task #4 content with subtasks list:\n" + getEpicAndSubs(4));
        System.out.println("\nSubtask #12 content:\n" + getSub(12));
// Removing task #1, epic task #4 and sutask #10
        removeTask(1);
        removeEpic(4);
        removeSub(10);

        System.out.println("\nTotal list of tasks after removing of task #1, epic task #4 and subtask #10:\n" + taskManager);
// Clear of all subtasks
        clearSub();
        System.out.println("\nTotal list of tasks after removing of all subtasks:\n" + taskManager);
// Clear of all epic tasks
        clearEpic();
        System.out.println("\nTotal list of tasks after removing of all epic tasks:\n" + taskManager);
// Clear of all tasks
        clearTask();
        System.out.println("\nTotal list of tasks after removing of all tasks:\n" + taskManager);

    }

    public static void addNewTask (String title, String description, StatusList status) {
        Task task = new Task();
        task.title = title;
        task.description = description;
        task.status = status;
        taskManager.addNewTask(task);
    }

    public static void addNewEpic (String title, String description, StatusList status) {
        EpicTask epicTask = new EpicTask();
        epicTask.title = title;
        epicTask.description = description;
        epicTask.status = status;
        HashMap<Integer, SubTask> subList = new HashMap<>();
        epicTask.subTasks = subList;
        taskManager.addNewEpic(epicTask);
    }

    public static void addNewSub (int epicId, String title, String description, StatusList status) {
        SubTask subTask = new SubTask();
        subTask.title = title;
        subTask.description = description;
        subTask.status = status;
        taskManager.addNewSub(epicId, subTask);
    }

    public static String taskList () { return taskManager.getTaskList(); }

    public static String epicList () { return taskManager.getEpicList(); }

    public static String subList () { return taskManager.getSubList(); }

    public static void updateTask (int id, String title, String description, StatusList status) {
        Task task = new Task();
        task.title = title;
        task.description = description;
        task.status = status;
        taskManager.updateTask(id, task);
    }

    public static void updateEpic (int id, String title, String description) {
        EpicTask epicTask = new EpicTask();
        epicTask.title = title;
        epicTask.description = description;
        epicTask.status = taskManager.epicList.get(id).status;
        HashMap<Integer, SubTask> subList = new HashMap<>();
        for (Integer subId : taskManager.epicList.get(id).subTasks.keySet()) {
            subList.put(subId, taskManager.epicList.get(id).subTasks.get(subId));
        }
        epicTask.subTasks = subList;
        taskManager.updateEpic(id, epicTask);
    }

    public static void updateSub (int epicId, int subId, String title, String description, StatusList status) {
        SubTask subTask = new SubTask();
        subTask.title = title;
        subTask.description = description;
        subTask.status = status;
        taskManager.updateSub(epicId, subId, subTask);
    }

    public static String getTask (int id) {
        return taskManager.getTask(id);
    }

    public static String getEpic (int id) {
        return taskManager.getEpic(id);
    }

    public static String getEpicAndSubs (int id) { return taskManager.getEpicWithSubs(id); }

    public static String getSub (int id) {
        return taskManager.getSub(id);
    }

    public static void removeTask (int id) {
        taskManager.removeTask(id);
    }

    public static void removeEpic (int id) {
        taskManager.removeEpic(id);
    }

    public static void removeSub (int id) {
        taskManager.removeSub(id);
    }

    public static void clearTask () { taskManager.clearTasks(); }

    public static void clearEpic () { taskManager.clearEpic(); }

    public static void clearSub () { taskManager.clearSub(); }
}