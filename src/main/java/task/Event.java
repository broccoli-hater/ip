package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * A task that occurs during a specific time period.
 * In the form: event {description} {/from yyyy-MM-dd /to yyyy-MM-dd} {tags}
 * This class extends the `Task` class and includes start and end dates for the event.
 */
public class Event extends Task {
    private final LocalDate start;
    private final LocalDate end;

    /**
     * Constructs an Event task with the specified name, start date, and end date.
     *
     * @param name  The name or description of the task.
     * @param start The start date of the event.
     * @param end   The end date of the event.
     */
    public Event(String name, LocalDate start, LocalDate end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs an Event task with the specified name, start date, end date,
     * and a list of tags.
     *
     * @param name  The name or description of the task.
     * @param start The start date of the event.
     * @param end   The end date of the event.
     * @param tagList The list of tags.
     */
    public Event(String name, LocalDate start, LocalDate end, ArrayList<String> tagList) {
        super(name, tagList);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the type identifier for the task.
     *
     * @return The type identifier "[E]" for Event tasks.
     */
    public String getType() {
        return "[E]";
    }

    /**
     * Returns the formatted start date as a string.
     *
     * @return The start date formatted as "dd/MM/yyyy".
     */
    public String getStart() {
        return start.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * Returns the formatted end date as a string.
     *
     * @return The end date formatted as "dd/MM/yyyy".
     */
    public String getEnd() {
        return end.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * Returns the timing information for the task, including the start and end dates.
     *
     * @return A string representing the event period in the format "(from: dd/MM/yyyy to: dd/MM/yyyy)".
     */
    public String getTiming() {
        return "(from: " + getStart() + " to: " + getEnd() + ")";
    }
}
