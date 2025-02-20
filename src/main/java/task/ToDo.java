package task;

import java.util.ArrayList;

/**
 * A task without a specific deadline or event timing.
 * In the form: todo {description} {tags}
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
     * Constructs a ToDo task with the specified name and a list of tags.
     *
     * @param name The name or description of the task.
     * @param tagList The list of tags.
     */
    public ToDo(String name, ArrayList<String> tagList) {
        super(name, tagList);
    }

    /**
     * Returns the type identifier for the task.
     *
     * @return The type identifier "[T]" for ToDo tasks.
     */
    public String getType() {
        return "[T]";
    }

    @Override
    public String toString() {
        return this.getType() + this.isComplete() + " " + this.getName() + " " + this.getTags();
    }
}
