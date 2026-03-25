package command;

import collection.ProductManager;
import input.InputHandler;
import model.Product;

/**
 * Выводит первый элемент (по сортировке).
 */
public class Head extends AbstractCommand{
    public Head(ProductManager productManager) {
        super(productManager);
    }

    @Override
    protected boolean hasArgument() {
        return false;
    }

    @Override
    protected void doExecute(String argument, InputHandler inputHandler) {
        Product first = productManager.getFirstSorted();
        if (first!=null) {
            System.out.println(first);
        }
        else {
            System.out.println("В коллекции нет информации о продуктах.");
        }
    }

    @Override
    protected String getCommandName() {
        return "head";
    }

}
