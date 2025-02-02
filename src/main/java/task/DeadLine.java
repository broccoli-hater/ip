package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadLine extends Task {
    private LocalDate deadLine;

    public DeadLine(String name, LocalDate deadLine) {
        super(name);
        this.deadLine = deadLine;
    }

    public String getType() {
        return "[D]";
    }

    public String getDeadLine() {
        return deadLine.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String getTiming() {
        return "(by: " + getDeadLine() + ")";
    }
}
