package util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.ErrorCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.UnmarkCommand;
import task.DeadLine;
import task.Event;
import task.ToDo;

/**
 * A Parser that parses user input into executable commands.
 * This class is responsible for interpreting user input and creating the corresponding command objects.
 */
public class Parser {
    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param input The user input to be parsed.
     * @return The command corresponding to the user input.
     */
    public Command parse(String input) {
        String[] tokens = input.trim().split(" ", 2);
        String command = tokens[0];

        try {
            switch (command) {
            case "todo" -> {
                return parseToDo(tokens);
            }
            case "deadline" -> {
                return parseDeadLine(tokens);
            }
            case "event" -> {
                return parseEvent(tokens);
            }
            case "list" -> {
                return new ListCommand();
            }
            case "delete" -> {
                return parseDelete(tokens);
            }
            case "mark" -> {
                return new MarkCommand(Integer.parseInt(tokens[1]) - 1);
            }
            case "unmark" -> {
                return new UnmarkCommand(Integer.parseInt(tokens[1]) - 1);
            }
            case "find" -> {
                return parseFind(tokens);
            }
            case "bye" -> {
                return new ExitCommand();
            }
            default -> {
                return new ErrorCommand("unknown");
            }
            }
        } catch (IllegalArgumentException e) {
            return new ErrorCommand(e.getMessage());
        }
    }

    /**
     * Parses a Find command from the input tokens.
     *
     * @param tokens The input tokens containing the Find command and keyword.
     * @return A FindCommand with the specified keyword.
     * @throws IllegalArgumentException If the keyword field is empty.
     */
    private FindCommand parseFind(String[] tokens) {
        if (tokens.length == 1) {
            throw new IllegalArgumentException("empty keyword");
        }
        assert tokens.length == 2 : "Expected 2 arguments, got " + tokens.length;
        return new FindCommand(tokens[1]);
    }

    /**
     * Parses a Delete command from the input tokens.
     *
     * @param tokens The input tokens containing the Delete command and an index.
     * @return A DeleteCommand with the specified index.
     * @throws IllegalArgumentException If the index field is empty.
     */
    private DeleteCommand parseDelete(String[] tokens) {
        if (tokens.length == 1) {
            throw new IllegalArgumentException("empty index");
        }
        assert tokens.length == 2 : "Expected 2 arguments, got " + tokens.length;
        return new DeleteCommand(Integer.parseInt(tokens[1]) - 1);
    }

    public static boolean hasTags(String token) {
        return token.split("#").length > 1;
    }

    public static String separateFromTags(String token) {
        return token.split("#")[0];
    }

    public static String separateTagsFromDescription(String token) {
        return token.split("#", 2)[1];
    }

    public static ArrayList<String> parseTags(String args) {
        args = separateTagsFromDescription(args);
        String[] tags = args.split("#");
        return new ArrayList<>(Arrays.asList(tags));
    }

    /**
     * Parses a ToDo command from the input tokens.
     *
     * @param tokens The input tokens containing the ToDo command and its description.
     * @return An AddCommand for the ToDo task.
     * @throws IllegalArgumentException If the description is empty.
     */
    private AddCommand parseToDo(String[] tokens) {
        if (tokens.length == 1) {
            throw new IllegalArgumentException("empty description");
        }
        assert tokens.length == 2 : "Expected 2 arguments, got " + tokens.length;

        String args = tokens[1];
        if (!hasTags(args)) {
            return new AddCommand(new ToDo(args));
        } else {
            return new AddCommand(new ToDo(separateFromTags(args), parseTags(args)));
        }
    }

    /**
     * Parses a DeadLine command from the input tokens.
     *
     * @param tokens The input tokens containing the DeadLine command, description, and deadline.
     * @return An AddCommand for the DeadLine task.
     * @throws IllegalArgumentException If the description or deadline is empty, or if the deadline format is invalid.
     */
    private AddCommand parseDeadLine(String[] tokens) {
        if (tokens.length == 1) {
            throw new IllegalArgumentException("empty description");
        }

        String args = tokens[1]; // {description} {deadline} {tags}
        String[] temp = args.split(" /by ", 2);
        String description = temp[0];

        if (temp.length < 2 || temp[1].isEmpty()) {
            throw new IllegalArgumentException("empty deadline");
        }

        String date = separateFromTags(temp[1]).trim();
        LocalDate deadline = verifyDateFormat(date);

        if (!hasTags(args)) {
            return new AddCommand(new DeadLine(description, deadline));
        } else {
            return new AddCommand(new DeadLine(description, deadline, parseTags(args)));
        }
    }

    /**
     * Parses an Event command from the input tokens.
     *
     * @param tokens The input tokens containing the Event command, description, start time, and end time.
     * @return An AddCommand for the Event task.
     * @throws IllegalArgumentException If the description, start time, or end time is empty,
     *     or if the time format is invalid.
     */
    private AddCommand parseEvent(String[] tokens) {
        if (tokens.length == 1) {
            throw new IllegalArgumentException("empty description");
        }
        assert tokens.length > 1 : "Expected more than 1 argument, got " + tokens.length;

        String args = tokens[1]; // {description} {startTime} {endTime} {tags}
        String[] temp = args.split(" /from ", 2);
        String description = temp[0];

        if (temp.length < 2 || temp[1].isEmpty()) {
            throw new IllegalArgumentException("empty start time");
        }
        String[] temp1 = temp[1].split(" /to ", 2);
        if (temp1.length < 2 || temp1[1].isEmpty()) {
            throw new IllegalArgumentException("empty end time");
        }

        String startDate = temp1[0].trim();
        LocalDate startTime = verifyDateFormat(startDate);
        String endDate = separateFromTags(temp1[1]).trim();
        LocalDate endTime = verifyDateFormat(endDate);

        if (!hasTags(args)) {
            return new AddCommand(new Event(description, startTime, endTime));
        } else {
            return new AddCommand(new Event(description, startTime, endTime, parseTags(args)));
        }
    }

    private LocalDate verifyDateFormat(String d) {
        try {
            return LocalDate.parse(d);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("invalid time format");
        }
    }
}
