import java.util.ArrayList;

class TaskList {
    ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    public void remove(int index) {
        taskList.remove(index);
    }

    public void print() {
        int counter = 1;
        for (Task task : taskList) {
            System.out.print(counter + ". ");
            System.out.println(task.toString());
            counter++;
        }
    }

    public String toString() {
        int counter = 1;
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : taskList) {
            stringBuilder.append(counter).append(". ");
            stringBuilder.append(task);
            counter++;
        }
        return stringBuilder.toString();
    }
}
