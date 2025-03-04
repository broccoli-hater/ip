package command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import task.TaskList;
import task.ToDo;
import util.StorageStub;
import util.Ui;

/**
 * A test class for the MarkCommand class.
 */
public class MarkCommandTest {
    @Test
    public void testExecute_success() {
        StorageStub storageStub = new StorageStub("test/test");
        TaskList taskList = new TaskList();

        AddCommand addCommand = new AddCommand(new ToDo("bing bong"));
        addCommand.execute(taskList, new Ui(), storageStub);
        AddCommand addCommand1 = new AddCommand(new ToDo("ding dong"));
        addCommand1.execute(taskList, new Ui(), storageStub);

        MarkCommand markCommand = new MarkCommand(0);
        markCommand.execute(taskList, new Ui(), storageStub);

        assertEquals("[T][X] bing bong ", taskList.get(0).toString());
    }

    @Test
    public void testExecute_indexOutOfBounds_exceptionThrown() {
        StorageStub storageStub = new StorageStub("test/test");
        TaskList taskList = new TaskList();

        AddCommand addCommand = new AddCommand(new ToDo("bing bong"));
        addCommand.execute(taskList, new Ui(), storageStub);
        AddCommand addCommand1 = new AddCommand(new ToDo("ding dong"));
        addCommand1.execute(taskList, new Ui(), storageStub);

        try {
            assertEquals("1. [T][-] bing bong ", taskList.get(2));
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index 2 out of bounds for length 2", e.getMessage());
        }
    }
}
