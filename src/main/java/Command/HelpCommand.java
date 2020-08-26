package main.java.Command;

import main.java.Storage.Storage;
import main.java.Task.TaskList;
import main.java.Ui.Ui;

/**
 * HelpCommand would execute the program when user specify
 * "help" as the command. This would show a list of
 * existing command together with the format of the command
 * to the user.
 */
public class HelpCommand extends Command {

    /**
     * A constructor with no argument to initialize
     * HelpCommand object.
     */
    public HelpCommand() {
        super();
    }

    /**
     * Executes parsed user command. The result is :
     * 1. Shows  the existing command and its format to
     * the user via Ui object.
     * @param tasks TaskList List of task.
     * @param ui Ui updating user interface to show intended messages.
     * @param storage Storage to update external file whenever needed.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.getCommandList();
    }
}
