package yandex.practicum.kanban;

public class Task {
    protected Integer taskID;
    protected String title;
    protected String description;
    protected StatusList status;

    @Override
    public String toString() {
        return "{taskID=" + "'" + taskID + "'" +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
