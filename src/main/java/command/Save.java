package command;

import collection.ProductManager;
import collection.ProductFileManager;
import input.InputHandler;
import java.io.IOException;

/**
 * Сохраняет коллекцию в файл.
 */
public class Save extends AbstractCommand {
    private final ProductFileManager fileManager;

    public Save(ProductManager productManager, ProductFileManager fileManager) {
        super(productManager);
        this.fileManager = fileManager;
    }

    @Override
    protected boolean hasArgument() {
        return false;
    }

    @Override
    protected void doExecute(String argument, InputHandler inputHandler) {
        try {
            fileManager.saveToFile(productManager.getAll());
            System.out.println("Изменения сохранены.");
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении файла: " + e.getMessage());
        }
    }

    @Override
    protected String getCommandName() {
        return "save";
    }
}
