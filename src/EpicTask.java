//package yandex.practicum.kanban;

public class EpicTask extends Task {

    @Override
    public String toString() {
        return "EpicTask = {" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
