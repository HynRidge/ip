package main.java.Command;

import main.java.DescriptionException;
import main.java.DukeDateTimeParserException;
import main.java.EventTask;
import main.java.Parser;
import main.java.Storage;
import main.java.TaskList;
import main.java.Ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

public class EventCommand extends Command {

    private String command;

    public EventCommand(String command) {
        super();
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DescriptionException, DukeDateTimeParserException {
        try {
            Map<String, String> taskDetails = Parser.taskDescriptionParser(this.command);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            EventTask eventTask = new EventTask(taskDetails.get("taskDescription"),
                    LocalDateTime.parse(taskDetails.get("taskTime"), formatter));

            tasks.add(eventTask);

            ui.taskMessage(eventTask, tasks.size());

            storage.updateFile(tasks);
        } catch (DescriptionException e){
            throw new DescriptionException();
        } catch (DateTimeParseException e) {
            throw new DukeDateTimeParserException();
        }
    }
}
