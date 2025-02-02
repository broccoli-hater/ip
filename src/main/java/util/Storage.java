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
    private String directory;
    private String filePath;

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

        try {
            File f = new File(filePath);
            if (f.exists()) {
                Scanner sc = new Scanner(f);
                while (sc.hasNextLine()) {
                    String[] task = sc.nextLine().split(" ");
                    switch (task[0]) {
                    case "[T]" -> {
                        ToDo toDo = new ToDo(task[2]);
                        if (task[1].equals("[X]")) {
                            toDo.markCompleted();
                        }
                        taskList.add(toDo);
                    }
                    case "[D]" -> {
                        String token = String.join(" ", Arrays.copyOfRange(task, 2, task.length));
                        String[] token1 = token.split(" \\(by: ", 2);
                        DeadLine deadLine = new DeadLine(token1[0],
                                LocalDate.parse(token1[1].substring(0, token1[1].length() - 1),
                                        DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                        if (task[1].equals("[X]")) {
                            deadLine.markCompleted();
                        }
                        taskList.add(deadLine);
                    }
                    case "[E]" -> {
                        String token = String.join(" ", Arrays.copyOfRange(task, 2, task.length));
                        String[] token1 = token.split(" \\(from: ", 2);
                        String[] token2 = token1[1].split(" to: ", 2);
                        Event event = new Event(token1[0],
                                LocalDate.parse(token2[0], DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                                LocalDate.parse(token2[1].substring(0, token2[1].length() - 1),
                                        DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                        if (task[1].equals("[X]")) {
                            event.markCompleted();
                        }
                        taskList.add(event);
                    }
                    default -> System.out.println("Wrong file format!");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return taskList;
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
