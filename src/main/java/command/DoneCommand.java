package command;

import parser.Parser;
import exception.NoIndexException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

/**
 * DoneCommand would execute the program when user specify
 * "done" as the command. This would automatically set the specified task
 * to done,show message to the user and update the external file.
 */
public class DoneCommand extends Command {

    private String command;


    /**
     * Constructs a DoneCommand with the given
     * user command.
     *
     * @param command String user command
     */
    public DoneCommand(String command) {
        super();
        this.command = command;
    }

    /**
     * Executes parsed user command. The result is:
     * 1. Sets the specified task to done via TaskList.
     * 2. Shows the updated task to user via Ui object.
     * 3. Updates the external file via Storage object.
     *
     * @param tasks TaskList List of task.
     * @param ui Ui updating user interface to show intended messages.
     * @param storage Storage to update external file whenever needed.
     * @throws IOException This exception would be thrown when the system failed
     * to detect the external file.
     * @throws NoIndexException  This exception would be thrown when user failed
     * to specify the index of the task in the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, NoIndexException {
        int index = Parser.findIndexParser(this.command);
        Task task = tasks.getTask(index-1);
        task.setDone(true);

        ui.getMessageTemplate(ui.formatMessage("Nice! I've marked this task as done:\n"
                +ui.formatMessage(task.toString())));

        storage.updateFile(tasks);

    }
}
