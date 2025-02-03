package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import util.StorageStub;

/**
 * A test class for the TaskList class.
 */
public class TaskListTest {
    @Test
    public void testToString() {
        TaskList taskList = new TaskList(new StorageStub("test/test").loadData());
        taskList.add(new ToDo("bing bong"));
        taskList.add(new DeadLine("ding dong", LocalDate.parse("2025-09-09")));
        taskList.add(new Event("ling long", LocalDate.parse("2025-09-09"),
                LocalDate.parse("2025-09-10")));

        String expected = "1. [T][-] bing bong "
                + "2. [D][-] ding dong (by: 09/09/2025) "
                + "3. [E][-] ling long (from: 09/09/2025 to: 10/09/2025) ";

        assertEquals(expected, taskList.toString());
    }

    @Test
    public void get_success() {
        TaskList taskList = new TaskList(new StorageStub("test/test").loadData());
        taskList.add(new ToDo("bing bong"));
        Task task = new ToDo("ding dong");
        taskList.add(task);

        assertEquals(task, taskList.get(1));
    }

    @Test
    public void get_fail() {
        TaskList taskList = new TaskList(new StorageStub("test/test").loadData());
        taskList.add(new ToDo("bing bong"));
        Task task = new ToDo("ding dong");
        taskList.add(task);

        assertNotEquals(task, taskList.get(0));
    }
}
