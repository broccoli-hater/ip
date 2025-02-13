package task;

import java.util.ArrayList;

/**
 * A task with a name and completion status.
 * This class serves as the base class for specific types of tasks.
 */
public class Task {
    private final String name;
    private boolean isComplete = false;
    private ArrayList<String> tags = new ArrayList<>();

    /**
     * Constructs a Task with the specified name.
     *
     * @param name The name or description of the task.
     */
    public Task(String name) {
        this.name = name;
    }

    public Task(String name, ArrayList<String> tags) {
        this.name = name;
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public String getTags() {
        StringBuilder tagList = new StringBuilder();
        for (String tag : tags) {
            tagList.append("#").append(tag);
        }
        return tagList.toString();
    }

    public void addTags(ArrayList<String> tagList) {
        for (String t : tagList) {
            this.tag(t);
        }
    }

    public void tag(String tag) {
        tags.add(tag);
    }

    public void untag(String tag) {
        tags.remove(tag);
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
        return this.getType() + this.isComplete() + " " + this.getName()
                + " " + this.getTiming() + " " + this.getTags();
    }
}
