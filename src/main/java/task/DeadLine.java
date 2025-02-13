package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * A task with a specific deadline.
 * In the form: deadline {description} {/by yyyy-MM-dd} {tags}
 * This class extends the `Task` class and includes a deadline date.
 */
public class DeadLine extends Task {
    private final LocalDate deadLine;

    /**
     * Constructs a DeadLine task with the specified name and deadline date.
     *
     * @param name     The name or description of the task.
     * @param deadLine The deadline date of the task.
     */
    public DeadLine(String name, LocalDate deadLine) {
        super(name);
        this.deadLine = deadLine;
    }

    public DeadLine(String name, LocalDate deadLine, ArrayList<String> tagList) {
        super(name, tagList);
        this.deadLine = deadLine;
    }

    /**
     * Returns the type identifier for the task.
     *
     * @return The type identifier "[D]" for DeadLine tasks.
     */
    public String getType() {
        return "[D]";
    }


    /**
     * Returns the formatted deadline date as a string.
     *
     * @return The deadline date formatted as "dd/MM/yyyy".
     */
    public String getDeadLine() {
        return deadLine.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * Returns the timing information for the task, including the deadline.
     *
     * @return A string representing the deadline in the format "(by: dd/MM/yyyy)".
     */
    public String getTiming() {
        return "(by: " + getDeadLine() + ") ";
    }
}
