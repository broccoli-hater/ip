package util;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.ErrorCommand;
import command.ExitCommand;
import command.ListCommand;
import command.MarkCommand;
import command.UnmarkCommand;
import task.DeadLine;
import task.Event;
import task.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
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

    private AddCommand parseToDo(String[] tokens) {
        if (tokens.length == 1) {
            throw new IllegalArgumentException("empty description");
        }
        return new AddCommand(new ToDo(tokens[1]));
    }

    private AddCommand parseDeadLine(String[] tokens) {
        if (tokens.length == 1) {
            throw new IllegalArgumentException("empty description");
        }
        String[] temp = tokens[1].split(" /by ", 2);
        if (temp.length < 2 || temp[1].isEmpty()) {
            throw new IllegalArgumentException("empty deadline");
        }

        LocalDate deadline;
        try {
            deadline = LocalDate.parse(temp[1]);
        } catch (DateTimeParseException e) {
            Ui.incorrectTimeFormat();
            throw new IllegalArgumentException("invalid deadline");
        }

        return new AddCommand(new DeadLine(temp[0], deadline));
    }

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

        LocalDate startTime;
        LocalDate endTime;
        try {
            startTime = LocalDate.parse(temp1[0]);
            endTime = LocalDate.parse(temp1[1]);
        } catch (DateTimeParseException e) {
            Ui.incorrectTimeFormat();
            throw new IllegalArgumentException("invalid deadline");
        }

        return new AddCommand(new Event(temp[0], startTime, endTime));
    }
}
