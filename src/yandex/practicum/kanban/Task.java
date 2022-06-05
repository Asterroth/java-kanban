package yandex.practicum.kanban;

public class Task {
    protected Integer taskId;
    protected String title;
    protected String description;
    protected StatusList status;

    @Override
    public String toString() {
        return "{taskID=" + "'" + taskId + "'" +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
