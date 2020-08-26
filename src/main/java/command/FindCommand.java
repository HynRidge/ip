package main.java.command;


import main.java.exception.DukeKeywordException;
import main.java.parser.Parser;
import main.java.storage.Storage;
import main.java.task.Task;
import main.java.task.TaskList;
import main.java.ui.Ui;

/**
 * FindCommand executes when use specify "find" in the command.
 */
public class FindCommand extends Command{

    private String command;

    /**
     * Constructs a new FindCommand with the specified
     * user command.
     * @param command
     */
    public FindCommand(String command) {
        this.command = command;
    }


    /**
     * Executes parsed user command. The result is:
     * 1. Shows the list of tasks with that keyword to the
     * user.
     *
     * @param tasks TaskList list of task
     * @param ui Ui updating user to show intended messages.
     * @param storage Storage Updates external file whenever needed.
     * @throws DukeKeywordException Thrown when user failed to specify
     * keyword in the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeKeywordException {
        String keyword = Parser.findKeywordParser(this.command);
        System.out.println(keyword.equals(""));
        StringBuilder sb = new StringBuilder();
        int i = 1;

        for(Task task : tasks.getTasks()) {
            if(task.getDescription().contains(keyword)) {
                sb.append(ui.formatMessage((i) + ". " + task + "\n"));
                i++;
            }
        }
        sb.deleteCharAt(sb.length()-1);
        ui.getMessageTemplate(ui.formatMessage("Here are the matching tasks in your list:\n" + sb.toString()));
    }
}