package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.util.stream.Stream;

/**
 * ListCommand would execute the program when user specify
 * "list" as the command. This would show all the existing
 * list of task to the user.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand without any
     * argument passed.
     */
    public ListCommand() {
        super();
    }

    /**
     * Executes parsed user command. The result is:
     * 1. Shows all the existing tasks in the list via Ui object.
     *
     * @param tasks TaskList List of task.
     * @param ui Ui updating user interface to show intended messages.
     * @param storage Storage to update external file whenever needed.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < tasks.size(); i++) {
            boolean isLastTask = i != tasks.size() - 1;
            if (isLastTask) {
                sb.append(i + 1).append(". ").append(tasks.getTask(i)).append("\n");
            } else {
                sb.append(i + 1).append(". ").append(tasks.getTask(i));
            }
        }

        return ui.getMessageTemplate("Here are the tasks in your list:\n" + sb.toString());
    }
}
