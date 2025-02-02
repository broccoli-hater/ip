import command.Command;
import command.ExitCommand;
import task.TaskList;
import util.Parser;
import util.Storage;
import util.Ui;

/**
 * SirTalksALot is a task management application with a snarky medieval knight's personality
 * It can manage tasks through a CLI.
 * It currently supports adding todos, deadlines and events
 * It supports delete, marking, unmarking and saving functions.
 */
public class SirTalksALot {
    private Storage storage;
    private Ui ui = new Ui();
    private Parser parser = new Parser();
    private TaskList taskList;

    /**
     * Constructs a SirTalksALot object with the specified file path for data storage.
     * Initializes the storage, task list, and loads existing data from the file.
     *
     * @param filePath The path to the file where task data is stored.
     */
    public SirTalksALot(String filePath) {
        storage = new Storage(filePath);
        taskList = new TaskList(storage.loadData());
    }

    /**
     * Runs the SirTalksALot application.
     * Displays a welcome message, reads user input, processes commands,
     * and executes them until an exit command is received.
     * Saves the task list to the file upon exiting.
     */
    public void run() {
        Ui.breakLine();
        Ui.sayHello();
        Ui.breakLine();

        boolean isExit = false;
        while (!isExit) {
            String input = ui.readCommand();
            Ui.breakLine();

            Command command = parser.parse(input);
            command.execute(taskList, ui, storage);
            if (command instanceof ExitCommand) {
                isExit = true;
            }

            Ui.breakLine();
        }
        storage.saveData(taskList);
    }

    /**
     * The entry point of the SirTalksALot application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new SirTalksALot("data/sirtalksalot.txt").run();
    }
}
