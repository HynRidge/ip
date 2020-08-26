package main.java.parser;

import main.java.command.Command;
import main.java.command.DeadlineCommand;
import main.java.command.DeleteAllCommand;
import main.java.command.DeleteCommand;
import main.java.command.DoneAllCommand;
import main.java.command.DoneCommand;
import main.java.command.EventCommand;
import main.java.command.ExitCommand;
import main.java.command.HelpCommand;
import main.java.command.ListCommand;
import main.java.command.ShowAfterCommand;
import main.java.command.ShowBeforeCommand;
import main.java.command.TodoCommand;
import main.java.command.WrongCommand;
import main.java.exception.DescriptionException;
import main.java.exception.DukeDateTimeParserException;
import main.java.exception.NoIndexException;
import main.java.task.DeadlineTask;
import main.java.task.EventTask;
import main.java.task.Task;
import main.java.task.TodoTask;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

public class Parser {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Parser(){}

    public static String[] fileParser(String filepath) {
        return filepath.split("/");
    }

    public static Task readFileParser(String line) {
        String[] strings = line.split("\\|", 3);
        String taskType = strings[0].trim();

        switch(taskType) {
        case "T" :
            TodoTask todoTask = new TodoTask();
            if (strings[1].trim().equals("1")) {
                todoTask.setDone(true);
            } else {
                todoTask.setDone(false);
            }
            todoTask.setDescription(strings[2].trim());
            return todoTask;
        case "D" :
            DeadlineTask deadlineTask = new DeadlineTask();
            if (strings[1].trim().equals("1")) {
                deadlineTask.setDone(true);
            } else {
                deadlineTask.setDone(false);
            }
            String[] taskDetails = strings[2].split("\\|");
            deadlineTask.setDescription(taskDetails[0].trim());
            deadlineTask.setDate(LocalDateTime.parse(taskDetails[1].trim(),formatter));
            return deadlineTask;
        case "E" :
            EventTask eventTask = new EventTask();
            if(strings[1].trim().equals("1")) {
                eventTask.setDone(true);
            } else {
                eventTask.setDone(false);
            }
            String[] taskDetails2 = strings[2].split("\\|");
            eventTask.setDescription(taskDetails2[0].trim());
            eventTask.setDateTime(LocalDateTime.parse(taskDetails2[1].trim(),formatter));
            return eventTask;
        default :
            return new Task() ;
        }
    }

    public static Command parseCommand(String command) {
        if (command.toLowerCase().equals(Command.EXIT_COMMAND)) {
            return new ExitCommand();
        } else if (command.toLowerCase().equals(Command.LIST_COMMAND)) {
            return new ListCommand();
        } else if(command.toLowerCase().equals(Command.DONE_ALL_COMMAND)) {
            return new DoneAllCommand();
        }else if (command.toLowerCase().contains(Command.DONE_COMMAND)) {
            return new DoneCommand(command);
        } else if (command.toLowerCase().contains(Command.TODO_COMMAND)) {
            return new TodoCommand(command);
        } else if (command.toLowerCase().contains(Command.DEADLINE_COMMAND)) {
            return new DeadlineCommand(command);
        } else if (command.toLowerCase().contains(Command.EVENT_COMMAND)) {
            return new EventCommand(command);
        } else if (command.toLowerCase().equals(Command.HELP_COMMAND)) {
            return new HelpCommand();
        } else if(command.toLowerCase().contains(Command.DELETE_ALL_COMMAND)) {
            return new DeleteAllCommand();
        } else if (command.toLowerCase().contains(Command.DELETE_COMMAND)){
            return new DeleteCommand(command);
        } else if (command.toLowerCase().contains(Command.SHOW_AFTER_COMMAND)) {
            return new ShowAfterCommand(command);
        } else if (command.toLowerCase().contains(Command.SHOW_BEFORE_COMMAND)) {
            return new ShowBeforeCommand(command);
        }else {
            return new WrongCommand(command);
        }
    }

    public static int findIndexParser(String input) throws NoIndexException {
        try {
            int index = Integer.parseInt(input.split("\\s")[1]);
            return index;
        } catch(IndexOutOfBoundsException e) {
            throw new NoIndexException();
        }
    }

    public static LocalDate findDateParser(String input) throws DukeDateTimeParserException {
        try {
            LocalDate localDate = LocalDate.parse(input.split("\\s")[2]);
            return localDate;
        } catch (DateTimeParseException e) {
            throw new DukeDateTimeParserException();
        }
    }

    public static Map<String,String> findDescriptionParser(String input) throws DescriptionException {
        try {
            Map<String, String> map = new HashMap<>();
            String[] getDetails = input.split("\\s", 2);
            String[] details = getDetails[1].split("/", 2);
            map.put("taskDescription", details[0].trim());
            String[] splitTimeDetails = details[1].split("\\s",2);
            map.put("taskTime", splitTimeDetails[1]);
            return map;
        } catch (IndexOutOfBoundsException e) {
            throw new DescriptionException();
        }
    }

    public static String findTodoParser(String input) throws DescriptionException {
        try {
            return input.split("\\s", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DescriptionException();
        }
    }
}
