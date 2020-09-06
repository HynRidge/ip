package command;

import java.time.LocalDate;

import exception.DukeDateTimeParserException;
import parser.Parser;
import storage.Storage;
import task.DeadlineTask;
import task.EventTask;
import task.Task;
import task.TaskList;
import ui.Ui;


/**
 * ShowBeforeCommand would execute the program when user specify
 * "show before" as the command. This would show all the task that exist
 * before the specified date that was passed in the user command.
 */
public class ShowBeforeCommand extends Command {

    private String command;

    /**
     * Constructs a ShowBeforeCommand with the given
     * user command.
     *
     * @param command String user command
     */
    public ShowBeforeCommand(String command) {
        super();
        this.command = command;
    }

    /**
     * Executes parsed user command. The result is:
     * 1. Shows user all the task that exist before the specified date.
     *
     * @param tasks TaskList List of task.
     * @param ui Ui updating user interface to show intended messages.
     * @param storage Storage to update external file whenever needed.
     * @throws DukeDateTimeParserException This exception would be thrown
     * when the user failed to specify the date in the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeDateTimeParserException {
        LocalDate localDate = Parser.findDateParser(this.command);
        StringBuilder sb = new StringBuilder();
        int i = 1;

        for (Task task : tasks.getTasks()) {
            if (task instanceof DeadlineTask) {
                DeadlineTask deadlineTask = (DeadlineTask) task;
                boolean isBeforeSpecifiedDate = deadlineTask.getDateTime().toLocalDate().isBefore(localDate);
                if (isBeforeSpecifiedDate) {
                    sb.append(i + ". " + deadlineTask + "\n");
                    i++;
                }
            } else if (task instanceof EventTask) {
                EventTask eventTask = (EventTask) task;
                boolean isBeforeSpecifiedDate = eventTask.getDateTime().toLocalDate().isBefore(localDate);
                if (isBeforeSpecifiedDate) {
                    sb.append(i + ". " + eventTask + "\n");
                    i++;
                }
            }
        }

        return ui.getMessageTemplate(sb.toString());
    }
}
