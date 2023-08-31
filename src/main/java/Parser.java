import java.util.Scanner;

public class Parser {
    static Scanner scanner = new Scanner(System.in);
    public String getUserInput() {
        return scanner.nextLine();
    }
    String userInput = scanner.nextLine();

    public boolean bye() {
        return userInput.equals("bye");
    }
    public boolean list() {
        return userInput.equals("list");
    }
    public boolean mark() {
        return userInput.startsWith("mark ");
    }
    public boolean unMark() {
        return userInput.startsWith("unmark ");
    }
    public boolean delete() {
        return userInput.startsWith("delete ");
    }
    public boolean todo() {
        return userInput.startsWith("todo ");
    }
    public boolean deadline() {
        return userInput.startsWith("deadline ");
    }
    public boolean event() {
        return userInput.startsWith("event ");
    }
    public void goodbye() {
        scanner.close();
    }
}
