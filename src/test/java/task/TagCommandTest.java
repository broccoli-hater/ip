package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import command.TagCommand;
import task.Task;
import task.TaskList;
import task.ToDo;
import util.Storage;
import util.StorageStub;
import util.Ui;

import java.util.ArrayList;

public class TagCommandTest {

    @Test
    public void testExecute_addTagsToTask() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("Read a book");
        taskList.add(task);

        ArrayList<String> tagList = new ArrayList<>();
        tagList.add("urgent");
        tagList.add("important");

        TagCommand tagCommand = new TagCommand(0, tagList);
        Ui ui = new Ui();
        Storage storage = new StorageStub("test/test");

        tagCommand.execute(taskList, ui, storage);

        Task updatedTask = taskList.get(0);
        assertEquals("[T][-] Read a book #urgent #important", updatedTask.toString());
    }

    @Test
    public void testExecute_addTagsToInvalidIndex_throwsException() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("Read a book");
        taskList.add(task);

        ArrayList<String> tagList = new ArrayList<>();
        tagList.add("urgent");

        TagCommand tagCommand = new TagCommand(1, tagList); // Invalid index
        Ui ui = new Ui();
        Storage storage = new StorageStub("test/test");

        assertThrows(IndexOutOfBoundsException.class, () -> {
            tagCommand.execute(taskList, ui, storage);
        });
    }
}
