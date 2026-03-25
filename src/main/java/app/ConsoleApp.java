package app;

import collection.*;
import command.*;
import input.InputHandler;
import model.Product;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Scanner;
/**
 * Точка входа. Загружает переменную окружения, инициализирует менеджеры,
 * запускает цикл обработки команд.
 */
public class ConsoleApp {
    public static void main(String[] args) {
        String filePath = System.getenv("PRODUCTS_FILE");
        if (filePath == null) {
            System.err.println("Установите переменную окружения PRODUCTS_FILE");
            System.exit(1);
        }

        IdGenerator productIdGenerator = new IdGenerator();
        IdGenerator organizationIdGenerator = new IdGenerator();
        ProductManager productManager = new ProductManager(productIdGenerator, organizationIdGenerator);
        ProductFileManager fileManager = new ProductFileManager(
                filePath,
                productIdGenerator,
                organizationIdGenerator,
                productManager.getPartNumbers()
        );

        try {
            ArrayDeque<Product> loaded = fileManager.loadFromFile();
            productManager.loadCollection(loaded);
            System.out.println("Успешная загрузка файла.");
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        Scanner scanner = new Scanner(System.in);
        InputHandler inputHandler = new InputHandler(scanner, false);
        CommandFactory commandFactory = new CommandFactory(productManager, fileManager,
                productIdGenerator, organizationIdGenerator);

        System.out.println("Чтобы вывести полный список команд, введите команду help");

        while (true) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("\\s+");
            String commandName = parts[0].toLowerCase();
            if (parts.length > 2) {
                System.out.println("Не может быть более 1 аргумента у команды. Попробуйте ввести " + commandName +  " ещё раз");
                continue;
            }
            String argument = parts.length > 1 ? parts[1] : null;
            Command command = commandFactory.getCommand(commandName);
            if (command == null) {
                System.out.println("Неизвестная команда. Введите help для списка команд.");
                continue;
            }
            try {
                command.execute(argument, inputHandler);
            } catch (Exception e) {
                if (!(e instanceof ScriptExecutionException)) {
                    System.err.println("Ошибка: " + e.getMessage());
                }
                else {
                    System.err.println(e.getMessage() + "Прекращение выполнения скрипта.");
                }
            }
        }
    }
}