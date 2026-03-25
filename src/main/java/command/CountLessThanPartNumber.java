package command;

import collection.ProductManager;
import input.InputHandler;

/**
 * Считает продукты с partNumber меньше заданного.
 */
public class CountLessThanPartNumber extends AbstractCommand {
    public CountLessThanPartNumber(ProductManager productManager) {
        super(productManager);
    }

    @Override
    protected boolean hasArgument() {
        return true;
    }

    @Override
    protected void doExecute(String argument, InputHandler inputHandler) {
        String partNumber = argument;
        long count = productManager.countLessThanPartNumber(partNumber);
        System.out.println("Количество продуктов с partNumber меньше '" + argument + "': " + count);
    }

    @Override
    protected String getCommandName() {
        return "count_less_than_part_number";
    }
}