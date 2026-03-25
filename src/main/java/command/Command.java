package command;

import input.InputHandler;

public interface Command {
    void execute(String argument, InputHandler inputHandler);
}
