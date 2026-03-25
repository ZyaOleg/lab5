package command;

import collection.ProductManager;
import input.InputHandler;
import model.*;
/**
 * Абстрактная команда добавления, читает продукт из ввода.
 */
public abstract class AddCommand extends AbstractCommand{
    public AddCommand(ProductManager productManager) {
        super(productManager);
    }

    @Override
    protected boolean hasArgument() {
        return false;
    }

    @Override
    protected void doExecute(String argument, InputHandler inputHandler) {
        Product newProduct = readProduct(inputHandler);
        if (newProduct != null && shouldAdd(newProduct)) {
            productManager.addProduct(newProduct);
            System.out.println("Продукт успешно добавлен.");
        } else if (newProduct != null) {
            System.out.println("Продукт не добавлен: условие не выполнено.");
        }
    }

    protected Product readProduct(InputHandler inputHandler) {
        try {
            String productName = inputHandler.readRequiredString("product name");
            float coordinateX = inputHandler.readFloat("coordinate x", null, null);
            float coordinateY = inputHandler.readFloat("coordinate y", null, 557F);
            int price = inputHandler.readInt("price", 1, null);

            String partNumber;
            while (true) {
                partNumber = inputHandler.readNullableStringWithLength("part number", 10, 56);
                if (partNumber == null || !productManager.isPartNumberExists(partNumber)) {
                    break;
                }
                System.err.println("part number " + partNumber + " уже используется. Попробуйте ввести другой.");
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

            return new Product(productName, coordinates, price, partNumber, manufactureCost, unitOfMeasure, manufacturer);
        } catch (Exception e) {
            System.err.println("Ошибка ввода: " + e.getMessage());
            return null;
        }
    }

    protected abstract boolean shouldAdd(Product product);
}
