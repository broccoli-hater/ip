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
    private final Storage storage;
    private final Ui ui = new Ui();
    private final Parser parser = new Parser();
    private final TaskList taskList;
    private String commandType;

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
        System.out.println(Ui.printBreakLine());
        System.out.println(Ui.sayHello());
        System.out.println(Ui.printBreakLine());

        boolean isExit = false;
        while (!isExit) {
            String input = ui.readCommand();
            System.out.println(Ui.printBreakLine());
            Command command = parser.parse(input);
            command.execute(taskList, ui, storage);
            commandType = command.getClass().getSimpleName();
            System.out.println(commandType);
            if (command instanceof ExitCommand) {
                isExit = true;
            }
            System.out.println(Ui.printBreakLine());
        }
        storage.saveData(taskList);
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        Command command = parser.parse(input);
        command.execute(taskList, ui, storage);
        String response = command.getResponse();
        commandType = command.getClass().getSimpleName();

        return response;
    }

    public String getCommandType() {
        return commandType;
    }

//    /**
//     * The entry point of the SirTalksALot application.
//     *
//     * @param args Command-line arguments (not used).
//     */
//    public static void main(String[] args) {
//        new SirTalksALot("data/sirtalksalot.txt").run();
//    }
}
