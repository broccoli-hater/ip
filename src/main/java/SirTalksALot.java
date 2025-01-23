import java.util.ArrayList;
import java.util.Scanner;

public class SirTalksALot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> text = new ArrayList<>();

        System.out.println("____________________________________________________________");
        greeting();
        System.out.println("____________________________________________________________");

        int counter = 1;
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            System.out.println("____________________________________________________________");
            if (input.equals("list")) {
                text.forEach(System.out::println);
            } else {
                System.out.println("added: " + input);
                text.add(counter + ". " + input);
                counter++;
            }
            System.out.println("____________________________________________________________");
            input = scanner.nextLine();
        }

        farewell();
        System.out.println("____________________________________________________________");
    }

    public static void greeting() {
        String greeting = "Greetings! It is I, Sir Talks-A-Lot. \n"
                + "Ah, so you wish to converse with one such as myself? \n"
                + "A noble knight, sworn to honor and valor, holder of great wisdom and unyielding strength? \n"
                + "Very well, I shall indulge thee in thy request. \n"
                + "Speak now, peasant, What dost thou seek from a knight of my stature?";
        System.out.println(greeting);
    }
    public static void farewell() {
        String bye = "Hah! A quick departure, is it? Very well, then. \n"
                + "But should you wish to return and bask in the glory of my knightly presence, you need only call. \n"
                + "Farewell for now.";
        System.out.println(bye);
    }
}
