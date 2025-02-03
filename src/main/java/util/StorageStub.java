package util;

import java.util.ArrayList;

import task.Task;

/**
 * A stub class for testing.
 */
public class StorageStub extends Storage {

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public StorageStub(String filePath) {
        super(filePath);
    }

    /**
     * Stub method that returns an empty arraylist of tasks.
     **/
    public ArrayList<Task> loadData() {
        return new ArrayList<>();
    }
}
