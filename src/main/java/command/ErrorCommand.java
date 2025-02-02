package command;

import task.TaskList;
import util.Ui;
import util.Storage;

public class ErrorCommand extends Command {
    private String error;

    public ErrorCommand(String error) {
        this.error = error;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        switch (error) {
        case "unknown" -> {
            Ui.promptAgain();
        }
        case "empty description" -> {
            Ui.descriptionNotFound();
        }
        case "empty deadline" -> {
            Ui.deadLineNotFound();
        }
        case "empty start time" -> {
            Ui.startTimeNotFound();
        }
        case "empty end time" -> {
            Ui.endTimeNotFound();
        }
        }
    }
}
