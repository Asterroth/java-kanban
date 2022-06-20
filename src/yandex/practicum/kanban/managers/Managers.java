package yandex.practicum.kanban.managers;

import yandex.practicum.kanban.managers.InMemoryTaskManager;
import yandex.practicum.kanban.model.TaskManager;

public class Managers {

    public static TaskManager getDefault() {
        return new InMemoryTaskManager();
    }

}