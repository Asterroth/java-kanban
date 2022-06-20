package yandex.practicum.kanban;

import yandex.practicum.kanban.managers.Managers;
import yandex.practicum.kanban.model.*;

public class Test {
    TaskManager taskManager = Managers.getDefault();

    public void testing() {
        Task task1 = new Task("Important and urgent", "Do it right now!", Status.NEW);
        taskManager.addTask(task1);
        taskManager.addTask(task1);
        taskManager.addTask(task1);
        taskManager.addTask(task1);
        taskManager.addTask(task1);
        taskManager.addTask(task1);
        taskManager.addTask(task1);
        taskManager.addTask(task1);
        taskManager.addTask(task1);
        taskManager.addTask(task1);
        taskManager.addTask(task1);
        taskManager.addTask(task1);
        taskManager.addTask(task1);

        Task task2 = new Task("Important and non-urgent", "Plan to do it!", Status.IN_PROGRESS);
        taskManager.addTask(task2);

        Task task3 = new Task("Unimportant and urgent", "Delegate it!", Status.DONE);
        taskManager.addTask(task3);

        Task task4 = new Task("Unimportant and non-urgent", "Maybe someday...", Status.NEW);
        taskManager.addTask(task4);

        Epic epic1 = new Epic("Super epic task", "It must be awesome!");
        taskManager.addEpic(epic1);

        Subtask subtask1 = new Subtask("First step", "Just start do it", Status.IN_PROGRESS, epic1.getId());
        taskManager.addSubtask(subtask1);

        Subtask subtask2 = new Subtask("Second step", "Keep going", Status.NEW, epic1.getId());
        taskManager.addSubtask(subtask2);

        Epic epic2 = new Epic("Very big task", "Something very difficult");
        taskManager.addEpic(epic2);

        Subtask subtask3 = new Subtask("Start doing", "Decide to start", Status.DONE, epic2.getId());
        taskManager.addSubtask(subtask3);

        taskManager.getTask(task1.getId());  // 1
        taskManager.removeTask(task1.getId());  // 1
        taskManager.getTask(task2.getId());  // 2
        taskManager.getTask(task3.getId());  // 3
        taskManager.getTask(task4.getId());  // 4
        taskManager.getEpic(epic1.getId());  // 5
        taskManager.getEpic(epic2.getId());  // 6
        taskManager.getSubTask(subtask1.getId());  // 7
        taskManager.getSubTask(subtask2.getId());  // 8
        taskManager.getSubTask(subtask3.getId());  // 9
        taskManager.getTask(task3.getId());  // 10
        taskManager.getEpic(epic1.getId());  // 11 -> 10
        System.out.print("History, last 10 requests:");
        System.out.println(taskManager.getHistory());

    }
}
