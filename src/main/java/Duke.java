package main.java;

import main.java.Command.Command;
import main.java.Exception.AnonymousException;
import main.java.Exception.DescriptionException;
import main.java.Exception.DukeCreateFileException;
import main.java.Exception.DukeDateTimeParserException;
import main.java.Exception.DukeFileException;
import main.java.Exception.DukeFileNotFoundException;
import main.java.Exception.DukeKeywordException;
import main.java.Exception.NoIndexException;
import main.java.Parser.Parser;
import main.java.Storage.Storage;
import main.java.Task.TaskList;
import main.java.Ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {

    TaskList tasks;
    Storage storage;
    Ui ui;

    public Duke(String filepath) {
        ui = new Ui();
        try {
            storage = new Storage(filepath);
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.getExceptionTemplate(new DukeFileNotFoundException());
            tasks = new TaskList();
        } catch (IOException e) {
            ui.getExceptionTemplate(new DukeCreateFileException());
            storage = new Storage();
            System.exit(-1);
        }
    }

    public void mainProgram() {
        ui.greet();
        boolean isExit = false;

        while (!isExit) {
            String command = ui.readCommand();
            try {
                Command c = Parser.parseCommand(command);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (FileNotFoundException e) {
                ui.getExceptionTemplate(new DukeFileNotFoundException());
            } catch (IOException e) {
                ui.getExceptionTemplate(new DukeFileException());
            } catch (AnonymousException e) {
                ui.getExceptionTemplate(new AnonymousException(command));
            } catch (DescriptionException e) {
                ui.getExceptionTemplate(new DescriptionException());
            } catch (DukeDateTimeParserException e) {
                ui.getExceptionTemplate(new DukeDateTimeParserException());
            } catch (NoIndexException e) {
                ui.getExceptionTemplate(new NoIndexException());
            } catch (DukeKeywordException e) {
                ui.getExceptionTemplate(new DukeKeywordException());
            }
        }
    }


    public static void main (String[]args){
        Duke duke = new Duke("data/data.txt");
        duke.mainProgram();
    }
}
