package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

/**
 * A test class for the TaskList class.
 */
public class TaskListTest {
    @Test
    public void testToString() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("bing bong"));
        taskList.add(new DeadLine("ding dong", LocalDate.parse("2025-09-09")));
        taskList.add(new Event("ling long", LocalDate.parse("2025-09-09"),
                LocalDate.parse("2025-09-10")));

        String expected = "1. [T][-] bing bong \n"
                + "2. [D][-] ding dong (by: 09/09/2025) \n"
                + "3. [E][-] ling long (from: 09/09/2025 to: 10/09/2025) \n";

        assertEquals(expected, taskList.toString());
    }

    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        Task task1 = new ToDo("bing bong");
        Task task2 = new ToDo("ding dong");
        taskList.add(task1);
        taskList.add(task2);

        assertEquals(2, taskList.size());
        assertEquals(task1, taskList.get(0));
        assertEquals(task2, taskList.get(1));
    }

    @Test
    public void testRemoveTask() {
        TaskList taskList = new TaskList();
        Task task1 = new ToDo("bing bong");
        Task task2 = new ToDo("ding dong");
        taskList.add(task1);
        taskList.add(task2);

        taskList.remove(0);

        assertEquals(1, taskList.size());
        assertEquals(task2, taskList.get(0));
    }

    @Test
    public void testGetTask_success() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("bing bong"));
        Task task = new ToDo("ding dong");
        taskList.add(task);

        assertEquals(task, taskList.get(1));
    }

    @Test
    public void testGetTask_fail() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("bing bong"));
        Task task = new ToDo("ding dong");
        taskList.add(task);

        assertNotEquals(task, taskList.get(0));
    }

    @Test
    public void testGetTask_invalidIndex_throwsException() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("bing bong"));

        assertThrows(IndexOutOfBoundsException.class, () -> taskList.get(1));
    }

    @Test
    public void testRemoveTask_invalidIndex_throwsException() {
        TaskList taskList = new TaskList();
        Task task1 = new ToDo("bing bong");
        taskList.add(task1);

        assertThrows(IndexOutOfBoundsException.class, () -> taskList.remove(1));
    }

    @Test
    public void testIsEmpty() {
        TaskList taskList = new TaskList();
        Task task1 = new ToDo("bing bong");

        assertTrue(taskList.isEmpty());
        taskList.add(task1);
        assertFalse(taskList.isEmpty());
    }

    @Test
    public void testSize() {
        TaskList taskList = new TaskList();
        Task task1 = new ToDo("bing bong");
        taskList.add(task1);

        // Assert
        assertEquals(1, taskList.size());
    }

    @Test
    public void testFindTasks() {
        TaskList taskList = new TaskList();
        Task task1 = new ToDo("bing bong");
        Task task2 = new ToDo("ding dong");
        Task task3 = new ToDo("ling long");

        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);

        TaskList foundTasks = taskList.find("bing");

        assertEquals(1, foundTasks.size());
        assertEquals(task1, foundTasks.get(0));
    }

    @Test
    public void testFindTasks_noMatch() {
        TaskList taskList = new TaskList();
        Task task1 = new ToDo("bing bong");
        Task task2 = new ToDo("ding dong");
        taskList.add(task1);
        taskList.add(task2);

        TaskList foundTasks = taskList.find("ladida");

        assertTrue(foundTasks.isEmpty());
    }

    @Test
    public void testSetTask() {
        TaskList taskList = new TaskList();
        Task task1 = new ToDo("bing bong");
        Task task2 = new ToDo("ding dong");
        Task task3 = new ToDo("ling long");

        taskList.add(task1);
        taskList.add(task2);

        taskList.set(1, task3);

        assertEquals(task3, taskList.get(1));
    }
}
