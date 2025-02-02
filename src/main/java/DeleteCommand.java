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
