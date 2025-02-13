package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import task.DeadLine;
import task.Event;
import task.Task;
import task.TaskList;
import task.ToDo;

/**
 * A class to handle the loading and saving of tasks to and from a file.
 */
public class Storage {
    private final String directory;
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.directory = filePath.substring(0, filePath.lastIndexOf("/"));
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file and returns them as an ArrayList.
     *
     * @return An ArrayList of tasks loaded from the file.
     */
    public ArrayList<Task> loadData() {
        ArrayList<Task> taskList = new ArrayList<>();
        File f = new File(filePath);
        if (!f.exists()) {
            return taskList;
        }

        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Task task = parseTask(line);
                if (task != null) {
                    taskList.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return taskList;
    }

    private static Task parseTask(String line) {
        String[] task = line.split(" ");
        if (task.length < 3) {
            return null;
        }

        String type = task[0];
        boolean isCompleted = task[1].equals("[X]");
        String details = String.join(" ", Arrays.copyOfRange(task, 2, task.length));

        switch (type) {
        case "[T]" -> {
            return createToDo(isCompleted, details);
        }
        case "[D]" -> {
            return createDeadLine(isCompleted, details);
        }
        case "[E]" -> {
            return createEvent(isCompleted, details);
        }
        default -> {
            System.out.println("Wrong file format!");
            return null;
        }
        }
    }

    private static Event createEvent(boolean isCompleted, String details) {
        String[] tokens = details.split(" \\(from: ");
        if (tokens.length != 2) {
            return null;
        }

        String[] timeTokens = tokens[1].split(" to: ");
        if (timeTokens.length != 2) {
            return null;
        }

        LocalDate startTime = LocalDate.parse(timeTokens[0], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate endTime = LocalDate.parse(timeTokens[1].substring(0, timeTokens[1].length() - 1),
                DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Event event = new Event(tokens[0], startTime, endTime);

        if (isCompleted) {
            event.markCompleted();
        }
        return event;
    }

    private static DeadLine createDeadLine(boolean isCompleted, String details) {
        String[] tokens = details.split(" \\(by: ");
        if (tokens.length != 2) {
            return null;
        }

        LocalDate date = LocalDate.parse(tokens[1].substring(0, tokens[1].length() - 1),
                DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        DeadLine deadLine = new DeadLine(tokens[0], date);

        if (isCompleted) {
            deadLine.markCompleted();
        }
        return deadLine;
    }

    private static ToDo createToDo(boolean isCompleted, String details) {
        ToDo toDo = new ToDo(details);
        if (isCompleted) {
            toDo.markCompleted();
        }
        return toDo;
    }

    /**
     * Saves the tasks from the TaskList to the file.
     *
     * @param taskList The TaskList containing the tasks to be saved.
     */
    public void saveData(TaskList taskList) {
        try {
            File d = new File(directory);
            File f = new File(filePath);

            if (!f.exists()) {
                d.mkdirs();
                f.createNewFile();
            }

            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                String type = task.getType();
                fw.write(type + " " + task.isComplete() + " " + task.getName() + " " + task.getTiming() + "\n");
            }
            fw.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
