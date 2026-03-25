package command;

import collection.ProductManager;
import model.Product;
/**
 * Добавление продукта, если он меньше максимального.
 */
public class AddIfMin extends AddCommand {
    public AddIfMin(ProductManager productManager) {
        super(productManager);
    }

    @Override
    protected boolean shouldAdd(Product product) {
        Product min = productManager.getMin();
        return min == null || product.compareTo(min) < 0;
    }

    @Override
    protected String getCommandName() {
        return "add_if_min";
    }
}