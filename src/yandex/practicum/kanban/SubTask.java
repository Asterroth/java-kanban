package yandex.practicum.kanban;

public class SubTask extends Task {

    protected int epicOwnerId;

    @Override
    public String toString() {
        return "{epicOwnerId=" + epicOwnerId +
                ", taskId=" + taskId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
