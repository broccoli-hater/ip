package task;

public class Task {
    private String name;
    private boolean isComplete = false;

    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

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

    public String toString() {
        return this.getType() + this.isComplete() + " " + this.getName() + " " + this.getTiming();
    }
}
