package main.java.Command;

import main.java.Parser;
import main.java.NoIndexException;
import main.java.Storage;
import main.java.Task;
import main.java.TaskList;
import main.java.Ui;

import java.io.IOException;

public class DoneCommand extends Command {

    private String command;

    public DoneCommand(String command) {
        super();
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, NoIndexException {
        int index = Parser.findIndexParser(this.command);
        Task task = tasks.getTask(index-1);
        task.setStatus(true);

        ui.messageTemplate(ui.formatMessage("Nice! I've marked this task as done:\n"
                +ui.formatMessage(task.toString())));

        storage.updateFile(tasks);

    }
}
