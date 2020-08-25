package main.java.Command;

import main.java.Storage.Storage;
import main.java.Task.TaskList;
import main.java.Ui.Ui;

public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < tasks.size(); i++) {
            if (i != tasks.size()-1) {
                sb.append(ui.formatMessage((i + 1) + ". " +tasks.getTask(i) + "\n"));
            } else {
                sb.append(ui.formatMessage((i + 1) + ". " + tasks.getTask(i)));
            }
        }

        ui.getMessageTemplate(ui.formatMessage("Here are the tasks in your list:\n" + sb.toString()));
    }
}
