package command;
import collection.ProductManager;
import model.Product;
/**
 * Добавление продукта без условий.
 */
public class Add extends AddCommand {
    public Add(ProductManager productManager) {
        super(productManager);
    }

    @Override
    protected boolean shouldAdd(Product product) {
        return true;
    }

    @Override
    protected String getCommandName() {
        return "add";
    }
}