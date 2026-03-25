package command;

import collection.ProductManager;
import input.InputHandler;

/**
 * Завершает программу.
 */
public class Exit extends AbstractCommand {
    public Exit(ProductManager productManager) {
        super(productManager);
    }

    @Override
    protected boolean hasArgument() {
        return false;
    }

    @Override
    protected void doExecute(String argument, InputHandler inputHandler) {
        System.out.println("Завершение работы");
        System.exit(0);
    }

    @Override
    protected String getCommandName() {
        return "exit";
    }
}
