package task;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    public String getType() {
        return "[T]";
    }
}
