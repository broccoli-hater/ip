package command;

import task.TaskList;
import util.Ui;
import util.Storage;

public abstract class Command {
    public void execute(TaskList task, Ui ui, Storage storage) {
    }
}

