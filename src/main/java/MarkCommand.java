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
