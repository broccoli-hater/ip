class Ui {

    public static void breakLine(){
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
}
