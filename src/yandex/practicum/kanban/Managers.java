package yandex.practicum.kanban;

public class Managers {

    public static TaskManager getDefault() {
        return new InMemoryTaskManager();
    }

}