package command;

import collection.ProductManager;
import input.InputHandler;
import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Выполняет скрипт из файла, следит за рекурсией.
 */
public class ExecuteScript extends AbstractCommand {
    private final CommandFactory commandFactory;

    public ExecuteScript(ProductManager productManager, CommandFactory commandFactory) {
        super(productManager);
        this.commandFactory = commandFactory;
    }

    @Override
    protected boolean hasArgument() {
        return true;
    }

    @Override
    protected void doExecute(String argument, InputHandler inputHandler) {
        executeScript(argument, new HashSet<>()) ;
    }

    private void executeScript(String filename, Set<String> calledFiles) {
        if (filename == null || filename.trim().isEmpty()) {
            System.out.println("Необходимо указать имя файла скрипта.");
            return;
        }
        if (calledFiles.contains(filename)) {
            System.err.println("Обнаружена рекурсия: скрипт " + filename + " уже вызывался в текущей цепочке. Прекращение работы скрипта.");
            return;
        }
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("Файл не найден: " + filename);
            return;
        }
        Set<String> newCalled = new HashSet<>(calledFiles);
        newCalled.add(filename);
        try (Scanner fileScanner = new Scanner(file, "UTF-8")) {
            InputHandler scriptHandler = new InputHandler(fileScanner, true);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split("\\s+");
                String commandName = parts[0].toLowerCase();
                if (parts.length > 2) {
                    throw new ScriptExecutionException("Не может быть более 1 аргумента у команды. ");
                }
                String argument = parts.length > 1 ? parts[1] : null;
                Command command = commandFactory.getCommand(commandName);
                if (command == null) {
                    throw new ScriptExecutionException("Неизвестная команда или нарушение целостности данных. ");
                }
                if (command instanceof ExecuteScript) {
                    ((ExecuteScript) command).executeScript(argument, newCalled);
                } else {
                    command.execute(argument, scriptHandler);
                }
            }
        } catch (IOException e) {
            throw new ScriptExecutionException("Ошибка чтения файла " + filename);
        }
    }

    @Override
    protected String getCommandName() {
        return "execute_script";
    }
}