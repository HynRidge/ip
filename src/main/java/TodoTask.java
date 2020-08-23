package main.java;

public class TodoTask extends Task {
    private static final String TODO = "[T]";

    public TodoTask(String description) {
        super(description);
    }

    public TodoTask() {}

    public String getType() {
        return TODO;
    }

    @Override
    public String toString() {
        return TODO + super.toString();
    }
}
