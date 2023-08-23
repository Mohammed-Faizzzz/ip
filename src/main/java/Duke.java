import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private static final String name = "Bartholomew Hamish Montgomery";
    private static final String line = "______________________________________________________________________________________\n";
    public static void main(String[] args) {

        greet();
        startChat();
    }

    private static void greet() {
        String greeting = line + "I extend to you my utmost felicitations, User! I am " + name + "." + "\n" + "What may I do for you?" + "\n" + line;
        System.out.println(greeting);
    }

    private static void startChat() {
        ArrayList<Task> tasks = new ArrayList<>();
//        Task[] tasks = new Task[5];
        int taskCount = 0;
        int taskId = 1;
        Scanner scanner = new Scanner(System.in);
        String userInput= scanner.nextLine();
        String goodbye = line + "Until we meet once more in the near future, I bid you farewell." + "\n" + line;

        while (!userInput.equals("bye")){
            try {
                if (userInput.equals("list")){
                    displayList(tasks, taskCount);
                } else if (userInput.startsWith("mark ")) {
                    mark(userInput, tasks, taskCount);
                } else if (userInput.startsWith("unmark ")) {
                    unMark(userInput, tasks, taskCount);
                } else if (userInput.startsWith("todo ")) {
                    String nameOfTask = userInput.substring(5);
                    ToDos task = new ToDos(nameOfTask, taskId);
                    addToList(task, tasks, taskCount);
                    if (taskCount < tasks.size()) {
                        taskCount++;
                        taskId++;
                    }
                } else if (userInput.startsWith("deadline ")) {
                    String[] parts = userInput.split("/");
                    String nameOfTask = parts[0].trim().substring(9);
                    Deadlines task = new Deadlines(nameOfTask, taskId, parts[1].trim());
                    addToList(task, tasks, taskCount);
                    if (taskCount < tasks.size()) {
                        taskCount++;
                        taskId++;
                    }
                } else if (userInput.startsWith("event ")) {
                    String[] parts = userInput.split("/");
                    String start = parts[1].trim();
                    String end = parts[2].trim();
                    String nameOfTask = parts[0].trim().substring(6);
                    Events task = new Events(nameOfTask, taskId, start, end);
                    addToList(task, tasks, taskCount);
                    if (taskCount < tasks.size()) {
                        taskCount++;
                        taskId++;
                    }
                } else {
                    throw new DukeException("Error: Invalid Command!");
                }
            } catch (DukeException exception) {
                System.out.println(line + exception.getMessage() + "\n" + line);
            }
            userInput = scanner.nextLine();
        }
        System.out.println(goodbye);
        scanner.close();
    }

    private static void displayList(ArrayList<Task> tasks, int taskCount){
        try {
            if (taskCount == 0) {
                throw new DukeException("Error: There are no items in the list!");
            }
            System.out.println(line);
            for(int i = 0; i < taskCount; i++){
                System.out.println(tasks.get(i).getTask());
            }
            System.out.println(line);
        } catch (DukeException emptyList) {
            System.out.println(line + emptyList.getMessage() + "\n" + line);
        }
    }

    public static void mark(String input, ArrayList<Task> tasks, int taskCount) {
        int taskIndex = Integer.parseInt(input.substring(5)) - 1;
        try {
            if (taskIndex > taskCount || taskIndex < 0) {
                throw new DukeException("Error: Invalid Task Index!");
            } else if (tasks.get(taskIndex).isMarked()) {
                throw new DukeException("Error: Task is already completed!");
            } else {
                tasks.get(taskIndex).mark();
            }
        } catch (DukeException exception) {
            System.out.println(line + exception.getMessage() + "\n" + line);
        }
    }

    public static void unMark(String input, ArrayList<Task> tasks, int taskCount) {
        int taskIndex = Integer.parseInt(input.substring(7)) - 1;
        try {
            if (taskIndex > taskCount || taskIndex < 0) {
                throw new DukeException("Error: Invalid Task Index!");
            } else if (!tasks.get(taskIndex).isMarked()) {
                throw new DukeException("Error: Task is already marked as incomplete!");
            } else {
                tasks.get(taskIndex).unMark();
            }
        } catch (DukeException exception) {
            System.out.println(line + exception.getMessage() + "\n" + line);
        }
    }

    private static void addToList(Task task, ArrayList<Task> tasks, int taskId) {
        int taskCount = taskId + 1;
        String response = line + "Got it! I've added this task:" + "\n" + task.toString() + "\n"
                + "You now have " + taskCount + " task(s) in the list" + "\n" + line;
        tasks.add(taskId, task);
        System.out.println(response);
    }
}
