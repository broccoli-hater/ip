abstract class Command {
    public void execute(TaskList task, Ui ui, Storage storage) {
    }
}

class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.isEmpty()) {
            Ui.countTask(0);
        } else {
            Ui.printList();
            taskList.print();
        }
    }
}

class DeleteCommand extends Command {
    private int index;
    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Ui.deleteTask(taskList.get(index));
            taskList.remove(index);
            storage.saveData(taskList);
            Ui.countTask(taskList.size());
        } catch (IndexOutOfBoundsException e) {
            Ui.taskNotFound();
        }
    }
}

class MarkCommand extends Command {
    private int index;
    public MarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.get(index).markCompleted();
            storage.saveData(taskList);
            Ui.markTask(taskList.get(index));
        } catch (IndexOutOfBoundsException e) {
            Ui.taskNotFound();
        }
    }
}

class UnmarkCommand extends Command {
    private int index;
    public UnmarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.get(index).unmarkCompleted();
            storage.saveData(taskList);
            Ui.unmarkTask(taskList.get(index));
        } catch (IndexOutOfBoundsException e) {
            Ui.taskNotFound();
        }
    }
}

class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Ui.sayBye();
    }
}

class ErrorCommand extends Command {
    private String error;
    public ErrorCommand(String error) {
        this.error = error;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (error.equals("unknown")) {
            Ui.promptAgain();
        } else if (error.equals("empty description")) {
            Ui.descriptionNotFound();
        } else if (error.equals("empty deadline")) {
            Ui.deadLineNotFound();
        } else if (error.equals("empty start time")) {
            Ui.startTimeNotFound();
        } else if (error.equals("empty end time")) {
            Ui.endTimeNotFound();
        }
    }
}

class AddCommand extends Command {
    private Task task;
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Ui.addTask();
        taskList.add(task);
        storage.saveData(taskList);
        System.out.print("    ");
        System.out.println(taskList.get(taskList.size() - 1).toString());
        Ui.countTask(taskList.size());
    }
}

