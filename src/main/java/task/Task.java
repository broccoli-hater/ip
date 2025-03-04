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
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Constructs a Task with the specified name and list of tags.
     *
     * @param name The name of the task.
     * @param tags The list of tags.
     */
    public Task(String name, ArrayList<String> tags) {
        this.name = name;
        this.tags = tags;
    }

    /**
     * Returns the name of the Task.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the tags associated with the Task.
     */
    public String getTags() {
        StringBuilder tagList = new StringBuilder();
        for (String tag : tags) {
            tagList.append("#").append(tag).append(" ");
        }
        return tagList.toString().trim();
    }

    /**
     * Adds a list of tags to the Task.
     *
     * @param tagList The list of tags.
     */
    public void addTags(ArrayList<String> tagList) {
        for (String t : tagList) {
            this.tag(t);
        }
    }

    /**
     * Adds a tag to the Task.
     *
     * @param tag The tags to be added.
     */
    private void tag(String tag) {
        tags.add(tag);
    }

    /**
     * Removes a list of tags from the Task.
     *
     * @param tagList The list of tags.
     */
    public void removeTags(ArrayList<String> tagList) {
        for (String t : tagList) {
            tags.remove(t);
        }
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
        return null;
    }

    public String getStart() {
        return null;
    }

    public String getEnd() {
        return null;
    }

    public String getType() {
        return null;
    }

    public String getTiming() {
        return null;
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
