package command;

import collection.ProductManager;
import input.InputException;
import input.InputHandler;

/**
 * Удаляет продукт по id.
 */
public class RemoveById extends AbstractCommand {
    public RemoveById(ProductManager productManager) {
        super(productManager);
    }

    @Override
    protected boolean hasArgument() {
        return true;
    }

    @Override
    protected void doExecute(String argument, InputHandler inputHandler) {
        int id;
        try {
            id = Integer.parseInt(argument);
            if (productManager.removeById(id)) {
                System.out.println("Информация о продукте успешно удалена.");
            } else {
                System.out.println("В коллекции нет продукта с таким id.");
            }
        } catch (NumberFormatException e) {
            throw new InputException("Попробуйте ввести команду ещё раз. Использование: remove_by_id <id>, где id - целое положительное число.");
        }
    }

    @Override
    protected String getCommandName() {
        return "remove_by_id";
    }
}
