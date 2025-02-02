package task;

/**
 * A task without a specific deadline or event timing.
 * This class extends the `Task` class and provides a specific type identifier for ToDo tasks.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo task with the specified name.
     *
     * @param name The name or description of the task.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Returns the type identifier for the task.
     *
     * @return The type identifier "[T]" for ToDo tasks.
     */
    public String getType() {
        return "[T]";
    }
}
