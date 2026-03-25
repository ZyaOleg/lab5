package command;

import collection.ProductManager;
import model.Product;
/**
 * Добавление продукта, если он больше максимального.
 */
public class AddIfMax extends AddCommand {
    public AddIfMax(ProductManager productManager) {
        super(productManager);
    }

    @Override
    protected boolean shouldAdd(Product product) {
        Product max = productManager.getMax();
        return max == null || product.compareTo(max) > 0;
    }

    @Override
    protected String getCommandName() {
        return "add_if_max";
    }
}