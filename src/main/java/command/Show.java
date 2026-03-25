package command;

import collection.ProductManager;
import input.InputHandler;
import model.Product;

import java.util.List;

/**
 * Выводит все продукты по возрастанию цены.
 */
public class Show extends AbstractCommand {
    public Show(ProductManager productManager) {
        super(productManager);
    }

    @Override
    protected boolean hasArgument() {
        return false;
    }

    @Override
    protected void doExecute(String argument, InputHandler inputHandler) {
        List<Product> products = productManager.getSorted();
        if (products.isEmpty()) {
            System.out.println("В коллекции нет информации о продуктах.");
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    @Override
    protected String getCommandName() {
        return "show";
    }
}