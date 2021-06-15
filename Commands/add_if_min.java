package Commands;
import Data.Product;
import Exceptions.WrongAmountOfElementsException;

import Collection.CollectionManager;
import Server.ServerApp;
import Server.utility.ReponseOutputer;

/**
 * Command 'add_if_min'. Adds a new element to collection if it's less than the minimal one.
 */
public class add_if_min extends AbstractCommand {
    private CollectionManager collectionManager;

    public add_if_min(CollectionManager collectionManager) {
        super("add_if_min", "{element}", "add a new element if its value is smaller than the smallest!");
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
            Product minInCollection = collectionManager.getWithMinPrice();
            if (collectionManager.collectionSize() == 0 || productToAdd.getPrice() < minInCollection.getPrice()) {

                ServerApp.logger.info(collectionManager.getWithMinPrice().toString());
                ServerApp.logger.info(productToAdd.toString());
                collectionManager.addToCollection(productToAdd);
                ServerApp.logger.info("Successfully added!");
                ReponseOutputer.appendln("Successfully added! ");

                return true;
            } else {
                ServerApp.logger.info("collectionManager.getFirst()) <= 0 " + productToAdd.compareTo(collectionManager.getWithMinPrice()));
            ReponseOutputer.appendln("collectionManager.getFirst()) <= 0 " + productToAdd.compareTo(collectionManager.getWithMinPrice()));
            }
        } catch (WrongAmountOfElementsException exception) {
            ReponseOutputer.appendln("using: '" + getName() + " " + getUsage() + "'");
        } catch (ClassCastException exception) {
            ReponseOutputer.appenderror("The object submitted by the client is invalid!");
        }
        return false;
    }
}
