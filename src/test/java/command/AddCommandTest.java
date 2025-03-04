package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import task.DeadLine;
import task.Event;
import task.TaskList;
import task.ToDo;
import util.StorageStub;
import util.Ui;

/**
 * A test class for the AddCommand class.
 */
public class AddCommandTest {
    @Test
    public void testExecute_success() {
        StorageStub storageStub = new StorageStub("test/test");
        TaskList taskList = new TaskList();

        AddCommand addCommand = new AddCommand(new ToDo("bing bong"));
        addCommand.execute(taskList, new Ui(), storageStub);
        AddCommand addCommand1 = new AddCommand(new DeadLine(
                "ding dong", LocalDate.parse("2025-09-09")));
        addCommand1.execute(taskList, new Ui(), storageStub);

        String expected = "1. [T][-] bing bong \n"
                + "2. [D][-] ding dong (by: 09/09/2025) \n";

        assertEquals(expected, taskList.toString());
    }

    @Test
    public void testExecute_fail() {
        StorageStub storageStub = new StorageStub("test/test");
        TaskList taskList = new TaskList();

        AddCommand addCommand = new AddCommand(new ToDo("bing bong"));
        addCommand.execute(taskList, new Ui(), storageStub);
        AddCommand addCommand1 = new AddCommand(new Event("ding dong",
                LocalDate.parse("2025-09-09"), LocalDate.parse("2025-09-10")));
        addCommand1.execute(taskList, new Ui(), storageStub);

        String expected = "1. [T][-] bing bong"
                + "2. [D][-] ding dong (by: 09/09/2025)";

        assertNotEquals(expected, taskList.toString());
    }
}
