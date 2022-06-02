import java.util.HashMap;

public class EpicTask extends Task {
    protected HashMap<Integer, SubTask> subTasks;

    @Override
    public String toString() {
        return "{title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
