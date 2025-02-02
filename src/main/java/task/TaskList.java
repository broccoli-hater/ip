package task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 * This class provides methods to manage and manipulate a list of tasks, such as
 * adding, removing, and printing tasks.
 *
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Checks if the task list is empty.
     *
     * @return True if the task list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * Removes the task at the specified index from the task list.
     *
     * @param index The index of the task to be removed.
     */
    public void remove(int index) {
        taskList.remove(index);
    }

    /**
     * Prints all tasks in the task list with their corresponding indices.
     */
    public void print() {
        int counter = 1;
        for (Task task : taskList) {
            System.out.print(counter + ". ");
            System.out.println(task.toString());
            counter++;
        }
    }

    /**
     * Returns a string representation of all tasks in the task list with their corresponding indices.
     *
     * @return A formatted string containing all tasks in the list.
     */
    public String toString() {
        int counter = 1;
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : taskList) {
            stringBuilder.append(counter).append(". ");
            stringBuilder.append(task);
            counter++;
        }
        return stringBuilder.toString();
    }
}
