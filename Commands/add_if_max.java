package Commands;

import Collection.CollectionManager;
import Data.Product;
import Exceptions.WrongAmountOfElementsException;
import Server.ServerApp;
import Server.utility.ReponseOutputer;

/**
 * Command 'add_if_mix'. Adds a new element to collection if it's more than the minimal one.
 */
public class add_if_max extends AbstractCommand {
    private CollectionManager collectionManager;

    public add_if_max(CollectionManager collectionManager) {
        super("add_if_max", "{element}", "Add a new element if its value is bigger than the smallest one");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     *
     * @return Command exit status.
     */
    @Override
    public boolean execute(String stringArgument, Object objectArgument) {
        try {
            if (!stringArgument.isEmpty() || objectArgument == null) throw new WrongAmountOfElementsException();
            Product productToAdd = (Product) objectArgument;
            Product MaxInCollecion = collectionManager.getWithMaxPrice();
            if (collectionManager.collectionSize() == 0 || productToAdd.getPrice() < MaxInCollecion.getPrice()) {

                ServerApp.logger.info(collectionManager.getWithMaxPrice().toString());
                ServerApp.logger.info(productToAdd.toString());
                collectionManager.addToCollection(productToAdd);
                ServerApp.logger.info("Successfully added!");
                ReponseOutputer.appendln("Successfully added! ");

                return true;
            } else {
                ServerApp.logger.info("collectionManager.getFirst()) <= 0 " + productToAdd.compareTo(collectionManager.getWithMinPrice()));
                ReponseOutputer.appendln("collectionManager.getFirst()) <= 0 " + productToAdd.compareTo(collectionManager.getWithMinPrice()));
            }
//            if (!stringArgument.isEmpty() || objectArgument == null) throw new WrongAmountOfElementsException();
//            Product productToAdd = (Product) objectArgument;
//
//            if (collectionManager.collectionSize() == 0 || productToAdd.compareTo(collectionManager.getWithMinPrice()) < 0) {
//                collectionManager.addToCollection(productToAdd);
//                ReponseOutputer.appendln("Added succesfully!");
//                return true;
//            } else
//                ReponseOutputer.appenderror("The Product price value is greater than the value of the smallest product price!");
        } catch (WrongAmountOfElementsException exception) {
            ReponseOutputer.appendln("using: '" + getName() + " " + getUsage() + "'");
        } catch (ClassCastException exception) {
            ReponseOutputer.appenderror("The object submitted by the client is invalid!");
        }
        return false;
    }
}
