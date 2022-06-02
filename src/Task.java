public class Task {
    protected String title;
    protected String description;
    protected String status;

    @Override
    public String toString() {
        return "{title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
