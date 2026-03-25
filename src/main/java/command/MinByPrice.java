package command;

import collection.ProductManager;
import input.InputHandler;
import model.Product;

/**
 * Выводит случайный продукт с минимальной ценой.
 */
public class MinByPrice extends AbstractCommand {
    public MinByPrice(ProductManager productManager) {
        super(productManager);
    }

    @Override
    protected boolean hasArgument() {
        return false;
    }

    @Override
    protected void doExecute(String argument, InputHandler inputHandler) {
        Product min = productManager.getMinByPrice();
        if (min == null) {
            System.out.println("В коллекции нет информации о продуктах.");
        } else {
            System.out.println(min);
        }
    }

    @Override
    protected String getCommandName() {
        return "min_by_price";
    }
}