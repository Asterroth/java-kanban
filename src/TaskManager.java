import java.util.Map;

public class TaskManager {
    static Map<Integer, Task> taskList;

    public static void setTaskList(Task task) {
        Integer taskID = -1;
        if (taskList != null) {
            taskID = taskList.size() + 1;
        } else {
            taskID = 1;
        }
            taskList.put(taskID, task);
    }

}
