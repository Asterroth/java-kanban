//package yandex.practicum.kanban;

public class SubTask extends Task {

    @Override
    public String toString() {
        return "SubTask = {" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
