package command;

import collection.ProductManager;
import input.InputHandler;
/**
 * Абстрактная команда, проверяет наличие аргумента.
 */
public abstract class AbstractCommand implements Command {
    protected final ProductManager productManager;

    protected AbstractCommand(ProductManager productManager) {
        this.productManager = productManager;
    }

    @Override
    public void execute(String argument, InputHandler inputHandler) {
        if (hasArgument() && argument == null) {
            System.out.println("У команды " + getCommandName() + " должен быть один аргумент. Попробуйте ввести её ещё раз.");
            return;
        }
        else if (!hasArgument() && argument != null) {
            System.out.println("У команды " + getCommandName() + " не должно быть аргументов. Попробуйте ввести её ещё раз.");
            return;
        }
        doExecute(argument, inputHandler);
    }

    protected abstract boolean hasArgument();

    protected abstract void doExecute(String argument, InputHandler inputHandler);
    protected abstract String getCommandName();
}
