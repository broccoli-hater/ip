import java.util.Arrays;
import java.util.Scanner;
import java.time.LocalDate;

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
        Ui.breakLine();
        Ui.sayBye();
        storage.saveData(taskList);
        Ui.breakLine();
    }

    public static void main(String[] args) {
        new SirTalksALot("data/sirtalksalot.txt").run();
    }
}
