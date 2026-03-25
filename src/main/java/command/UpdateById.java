package command;

import collection.ProductManager;
import input.InputException;
import input.InputHandler;
import model.*;

/**
 * Обновляет продукт по id, запрашивает новые значения.
 */
public class UpdateById extends AbstractCommand {
    public UpdateById(ProductManager productManager) {
        super(productManager);
    }

    @Override
    protected boolean hasArgument() {
        return true;
    }

    @Override
    protected void doExecute(String argument, InputHandler inputHandler) {
        int id;
        try {
            id = Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            throw new InputException("Попробуйте ввести команду ещё раз. Использование: update_by_id <id>, где id - целое положительное число");
        }

        if (!productManager.productInCollection(id)) {
            System.err.println("В коллекции нет продукта с таким id.");
            return;
        }

        String productName = inputHandler.readRequiredString("product name");
        float coordinateX = inputHandler.readFloat("coordinate x", null, null);
        float coordinateY = inputHandler.readFloat("coordinate y", null, 557F);
        int price = inputHandler.readInt("price", 1, null);

        String partNumber;
        while (true) {
            partNumber = inputHandler.readNullableStringWithLength("partNumber", 10, 56);
            Product product = productManager.getProductById(id);
            if (partNumber == null ||
                    !productManager.isPartNumberExists(partNumber) ||
                    partNumber.equals(product.getPartNumber())) {
                break;
            }
            System.out.println("part number " + partNumber + " уже используется. Попробуйте ввести другой.");
        }

        Long manufactureCost = inputHandler.readLong("manufacture cost", null, null);
        UnitOfMeasure unitOfMeasure = inputHandler.readEnum("unit of measure", UnitOfMeasure.class, true);

        String organizationName = inputHandler.readRequiredString("organization name");
        long annualTurnover = inputHandler.readLong("annual turnover", 1L, null);
        OrganizationType organizationType = inputHandler.readEnum("organization type", OrganizationType.class, true);
        String addressStreet = inputHandler.readNullableString("address street");
        String zipCode = inputHandler.readNullableStringWithLength("zip code", 6, null);
        double locationX = inputHandler.readDouble("location x", null, null);
        float locationY = inputHandler.readFloat("location y", null, null);
        float locationZ = inputHandler.readFloat("location z", null, null);
        String locationName = inputHandler.readNullableString("location name");

        Location town = new Location(locationX, locationY, locationZ, locationName);
        Address postalAddress = new Address(addressStreet, zipCode, town);
        Organization manufacturer = new Organization(organizationName, annualTurnover, organizationType, postalAddress);
        Coordinates coordinates = new Coordinates(coordinateX, coordinateY);
        Product newProduct = new Product(productName, coordinates, price, partNumber, manufactureCost, unitOfMeasure, manufacturer);


        if (productManager.updateById(newProduct, id)) {
            System.out.println("Информация о продукте успешно обновлена.");
        } else {
            System.out.println("Не удалось обновить продукт.");
        }
    }

    @Override
    protected String getCommandName() {
        return "update_by_id";
    }
}