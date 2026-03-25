package command;

import collection.ProductManager;
import input.InputHandler;

/**
 * Удаление всей информации из коллекции.
 */
public class Clear extends AbstractCommand {
    public Clear(ProductManager productManager) {
        super(productManager);
    }

    @Override
    protected boolean hasArgument() {
        return false;
    }

    @Override
    protected void doExecute(String argument, InputHandler inputHandler) {
        if (productManager.getAll().isEmpty()) {
            System.out.println("В коллекции нет информации о продуктах.");
        } else {
            productManager.clear();
            System.out.println("Информация о всех продуктах успешно удалена.");
        }
    }

    @Override
    protected String getCommandName() {
        return "clear";
    }
}
