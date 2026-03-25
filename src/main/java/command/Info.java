package command;

import collection.ProductManager;
import input.InputHandler;
import model.Product;

import java.util.Collection;
import java.util.IntSummaryStatistics;
/**
 * Информация о коллекции: тип, дата, количество, диапазоны id и цен (последние три - если коллекция непустая).
 */
public class Info extends AbstractCommand{
    public Info(ProductManager productManager){
        super(productManager);
    }

    @Override
    protected boolean hasArgument() {
        return false;
    }

    @Override
    protected void doExecute(String argument, InputHandler inputHandler) {
        System.out.println("Тип коллекции: ArrayDeque<Product>");
        System.out.println("Дата инициализации: " + productManager.getInitDate());
        System.out.println("Количество продуктов: " + productManager.getSize());
        Collection<Product> product = productManager.getAll();
        if (!product.isEmpty()) {
            IntSummaryStatistics idStats = product.stream().mapToInt(Product::getId).summaryStatistics();
            System.out.println("Диапазон id: от " + idStats.getMin() + " до " + idStats.getMax());
            IntSummaryStatistics priceStats = product.stream().mapToInt(Product::getPrice).summaryStatistics();
            System.out.println("Цены: минимальная=" + priceStats.getMin() + ", максимальная=" + priceStats.getMax() + ", средняя=" + priceStats.getAverage());
        }
    }

    @Override
    protected String getCommandName() {
        return "info";
    }

}
