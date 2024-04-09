import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class Task {
    private String name;
    private String description;
    private ArrayList<String> subTasks;
    private float completed;

    public Task(String name) {
        this.name = name;
        this.description = "";
        this.subTasks = new ArrayList<>();
        this.completed = 0.00f;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getSubTasks() {
        return subTasks;
    }

    public void addSubTask(String subTask) {
        subTasks.add(subTask);
    }

    public void removeSubTask(String subTask) {
        subTasks.remove(subTask);
    }

    public float isCompleted() {
        return completed;
    }

    public void setCompleted(float completed) {
        this.completed = completed;
    }
}

public class ToDoList {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    editTask();
                    break;
                case 3:
                    removeTask();
                    break;
                case 4:
                    displayTasks();
                    break;
                case 5:
                    markAsComplete();
                    break;
                case 6:
                    System.out.println("Exiting program...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n===== To-Do List Menu =====");
        System.out.println("1. Add Task");
        System.out.println("2. Edit Task");
        System.out.println("3. Remove Task");
        System.out.println("4. Display Tasks");
        System.out.println("5. Mark as Complete");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice() {
        int choice = 0;
        try {
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Consume invalid input
        }
        return choice;
    }

    private static void addTask() {
        System.out.print("Enter task name: ");
        String taskName = scanner.nextLine();
        Task task = new Task(taskName);

        System.out.print("Do you want to enter a description? (yes/no): ");
        String addDescription = scanner.nextLine();
        if (addDescription.equalsIgnoreCase("yes")) {
            System.out.print("Enter task description (max 50 words): ");
            String description = scanner.nextLine();
            task.setDescription(description);
        }

        System.out.print("Do you want to add sub-tasks? (yes/no): ");
        String addSubTasks = scanner.nextLine();
        if (addSubTasks.equalsIgnoreCase("yes")) {
            System.out.print("Enter the number of sub-tasks: ");
            int numSubTasks = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            for (int i = 0; i < numSubTasks; i++) {
                System.out.print("Enter sub-task " + (i + 1) + ": ");
                String subTask = scanner.nextLine();
                task.addSubTask(subTask);
            }
        }

        tasks.add(task);
        System.out.println("Task added successfully!");
    }

    private static void editTask() {
        // Display the list of tasks for the user to select from
        System.out.println("\nSelect the task you want to edit:");
        // displayTasks();

        // Ask the user to input the index of the task they want to edit
        System.out.print("Enter the index of the task you want to edit: ");
        int taskIndex = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        // Check if the entered index is valid
        if (taskIndex < 1 || taskIndex > tasks.size()) {
            System.out.println("Invalid task index.");
            return;
        }

        Task task = tasks.get(taskIndex - 1);

        // Provide options for what aspect of the task the user wants to edit
        System.out.println("Select what you want to edit:");
        System.out.println("1. Name");
        System.out.println("2. Description");
        System.out.println("3. Sub-tasks");
        System.out.print("Enter your choice: ");
        int editChoice = getUserChoice();

        switch (editChoice) {
            case 1:
                // Edit task name
                System.out.print("Enter new task name: ");
                String newName = scanner.nextLine();
                task.setName(newName);
                System.out.println("Task name updated successfully.");
                break;
            case 2:
                // Edit task description
                System.out.print("Enter new task description: ");
                String newDescription = scanner.nextLine();
                task.setDescription(newDescription);
                System.out.println("Task description updated successfully.");
                break;
            case 3:
                // Edit sub-tasks
                ArrayList<String> subTasks = task.getSubTasks();
                boolean backToMenu = false;
                while (!backToMenu) {
                    System.out.println("1. Edit existing sub-task");
                    System.out.println("2. Add new sub-task");
                    System.out.println("3. Back to main menu");
                    System.out.print("Enter your choice: ");
                    int subTaskChoice = getUserChoice();
                    switch (subTaskChoice) {
                        case 1:
                            // Edit existing sub-task
                            System.out.println("Select the sub-task you want to edit:");
                            for (int i = 0; i < subTasks.size(); i++) {
                                System.out.println((i + 1) + ". " + subTasks.get(i));
                            }
                            System.out.print("Enter the index of the sub-task you want to edit: ");
                            int subTaskIndex = scanner.nextInt();
                            scanner.nextLine(); // Consume newline character
                            if (subTaskIndex < 1 || subTaskIndex > subTasks.size()) {
                                System.out.println("Invalid sub-task index.");
                            } else {
                                System.out.print("Edit sub-task: ");
                                String newSubTask = scanner.nextLine();
                                subTasks.set(subTaskIndex - 1, newSubTask);
                                System.out.println("Sub-task updated successfully.");
                            }
                            break;
                        case 2:
                            // Add new sub-task
                            System.out.print("Enter new sub-task: ");
                            String newSubTask = scanner.nextLine();
                            subTasks.add(newSubTask);
                            System.out.println("New sub-task added successfully.");
                            break;
                        case 3:
                            backToMenu = true; // Exit the loop and go back to the main menu
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                }
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void removeTask() {
        System.out.println("\nSelect the task you want to remove:");
        // displayTasks();

        System.out.print("Enter the index of the task you want to remove: ");
        int taskIndex = getUserChoice();

        if (taskIndex < 1 || taskIndex > tasks.size()) {
            System.out.println("Invalid task index.");
            return;
        }

        Task taskToRemove = tasks.get(taskIndex - 1);

        System.out.println("1. Remove whole task");
        System.out.println("2. Remove sub-task");
        System.out.print("Enter your choice: ");
        int removeChoice = getUserChoice();

        switch (removeChoice) {
            case 1:
                // Remove whole task
                tasks.remove(taskToRemove);
                System.out.println("Task '" + taskToRemove.getName() + "' removed successfully.");
                break;
            case 2:
                // Remove sub-task
                ArrayList<String> subTasks = taskToRemove.getSubTasks();
                if (subTasks.isEmpty()) {
                    System.out.println("No sub-tasks found for this task.");
                    return;
                }

                System.out.println("\nSelect the sub-task you want to remove:");
                for (int i = 0; i < subTasks.size(); i++) {
                    System.out.println((i + 1) + ". " + subTasks.get(i));
                }
                System.out.print("Enter the index of the sub-task you want to remove: ");
                int subTaskIndex = getUserChoice();

                if (subTaskIndex < 1 || subTaskIndex > subTasks.size()) {
                    System.out.println("Invalid sub-task index.");
                    return;
                }

                String removedSubTask = subTasks.remove(subTaskIndex - 1);
                System.out.println("Sub-task '" + removedSubTask + "' removed successfully.");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void displayTasks() {
        System.out.println("\n===== Tasks =====");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            double percentageComplete = calculateCompletion(task);
            float floatValue = (float) percentageComplete;
            task.setCompleted(floatValue);
            float percentage = task.isCompleted();
            System.out.print("Task " + (i + 1) + ": " + task.getName() + "\t");
            System.out.println("[" + percentage + "%] ");
        }
        System.out.println("\nEnter the index number of the task for detailed view or 'back' to go back: ");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("back")) {
            return;
        }
        try {
            int index = Integer.parseInt(input) - 1;
            if (index >= 0 && index < tasks.size()) {
                Task selectedTask = tasks.get(index);
                displayDetailedView(selectedTask);
            } else {
                System.out.println("Invalid index. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid index or 'back'.");
        }
    }

    private static void displayDetailedView(Task task) {
        System.out.println("Task: " + task.getName());
        System.out.println("Description: " + task.getDescription());
        ArrayList<String> subTasks = task.getSubTasks();
        if (!subTasks.isEmpty()) {
            System.out.println("Sub-tasks:");
            for (int i = 0; i < subTasks.size(); i++) {
                System.out.println((i + 1) + ". " + subTasks.get(i));
            }
        }
        System.out.println("Enter 'back' to return to the main menu.");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("back")) {
            return;
        }
    }

    private static double calculateCompletion(Task task) {
        if (task.getSubTasks().isEmpty()) {
            return task.isCompleted();
        } else {
            int numSubTasks = task.getSubTasks().size();
            int completedSubTasks = 0;
            for (String subTask : task.getSubTasks()) {
                if (subTask != null && subTask.contains("completed")) {
                    completedSubTasks++;
                }
                // Check if sub-task is completed
                // Increment completedSubTasks if it is completed
            }
            return ((double) completedSubTasks / numSubTasks) * 100;
        }
    }

    private static void markAsComplete() {
        System.out.print("Enter the task index to mark as complete: ");
        int taskIndex = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        if (taskIndex < 1 || taskIndex > tasks.size()) {
            System.out.println("Invalid task index.");
            return;
        }
        Task task = tasks.get(taskIndex - 1);

        if (task.getSubTasks().isEmpty()) {
            task.setCompleted(100);
            System.out.println("Task marked as complete.");
        } else {
            // Show marking menu
            System.out.println("Marking Menu:");
            System.out.println("1. Mark the whole task as complete");
            System.out.println("2. Mark sub-tasks as complete");

            System.out.print("Enter your choice: ");
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    task.setCompleted(100);
                    System.out.println("Task marked as complete.");
                    break;
                case 2:
                    // Display sub-tasks
                    System.out.println("Sub-tasks:");
                    ArrayList<String> subTasks = task.getSubTasks();
                    for (int i = 0; i < subTasks.size(); i++) {
                        System.out
                                .println((i + 1) + ". " + subTasks.get(i));
                    }

                    // Select sub-tasks to mark as complete
                    System.out
                            .print("Enter the index numbers of sub-tasks to mark as complete (separated by commas): ");
                    String indicesInput = scanner.nextLine();
                    String[] indicesStr = indicesInput.split(",");
                    for (String indexStr : indicesStr) {
                        try {
                            int index = Integer.parseInt(indexStr.trim()) - 1;
                            if (index >= 0 && index < subTasks.size()) {
                                subTasks.set(index, subTasks.get(index) + " - completed");
                                System.out.println("Sub-task marked as complete.");
                            } else {
                                System.out.println("Invalid sub-task index: " + indexStr);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input: " + indexStr);
                        }
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Task not marked as complete.");
            }
        }
    }

}
