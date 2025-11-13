public class Task {


    private int id;
    private String title;
    private String status; // todo, in-progress, done

    public Task(int id, String title, String status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getStatus() { return status; }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Convert Task → JSON-like String
    public String toJson() {
        return String.format("{\"id\":%d,\"title\":\"%s\",\"status\":\"%s\"}",
                id, title.replace("\"", "\\\""), status);
    }

    @Override
    public String toString() {
        return "[" + id + "] " + title + " (" + status + ")";
    }
}
