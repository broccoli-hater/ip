import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SirTalksALot {
    public static class Task {
        String name;
        boolean completed = false;

        public Task(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void markCompleted() {
            completed = true;
        }

        public void unmarkCompleted() {
            completed = false;
        }

        public String getType() {
            return "[ ]";
        }
        public String toString() {
            return "";
        }

    }
    public static class ToDo extends Task {

        public ToDo(String name) {
            super(name);
        }
        public String getType() {
            return "[T]";
        }
    }
    public static class DeadLine extends Task {
        String deadLine;

        public DeadLine(String name, String deadLine) {
            super(name);
            this.deadLine = deadLine;
        }
        public String getType() {
            return "[D]";
        }
        public String getDeadLine() {
            return deadLine;
        }
        public String toString() {
            return "(by: " + getDeadLine() + ")";
        }
    }
    public static class Event extends Task {
        String start;
        String end;

        public Event(String name, String start, String end) {
            super(name);
            this.start = start;
            this.end = end;
        }
        public String getType() {
            return "[E]";
        }
        public String getStart() {
            return start;
        }
        public String getEnd() {
            return end;
        }
        public String toString() {
            return "(from: " + getStart() + ", to: " + getEnd() + ")";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        System.out.println("____________________________________________________________");
        greeting();
        System.out.println("____________________________________________________________");

        String[] input = scanner.nextLine().split(" ");
        while (!input[0].equals("bye")) {
            System.out.println("____________________________________________________________");

            switch (input[0]) {
                case "list" -> {
                    System.out.println("Hear ye! These are the tasks upon thy list, as decreed by thine own hand:");
                    String s = "";
                    int counter = 1;
                    for (Task task : taskList) {
                        if (task.completed) {
                            s = counter + "." + task.getType() + "[X] " + task.getName() + " " + task.toString();
                        } else {
                            s = counter + "." + task.getType() + "[ ] " + task.getName() + " " + task.toString();
                        }
                        counter++;
                        System.out.println(s);
                    }
                }
                case "mark" -> {
                    System.out.println("Behold! A task completed! A most noble accomplishment.");
                    int index = Integer.parseInt(input[1]) - 1;
                    taskList.get(index).markCompleted();
                    System.out.println("[X] " + taskList.get(index).getName());
                }
                case "unmark" -> {
                    System.out.println("Alas, a task left to be conquered. Its time has not yet come to pass.");
                    int index = Integer.parseInt(input[1]) - 1;
                    taskList.get(index).unmarkCompleted();
                    System.out.println("[ ] " + taskList.get(index).getName());
                }
                case "todo" -> {
                    String todo = String.join(" ", Arrays.copyOfRange(input, 1, input.length));
                    addTask();
                    taskList.add(new ToDo(todo));
                    System.out.println("    [T][ ] " + taskList.get(taskList.size() - 1).getName());
                    countTask(taskList.size());
                }
                case "deadline" -> {
                    String deadLine = String.join(" ", Arrays.copyOfRange(input, 1, input.length));
                    String[] temp = deadLine.split(" /by ", 2);

                    addTask();
                    taskList.add(new DeadLine(temp[0], temp[1]));
                    System.out.println("    [D][ ] " + taskList.get(taskList.size() - 1).getName() + " (by: " + temp[1] + ")");
                    countTask(taskList.size());
                }
                case "event" -> {
                    String event= String.join(" ", Arrays.copyOfRange(input, 1, input.length));
                    String[] temp = event.split(" /from ", 2);
                    String[] temp1 = temp[1].split(" /to ", 2);

                    addTask();
                    taskList.add(new Event(temp[0], temp1[0], temp1[1]));
                    System.out.println("    [E][ ] " + taskList.get(taskList.size() - 1).getName() + " (from: " + temp1[0] + " to: " + temp1[1] + ")");
                    countTask(taskList.size());
                }
                default -> {
                    String task = String.join(" ", Arrays.copyOfRange(input, 0, input.length));
                    addTask();
                    System.out.println(task);
                    taskList.add(new Task(task));
                }
            }
            System.out.println("____________________________________________________________");
            input = scanner.nextLine().split(" ");
        }
        System.out.println("____________________________________________________________");
        farewell();
        System.out.println("____________________________________________________________");
    }

    public static void addTask() {
        String addTask = "Verily, I have inscribed this task upon the list.\n"
                        + "Let no task go unfulfilled and no duty unrecorded!";
        System.out.println(addTask);
    }
    public static void countTask(int count) {
        String countTask = "";
        if (count == 1) {
            countTask = "Thou hast " + count + " task upon the list. A worthy pursuit!";
        } else {
            countTask = "Thou hast " + count + " tasks upon the list, each one a worthy pursuit!";
        }
        System.out.println(countTask);
    }

    public static void greeting() {
        String greeting = "Greetings! It is I, Sir Talks-A-Lot. \n"
                + "Ah, so you wish to converse with one such as myself? \n"
                + "A noble knight, sworn to honor and valor, holder of great wisdom and unyielding strength? \n"
                + "Very well, I shall indulge thee in thy request. \n"
                + "Speak now, peasant, What dost thou seek from a knight of my stature?";
        System.out.println(greeting);
    }

    public static void farewell() {
        String bye = "Hah! A quick departure, is it? Very well, then. \n"
                    + "Farewell for now.";
        System.out.println(bye);
    }
}
