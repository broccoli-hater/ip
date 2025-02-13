import command.Command;
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
     * Generates a response for the user's chat message.
     * The response is based on the user's input and the processed command
     */
    public String getResponse(String input) {
        Command command = parser.parse(input);
        command.execute(taskList, ui, storage);
        String response = command.getResponse();
        commandType = command.getClass().getSimpleName();

        return response;
    }

    /**
     * Returns the command type according to the user's input.
     */
    public String getCommandType() {
        return commandType;
    }
}
