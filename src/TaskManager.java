import java.util.Map;

public class TaskManager {
    static Integer taskID;
    static Map<Integer, Task> taskList;

    public static void setTaskList(Task task) {
        taskID = -1;
        if (taskList != null) {
            taskID = taskList.size() + 1;
        } else {
            taskID = 1;
        }
            taskList.put(taskID, task);
    }

}
