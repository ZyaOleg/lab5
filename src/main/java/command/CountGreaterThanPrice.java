package command;

import collection.ProductManager;
import input.InputHandler;

/**
 * Считает продукты с ценой больше заданной.
 */
public class CountGreaterThanPrice extends AbstractCommand {
    public CountGreaterThanPrice(ProductManager productManager) {
        super(productManager);
    }

    @Override
    protected boolean hasArgument() {
        return true;
    }

    @Override
    protected void doExecute(String argument, InputHandler inputHandler) {
        try {
            int price = Integer.parseInt(argument);
            long count = productManager.countGreaterThanPrice(price);
            System.out.println("Количество продуктов с ценой больше " + price + ": " + count);
        } catch (NumberFormatException e) {
            System.out.println("Попробуйте ввести команду ещё раз. Использование: count_greater_than_price <price>, где price - целое положительное число");
        }
    }

    @Override
    protected String getCommandName() {
        return "count_greater_than_price";
    }
}