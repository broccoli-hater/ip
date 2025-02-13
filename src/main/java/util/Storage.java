package util;

import java.io.File;
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
        String tokens = Parser.separateDescriptionFromTags(details).trim(); //{description} {start} {end}

        String[] temp = tokens.split(" \\(from: ");
        String description = temp[0].trim();
        if (temp.length != 2) {
            return null;
        }
        String[] timeTokens = temp[1].split(" to: ");
        if (timeTokens.length != 2) {
            return null;
        }

        String startDate = timeTokens[0].trim();
        LocalDate startTime = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String endDate = timeTokens[1].trim();
        LocalDate endTime = LocalDate.parse(endDate.substring(0, timeTokens[1].length() - 1),
                DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Event event = new Event(description, startTime, endTime);

        if (isCompleted) {
            event.markCompleted();
        }
        if (Parser.hasTags(details)) {
            ArrayList<String> tagList = Parser.parseTags(details);
            event.addTags(tagList);
        }
        return event;
    }

    private static DeadLine createDeadLine(boolean isCompleted, String details) {
        String tokens = Parser.separateDescriptionFromTags(details).trim(); //{description} {deadline}
        String[] temp = tokens.split(" \\(by: ");
        String description = temp[0];
        String dateString = temp[1];

        if (temp.length != 2) {
            return null;
        }

        LocalDate date = LocalDate.parse(dateString.substring(0, dateString.length() - 1),
                DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        DeadLine deadLine = new DeadLine(description, date);

        if (isCompleted) {
            deadLine.markCompleted();
        }
        if (Parser.hasTags(details)) {
            ArrayList<String> tagList = Parser.parseTags(details);
            deadLine.addTags(tagList);
        }
        return deadLine;
    }

    private static ToDo createToDo(boolean isCompleted, String details) {
        String description = Parser.separateDescriptionFromTags(details);
        ToDo toDo = new ToDo(description);
        if (isCompleted) {
            toDo.markCompleted();
        }
        if (Parser.hasTags(details)) {
            ArrayList<String> tagList = Parser.parseTags(details);
            toDo.addTags(tagList);
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
                fw.write(type + " " + task.isComplete() + " " + task.getName()
                        + " " + task.getTiming() + " " + task.getTags() + "\n");
            }
            fw.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
