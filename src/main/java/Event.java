import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Event extends Task {
    private LocalDate start;
    private LocalDate end;

    public Event(String name, LocalDate start, LocalDate end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    public String getType() {
        return "[E]";
    }

    public String getStart() {
        return start.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String getEnd() {
        return end.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String getTiming() {
        return "(from: " + getStart() + " to: " + getEnd() + ")";
    }
}
