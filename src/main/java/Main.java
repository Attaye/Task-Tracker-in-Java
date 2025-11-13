public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        if (args.length == 0) {
            System.out.println("""
                Usage:
                  task-cli add <title>
                  task-cli update <id> <new title>
                  task-cli delete <id>
                  task-cli mark-in-progress <id>
                  task-cli mark-done <id>
                  task-cli list [todo|done|in-progress]
            """);
            return;
        }

        String command = args[0];

        switch (command) {
            case "add":
                if (args.length < 2) {
                    System.out.println("Usage: task-cli add <title>");
                    return;
                }
                manager.addTask(args[1]);
                break;

            case "update":
                if (args.length < 3) {
                    System.out.println("Usage: task-cli update <id> <new title>");
                    return;
                }
                manager.updateTask(Integer.parseInt(args[1]), args[2]);
                break;

            case "delete":
                if (args.length < 2) {
                    System.out.println("Usage: task-cli delete <id>");
                    return;
                }
                manager.deleteTask(Integer.parseInt(args[1]));
                break;

            case "mark-in-progress":
                manager.markStatus(Integer.parseInt(args[1]), "in-progress");
                break;

            case "mark-done":
                manager.markStatus(Integer.parseInt(args[1]), "done");
                break;

            case "list":
                if (args.length == 1)
                    manager.listTasks(null);
                else
                    manager.listTasks(args[1]);
                break;

            default:
                System.out.println("Unknown command: " + command);
        }
    }
}