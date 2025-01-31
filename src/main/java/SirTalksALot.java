import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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
    private String deadLine;

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

class Event extends Task {
    private String start;
    private String end;

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
        return "(from: " + getStart() + " to: " + getEnd() + ")";
    }
}
public class SirTalksALot {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList;

        taskList = loadData();

        System.out.println("____________________________________________________________");
        sayHello();
        System.out.println("____________________________________________________________");

        String[] input = scanner.nextLine().split(" ");
        while (!input[0].equals("bye")) {
            System.out.println("____________________________________________________________");

            switch (input[0]) {
            case "list" -> {
                if (taskList.isEmpty()) {
                    countTask(0);
                    break;
                }
                System.out.println("Hear ye! These are the tasks upon thy list, as decreed by thine own hand:");
                String s = "";
                int counter = 1;
                for (Task task : taskList) {
                    s = counter + "." + task.getType() + task.isComplete() + " " + task.getName() + " " + task.toString();
                    counter++;
                    System.out.println(s);
                }
            }
            case "delete" -> {
                try {
                    int index = Integer.parseInt(input[1]) - 1;
                    String completion = "";

                    System.out.println("Noted, then. I have seen fit to remove this trivial task.");
                    System.out.println(taskList.get(index).getType() + taskList.get(index).isComplete() + " " + taskList.get(index).getName());
                    taskList.remove(index);
                    countTask(taskList.size());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("It seems no such task exists, for I have heard no mention of it, nor does it appear to fall within the realm of possibility.");
                }
            }
            case "mark" -> {
                try {
                    int index = Integer.parseInt(input[1]) - 1;
                    taskList.get(index).markCompleted();
                    saveData(taskList);
                    System.out.println("Behold! A task completed! A most noble accomplishment.");
                    System.out.println(taskList.get(index).getType() + "[X] " + taskList.get(index).getName());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("It seems no such task exists, for I have heard no mention of it, nor does it appear to fall within the realm of possibility.");
                }
            }
            case "unmark" -> {
                try {
                    int index = Integer.parseInt(input[1]) - 1;
                    taskList.get(index).unmarkCompleted();
                    saveData(taskList);
                    System.out.println("Alas, a task left to be conquered. Its time has not yet come to pass.");
                    System.out.println(taskList.get(index).getType() + "[ ] " + taskList.get(index).getName());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("It seems no such task exists, for I have heard no mention of it, nor does it appear to fall within the realm of possibility.");
                }
            }
            case "todo" -> {
                if (input.length == 1) {
                    System.out.println("The description of the todo cannot be left empty, for how would one know what is to be done?");
                    break;
                } else {
                    String todo = String.join(" ", Arrays.copyOfRange(input, 1, input.length));

                    addTask();
                    taskList.add(new ToDo(todo));
                    saveData(taskList);
                    System.out.println("    [T][-] " + taskList.get(taskList.size() - 1).getName());
                    countTask(taskList.size());
                }
            }
            case "deadline" -> {
                if (input.length == 1) {
                    System.out.println("The description of the deadline cannot be left empty, for how would one know what is to be done?");
                    break;
                }
                String deadLine = String.join(" ", Arrays.copyOfRange(input, 1, input.length));
                String[] temp = deadLine.split(" /by ", 2);
                if (deadLine.startsWith("/by") && temp.length == 1) {
                    System.out.println("The description of the deadline cannot be left empty, for how would one know what is to be done?");
                    break;
                }
                if (temp.length == 1) {
                    System.out.println("The deadline cannot be left empty, for how would one know when the task is to be done?");
                    break;
                }

                addTask();
                taskList.add(new DeadLine(temp[0], temp[1]));
                saveData(taskList);
                System.out.println("    [D][-] " + taskList.get(taskList.size() - 1).getName() + " (by: " + temp[1] + ")");
                countTask(taskList.size());
            }
            case "event" -> {
                if (input.length == 1) {
                    System.out.println("The description of the event cannot be left empty, for how would one know what is to be done?");
                    break;
                }
                String event = String.join(" ", Arrays.copyOfRange(input, 1, input.length));

                String[] temp = event.split(" /from ", 2);
                if (event.startsWith("/from") && temp.length == 1) {
                    System.out.println("The description of the deadline cannot be left empty, for how would one know what is to be done?");
                    break;
                }
                if (temp.length == 1) {
                    System.out.println("The start time of the event cannot be left empty, for how would one know when the event is?");
                    break;
                }
                String[] temp1 = temp[1].split(" /to ", 2);
                if (temp1.length == 1) {
                    System.out.println("The end time of the event cannot be left empty, for how would one know when the event concludes?");
                    break;
                }

                addTask();
                taskList.add(new Event(temp[0], temp1[0], temp1[1]));
                saveData(taskList);
                System.out.println("    [E][-] " + taskList.get(taskList.size() - 1).getName() + " (from: " + temp1[0] + " to: " + temp1[1] + ")");
                countTask(taskList.size());
            }
            default -> {
                System.out.println("Speak clearly! I know not what such words mean. Attempt once more.");
            }
            }
            System.out.println("____________________________________________________________");
            input = scanner.nextLine().split(" ");
        }

        System.out.println("____________________________________________________________");
        sayBye();
        saveData(taskList);
        System.out.println("____________________________________________________________");
    }

    public static void saveData(ArrayList<? extends Task> taskList) {
        try {
            String directory = "data";
            String filename = directory + File.separator + "sirtalksalot.txt";
            File d = new File(directory);
            File f = new File(filename);

            if (!f.exists()) {
                d.mkdirs();
                f.createNewFile();
            }

            FileWriter fw = new FileWriter(filename);
            for (Task task : taskList) {
                String type = task.getType();
                fw.write(type + " " + task.isComplete() + " " + task.getName() + " " + task.toString() + "\n");
            }
            fw.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Task> loadData(){
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            String directory = "data";
            String filename = directory + File.separator + "sirtalksalot.txt";
            File f = new File(filename);
            if (f.exists()) {
                Scanner sc = new Scanner(f);
                while (sc.hasNextLine()) {
                    String[] task = sc.nextLine().split(" ");
                    if (task[0].equals("[T]")){
                        ToDo toDo = new ToDo(task[2]);
                        if (task[1].equals("[X]")) {
                            toDo.markCompleted();
                        }
                        taskList.add(toDo);
                    } else if (task[0].equals("[D]")){
                        String token = String.join(" ", Arrays.copyOfRange(task, 2, task.length));
                        String[] token1 = token.split(" \\(by: ", 2);
                        DeadLine deadLine = new DeadLine(token1[0], token1[1].substring(0, token1[1].length() - 1));
                        if (task[1].equals("[X]")) {
                            deadLine.markCompleted();
                        }
                        taskList.add(deadLine);
                    } else if (task[0].equals("[E]")){
                        String token = String.join(" ", Arrays.copyOfRange(task, 2, task.length));
                        String[] token1 = token.split(" \\(from: ", 2);
                        String[] token2 = token1[1].split(" to: ", 2);
                        Event event = new Event(token1[0], token2[0], token2[1].substring(0, token2[1].length() - 1));
                        if (task[1].equals("[X]")) {
                            event.markCompleted();
                        }
                        taskList.add(event);
                    } else {
                        System.out.println("Wrong file format!");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return taskList;
    }

    public static void addTask() {
        String addTask = "Verily, I have inscribed this task upon the list.\n"
                + "Let no task go unfulfilled and no duty unrecorded!";
        System.out.println(addTask);
    }

    public static void countTask(int count) {
        String countTask = "";
        if (count == 0) {
            countTask = "Thou hast " + count + " tasks upon the list.";
        } else if (count == 1) {
            countTask = "Thou hast " + count + " task upon the list. A worthy pursuit!";
        } else {
            countTask = "Thou hast " + count + " tasks upon the list, each one a worthy pursuit!";
        }
        System.out.println(countTask);
    }

    public static void sayHello() {
        String greeting = "Greetings! It is I, Sir Talks-A-Lot. \n"
                + "Ah, so you wish to converse with one such as myself? \n"
                + "A noble knight, sworn to honor and valor, holder of great wisdom and unyielding strength? \n"
                + "Very well, I shall indulge thee in thy request. \n"
                + "Speak now, peasant, What dost thou seek from a knight of my stature?";
        System.out.println(greeting);
    }

    public static void sayBye() {
        String bye = "Hah! A quick departure, is it? Very well, then. \n"
                + "Farewell for now.";
        System.out.println(bye);
    }
}
