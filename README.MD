# Task Tracker in Pure Java

A simple **command-line Task Tracker** application written in **pure Java (no frameworks)**.  
This project helps you manage daily tasks directly from your terminal — add, list, update, and delete tasks efficiently.

---

## Features

- Add new tasks with title and description
- List all tasks with their IDs and status
- Mark tasks as completed
- Delete tasks by ID
- Data is stored locally in a text file (`tasks.txt`) for persistence

---

## Technologies Used

- Java 17+
- File I/O (for data storage)
- Object-Oriented Programming principles
- Command-line based interaction

---

##  Project Structure
Task-Tracker-in-Java/
│
├── src/
│ ├── Main.java # Entry point
│ ├── Task.java # Task model class
│ ├── TaskManager.java # Logic for handling tasks
| ├── tasks.json # Data file (auto-created)
│
└── README.md # Project description


---

##  How to Run

### 1 Compile the project
Make sure you’re inside the project folder and run:
```bash
javac -d out src/*.java

---
###Run the application
java -cp out Main

### Example Usage
===== Task Tracker =====
1. Add Task
2. List Tasks
3. Mark Task as Completed
4. Delete Task
5. Exit
Enter your choice:

### Add Task
Enter title: Learn Java
Enter description: Study OOP and practice exercises
 Task added successfully!

--- Task List ---
[1] Learn Java - Pending
------------------

### Mark Task as Completed
Enter task ID to complete: 1
Task marked as completed!

###Delete Task
Enter task ID to delete: 1
Task deleted successfully!
```

### Author
Mustafa Attaye
📍 Germany
GitHub: Attaye