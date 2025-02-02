package task;

/**
 * A task with a name and completion status.
 * This class serves as the base class for specific types of tasks.
 */
public class Task {
    private String name;
    private boolean isComplete = false;

    /**
     * Constructs a Task with the specified name.
     *
     * @param name The name or description of the task.
     */
    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Returns the completion status of the task in a formatted string.
     *
     * @return "[X]" if the task is complete, "[-]" otherwise.
     */
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

    public String getTiming() {
        return "";
    }

    /**
     * Returns a string representation of the task, including its type, completion status, name, and timing.
     *
     * @return A formatted string representing the task.
     */
    public String toString() {
        return this.getType() + this.isComplete() + " " + this.getName() + " " + this.getTiming();
    }
}
