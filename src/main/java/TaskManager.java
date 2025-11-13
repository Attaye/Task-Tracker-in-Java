import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private static final String FILE_NAME = "tasks.json";

    private List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return tasks;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) json.append(line);
            String content = json.toString().trim();

            // Simple JSON parsing (for our structure only)
            if (content.length() < 5) return tasks; // "[]"
            content = content.substring(1, content.length() - 1); // remove [ ]
            String[] items = content.split("\\},\\{");

            for (String item : items) {
                item = item.replace("{", "").replace("}", "");
                String[] parts = item.split(",");
                int id = 0;
                String title = "";
                String status = "";

                for (String p : parts) {
                    String[] kv = p.split(":", 2);
                    String key = kv[0].replace("\"", "").trim();
                    String value = kv[1].replace("\"", "").trim();

                    switch (key) {
                        case "id": id = Integer.parseInt(value); break;
                        case "title": title = value; break;
                        case "status": status = value; break;
                    }
                }
                tasks.add(new Task(id, title, status));
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return tasks;
    }

    private void saveTasks(List<Task> tasks) {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write("[");
            for (int i = 0; i < tasks.size(); i++) {
                writer.write(tasks.get(i).toJson());
                if (i < tasks.size() - 1) writer.write(",");
            }
            writer.write("]");
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    public void addTask(String title) {
        List<Task> tasks = loadTasks();
        int id = tasks.isEmpty() ? 1 : tasks.get(tasks.size() - 1).getId() + 1;
        tasks.add(new Task(id, title, "todo"));
        saveTasks(tasks);
        System.out.println("Task added successfully (ID: " + id + ")");
    }

    public void updateTask(int id, String newTitle) {
        List<Task> tasks = loadTasks();
        for (Task t : tasks) {
            if (t.getId() == id) {
                t.setTitle(newTitle);
                saveTasks(tasks);
                System.out.println("Task updated successfully.");
                return;
            }
        }
        System.out.println("Task not found.");
    }

    public void deleteTask(int id) {
        List<Task> tasks = loadTasks();
        boolean removed = tasks.removeIf(t -> t.getId() == id);
        saveTasks(tasks);
        System.out.println(removed ? "Task deleted successfully." : "Task not found.");
    }

    public void markStatus(int id, String status) {
        List<Task> tasks = loadTasks();
        for (Task t : tasks) {
            if (t.getId() == id) {
                t.setStatus(status);
                saveTasks(tasks);
                System.out.println("Task marked as " + status);
                return;
            }
        }
        System.out.println("Task not found.");
    }

    public void listTasks(String filter) {
        List<Task> tasks = loadTasks();
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }
        for (Task t : tasks) {
            if (filter == null || t.getStatus().equalsIgnoreCase(filter)) {
                System.out.println(t);
            }
        }
    }
}