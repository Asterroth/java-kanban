import java.util.HashMap;

public class TaskManager {
    protected static int taskID;
    protected HashMap<Integer, Task> taskList;
    protected HashMap<Integer, EpicTask> epicList;

    // Adding of new task
    public void addNewTask (Task task) {
        if (task != null) {
            taskID++;
            taskList.put(taskID, task);
        }
    }

    // Adding of new epic task
    public void addNewEpic (EpicTask epicTask) {
        if (epicTask != null) {
            taskID++;
            epicList.put(taskID, epicTask);
        }
    }

    // Adding of new subtask
    public void addNewSub (Integer epicID, SubTask subTask) {
        if (subTask != null) {
            taskID++;
            epicList.get(epicID).subTasks.put(taskID, subTask);
            epicStatus(epicID);
        }
    }

    // Setting of status for epic task
    public void epicStatus (Integer epicID) {
        boolean statusNew = false;
        boolean statusDone = false;
        int newCounter = 0;
        int doneCounter = 0;
        for (Integer subID : epicList.get(epicID).subTasks.keySet()) {
            if (epicList.get(epicID).subTasks.get(subID).status.equals("NEW")) {
                newCounter++;
            } else {
                newCounter--;
            }
        }
        if (newCounter == epicList.get(epicID).subTasks.size()) {
            statusNew = true;
        }
        for (Integer subID : epicList.get(epicID).subTasks.keySet()) {
            if (epicList.get(epicID).subTasks.get(subID).status.equals("DONE")) {
                doneCounter++;
            } else {
                doneCounter--;
            }
        }
        if (doneCounter == epicList.get(epicID).subTasks.size()) {
            statusDone = true;
        }
        if (statusNew) {
            epicList.get(epicID).status = StatusList.statusNEW;
        } else if (statusDone) {
            epicList.get(epicID).status = StatusList.statusDONE;
        } else {
            epicList.get(epicID).status = StatusList.statusINP;
        }
    }

    // Getting of tasks list
    public String getTaskList () {
        String result = "";
        for (Integer ID : taskList.keySet()) {
            result += "\nTask ID: " + ID
                    + " = "
                    + taskList.get(ID).toString();
        }
        return result;
    }

    // Getting of epic tasks list
    public String getEpicList () {
        String result = "";
        for (Integer ID : epicList.keySet()) {
            result += "\nEpic task ID: " + ID
                    + " = "
                    + epicList.get(ID).toString();
        }
        return result;
    }

    // Getting of subtasks list
    public String getSubList () {
        String result = "";
        for (Integer epicID : epicList.keySet()) {
            for (Integer subID : epicList.get(epicID).subTasks.keySet()) {
                result += "\nSubtask ID: " + subID
                        + " = "
                        + epicList.get(epicID).subTasks.get(subID).toString();
            }
        }
        return result;
    }

    // Updating of selected task
    public void updateTask (int ID, Task task) {
        if (taskList.containsKey(ID) && task != null) {
            taskList.put(ID, task);
        } else {
            System.out.println("No such task ID!");
        }
    }

    // Updating of selected epic task
    public void updateEpic (int ID, EpicTask epicTask) {
        if (epicList.containsKey(ID) && epicTask != null) {
            epicList.put(ID, epicTask);
        } else {
            System.out.println("No such epic task ID!");
        }
    }

    // Updating of selected subtask
    public void updateSub (int epicID, int ID, SubTask subTask) {
        if (epicList.get(epicID).subTasks.containsKey(ID) && subTask != null) {
            epicList.get(epicID).subTasks.put(ID, subTask);
            epicStatus(epicID);
        }
    }

    // Getting of selected task
    public String getTask (int ID) {
        if (taskList.containsKey(ID)) {
            return "Task ID = " + ID + ", " + taskList.get(ID).toString();
        } else {
            return "No such task ID!";
        }
    }

    // Getting of selected epic task
    public String getEpic (int ID) {
        if (epicList.containsKey(ID)) {
            return "Epic task ID = " + ID + ", " + epicList.get(ID).toString();
        } else {
            return "No such epic task ID!";
        }
    }

    // Getting of selected epic task with subtasks list
    public String getEpicWithSubs (int ID) {
        String result = "";
        if (epicList.containsKey(ID)) {
            result +="Epic task ID = " + ID + ", " + epicList.get(ID).toString();
            for (Integer subID : epicList.get(ID).subTasks.keySet()) {
                result += "\n\tSubtask ID = " + subID + ", " + epicList.get(ID).subTasks.get(subID).toString();
            }
            return result;
        } else {
            return "No such epic task ID!";
        }
    }

    // Getting of selected subtask
    public String getSub (int ID) {
        for (Integer epicID : epicList.keySet()) {
            if (epicList.get(epicID).subTasks.containsKey(ID)) {
                return "Subtask ID = " + ID + ", " + epicList.get(epicID).subTasks.get(ID).toString();
            }
        }
        return "No such subtask ID!";
    }

    // Removing of selected task
    public void removeTask (int ID) {
        if (taskList.containsKey(ID)) {
            taskList.remove(ID);
        } else {
            System.out.println("No such task ID!");
        }
    }

    // Removing of selected epic task
    public void removeEpic (int ID) {
        if (epicList.containsKey(ID)) {
            epicList.remove(ID);
        } else {
            System.out.println("No such epic task ID!");
        }
    }

    // Removing of selected subtask
    public void removeSub (int ID) {
        boolean wasRemoved = false;
        for (Integer epicID : epicList.keySet()) {
            if (epicList.get(epicID).subTasks.containsKey(ID)) {
                epicList.get(epicID).subTasks.remove(ID);
                epicStatus(epicID);
                wasRemoved = true;
            }
        }
        if (!wasRemoved) {
            System.out.println("No such epic task ID!");
        }
    }

    // Removing of all tasks
    public void clearTasks () { taskList.clear(); }

    // Removing of all epic tasks
    public void clearEpic () { epicList.clear(); }

    // Removing of all subtasks
    public void clearSub () {
        for (Integer epicID : epicList.keySet()) {
            epicList.get(epicID).subTasks.clear();
        }
    }

    @Override
    public String toString() {
        String result = "{\n";
        for (Integer taskID : taskList.keySet()) {
            result = result + "Task ID = " + taskID + ", " + taskList.get(taskID).toString() + "\n";
        }
        for (Integer epicID : epicList.keySet()) {
            result = result + "\nEpic ID = " + epicID + ", " + epicList.get(epicID).toString();
            for (Integer subID : epicList.get(epicID).subTasks.keySet()) {
                result += "\n\tSubtask ID = " + subID + ", " + epicList.get(epicID).subTasks.get(subID).toString();
            }
        }
        return result + "\n}";
    }
}
