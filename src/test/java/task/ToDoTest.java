package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * A test class for the ToDo class.
 */
public class ToDoTest {
    @Test
    public void testGetType_success() {
        assertEquals("[T]", new ToDo("test").getType());
    }
}


