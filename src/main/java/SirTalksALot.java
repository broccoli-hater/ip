import command.Command;
import command.ExitCommand;
import task.TaskList;
import util.Parser;
import util.Storage;
import util.Ui;

public class SirTalksALot {
    private Storage storage;
    private Ui ui = new Ui();
    private Parser parser = new Parser();
    private TaskList taskList;

    public SirTalksALot(String filePath) {
        storage = new Storage(filePath);
        taskList = new TaskList(storage.loadData());
    }

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
            if(command instanceof ExitCommand) {
                isExit = true;
            }

            Ui.breakLine();
        }
        storage.saveData(taskList);
    }

    public static void main(String[] args) {
        new SirTalksALot("data/sirtalksalot.txt").run();
    }
}
