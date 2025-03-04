package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * A test class for the ToDo class.
 */
public class ToDoTest {
    @Test
    public void testGetType() {
        ToDo todo = new ToDo("bing bong");
        String type = todo.getType();
        assertEquals("[T]", type);
    }

    @Test
    public void testToString_withoutTags() {
        ToDo todo = new ToDo("bing bong");
        String result = todo.toString();

        assertEquals("[T][-] bing bong ", result);
    }

    @Test
    public void testToString_withTags() {
        ArrayList<String> tagList = new ArrayList<>();
        tagList.add("urgent");
        tagList.add("important");

        ToDo todo = new ToDo("bing bong", tagList);

        String result = todo.toString();

        assertEquals("[T][-] bing bong #urgent #important", result);
    }

    @Test
    public void testMarkAsComplete() {
        ToDo todo = new ToDo("bing bong");
        todo.markCompleted();

        assertEquals("[T][X] bing bong ", todo.toString());
    }

    @Test
    public void testUnmarkAsComplete() {
        ToDo todo = new ToDo("bing bong");
        todo.markCompleted();
        todo.unmarkCompleted();

        assertEquals("[T][-] bing bong ", todo.toString());
    }
}


