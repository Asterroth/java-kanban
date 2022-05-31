//package yandex.practicum.kanban;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        Task task1 = new Task();
        task1.setTitle("Task #1");
        task1.setDescription("Description of Task #1");
        task1.setStatus(StatusList.statusNEW);
        TaskManager taskManager = new TaskManager();
        HashMap<Integer, Task> newTaskList = new HashMap<>();
        taskManager.taskList = newTaskList;
        taskManager.addNewTask(task1);

        Task task2 = new Task();
        task2.setTitle("Task #2");
        task2.setDescription("Description of Task #2");
        task2.setStatus(StatusList.statusINP);
        taskManager.addNewTask(task2);

        Task task3 = new Task();
        task3.setTitle("Task #3");
        task3.setDescription("Description of Task #3");
        task3.setStatus(StatusList.statusDONE);
        taskManager.addNewTask(task3);

        System.out.println("\nTotal list of created tasks:\n" + taskManager);

        Task task4 = new Task();
        int updateID = 3;
        task4.setTitle("Task #3-1");
        task4.setDescription("Description of Task #3-1");
        task4.setStatus(StatusList.statusDONE);
        taskManager.updateTask(updateID, task4);

        System.out.println("\nTask No. 3 was updated:\n" + taskManager);

        System.out.println("\nShow Task No. 2:\n" + taskManager.getTask(2));

        taskManager.removeTask(1);
        System.out.println("\nTask No. 1 was removed:\n" + taskManager);

        taskManager.clearAllTasks();
        System.out.println("\nTotally cleaned task list:\n" + taskManager);



        SubTask subTask1 = new SubTask();
        subTask1.setTitle("SubTask #1");
        subTask1.setDescription("Description of SubTask #1");
        subTask1.setStatus(StatusList.statusNEW);
    }
}
