import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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

            if (input[0].equals("list")) {
                System.out.println("Hear ye! These are the tasks upon thy list, as decreed by thine own hand:");
                String s = "";
                int counter = 1;
                for (Task task : taskList) {
                    if (task.completed) {
                        s = counter + ".[X] " + task.getName();
                    } else {
                        s = counter + ".[ ] " + task.getName();
                    }
                    counter++;
                    System.out.println(s);
                }
            } else if (input[0].equals("mark")) {
                System.out.println("Behold! A task completed! A most noble accomplishment.");
                int index = Integer.parseInt(input[1]) - 1;
                taskList.get(index).markCompleted();
                System.out.println("[X] " + taskList.get(index).getName());
            } else if (input[0].equals("unmark")) {
                System.out.println("Alas, a task left to be conquered. Its time has not yet come to pass.");
                int index = Integer.parseInt(input[1]) - 1;
                taskList.get(index).unmarkCompleted();
                System.out.println("[ ] " + taskList.get(index).getName());
            } else {
                String task = String.join(" ", Arrays.copyOfRange(input, 0, input.length));
                System.out.println("Verily, I have inscribed this task upon the list: \n" + task);
                taskList.add(new Task(task));
            }

            System.out.println("____________________________________________________________");
            input = scanner.nextLine().split(" ");
        }

        farewell();
        System.out.println("____________________________________________________________");
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
                + "But should you wish to return and bask in the glory of my knightly presence, you need only call. \n"
                + "Farewell for now.";
        System.out.println(bye);
    }
}
