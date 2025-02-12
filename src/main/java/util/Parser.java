package util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
                return new DeleteCommand(Integer.parseInt(tokens[1]) - 1);
            }
            case "mark" -> {
                return new MarkCommand(Integer.parseInt(tokens[1]) - 1);
            }
            case "unmark" -> {
                return new UnmarkCommand(Integer.parseInt(tokens[1]) - 1);
            }
            case "find" -> {
                return new FindCommand(tokens[1]);
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
        return new AddCommand(new ToDo(tokens[1]));
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
        String[] temp = tokens[1].split(" /by ", 2);
        if (temp.length < 2 || temp[1].isEmpty()) {
            throw new IllegalArgumentException("empty deadline");
        }

        LocalDate deadline = verifyDateFormat(temp[1]);

        return new AddCommand(new DeadLine(temp[0], deadline));
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
        String[] temp = tokens[1].split(" /from ", 2);
        if (temp.length < 2 || temp[1].isEmpty()) {
            throw new IllegalArgumentException("empty start time");
        }
        String[] temp1 = temp[1].split(" /to ", 2);
        if (temp1.length < 2 || temp1[1].isEmpty()) {
            throw new IllegalArgumentException("empty end time");
        }

        LocalDate startTime = verifyDateFormat(temp1[0]);
        LocalDate endTime = verifyDateFormat(temp1[1]);

        return new AddCommand(new Event(temp[0], startTime, endTime));
    }

    private LocalDate verifyDateFormat(String d) {
        try {
            return LocalDate.parse(d);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("invalid deadline");
        }
    }
}
