package command;

import collection.ProductManager;
import input.InputHandler;

/**
 * Выводит список команд.
 */
public class Help extends AbstractCommand {
    public Help(ProductManager productManager) {
        super(productManager);
    }

    @Override
    protected boolean hasArgument() {
        return false;
    }

    @Override
    protected void doExecute(String argument, InputHandler inputHandler) {
        System.out.println("help: вывести справку по доступным командам");
        System.out.println("info: вывести информацию о коллекции");
        System.out.println("show: вывести информацию о всех продуктах");
        System.out.println("add: добавить информацию о новом продукте");
        System.out.println("update_by_id id: изменить информацию о продукте по его id");
        System.out.println("remove_by_id id: удалить информацию о продукте по его id");
        System.out.println("clear: удалить информацию о всех продуктах");
        System.out.println("save: сохранить изменения");
        System.out.println("execute_script file_name: выполнить скрипт из указанного файла");
        System.out.println("exit: завершить программу (без сохранения изменений!)");
        System.out.println("head: вывести информацию о первом в коллекции продукте");
        System.out.println("add_if_max: добавить информацию о продукте, который дороже, чем любой другой продукт в коллекции");
        System.out.println("add_if_min: добавить информацию о продукте, который дешевле, чем любой другой продукт в коллекции");
        System.out.println("min_by_price: вывести информацию о любом самом дешёвом продукте в коллекции");
        System.out.println("count_less_than_part_number partNumber: вывести число продуктов, у которых партийный номер меньше заданного");
        System.out.println("count_greater_than_price price: вывести число продуктов, цена которых выше заданной");
    }

    @Override
    protected String getCommandName() {
        return "help";
    }
}

