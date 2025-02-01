import com.sun.source.util.TaskListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Task {
    private String name;
    private boolean isComplete = false;

    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String isComplete() {
        return isComplete ? "[X]" : "[-]";
    }

    public void markCompleted() {
        isComplete = true;
    }

    public void unmarkCompleted() {
        isComplete = false;
    }

    public String getDeadLine() {
        return "";
    }

    public String getStart() {
        return "";
    }

    public String getEnd() {
        return "";
    }

    public String getType() {
        return "[ ]";
    }

    public String toString() {
        return "";
    }

}

class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    public String getType() {
        return "[T]";
    }
}

class DeadLine extends Task {
    private LocalDate deadLine;

    public DeadLine(String name, LocalDate deadLine) {
        super(name);
        this.deadLine = deadLine;
    }

    public String getType() {
        return "[D]";
    }

    public String getDeadLine() {
        return deadLine.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String toString() {
        return "(by: " + getDeadLine() + ")";
    }
}

class Event extends Task {
    private LocalDate start;
    private LocalDate end;

    public Event(String name, LocalDate start, LocalDate end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    public String getType() {
        return "[E]";
    }

    public String getStart() {
        return start.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String getEnd() {
        return end.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String toString() {
        return "(from: " + getStart() + " to: " + getEnd() + ")";
    }
}

public class SirTalksALot {
    private Storage storage;
    private Parser parser;
    private TaskList taskList;

    public SirTalksALot(String filePath) {
        storage = new Storage(filePath);
        taskList = new TaskList(storage.loadData());
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        Ui.breakLine();
        Ui.sayHello();
        Ui.breakLine();

        String[] input = scanner.nextLine().split(" ");

        while (!input[0].equals("bye")) {
            Ui.breakLine();

            switch (input[0]) {
            case "list" -> {
                if (taskList.isEmpty()) {
                    Ui.countTask(0);
                } else {
                    Ui.printList(taskList);
                }
            }
            case "delete" -> {
                try {
                    int index = Integer.parseInt(input[1]) - 1;
                    Ui.deleteTask(taskList.get(index));
                    taskList.remove(index);
                    storage.saveData(taskList);
                    Ui.countTask(taskList.size());
                } catch (IndexOutOfBoundsException e) {
                    Ui.taskNotFound();
                }
            }
            case "mark" -> {
                try {
                    int index = Integer.parseInt(input[1]) - 1;
                    taskList.get(index).markCompleted();
                    storage.saveData(taskList);
                    Ui.markTask(taskList.get(index));
                } catch (IndexOutOfBoundsException e) {
                    Ui.taskNotFound();
                }
            }
            case "unmark" -> {
                try {
                    int index = Integer.parseInt(input[1]) - 1;
                    taskList.get(index).unmarkCompleted();
                    storage.saveData(taskList);
                    Ui.unmarkTask(taskList.get(index));
                } catch (IndexOutOfBoundsException e) {
                    Ui.taskNotFound();
                }
            }
            case "todo" -> {
                if (input.length == 1) {
                    Ui.descriptionNotFound();
                    break;
                } else {
                    String todo = String.join(" ", Arrays.copyOfRange(input, 1, input.length));

                    Ui.addTask();
                    taskList.add(new ToDo(todo));
                    storage.saveData(taskList);
                    System.out.print("    ");
                    Ui.printTask(taskList.get(taskList.size() - 1));
                    Ui.countTask(taskList.size());
                }
            }
            case "deadline" -> {
                if (input.length == 1) {
                    Ui.descriptionNotFound();
                    break;
                }
                String deadLine = String.join(" ", Arrays.copyOfRange(input, 1, input.length));
                String[] temp = deadLine.split(" /by ", 2);
                if (deadLine.startsWith("/by") && temp.length == 1) {
                    Ui.descriptionNotFound();
                    break;
                }
                if (temp.length == 1) {
                    Ui.deadLineNotFound();
                    break;
                }
                Ui.addTask();
                taskList.add(new DeadLine(temp[0], LocalDate.parse(temp[1])));
                storage.saveData(taskList);
                System.out.print("    ");
                Ui.printTask(taskList.get(taskList.size() - 1));
                Ui.countTask(taskList.size());
            }
            case "event" -> {
                if (input.length == 1) {
                    Ui.descriptionNotFound();
                    break;
                }
                String event = String.join(" ", Arrays.copyOfRange(input, 1, input.length));
                String[] temp = event.split(" /from ", 2);
                if (event.startsWith("/from") && temp.length == 1) {
                    Ui.descriptionNotFound();
                    break;
                }
                if (temp.length == 1) {
                    Ui.startTimeNotFound();
                    break;
                }
                String[] temp1 = temp[1].split(" /to ", 2);
                if (temp1.length == 1) {
                    Ui.endTimeNotFound();
                    break;
                }
                Ui.addTask();
                taskList.add(new Event(temp[0], LocalDate.parse(temp1[0]), LocalDate.parse(temp1[1])));
                storage.saveData(taskList);
                System.out.print("    ");
                Ui.printTask(taskList.get(taskList.size() - 1));
                Ui.countTask(taskList.size());
            }
            default -> {
                Ui.promptAgain();
            }
            }
            Ui.breakLine();
            input = scanner.nextLine().split(" ");
        }
        Ui.breakLine();
        Ui.sayBye();
        storage.saveData(taskList);
        Ui.breakLine();
    }

    public static void main(String[] args) {
        new SirTalksALot("data/sirtalksalot.txt").run();
    }
}
