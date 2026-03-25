package command;

import collection.ProductManager;
import collection.ProductFileManager;
import collection.IdGenerator;

import java.util.HashMap;
import java.util.Map;

/**
 * Фабрика команд, создаёт объекты команд по имени.
 */
public class CommandFactory {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandFactory(ProductManager productManager, ProductFileManager fileManager,
                          IdGenerator productIdGen, IdGenerator orgIdGen) {
        commands.put("help", new Help(productManager));
        commands.put("info", new Info(productManager));
        commands.put("show", new Show(productManager));
        commands.put("add", new Add(productManager));
        commands.put("update_by_id", new UpdateById(productManager));
        commands.put("remove_by_id", new RemoveById(productManager));
        commands.put("clear", new Clear(productManager));
        commands.put("save", new Save(productManager, fileManager));
        commands.put("exit", new Exit(productManager));
        commands.put("head", new Head(productManager));
        commands.put("add_if_max", new AddIfMax(productManager));
        commands.put("add_if_min", new AddIfMin(productManager));
        commands.put("min_by_price", new MinByPrice(productManager));
        commands.put("count_less_than_part_number", new CountLessThanPartNumber(productManager));
        commands.put("count_greater_than_price", new CountGreaterThanPrice(productManager));
        commands.put("execute_script", new ExecuteScript(productManager, this));
    }

    public Command getCommand(String name) {
        return commands.get(name);
    }
}