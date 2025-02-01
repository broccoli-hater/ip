import java.util.ArrayList;

class Ui {

    public static void breakLine() {
        System.out.println("____________________________________________________________");
    }

    public static void sayHello() {
        String greeting = "Greetings! It is I, Sir Talks-A-Lot. \n"
                + "Ah, so you wish to converse with one such as myself? \n"
                + "A noble knight, sworn to honor and valor, holder of great wisdom and unyielding strength? \n"
                + "Very well, I shall indulge thee in thy request. \n"
                + "Speak now, peasant, What dost thou seek from a knight of my stature?";
        System.out.println(greeting);
    }

    public static void sayBye() {
        String bye = "Hah! A quick departure, is it? Very well, then. \n"
                + "Farewell for now.";
        System.out.println(bye);
    }

    public static void promptAgain() {
        System.out.println("Speak clearly! I know not what such words mean. Attempt once more.");
    }

    public static void addTask() {
        String addTask = "Verily, I have inscribed this task upon the list.\n"
                + "Let no task go unfulfilled and no duty unrecorded!";
        System.out.println(addTask);
    }

    public static void countTask(int count) {
        String countTask = "";
        if (count == 0) {
            countTask = "Thou hast " + count + " tasks upon the list.";
        } else if (count == 1) {
            countTask = "Thou hast " + count + " task upon the list. A worthy pursuit!";
        } else {
            countTask = "Thou hast " + count + " tasks upon the list, each one a worthy pursuit!";
        }
        System.out.println(countTask);
    }

    public static void printTask(Task task) {
        System.out.println(task.getType() + task.isComplete() + " " + task.getName() + " " + task);
    }

    public static void printList(ArrayList<Task> taskList) {
        System.out.println("Hear ye! These are the tasks upon thy list, as decreed by thine own hand:");
        int counter = 1;
        for (Task task : taskList) {
            System.out.print(counter + ". ");
            printTask(task);
            counter++;
        }
    }

    public static void deleteTask(Task task) {
        System.out.println("Noted, then. I have seen fit to remove this trivial task.");
        System.out.print("    ");
        printTask(task);
    }

    public static void markTask(Task task) {
        System.out.println("Behold! A task completed! A most noble accomplishment.");
        printTask(task);
    }

    public static void unmarkTask(Task task) {
        System.out.println("Alas, a task left to be conquered. Its time has not yet come to pass.");
        printTask(task);
    }

    public static void taskNotFound() {
        System.out.println("It seems no such task exists, for I have heard no mention of it, "
                + "nor does it appear to fall within the realm of possibility.");
    }

    public static void descriptionNotFound() {
        System.out.println("The description cannot be left empty, for how would one know what is to be done?");
    }

    public static void deadLineNotFound() {
        System.out.println("The deadline cannot be left empty, for how would one know when the task is to be done?");
    }

    public static void startTimeNotFound() {
        System.out.println("The start time cannot be left empty, for how would one know when the event is?");
    }

    public static void endTimeNotFound() {
        System.out.println("The end time cannot be left empty, for how would one know when the event concludes?");
    }
}